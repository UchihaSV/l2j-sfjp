package com.l2jserver.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * java -Dtee=log/stdout.log --> java | tee log/stdout.log 2>&1
 * @author JOJO
 */

public class JavaTea extends PrintStream
{
	private final PrintStream file;
	
	public JavaTea(String name, boolean append) throws FileNotFoundException
	{
		super(System.out, true);	// console
		file = new PrintStream(new FileOutputStream(name, append), true);	// file
	}
	
	@Override
	public void close()
	{
		super.close();	// console
		file.close();	// file
	}
	
	@Override
	public void flush()
	{
		super.flush();	// console
		file.flush();	// file
	}
	
	@Override
	public void write(int b)
	{
		super.write(b);	// console
		file.write(b);	// file
	}
	
	@Override
	public void write(byte[] buf, int off, int len)
	{
		super.write(buf, off, len);	// console
		file.write(buf, off, len);	// file
	}
	
	public static void redirect(String name)
	{
		if (name != null) {
			try {
				System.setOut(new JavaTea(name, true));	// java | tee log/stdout.log
				System.setErr(System.out);				// 2>&1
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
