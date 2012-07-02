## Active Perl 5.8.8

## SystemMessageId.java を日本語化する

use utf8;
use strict;
use warnings;
use Encode;

binmode STDOUT,':encoding(cp932)'; $|=1;
sub   FS {Encode::encode('cp932',shift)}
sub UTF8 {Encode::decode('cp932',shift)}

my $systemmsg_ja = '../../../../../../L2J_DataPack_BETA/dist/game/data/lang/ja/systemmsg.txt';
my $systemmsg_tw = '../../../../../../L2J_DataPack_BETA/dist/game/data/lang/tw/systemmsg.txt';

my $DEBUG = 1; # 1 or 0

my $LogFile = UTF8(__FILE__);
   $LogFile =~ s!\.[^.\/\\]*$!.log!;
open LOG, '>:utf8', FS($LogFile)  or die "'$LogFile' $!";

my %array_message_jp = ();
my %array_message_tw = ();
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

	my @ALL = split /(?=[ \t]\/\*\*)/, $text;
	foreach my $item (@ALL) {
		next unless $item =~ m/ID: (\d+)/m;     my $id = $1;
		next unless $item =~ m/Message: (.*)/m; my $message_en = $1;

		if ($message_en eq '$s2 $s1') {$id = '2010'}	# FIX BUG

		my $message_jp = $array_message_jp{$id};
		my $message_tw = $array_message_tw{$id};
		next unless $message_jp;

		$message_tw =~ s/(.)/'&#'.ord($1).';'/eg;	# html encode

		$item =~ s{/\*\*.*\*/}{/**
\t * ID: $id<br>
\t * Message: $message_jp<br>
\t * Message: $message_tw<br>
\t * Message: $message_en
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
	open FILE,'<:utf8',$systemmsg_ja  or do {warn "'$systemmsg_ja' $!";exit 0};
	while (not eof FILE) {
		my $line = <FILE>;
		next if $line =~ m!^/!;
		chomp $line;
		my ($N_id, $N_message) = split /\t/, $line;
		$array_message_jp{$N_id} = $N_message
	}
	close FILE;

	open FILE,'<:utf8',$systemmsg_tw  or do {warn "'$systemmsg_tw' $!";exit 0};
	while (not eof FILE) {
		my $line = <FILE>;
		next if $line =~ m!^/!;
		chomp $line;
		my ($N_id, $N_message) = split /\t/, $line;
		$array_message_tw{$N_id} = $N_message
	}
	close FILE;
}
