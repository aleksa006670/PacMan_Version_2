package GameTest;

import Game.Direction;
import Game.Easy;
import Game.Game;
import Game.Ghost;
import Game.Medium;
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
	 * about loop coverage in getNextDirection in RandomSearch
	 * since the number of accessible direction is either 4 or 3
	 * we cannot test 0 and 1 (loop)
	 * */
	
	
	@Test
	public void TestRandomSearch01() {
		Game game = Game.getInstance();
		game.gameInit("Easy", "Frightened");
		
		//any ghost will do
		Ghost red = Ghost.getGhostByName('R');
		red.setPosition(new Tuple(9,8));
		red.setDirection(Direction.RIGHT);
		
		red.moveToTarget(RandomSearch.getInstance(), null, Easy.getInstance().doReverse());
		
		//testing
		Tuple redNewPosition = red.getTuple();
		Boolean isNewPositionValid = !redNewPosition.equals(new Tuple(8,8))&&!redNewPosition.equals(new Tuple(9,8));
		game.gameOver();
		assertEquals(true, isNewPositionValid);
	}
	
	
	/**
	 * ghost is passing by the house
	 * */
	@Test
	public void TestRandomSearch02() {
		Game game = Game.getInstance();
		game.gameInit("Medium", "Frightened");
		
		//any ghost will do
		Ghost red = Ghost.getGhostByName('R');
		red.setPosition(new Tuple(14,11));
		red.setDirection(Direction.LEFT);
		
		red.moveToTarget(RandomSearch.getInstance(), null, Medium.getInstance().doReverse());
		
		//testing
		Tuple redNewPosition = red.getTuple();
		Boolean isNewPositionValid = redNewPosition.equals(new Tuple(13,11));
		game.gameOver();
		assertEquals(true, isNewPositionValid);
	}
	
	/**
	 * ghost is facing a dead end and cannot move
	 * */
	@Test
	public void TestRandomSearch03() {
		Game game = Game.getInstance();
		game.gameInit("Medium", "Frightened");
		
		//any ghost will do
		Ghost red = Ghost.getGhostByName('R');
		red.setPosition(new Tuple(1,7));
		red.setDirection(Direction.LEFT);
		
		red.moveToTarget(RandomSearch.getInstance(), null, Medium.getInstance().doReverse());
		
		//testing
		Tuple redNewPosition = red.getTuple();
		Boolean isNewPositionValid = redNewPosition.equals(new Tuple(1,7));
		game.gameOver();
		assertEquals(true, isNewPositionValid);
	}
	
	/**
	 * ghost is getting out of the house
	 * */
	@Test
	public void TestRandomSearch04() {
		Game game = Game.getInstance();
		game.gameInit("Easy", "Frightened");
		
		//any ghost will do
		Ghost red = Ghost.getGhostByName('R');
		red.setPosition(new Tuple(14,13));
		red.setDirection(Direction.UP);
		
		red.moveToTarget(RandomSearch.getInstance(), null, Easy.getInstance().doReverse());
		
		//testing
		Tuple redNewPosition = red.getTuple();
		Boolean isNewPositionValid = redNewPosition.equals(new Tuple(14,12));
		game.gameOver();
		assertEquals(true, isNewPositionValid);
	}
	
	/**
	 * test 05, 06, etc. are to cover Tuple.toClip()
	 * */
	@Test
	public void TestRandomSearch05() {
		Game game = Game.getInstance();
		game.gameInit("Easy", "Frightened");
		
		//any ghost will do
		Ghost red = Ghost.getGhostByName('R');
		red.setPosition(new Tuple(15,18));
		red.setDirection(Direction.RIGHT);
		
		red.moveToTarget(RandomSearch.getInstance(), null, Easy.getInstance().doReverse());
		
		//testing
		Tuple redNewPosition = red.getTuple();
		Boolean isNewPositionValid = redNewPosition.equals(new Tuple(16,18));
		game.gameOver();
		assertEquals(true, isNewPositionValid);
	}
	
	@Test
	public void TestRandomSearch06() {
		Game game = Game.getInstance();
		game.gameInit("Easy", "Frightened");
		
		//any ghost will do
		Ghost red = Ghost.getGhostByName('R');
		red.setPosition(new Tuple(20,14));
		red.setDirection(Direction.RIGHT);
		
		red.moveToTarget(RandomSearch.getInstance(), null, Easy.getInstance().doReverse());
		
		//testing
		Tuple redNewPosition = red.getTuple();
		Boolean isNewPositionValid = redNewPosition.equals(new Tuple(21,14));
		game.gameOver();
		assertEquals(true, isNewPositionValid);
	}
}
