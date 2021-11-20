package GameTest;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;

public class WriteFile {
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
//	      System.out.println("Successfully wrote to the file.");
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


