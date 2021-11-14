package Game;



public class ScatterTopLeftCorner implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g, SearchAlgorithm sa, boolean doReverse) {
		
		/**
		 * pink ghost behavior
		 * */
		
		Maze maze = Maze.getInstance();
		Tuple targetTile = maze.getTopLeftCorner();
		g.moveToTarget(sa, targetTile, doReverse);
		
		return targetTile;
	}

}
