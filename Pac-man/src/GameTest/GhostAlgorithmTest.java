package GameTest;

import Game.Game;
import Game.Ghost;
import Game.PacMan;
import Game.Tuple;

import org.junit.Test;

import static org.junit.Assert.*;

import Game.Direction;

public class GhostAlgorithmTest {
	
	
//	test chase aggressive----------------------------------------------------------------------------
	/**
	 * ghost (9,8) RIGHT (intersection) 
	 * pacman (12,4) UP
	 * GreedySearch
	 * easy - no reverse
	 * */
	@Test
	public void TestChaseAggressive01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Easy", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost red = Ghost.getGhostByName('R');
		
		red.setPosition(new Tuple(9,8));
		red.setDirection(Direction.RIGHT);
		
		pacman.setPosition(new Tuple(12,4));
		pacman.setDirection(Direction.UP);
		
		game.moveGhosts();
		Tuple redNewPosition = red.getTuple();
		Tuple expectedRedPosition = new Tuple(9,7);
		game.gameOver();
		assertEquals(expectedRedPosition, redNewPosition);
	}
	
	/**
	 * ghost (10,8) RIGHT (not intersection)
	 * pacman (12,4) UP
	 * GreedySearch
	 * easy - no reverse
	 * */
	@Test
	public void TestChaseAggressive02() {
		Game game = Game.getInstance();
		int res = game.gameInit("Easy", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost red = Ghost.getGhostByName('R');
		
		red.setPosition(new Tuple(10,8));
		red.setDirection(Direction.RIGHT);
		
		pacman.setPosition(new Tuple(12,4));
		pacman.setDirection(Direction.UP);
		
		game.moveGhosts();
		Tuple redNewPosition = red.getTuple();
		Tuple expectedRedPosition = new Tuple(11,8);
		game.gameOver();
		assertEquals(expectedRedPosition, redNewPosition);
	}
	
	/**
	 * ghost (15,11) RIGHT (not intersection)
	 * pacman (12,8) UP
	 * A_star
	 * hard - reverse
	 * */
	@Test
	public void TestChaseAggressive03() {
		Game game = Game.getInstance();
		int res = game.gameInit("Hard", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost red = Ghost.getGhostByName('R');
		
		red.setPosition(new Tuple(15,11));
		red.setDirection(Direction.RIGHT);
		
		pacman.setPosition(new Tuple(12,8));
		pacman.setDirection(Direction.UP);
		
		game.moveGhosts();
		Tuple redNewPosition = red.getTuple();
		Tuple expectedRedPosition = new Tuple(14,11);
		game.gameOver();
		assertEquals(expectedRedPosition, redNewPosition);
	}
	
//	test chase ambush---------------------------------------------------------------------------
	
	/**
	 * ghost (19,7) LEFT (intersection)
	 * pacman (15,11) LEFT
	 * GreedySearch
	 * medium - no reverse
	 * */
	@Test
	public void TestChaseAmbush01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Medium", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost pink = Ghost.getGhostByName('P');
		
		pink.setPosition(new Tuple(19,7)); //18,7 or 19,8
		pink.setDirection(Direction.DOWN);
		
		pacman.setPosition(new Tuple(15,11)); //11,11
		pacman.setDirection(Direction.LEFT);
		
		game.moveGhosts();
		Tuple pinkNewPosition = pink.getTuple();
		Tuple expectedPinkPosition = new Tuple(18,7);
		game.gameOver();
		assertEquals(expectedPinkPosition, pinkNewPosition);
	}
	
	/**
	 * ghost (17,7) RIGHT (not intersection)
	 * pacman (15,11) LEFT
	 * GreedySearch
	 * medium - no reverse
	 * */
	@Test
	public void TestChaseAmbush02() {
		Game game = Game.getInstance();
		int res = game.gameInit("Medium", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost pink = Ghost.getGhostByName('P');
		
		pink.setPosition(new Tuple(17,7));
		pink.setDirection(Direction.RIGHT);
		
		pacman.setPosition(new Tuple(15,11));
		pacman.setDirection(Direction.LEFT);
		
		game.moveGhosts();
		Tuple pinkNewPosition = pink.getTuple();
		Tuple expectedPinkPosition = new Tuple(18,7);
		game.gameOver();
		assertEquals(expectedPinkPosition, pinkNewPosition);
	}
	
	/**
	 * ghost (18,8) LEFT (not intersection)
	 * pacman (9,7) DOWN
	 * A_star
	 * hard - reverse
	 * */
	@Test
	public void TestChaseAmbush03() {
		Game game = Game.getInstance();
		int res = game.gameInit("Hard", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost pink = Ghost.getGhostByName('P');
		
		pink.setPosition(new Tuple(18,8));
		pink.setDirection(Direction.LEFT);
		
		pacman.setPosition(new Tuple(9,7));
		pacman.setDirection(Direction.DOWN);
		
		game.moveGhosts();
		Tuple pinkNewPosition = pink.getTuple();
		Tuple expectedPinkPosition = new Tuple(19,8);
		game.gameOver();
		assertEquals(expectedPinkPosition, pinkNewPosition);
	}
	
//	test chase patrol-------------------------------------------------------------------
	
	/**
	 * blue (17,18) RIGHT (intersection)
	 * red (16,24) LEFT
	 * pacman (18,21) RIGHT
	 * GreedySearch
	 * easy - no reverse
	 * */
	@Test
	public void TestChasePatrol01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Easy", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost blue = Ghost.getGhostByName('B');
		Ghost red = Ghost.getGhostByName('R');

		blue.setPosition(new Tuple(17,18));
		blue.setDirection(Direction.RIGHT);
		
		red.setPosition(new Tuple(16,24));
		red.setDirection(Direction.LEFT);
		
		pacman.setPosition(new Tuple(18,21));
		pacman.setDirection(Direction.RIGHT); 
		pacman.setFood(30);//blue will not move unless pacman has eaten at least 30 food items
		
		game.moveGhosts();
		Tuple blueNewPosition = blue.getTuple();
		Tuple expectedBluePosition = new Tuple(18,18);
		game.gameOver();
		assertEquals(expectedBluePosition, blueNewPosition);
	}
	
	/**
	 * blue (17,19) DOWN (not intersection)
	 * red (16,24) LEFT
	 * pacman (18,21) RIGHT
	 * GreedySearch
	 * easy - no reverse
	 * */
	@Test
	public void TestChasePatrol02() {
		Game game = Game.getInstance();
		int res = game.gameInit("Easy", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost blue = Ghost.getGhostByName('B');
		Ghost red = Ghost.getGhostByName('R');

		blue.setPosition(new Tuple(17,19));
		blue.setDirection(Direction.DOWN);
		
		red.setPosition(new Tuple(16,24));
		red.setDirection(Direction.LEFT);
		
		pacman.setPosition(new Tuple(18,21));
		pacman.setDirection(Direction.RIGHT); 
		pacman.setFood(30);//blue will not move unless pacman has eaten at least 30 food items
		
		game.moveGhosts();
		Tuple blueNewPosition = blue.getTuple();
		Tuple expectedBluePosition = new Tuple(17,20);
		game.gameOver();
		assertEquals(expectedBluePosition, blueNewPosition);
	}
	
	/**
	 * blue (19,6) DOWN (not intersection)
	 * red (16,11) LEFT
	 * pacman (19,10) UP
	 * AStar
	 * hard -  reverse
	 * */
	@Test
	public void TestChasePatrol03() {
		Game game = Game.getInstance();
		int res = game.gameInit("Hard", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost blue = Ghost.getGhostByName('B');
		Ghost red = Ghost.getGhostByName('R');

		blue.setPosition(new Tuple(19,6));
		blue.setDirection(Direction.DOWN);
		
		red.setPosition(new Tuple(16,11)); //14,5
		red.setDirection(Direction.LEFT);
		
		pacman.setPosition(new Tuple(19,10)); //17,8 -> 15,8
		pacman.setDirection(Direction.UP);  
		pacman.setFood(30);//blue will not move unless pacman has eaten at least 30 food items
		
		game.moveGhosts();
		Tuple blueNewPosition = blue.getTuple();
		Tuple expectedBluePosition = new Tuple(19,7);
		game.gameOver();
		assertEquals(expectedBluePosition, blueNewPosition);
	}
	
}



