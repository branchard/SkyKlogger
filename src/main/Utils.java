package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.CodeSource;

public class Utils
{
	public static void logg(String line)
	{
		System.out.println("Log: " + line);
		BufferedWriter bf = null;
		try
		{
			bf = new BufferedWriter(new FileWriter(Constants.logFile, true));
			bf.write(line + "\n");
			
			bf.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bf.close();
			}
			catch(IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String getApplicationRootDirectory()
	{
		String jarDir = "";
		try
		{
			CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
			File jarFile = new File(URLDecoder.decode(codeSource.getLocation().toURI().getPath(), "UTF-8"));
			jarDir = jarFile.getParentFile().getPath();
		}
		catch(URISyntaxException e)
		{
			e.printStackTrace();
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return jarDir;
	}
}
