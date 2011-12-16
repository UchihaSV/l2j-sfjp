use utf8;
use strict;
use warnings;

&ToInt('KObjectMap.template', 'IntObjectMap.java.txt');
&ToLong('KObjectMap.template', 'LongObjectMap.java.txt');

&ToInt('FastKObjectMap.template', 'FastIntObjectMap.java.txt');
&ToLong('FastKObjectMap.template', 'FastLongObjectMap.java.txt');

&ToInt('KProcedure.template', 'IntProcedure.java.txt');
&ToLong('KProcedure.template', 'LongProcedure.java.txt');

&ToInt('KObjectProcedure.template', 'IntObjectProcedure.java.txt');
&ToLong('KObjectProcedure.template', 'LongObjectProcedure.java.txt');

exit;

sub ToInt()
{
	my ($template, $output) = @_;
	$_ = readAll($template);

	s/\Q{K}/int/g;
	s/\bHASHCODE\((\w+?)\)/$1/g;

	s/[ \t]*\/\*\@IF INT\@\*\/[ \t]*//g;
	s/^.*\/\*\@IF \w+\@\*\/[^\r\n]*/\010/mg;
	s/\r\n\010//g || s/\n\010//g || s/\r\010//g;

	writeAll($output, $_);
}

sub ToLong()
{
	my ($template, $output) = @_;
	$_ = readAll($template);

	s/\Q{K}/long/g;
	s/\bHASHCODE\((\w+?)\)/(int)($1 ^ ($1 >>> 32))/g;

	s/[ \t]*\/\*\@IF LONG\@\*\/[ \t]*//g;
	s/^.*\/\*\@IF \w+\@\*\/[^\r\n]*/\010/mg;
	s/\r\n\010//g || s/\n\010//g || s/\r\010//g;

	s/\bInteger\b/Long/g;
	s/\bIntProcedure\b/LongProcedure/g;
	s/IntObject/LongObject/g;

	writeAll($output, $_);
}

sub readAll()
{
	my ($path) = @_;
	open FILE, '<', $path;
	binmode FILE;
	read FILE, my $text, -s FILE;
	close FILE;
	return $text;
}

sub writeAll()
{
	my ($path, $text) = @_;
	open FILE, '>', $path;
	binmode FILE;
	print FILE $text;
	close FILE
}
