use utf8;
use strict;
use warnings;

my $PACKAGE = 'com.l2jserver.util.troja';

&ToInt( '_E_ObjectMap.template');
&ToLong('_E_ObjectMap.template');

&ToInt( 'Fast_E_ObjectMap.template');
&ToLong('Fast_E_ObjectMap.template');

&ToInt( '_E_Procedure.template');
&ToLong('_E_Procedure.template');

&ToInt( '_E_ObjectProcedure.template');
&ToLong('_E_ObjectProcedure.template');

&ToInt( 'ObjectProcedure.template');
#&ToLong('ObjectProcedure.template');

&ToInt( 'FastObject_E_Map.template');
&ToLong('FastObject_E_Map.template');

&ToInt( '_E_Iterator.template');
&ToLong('_E_Iterator.template');

exit;

sub ToInt()
{
	my ($template) = @_;
	my $output = $template;
	$output =~ s/_E_/Int/;
	$output =~ s/\.template/.java.txt/;

	$_ = readAll($template);

	s/#p#/$PACKAGE/;

	s/#e#/int/g;
	s/#ET#/Integer/g;
	s/#E#/Int/g;
	s/#EC#/INT/g;

	s/\bHASHCODE\((\w+?)\)/$1/g;

	s/[ \t]*\/\*\@IF INT\@\*\/[ \t]*//g;
	s/^.*\/\*\@IF \w+\@\*\/[^\r\n]*/\010/mg;
	s/\r\n\010//g || s/\n\010//g || s/\r\010//g;

	writeAll($output, $_);
}

sub ToLong()
{
	my ($template) = @_;
	my $output = $template;
	$output =~ s/_E_/Long/;
	$output =~ s/\.template/.java.txt/;

	$_ = readAll($template);

	s/#p#/$PACKAGE/;

	s/#e#/long/g;
	s/#ET#/Long/g;
	s/#E#/Long/g;
	s/#EC#/LONG/g;

	s/\bHASHCODE\((\w+?)\)/(int)($1 ^ ($1 >>> 32))/g;

	s/[ \t]*\/\*\@IF LONG\@\*\/[ \t]*//g;
	s/^.*\/\*\@IF \w+\@\*\/[^\r\n]*/\010/mg;
	s/\r\n\010//g || s/\n\010//g || s/\r\010//g;

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
