package GameTest;

import java.util.Locale;

/**
* @implNote This is not a test case file. 
*            This is a helper class that determines the OS type (one of Windows, MacOS, Linux).
*            
* @apiNote You can use isWindows(), isMac(), and isLinux() to determine the OS type
*
*/
public final class OSType {
    
	private static OS theOS = null;
	
	public enum OS {
		Windows, MacOS, Linux, Other
	};
	static{
		   if (theOS == null) {
			   String osname = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
		      if ((osname.indexOf("mac") >= 0) || (osname.indexOf("darwin") >= 0)) {
		        theOS = OS.MacOS;
		      } else if (osname.indexOf("win") >= 0) {
		        theOS = OS.Windows;
		      } else if (osname.indexOf("nux") >= 0) {
		        theOS = OS.Linux;
		      } else {
		        theOS = OS.Other;
		      }
		   }

	   }
   public static String getOSName()
   {
       return System.getProperty("os.name").toLowerCase(Locale.ENGLISH);  
   }
   
   public static boolean isWindows() {return theOS == OS.Windows;}
   public static boolean isMac() {return theOS == OS.MacOS;}
   public static boolean isLinux() {return theOS == OS.Linux;}
}