package Game;

public class ChaseAggressive implements GhostAlgorithm {

	@Override
	public Tuple behave(Ghost g, SearchAlgorithm sa, boolean doReverse) {

		// The target tile is: pacman's position
		
		// Getting the position of Pac-Man
		PacMan pacman = PacMan.getInstance();
		Tuple targetTile = pacman.getTuple();
		
		
		g.moveToTarget(sa, targetTile, doReverse);
		
		return targetTile;

	}

}
