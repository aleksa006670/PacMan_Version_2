package GameTest;

import Game.Direction;
import Game.Game;
import Game.Ghost;
import Game.PacMan;
import Game.RandomSearch;
import Game.Tuple;

import org.junit.Test;

import static org.junit.Assert.*;

public class Integration_Ghost_RandomSearch_Direction_Tuple {

	/**
	 * in frightened mode, ghost cannot reverse 
	 * (ghost can reverse in level Hard, but Hard does not have frightened mode)
	 * therefore, doReverse = false and a branch in moveToTarget method is never covered
	 * */
	
	/**
	 * get next direction in RandomSearch does not return null
	 * since the maze does not have a dead end
	 * therefore, nextDirection == null is never covered
	 * */
	
	
	@Test
	public void TestRandomSearch01() {
		Game game = Game.getInstance();
		game.gameInit("Easy", "Frightened");
		
		//any ghost will do
		Ghost red = Ghost.getGhostByName('R');
		red.setPosition(new Tuple(9,8));
		red.setDirection(Direction.RIGHT);
		
		red.moveToTarget(RandomSearch.getInstance(), null, false);
		
		//testing
		Tuple redNewPosition = red.getTuple();
		Boolean isNewPositionValid = !redNewPosition.equals(new Tuple(8,8))&&!redNewPosition.equals(new Tuple(9,8));
		game.gameOver();
		assertEquals(true, isNewPositionValid);
	}
}
