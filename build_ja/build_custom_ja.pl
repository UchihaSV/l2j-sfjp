## Active Perl 5.8.8

# L2J_DataPack language tool for Japanese.
# part 1 - *.ja ファイルを処理する

use utf8;
use warnings;
use strict;
use Encode;
use Cwd;

binmode STDOUT,':encoding(cp932)'; $|=1;
sub   FS {Encode::encode('cp932',shift)}
sub UTF8 {Encode::decode('cp932',shift)}

my $DEBUG = 0; # 1 or 0

my $custom_ja_script = '!build_ja.cgi';
my $perl = 'perl.exe';	#Win32 Active Perl
# my $perl = '/usr/local/bin/perl';	#UNIX/LINUX

my $LogFile = UTF8(__FILE__);
   $LogFile =~ s!\.[^.\/\\]*$!.log!;
open LOG, '>:utf8', FS($LogFile)  or die "'$LogFile' $!";

my $ERROR_HEADER;

$SIG{__WARN__} = sub {
	print STDERR $ERROR_HEADER,' ' if $ERROR_HEADER;
	foreach my $m (@_) {
		my $a = $m;
		$a =~ s!build[/\\]dist[/\\]game[/\\](data[/\\])!$1!;
		$a =~ s!build[/\\]dist[/\\]!!;
		if ($a =~ m/^Malformed UTF-8 character \(/) {
			print STDERR $a;
			exit 1;
		}
		print STDERR $a;
		print LOG    $a;
	}
};

$SIG{__DIE__} = sub {
	print STDERR $ERROR_HEADER,' ' if $ERROR_HEADER;
	foreach my $m (@_) {
		my $a = $m;
		$a =~ s!build[/\\]dist[/\\]game[/\\](data[/\\])!$1!;
		$a =~ s!build[/\\]dist[/\\]!!;
		print STDERR $a;
	}
	exit 1;
};

&start('build/dist/');

close LOG;
exit 0;

sub start {
	my ($subDir) = @_;

	print "[",$subDir,"]\n";
	opendir DIR, $subDir  or die "'$subDir' $!";
	my @files = readdir DIR;
	foreach (@files) {$_ = UTF8($_)}
	closedir DIR;

	foreach my $fileName (@files) {
		next if $fileName =~ m/^\./;
		next if $fileName =~ m/ /;

		my $jaPath = $subDir.$fileName;

		if (-d $jaPath) {

			&start($jaPath.'/');
			next;

		} elsif ($fileName =~ m/\.ja$/) {

			my $lang_path = $jaPath;
			print '  ',$lang_path,"\n" if $DEBUG;
			my $inpPath = $jaPath; $inpPath =~ s/\.ja$//;
			my $outPath = $inpPath;
			   $outPath = $inpPath.'.text' if $DEBUG;

			my ($ext,$encode);
			   if ($inpPath =~ m/\.html?$/){$ext = 'htm';  $encode = ':utf8'}
			elsif ($inpPath =~ m/\.xml$/)  {$ext = 'xml';  $encode = ':utf8'}
			elsif ($inpPath =~ m/\.sql$/)  {$ext = 'sql';  $encode = ':utf8'}
			elsif ($inpPath =~ m/\.csv$/)  {$ext = 'csv';  $encode = ':utf8'}
			elsif ($inpPath =~ m/\.properties?$/){$ext = 'properties';  $encode = ':utf8'}
			elsif ($inpPath =~ m/\.py$/)   {$ext = 'py';   $encode = ':encoding(cp932)'}
			elsif ($inpPath =~ m/\.java$/) {$ext = 'java'; $encode = ':encoding(cp932)'}
			elsif ($inpPath =~ m/\.bat$/)  {$ext = 'bat';  $encode = ':encoding(cp932)'}
			else {die $inpPath," - Unknown encoding."}

			open FILE, '<'.$encode, $inpPath  or die "'$inpPath' $!";
			read FILE, my $text, -s FILE;
			my $mtime = (stat FILE)[9];
			close FILE;
			my $original = $text;

			$_ = $text; do $lang_path; $text = $_;

			if ($ext eq 'htm') {
				$text =~ s/([\x{1000}-\x{FFFF}])[\r\n]+([\x{1000}-\x{FFFF}])/$1$2/gs;
				$text =~ s!<br>(\s+</body>)!$1!;
			}

			if ($text ne $original) {
			#	print $inpPath,"\n";
				open FILE, '>'.$encode, $outPath  or die "'$outPath' $!";
				print FILE $text;
				close FILE;
				utime $mtime, $mtime, $outPath;

				my $i = &minidiff($text, $original);
				print  '+',$outPath,"($i)\n";
				print LOG  $outPath,"($i)\n";
			}

			unless ($DEBUG) {
				unlink $lang_path.'.BAK';
				rename $lang_path, $lang_path.'.BAK' or die "'$lang_path' $!";
			}
		}
	}

	if (-e $subDir.$custom_ja_script) {
		my $cwd = Cwd::getcwd();
		chdir $subDir or die "'$subDir' $!";
		my $ERRORLEVEL = system($perl.' '.$custom_ja_script);
		chdir $cwd or die "'$cwd' $!";
		die "'$subDir$custom_ja_script' ERRORLEVEL=[$ERRORLEVEL]" if $ERRORLEVEL;

		unless ($DEBUG) {
			unlink $subDir.$custom_ja_script.'.BAK';
			rename $subDir.$custom_ja_script, $subDir.$custom_ja_script.'.BAK' or die "'$subDir.$custom_ja_script' $!";
		}
	}
}

sub minidiff {
	my ($textA, $textB) = @_;

	$textA =~ s/^\x{FEFF}//;
	$textB =~ s/^\x{FEFF}//;
	my @A = split /\n/,$textA;
	my @B = split /\n/,$textB;
	my $min = $#A <= $#B ? $#A : $#B;
	for (my $i=0; $i <=$min; ++$i) {
		return $i+1 if $A[$i] ne $B[$i];
	}
	return 0;
}
