package GameTest;

import Game.Game;
import Game.Ghost;
import Game.PacMan;
import Game.Tuple;

import org.junit.Test;

import static org.junit.Assert.*;

import Game.Direction;

public class GhostAlgorithmTest {

	/**
	 * ghost (9,8) RIGHT
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
	}
	
	/**
	 * ghost (10,8) RIGHT
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
	}
	
	/**
	 * ghost (15,11) RIGHT
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
		System.out.println(redNewPosition.getFirst() + " " + redNewPosition.getSecond());
		game.printMaze();
		assertEquals(expectedRedPosition, redNewPosition);
	}
	
}
