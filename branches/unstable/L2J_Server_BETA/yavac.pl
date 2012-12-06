## Active Perl 5.8.8

use utf8;
use warnings;
use strict;
use File::Path;
use File::Spec;
$|=1;

my $lang_suffix = '.ja';
my $tabsize = 4;

sub start($);
sub PreProcess($$);

open LOG, '>:utf8', 'yavac.log'  or die $!;

my $CD = 'java/';
my $EF = 'build/source/';
my $total = 0;
my $copied = 0;
my $transrated = 0;
my $errors = 0;

my $ERROR;
local $SIG{__WARN__} = sub {
	if ($_[0] =~ /(.*) does not map to Unicode/) {
		# 9.Bad file descriptor
		$ERROR = "$1 was illegal character."
	} else {
		warn @_
	}
};

&start('');
die "*** $errors errors. Die" if $errors;
print $total," files total.\n";
print $copied," files copied.\n";
print $transrated," files transrated.\n";
exit 0;

sub start($) {
	my ($subdir) = @_;
	opendir DIR, $CD.$subdir  or die $!;
	my @dir = readdir DIR;
	closedir DIR;
	foreach my $filename (@dir) {
		next if $filename =~ m/^\./;
		if (-d $CD.$subdir.$filename) {
			&start($subdir.$filename.'/');
		} elsif ($filename =~ m/\.java$/) {
			&PreProcess($subdir, $filename);
		}
	}
}

