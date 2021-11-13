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
		assertEquals(expectedRedPosition, redNewPosition);
		game.gameOver();
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
		assertEquals(expectedRedPosition, redNewPosition);
		game.gameOver();
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
		assertEquals(expectedRedPosition, redNewPosition);
		game.gameOver();
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
		
		pink.setPosition(new Tuple(19,7));
		pink.setDirection(Direction.LEFT);
		
		pacman.setPosition(new Tuple(15,11));
		pacman.setDirection(Direction.LEFT);
		
		game.moveGhosts();
		Tuple pinkNewPosition = pink.getTuple();
		Tuple expectedPinkPosition = new Tuple(18,7);
		assertEquals(expectedPinkPosition, pinkNewPosition);
		game.gameOver();
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
		assertEquals(expectedPinkPosition, pinkNewPosition);
		game.gameOver();
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
		assertEquals(expectedPinkPosition, pinkNewPosition);
		game.gameOver();
	}
}
