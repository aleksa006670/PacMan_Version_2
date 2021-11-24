package GameTest;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;

/**
* @implNote This is not a test case file. 
*            This is a helper class that lets you create text file
*            (e.g. init data for PacMan or Ghost) on the fly during the
*            execution of test cases.
* @apiNote There are two main static methods for use: DoWriting() and DeleteFile()
*
*/
public class WriteFile {
	/**
	 * 
	 * @param filepath. The absolute path of the file to write.
	 * @param lines. An array of String. Each string will occupy a new line in the file.
	 * @return
	 */
	public static boolean DoWriting(String filepath, String [] lines) {
	    if (new File(filepath).exists() == false) {
	    	File f = new File(filepath);
	    }
		try {
	      FileWriter myWriter = new FileWriter(filepath);
	      for (String line : lines) {
	    	  myWriter.write(line+"\r\n");
	      }
	      myWriter.close();
	      return  true;
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	      return false;
	    }
	  }
	
	public static void main(String [] args) {
		String fpn = "src/Resource/dummy.txt";
		boolean result = DoWriting(fpn, new String[] {"Testing!"});
		if (result)
			System.out.printf("Successfully writing to file %s!\n", fpn);
		else 
			System.out.printf("Writing to file %s failed!\n", fpn);
		    
	}
	/**
	 * 
	 * @param filepath. Absolute path inside this project.
	 * @return boolean. If deletion success (or the file does not exist) return true
	 *                  Otherwise return false.
	 */
	public static boolean DeleteFile(String filepath) {
		boolean result;
		if (new File(filepath).exists() == false) {
			result = true;
		}
		else {
			result = new File(filepath).delete();
		}
		return result;
	}
}