sub PreProcess($$) {
	my ($subdir, $filename) = @_;
	die unless $filename =~ m/\.java$/;

	my $srcPath = $CD.$subdir.$filename;
	my $dstPath = $EF.$subdir.$filename;

	++$total;
	my $srcMtime = (stat $srcPath)[9]; die unless $srcMtime;
	my $dstMtime = (stat $dstPath)[9];
	return if $dstMtime && $srcMtime == $dstMtime;

	$ERROR = undef;
	open JAVA, '<:encoding(cp932)', $srcPath  or die $!;
	read JAVA, my $text, -s JAVA;
	close JAVA;
	if ($ERROR) {
		print STDERR File::Spec->rel2abs($srcPath),": $ERROR\n";
		++$errors;
		unlink $dstPath;
		return;
	}

	my @MM =  split /(?<=\n)/,$text;	# backup original
	my $lang_path = $srcPath.$lang_suffix;
	if (-e $lang_path) {
		$_ = $text; do $lang_path; $text = $_;
		print LOG $lang_path,"\n";
	}

	#####################################
	# StringUtil.append を回避
	#####################################
	$text =~ s!StringUtil\.append\(!STRINGUTIL_APPEND(!g;

	my @LL = split /(?<=\n)/,$text;
	die if $#LL != $#MM;	#assert
	$text = undef;

	# __LINE__ => ソースファイルの行番号
	# __FILE__ => ソースファイルのパス
	# __BASENAME__ => ソースファイル名
	my $basename = $filename;
	   $basename =~ s/\.java$//;
	for (my $i = 0; $i <= $#LL; ++$i) {
		my $line = $i + 1;
		$LL[$i] =~ s/Integer\.valueOf\("__LINE__"\)/$line/g;	#(仮)
		$LL[$i] =~ s/__LINE__/$line/g;
		$LL[$i] =~ s/__FILE__/$subdir$filename/g;
		$LL[$i] =~ s/__BASENAME__/$basename/g;

		# 小技： "文字列" + String.valueOf(数値) => "文字列" + (数値)
		$LL[$i] =~ s!(" *\+ *)String\.valueOf!$1!g;
	}

#-	#####################################
#-	# StringUtil.append を回避
#-	#####################################
#-	s!StringUtil\.append\(!STRINGUTIL_APPEND(! foreach @LL;

	#####################################
	# append() 内の + を append() に変更
	#####################################
	#--[α]行またぎの処理
	#<  items.append("<tr>" +
	#<      "<td>"+a.getItemId()+"</td>" +
	#<      "<td><a action=\"bypass -h npc_"+getObjectId()+"\">"+
	#<      a.getItemName()+"</a></td>" +
	#<      "</tr>");
	#
	#>  items.append("<tr>" +
	#>      "<td>").append(a.getItemId()).append("</td>" +
	#>      "<td><a action=\"bypass -h npc_").append(getObjectId()).append("\">")
	#>      .append(a.getItemName()).append("</a></td>" +
	#>      "</tr>");
	#--[β]行内の処理
	#<  html.append("<td>" + curMsg.getFromName() + "</td>");
	#                   A~~~~                   B~~~~
	#>  html.append("<td>").append(curMsg.getFromName()).append("</td>");
	#                   A~~~~~~~~~~                   B~~~~~~~~~~
	#     A "文字"+以外 --> "文字").append(以外
	#     B 以外+"文字" --> 以外).append("文字"
	#       "文字"+"文字" --> そのまま
	#       以外+以外 --> そのまま
	for (my $i = 0; $i <= $#LL; ++$i) {
		if ($LL[$i] =~ m!^\s+[A-Za-z_][A-Za-z_0-9]*\.append\(!) {

			my $t = $i; for (;;) {
				# 下準備として、行末の + を、次行先頭に持っていく.
				# （+ を前につけるか、後ろにつけるか、統一させるため）
				#<  htmlCode.append(hoge +
				#<      hage
				#<      + age +
				#<      sage);
				#>  htmlCode.append(hoge
				#>      + hage
				#>      + age
				#>      + sage);
				if ($LL[$t+0] =~ s!\s*[+](\s*)$!$1!) {
					$LL[$t+1] =~ s!^\s*!$&+ !;
					$t += 1;
				} elsif ($LL[$t+1] =~ m!^\s*[+]!) {
					$t += 1;
				} else {
					last;
				}
			}

			#[α]行またぎの処理
			for (my $j = $i; $j <= $t-1; ++$j) {
				unless ($LL[$j+0] =~ m!["]\s*$!
				     && $LL[$j+1] =~ m!^\s*[+]\s*["]!) {
					$LL[$j+0] =~ s!\s*$!)$&!;
					$LL[$j+1] =~ s![+]\s*!.append(!;
				}
			}

			#[β]行内の処理
			for (my $j = $i; $j <= $t; ++$j) {
				$LL[$j] =~ s!["]\s*[+]\s*([^\s"])!").append($1!g;	#[β]A "文字"+以外
				$LL[$j] =~ s!([^"\s])\s*[+]\s*["]!$1).append("!g;	#[β]B 以外+"文字"
			}
			$i = $t;
		}
	}

	############################################
	# append() 行が連続したら、ひとつにまとめる
	############################################
	for (my $i = $#LL - 1; $i >= 0; --$i) {
		my $j = $i + 1;
		# 空行読み飛ばし
		while ($j < $#LL
		       && ($LL[$j] =~ m!^\s*//!s
		        || $LL[$j] =~ m!^\s*$!s )) {$j++}

		# インデントが同じか確かめる
		#   if (条件)
		#       html.append("<a href=...>□</a>"); <┐インデント違いは
		#   html.append("<br>");                   <┘統合できない。
		my $a = ($LL[$i] =~ m!^([ \t]*)!) ? $1 : '';
		my $b = ($LL[$j] =~ m!^([ \t]*)!) ? $1 : '';
		1 while $a =~ s/\t+/' ' x (length($&) * $tabsize - length($`) % $tabsize)/e;
		1 while $b =~ s/\t+/' ' x (length($&) * $tabsize - length($`) % $tabsize)/e;
		next if $a ne $b;

		#<  html.append("<html>");
		#<  html.append("<body>");
		#>  html.append("<html>"
		#>         +/**/"<body>");
		if ($LL[$i] =~ m!^\s+[A-Za-z_][A-Za-z_0-9]*\.append\(.*"\);\s*$!
		 && $LL[$j] =~ m!^\s+[A-Za-z_][A-Za-z_0-9]*\.append\("!) {
			$LL[$i] =~ s!\);(\s*)$!$1!;
			$LL[$j] =~ s!^(\s+)[A-Za-z_][A-Za-z_0-9]*\.append\(!$1\t+ !;
		}

		#<  TextBuilder html = new TextBuilder("<html>");
		#<  html.append("<body>");
		#>  TextBuilder html = new TextBuilder("<html>"
		#>         +/**/"<body>");
#-		if ($LL[$i] =~ m!new TextBuilder\(.*"\);\s*$!
		if ($LL[$i] =~ m!^\s+TextBuilder +[A-Za-z_][A-Za-z_0-9]* *= new TextBuilder\(.*"\);\s*$!
		 && $LL[$j] =~ m!^\s+[A-Za-z_][A-Za-z_0-9]*\.append\("!) {
			$LL[$i] =~ s!\);(\s*)$!$1!;
			$LL[$j] =~ s!^(\s+)[A-Za-z_][A-Za-z_0-9]*\.append\(!$1\t+ !;
		}
		if ($LL[$i] =~ m!^\s+StringBuilder +[A-Za-z_][A-Za-z_0-9]* *= new StringBuilder\(.*"\);\s*$!
		 && $LL[$j] =~ m!^\s+[A-Za-z_][A-Za-z_0-9]*\.append\("!) {
			$LL[$i] =~ s!\);(\s*)$!$1!;
			$LL[$j] =~ s!^(\s+)[A-Za-z_][A-Za-z_0-9]*\.append\(!$1\t+ !;
		}
		if ($LL[$i] =~ m!^\s+StringBuffer +[A-Za-z_][A-Za-z_0-9]* *= new StringBuffer\(.*"\);\s*$!
		 && $LL[$j] =~ m!^\s+[A-Za-z_][A-Za-z_0-9]*\.append\("!) {
			$LL[$i] =~ s!\);(\s*)$!$1!;
			$LL[$j] =~ s!^(\s+)[A-Za-z_][A-Za-z_0-9]*\.append\(!$1\t+ !;
		}
	}

	foreach (@LL) {
		s!\.append\("(\\[bfnrt"\\])"\)!.append('$1')!g;
		s!\.append\("'"\)!.append('\\'')!g;
		s!\.append\("(.)"\)!.append('$1')!g;
	}

	$text = join '', @LL;
	#####################################
	# StringUtil.append を復帰
	#####################################
	$text =~ s!STRINGUTIL_APPEND\(!StringUtil.append(!g;


	#####################################
	# Ｌ２Ｊ専用
	#####################################
	$text =~ s!new SimpleDateFormat\("dd/MM/yyyy HH:mm"\)!new SimpleDateFormat("yyyy/MM/dd HH:mm")!;
	$text =~ s/\\"l2ui_ch3.smallbutton2_down\\"/\\"L2UI_ct1.button_df_down\\"/g;
	$text =~ s/\\"l2ui_ch3.smallbutton2\\"/\\"L2UI_ct1.button_df\\"/g;
	$text =~ s/back=\\"l2ui_ct1.button_df\\"/back=\\"L2UI_CT1.Button_DF_Down\\"/gi;

	$text =~ s/(<button value=[^>]*?height)=(1[56789]\b|\\"1[56789]\\"|2[02]\b|\\"2[02]\\")/$1=21/g;

	$text =~ s/\Qnew StringBuilder()/new StringBuilder(256)/g;
	$text =~ s/\Qnew StringBuilder("/new StringBuilder(256).append("/g;

	#    try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection()
	# -> try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnectionFast()
	$text =~ s{(try *\((?:java\.sql\.|)Connection *\w+ *= *L2DatabaseFactory\.getInstance\(\))\.getConnection\(\)}
	          {$1.getConnectionFast()}g;
	#####################################

	@LL = split /(?<=\n)/,$text;
	die if $#LL != $#MM;	#assert
	$text = undef;

	mkpath $EF.$subdir;
	open JAVA, '>:encoding(cp932)', $dstPath  or die $!;
	print JAVA @LL;
	close JAVA;
	utime $srcMtime, $srcMtime, $dstPath;

	my $diff;
	for (my $i = 0; $i <= $#LL; $i++) {
		if ($LL[$i] ne $MM[$i]) {
			$diff = $i+1;
			last;
		}
	}
	if ($diff) {
		++$transrated;
		print $subdir.$filename,"\n";
		print LOG $dstPath,":",$diff,"\n";
	} else {
		++$copied;
	}
}
