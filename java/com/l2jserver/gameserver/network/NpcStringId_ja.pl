## Active Perl 5.8.8

## SystemMessageId.java を日本語化する

use utf8;
use strict;
use warnings;
use Encode;

binmode STDOUT,':encoding(cp932)'; $|=1;
sub   FS {Encode::encode('cp932',shift)}
sub UTF8 {Encode::decode('cp932',shift)}

my $npcstring_txt = '../../../../../../L2J_DataPack_BETA/dist/game/data/lang/ja/npcstring.txt';

my $DEBUG = 1; # 1 or 0

my $LogFile = UTF8(__FILE__);
   $LogFile =~ s!\.[^.\/\\]*$!.log!;
open LOG, '>:utf8', FS($LogFile)  or die "'$LogFile' $!";

my %array_name_jp = ();
&loadSystemMessageAll();

&start('.svn/text-base/NpcStringId.java.svn-base', 'NpcStringId.java');
# &start('NpcStringId.java', 'NpcStringId.java.text');

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

	my @ALL = split /(?=[ \t]\/\*\*)/, $text;
	foreach my $item (@ALL) {
		next unless $item =~ m/ID: (\d+)/m;     my $id = $1;
		next unless $item =~ m/Message: (.*)/m; my $name_en = $1;

		my $name_jp = $array_name_jp{$id};
		next unless $name_jp;

		$item =~ s{/\*\*.*\*/}{/**
\t * ID: $id<br>
\t * Message: $name_jp<br>
\t * Message: $name_en
\t */}s;
		$UPDATE = 1;
		print $item;
	}
	
	$text = join '', @ALL;

	if ($UPDATE) {
		open FILE, '>:encoding(cp932)', FS($outPath) or die "'$outPath' $!";
		print FILE $text;
		close FILE;
		utime $mtime, $mtime, FS($outPath);
	}
}

sub loadSystemMessageAll {
	open FILE,'<:utf8',$npcstring_txt  or do {warn "'$npcstring_txt' $!";exit 0};
	while (not eof FILE) {
		my $line = <FILE>;
		next if $line =~ m!^/!;
		chomp $line;
		my ($N_id, $N_name) = split /\t/, $line;
		$array_name_jp{$N_id} = $N_name
	}
	close FILE;
}
