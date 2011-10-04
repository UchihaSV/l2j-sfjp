## Active Perl 5.8.8

## SystemMessageId.java を日本語化する

use utf8;
use strict;
use warnings;
use Encode;

binmode STDOUT,':encoding(cp932)'; $|=1;
sub   FS {Encode::encode('cp932',shift)}
sub UTF8 {Encode::decode('cp932',shift)}

my $systemmsg_txt = '../../../../../../L2J_DataPack_BETA/dist/game/data/lang/ja/systemmsg.txt';

my $DEBUG = 1; # 1 or 0

my $LogFile = UTF8(__FILE__);
   $LogFile =~ s!\.[^.\/\\]*$!.log!;
open LOG, '>:utf8', FS($LogFile)  or die "'$LogFile' $!";

my %array_name_jp = ();
&loadSystemMessageAll();

&start('.svn/text-base/SystemMessageId.java.svn-base', 'SystemMessageId.java');
# &start('SystemMessageId.java', 'SystemMessageId.java.text');

close LOG;
exit 0;

sub start {
	my ($filePath, $outPath) = @_;

	print "  $filePath\n" if $DEBUG;
	open FILE, '<:encoding(cp932)', FS($filePath) or die "'$filePath' $!";
	my $mtime = (stat FILE)[9];
	read FILE,my $text,-s FILE;
	close FILE;

	my $UPDATE = 0;

	$text =~ s!\bSUBMITTED_A_BID\b!SUBMITTED_A_BID_OF_S1!g;	# FIX BUG

	#	/**
	#	 * 3119	<BR>	The couple action was denied.
	#	 */
	#	/**
	#	 * ID: 3119<br>
	#	 * Message: The couple action was denied.
	#	 */
	$text =~ s!([ \t]+)/\*\*[\s\*]+(\d+)[ \t]<BR>[ \t](.+?)\s+\*/!$1/**\n$1 * ID: $2<br>\n$1 * Message: $3\n$1 */!gs;
#	/**
#	 * ID: 60<br>
#	 * Message: You have failed to earn $s2 $s1(s).
#	 */
	while ($text =~ m!([ \t]+)(/\*\*[\s\*]*ID: *)(\d+)(.+?\*/)!gs) {
		my $tab = $1; # \t\t
		my $L   = $2; # /**\n* ID: 
		my $id  = $3; # 60
		my $R   = $4; # <br>\n\t* Message: .....\n\t*/

		die unless length $id;

		my $name_jp = $array_name_jp{$id};
		next unless $name_jp;

		if ($text =~ s!\Q$tab$L$id$R\E!$tab/**\n$tab * id: $id<br>\n$tab * Message: $name_jp\n$tab */!s) {
			print     "$id\t[",$name_jp,"]\n";
			print LOG "$id\t[",$name_jp,"]\n";
			$UPDATE = 1;
		}
	}
	$text =~ s/\Q* id: \E/* ID: /gs;

	if ($UPDATE) {
		open FILE, '>:encoding(cp932)', FS($outPath) or die "'$outPath' $!";
		print FILE $text;
		close FILE;
		utime $mtime, $mtime, FS($outPath);
	}
}

sub loadSystemMessageAll {
	open FILE,'<:utf8',$systemmsg_txt  or do {warn "'$systemmsg_txt' $!";exit 0};
	while (not eof FILE) {
		my $line = <FILE>;
		next if $line =~ m!^/!;
		chomp $line;
		my ($N_id, $N_name) = split /\t/, $line;
		$array_name_jp{$N_id} = $N_name
	}
	close FILE;
}
