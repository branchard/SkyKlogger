package main;

import static main.Constants.LOG_FILE;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Logger file: " + LOG_FILE.getAbsolutePath());
		GlobalKeyListener.main();
		
	}
}
