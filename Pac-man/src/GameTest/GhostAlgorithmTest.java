package GameTest;

import Game.Game;

import Game.Ghost;
import Game.Greedy_Search;
import Game.Hard;
import Game.PacMan;
import Game.RandomSearch;
import Game.ScatterBottomLeftCorner;
import Game.ScatterBottomRightCorner;
import Game.ScatterTopLeftCorner;
import Game.ScatterTopRightCorner;
import Game.Tuple;
import Game.Maze;
import Game.Medium;

import org.junit.Test;

import static org.junit.Assert.*;

import Game.A_star;
import Game.ChaseAggressive;
import Game.ChaseAmbush;
import Game.ChasePatrol;
import Game.ChaseRandom;
import Game.Direction;
import Game.Easy;
import Game.FrightenedWandering;

public class GhostAlgorithmTest {
	/**
	 * this class tests ghost algorithm only (not testing search algorithm or collision)
	 * 
	 * number of test cases:
	 * 
	 * chase aggressive: 1
	 * chase ambush: 2
	 * chase patrol: 3
	 * chase random: 3
	 * 
	 * frightened wandering: 1
	 * 
	 * 
	 * */
	
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
		
		Tuple redTargetTile = new ChaseAggressive().behave(red, Greedy_Search.getInstance(), Easy.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(12,4);
		game.gameOver();
		assertEquals(expectedTargetTile, redTargetTile);
	}
	
//	/**
//	 * ghost (10,8) RIGHT (not intersection)
//	 * pacman (12,4) UP
//	 * GreedySearch
//	 * easy - no reverse
//	 * */
//	@Test
//	public void TestChaseAggressive02() {
//		Game game = Game.getInstance();
//		int res = game.gameInit("Easy", "Chase");
//		PacMan pacman = PacMan.getInstance();
//		Ghost red = Ghost.getGhostByName('R');
//		
//		red.setPosition(new Tuple(10,8));
//		red.setDirection(Direction.RIGHT);
//		
//		pacman.setPosition(new Tuple(12,4));
//		pacman.setDirection(Direction.UP);
//		
//		Tuple redTargetTile = new ChaseAggressive().behave(red, Greedy_Search.getInstance(), false);
//		Tuple expectedTargetTile = new Tuple(12,4);
//		game.gameOver();
//		assertEquals(expectedTargetTile, redTargetTile);
//	}
//	
//	/**
//	 * ghost (15,11) RIGHT (not intersection)
//	 * pacman (12,8) UP
//	 * A_star
//	 * hard - reverse
//	 * */
//	@Test
//	public void TestChaseAggressive03() {
//		Game game = Game.getInstance();
//		int res = game.gameInit("Hard", "Chase");
//		PacMan pacman = PacMan.getInstance();
//		Ghost red = Ghost.getGhostByName('R');
//		
//		red.setPosition(new Tuple(15,11));
//		red.setDirection(Direction.RIGHT);
//		
//		pacman.setPosition(new Tuple(12,8));
//		pacman.setDirection(Direction.UP);
//		
//		Tuple redTargetTile = new ChaseAggressive().behave(red, A_star.getInstance(), true);
//		Tuple expectedTargetTile = new Tuple(12,8);
//		game.gameOver();
//		assertEquals(expectedTargetTile, redTargetTile);
//	}
	
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
		
		pink.setPosition(new Tuple(19,7)); //
		pink.setDirection(Direction.DOWN);
		
		pacman.setPosition(new Tuple(15,11)); //11,11
		pacman.setDirection(Direction.LEFT);
		
		Tuple pinkTargetTile = new ChaseAmbush().behave(pink, Greedy_Search.getInstance(), Medium.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(11,11);
		game.gameOver();
		assertEquals(expectedTargetTile, pinkTargetTile);
	}
	
