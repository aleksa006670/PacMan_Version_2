package Game;


public class FrightenedWandering implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g, SearchAlgorithm sa, boolean doReverse) {
		// if facing an intersection: choose a random path
		// if not facing an intersection: continue moving forward
		Tuple targetTile = null;
		g.moveToTarget(sa, targetTile, doReverse);
		
		return g.getTuple();
	}

}
