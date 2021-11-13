package Game;


public class ScatterTopRightCorner implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g, SearchAlgorithm sa, boolean doReverse) {
		
		Maze maze = Maze.getInstance();
		Tuple targetTile = maze.getTopRightCorner();
		g.moveToTarget(sa, targetTile, doReverse);
		
		return targetTile;
	}

}