	/**
	 * ghost (9,11) LEFT (intersection)
	 * pacman (19,11) UP
	 * GreedySearch
	 * medium - no reverse
	 * */
	@Test
	public void TestChaseAmbush02() {
		Game game = Game.getInstance();
		int res = game.gameInit("Medium", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost pink = Ghost.getGhostByName('P');
		
		pink.setPosition(new Tuple(9,11)); //
		pink.setDirection(Direction.DOWN);
		
		pacman.setPosition(new Tuple(19,11)); // -> 19,7 -> 15,7
		pacman.setDirection(Direction.UP);
		
		Tuple pinkTargetTile = new ChaseAmbush().behave(pink, Greedy_Search.getInstance(), Medium.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(15,7);
		game.gameOver();
		assertEquals(expectedTargetTile, pinkTargetTile);
	}
	
//	/**
//	 * ghost (17,7) RIGHT (not intersection)
//	 * pacman (15,11) LEFT
//	 * GreedySearch
//	 * medium - no reverse
//	 * */
//	@Test
//	public void TestChaseAmbush02() {
//		Game game = Game.getInstance();
//		int res = game.gameInit("Medium", "Chase");
//		PacMan pacman = PacMan.getInstance();
//		Ghost pink = Ghost.getGhostByName('P');
//		
//		pink.setPosition(new Tuple(17,7));
//		pink.setDirection(Direction.RIGHT);
//		
//		pacman.setPosition(new Tuple(15,11));
//		pacman.setDirection(Direction.LEFT);
//		
//		Tuple pinkTargetTile = new ChaseAmbush().behave(pink, Greedy_Search.getInstance(), false);
//		Tuple expectedTargetTile = new Tuple(11,11);
//		game.gameOver();
//		assertEquals(expectedTargetTile, pinkTargetTile);
//	}
//	
//	/**
//	 * ghost (18,8) LEFT (not intersection)
//	 * pacman (9,7) DOWN
//	 * A_star
//	 * hard - reverse
//	 * */
//	@Test
//	public void TestChaseAmbush03() {
//		Game game = Game.getInstance();
//		int res = game.gameInit("Hard", "Chase");
//		PacMan pacman = PacMan.getInstance();
//		Ghost pink = Ghost.getGhostByName('P');
//		
//		pink.setPosition(new Tuple(18,8));
//		pink.setDirection(Direction.LEFT);
//		
//		pacman.setPosition(new Tuple(9,7));
//		pacman.setDirection(Direction.DOWN);
//		
//		Tuple pinkTargetTile = new ChaseAmbush().behave(pink, A_star.getInstance(), true);
//		Tuple expectedTargetTile = new Tuple(9,11);
//		game.gameOver();
//		assertEquals(expectedTargetTile, pinkTargetTile);
//	}
	
//	test chase patrol-------------------------------------------------------------------
	
	/**
	 * not enough food
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
		
		red.setPosition(new Tuple(16,24)); // -> 24,18
		red.setDirection(Direction.LEFT);
		
		pacman.setPosition(new Tuple(18,21)); // -> 20,21
		pacman.setDirection(Direction.RIGHT); 
		
		Tuple blueTargetTile = new ChasePatrol().behave(blue, Greedy_Search.getInstance(), Easy.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(17,18);
		game.gameOver();
		assertEquals(expectedTargetTile, blueTargetTile);
	}
	
	/**
	 * enough food
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
		
		red.setPosition(new Tuple(16,24)); //-> 24,18
		red.setDirection(Direction.LEFT);
		
		pacman.setPosition(new Tuple(18,21)); //->20,21
		pacman.setDirection(Direction.RIGHT); 
		pacman.setFood(30);//blue will not move unless pacman has eaten at least 30 food items
		
		Tuple blueTargetTile = new ChasePatrol().behave(blue, Greedy_Search.getInstance(), Easy.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(24,18);
		game.gameOver();
		assertEquals(expectedTargetTile, blueTargetTile);
	}
	
	/**
	 * enough food
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
		
		red.setPosition(new Tuple(16,11)); // -> 18,5
		red.setDirection(Direction.LEFT);
		
		pacman.setPosition(new Tuple(19,10)); //-> 19,8 -> 17,8
		pacman.setDirection(Direction.UP);  
		pacman.setFood(30);//blue will not move unless pacman has eaten at least 30 food items
		
		Tuple blueTargetTile = new ChasePatrol().behave(blue, A_star.getInstance(), Hard.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(18,5);
		game.gameOver();
		assertEquals(expectedTargetTile, blueTargetTile);
	}
	
	
//	/**
//	 * blue (17,18) RIGHT (intersection)
//	 * red (16,24) LEFT
//	 * pacman (18,21) RIGHT
//	 * GreedySearch
//	 * easy - no reverse
//	 * */
//	@Test
//	public void TestChasePatrol01() {
//		Game game = Game.getInstance();
//		int res = game.gameInit("Easy", "Chase");
//		PacMan pacman = PacMan.getInstance();
//		Ghost blue = Ghost.getGhostByName('B');
//		Ghost red = Ghost.getGhostByName('R');
//
//		blue.setPosition(new Tuple(17,18));
//		blue.setDirection(Direction.RIGHT);
//		
//		red.setPosition(new Tuple(16,24)); // -> 24,18
//		red.setDirection(Direction.LEFT);
//		
//		pacman.setPosition(new Tuple(18,21)); // -> 20,21
//		pacman.setDirection(Direction.RIGHT); 
//		pacman.setFood(30);//blue will not move unless pacman has eaten at least 30 food items
//		
//		Tuple blueTargetTile = new ChasePatrol().behave(blue, Greedy_Search.getInstance(), false);
//		Tuple expectedTargetTile = new Tuple(24,18);
//		game.gameOver();
//		assertEquals(expectedTargetTile, blueTargetTile);
//	}
	
	
//	test chase random-------------------------------------------------------------------------------------------
	
	/**
	 * not enough food
	 * */
	@Test
	public void TestChaseRandom01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Medium", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost orange = Ghost.getGhostByName('O');
		
		orange.setPosition(new Tuple(19,7)); //
		orange.setDirection(Direction.DOWN);
		
		pacman.setPosition(new Tuple(15,11)); 
		pacman.setDirection(Direction.LEFT);
		
		Tuple orangeTargetTile = new ChaseRandom().behave(orange, Greedy_Search.getInstance(), Medium.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(19,7);
		game.gameOver();
		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	
	/**
	 *  enough food
	 *  distance >= 8 tiles
	 * */
	@Test
	public void TestChaseRandom02() {
		Game game = Game.getInstance();
		int res = game.gameInit("Medium", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost orange = Ghost.getGhostByName('O');
		
		orange.setPosition(new Tuple(19,7)); //
		orange.setDirection(Direction.DOWN);
		
		pacman.setPosition(new Tuple(9,17)); 
		pacman.setDirection(Direction.LEFT);
		
		//pacman ate enough food
		Maze maze = Maze.getInstance();
		int enoughFood = maze.getTotalNumOfFood()/3 + 1;
		pacman.setFood(enoughFood);
		
		Tuple orangeTargetTile = new ChaseRandom().behave(orange, Greedy_Search.getInstance(), Medium.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(9,17);
		game.gameOver();
		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	
	/**
	 *  enough food
	 *  distance < 8 tiles
	 * */
	@Test
	public void TestChaseRandom03() {
		Game game = Game.getInstance();
		int res = game.gameInit("Medium", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost orange = Ghost.getGhostByName('O');
		
		orange.setPosition(new Tuple(19,7)); //
		orange.setDirection(Direction.DOWN);
		
		pacman.setPosition(new Tuple(19,10)); 
		pacman.setDirection(Direction.UP);
		
		//pacman ate enough food
		Maze maze = Maze.getInstance();
		int enoughFood = maze.getTotalNumOfFood()/3 + 1;
		pacman.setFood(enoughFood);
		
		Tuple orangeTargetTile = new ChaseRandom().behave(orange, Greedy_Search.getInstance(), Medium.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(0,31);
		game.gameOver();
		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	
//	test frightened wandering-------------------------------------------------------------------------------------------
	
	/**
	 * method .behave() return frightened ghost's new position
	 * */
	@Test
	public void TestFrightenedWandering01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Easy", "Frightened");
		Ghost orange = Ghost.getGhostByName('O');
		
		orange.setPosition(new Tuple(20,8)); 
		orange.setDirection(Direction.RIGHT);
		
		Tuple orangeNewPosition = new FrightenedWandering().behave(orange, RandomSearch.getInstance(), Easy.getInstance().doReverse());
		Boolean doGhostReverse = orangeNewPosition.equals(new Tuple(18,8));
		game.gameOver();
		assertEquals(false, doGhostReverse);
	}
	
//	test scatter-------------------------------------------------------------------------------------------------------
	
	/**
	 * orange - bottom left
	 * not enough food
	 * */
	@Test
	public void TestScatterBottomLeft01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Easy", "Scatter");
		Ghost orange = Ghost.getGhostByName('O');
		
		orange.setPosition(new Tuple(19,8)); 
		orange.setDirection(Direction.RIGHT);
		
		Tuple orangeTargetTile = new ScatterBottomLeftCorner().behave(orange, Greedy_Search.getInstance(), Easy.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(19,8);
		game.gameOver();
		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	
	/**
	 * orange - bottom left
	 * enough food
	 * */
	@Test
	public void TestScatterBottomLeft02() {
		Game game = Game.getInstance();
		int res = game.gameInit("Easy", "Scatter");
		Ghost orange = Ghost.getGhostByName('O');
		PacMan pacman = PacMan.getInstance();
		
		orange.setPosition(new Tuple(19,8)); 
		orange.setDirection(Direction.RIGHT);
		
		//pacman ate enough food
		Maze maze = Maze.getInstance();
		int enoughFood = maze.getTotalNumOfFood()/3 + 1;
		pacman.setFood(enoughFood);
		
		Tuple orangeTargetTile = new ScatterBottomLeftCorner().behave(orange, Greedy_Search.getInstance(), Easy.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(0,31);
		game.gameOver();
		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	
	/**
	 * blue - bottom right
	 * not enough food
	 * */
	@Test
	public void TestScatterBottomRight01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Hard", "Scatter");
		Ghost blue = Ghost.getGhostByName('B');
		
		blue.setPosition(new Tuple(17,8)); 
		blue.setDirection(Direction.RIGHT);
		
		Tuple blueTargetTile = new ScatterBottomRightCorner().behave(blue, A_star.getInstance(), Hard.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(17,8);
		game.gameOver();
		assertEquals(expectedTargetTile, blueTargetTile);
	}
	
	/**
	 * blue - bottom right
	 * enough food
	 * */
	@Test
	public void TestScatterBottomRight02() {
		Game game = Game.getInstance();
		int res = game.gameInit("Hard", "Scatter");
		Ghost blue = Ghost.getGhostByName('B');
		PacMan pacman = PacMan.getInstance();
		
		blue.setPosition(new Tuple(17,8)); 
		blue.setDirection(Direction.RIGHT);
		
		pacman.setFood(30);//blue will not move unless pacman has eaten at least 30 food items
		
		Tuple blueTargetTile = new ScatterBottomRightCorner().behave(blue, A_star.getInstance(), Hard.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(28,31);
		game.gameOver();
		assertEquals(expectedTargetTile, blueTargetTile);
	}
	
	/**
	 * pink - top left
	 * */
	@Test
	public void TestScatterTopLeft01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Medium", "Scatter");
		Ghost pink = Ghost.getGhostByName('P');
		
		pink.setPosition(new Tuple(11,23)); 
		pink.setDirection(Direction.RIGHT);
		
		Tuple pinkTargetTile = new ScatterTopLeftCorner().behave(pink, Greedy_Search.getInstance(), Medium.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(0,0);
		game.gameOver();
		assertEquals(expectedTargetTile, pinkTargetTile);
	}
	
	/**
	 * red - top right
	 * */
	@Test
	public void TestScatterTopRight01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Medium", "Scatter");
		Ghost red = Ghost.getGhostByName('P');
		
		red.setPosition(new Tuple(11,23)); 
		red.setDirection(Direction.RIGHT);
		
		Tuple redTargetTile = new ScatterTopRightCorner().behave(red, Greedy_Search.getInstance(), Medium.getInstance().doReverse());
		Tuple expectedTargetTile = new Tuple(28,0);
		game.gameOver();
		assertEquals(expectedTargetTile, redTargetTile);
	}
}



