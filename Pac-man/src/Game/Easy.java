package Game;

public class Easy extends Difficulty{
	private static Easy instance = new Easy();
	private Easy() {};
	
	public static Easy getInstance() {
		return instance;
	}
	
	@Override
	public String getGhostFile() {
		return "src/Resource/easyGhostData.txt";
	}
	
	@Override
	public String getPacMan() {
		return "src/Resource/easyPacmanData.txt";
	}
	
	@Override
	public String getAlgorithm() {
		return "src/Resource/easyAlgorithmData.txt";
	}
	
	@Override
	public String getMaze() {
		return "src/Resource/easyMaze.txt";
	}
	
	@Override
	public boolean doReverse() {
		return false;
	}

}

