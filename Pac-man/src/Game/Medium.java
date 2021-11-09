package Game;

public class Medium extends Difficulty{
	private static Medium instance = new Medium();
	private Medium(){};
	
	public static Medium getInstance() {
		return instance;
	}
	
	@Override
	public String getGhostFile() {
		return "src/Resource/mediumGhostData.txt";
	}
	
	@Override
	public String getPacMan() {
		return "src/Resource/mediumPacMandata.txt";
	}
	
	@Override
	public String getAlgorithm() {
		return "src/Resource/mediumAlgorithmData.txt";
	}
	
	@Override
	public String getMaze() {
		return "src/Resource/mediumMaze.txt";
	}
	
	@Override
	public boolean doReverse() {
		return false;
	}
}
