package main;

import java.io.File;
import java.util.HashMap;

public class Constants
{
	public static final File LOG_FILE = new File(Utils.getApplicationRootDirectory() + File.separatorChar + "skyLog.txt");
	
	// Defining the character corresponding to the key name
	public static final HashMap<String, Character> KEYNAME_CHAR = getMap();
	
	private static HashMap<String, Character> getMap()
	{
		HashMap<String, Character> result = new HashMap<String, Character>();// Keys must beings uppercase
		result.put("ESPACE", ' ');
		return result;
	}
}
