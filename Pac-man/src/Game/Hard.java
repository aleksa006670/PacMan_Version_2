package Game;

public class Hard extends Difficulty{
	private static Hard instance = new Hard();
	private Hard() {};
	
	public static Hard getInstance() {
		return instance;
	}
	
	@Override
	public String getGhostFile() {
		return "src/Resource/hardGhostData.txt";
	}
	
	@Override
	public String getPacMan() {
		return "src/Resource/hardPacMandata.txt";
	}
	
	@Override
	public String getAlgorithm() {
		return "src/Resource/hardAlgorithmData.txt";
	}
	
	@Override
	public String getMaze() {
		return "src/Resource/hardMaze.txt";
	}
	
	@Override
	public boolean doReverse() {
		return true;
	}
}
