package Game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreSystem {

	private static ScoreSystem ss;
	private ScoreSystem() {};
	
	public static ScoreSystem getInstance() {
		if(ss==null) {
			ss=new ScoreSystem();
		}
		return ss;
	}
	
	public boolean printScore(String filename, int score, String name, String difficulty) {
		try {
			filename="src/"+difficulty+filename;
			File f = new File(filename);
			
			if(f.createNewFile()) {
				f.delete();
				throw new PrintWriterNotFOundException();
			}
			
			
			FileWriter myWriter = new FileWriter(filename, true);
			Date d = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy          HH:mm:ss");
			String line = String.format("%-20s%-21s%s\n",name, score, formatter.format(d));
	        myWriter.append(line);
	        myWriter.close();
	        return true;
		} catch(IOException e) {
			System.out.printf("The file %s does not exist or cannot be accessed!", filename);
			e.printStackTrace();
			return false;
		}
	}
}
