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
import Game.Mode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

import Game.A_star;
import Game.ChaseAggressive;
import Game.ChaseAmbush;
import Game.ChasePatrol;
import Game.ChaseRandom;
import Game.Direction;
import Game.Easy;

public class Integration_GhostAlgorithm_Ghost_Maze_Tuple_Pacman_Test {
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
	@BeforeEach
	public void init() {
		Ghost.initGhosts("src/Resource/hardGhostData.txt");
		PacMan.initPacMan("src/Resource/hardPacmanData.txt");
	}
	
	@AfterEach
	public void destroySingletons() {
		PacMan.destroyPacman();
		Ghost.destroyGhosts();
		Maze.destroyMaze();
	}
	
//	test chase aggressive----------------------------------------------------------------------------
	/**
	 * ghost (9,8) RIGHT (intersection) 
	 * pacman (12,4) UP
	 * GreedySearch
	 * easy - no reverse
	 * */
	@Test
	public void TestChaseAggressive01() {
		Maze.initMaze("src/Resource/easyMaze.txt");
		PacMan pacman = PacMan.getInstance();
		Ghost red = Ghost.getGhostByName('R');
		
		red.setPosition(new Tuple(9,8));
		red.setDirection(Direction.RIGHT);
		
		pacman.setPosition(new Tuple(12,4));
		pacman.setDirection(Direction.UP);
		
		Tuple redTargetTile = new ChaseAggressive().behave(red);
		Tuple expectedTargetTile = new Tuple(12,4);
		
		assertEquals(expectedTargetTile, redTargetTile);
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
		Maze.initMaze("src/Resource/mediumMaze.txt");
		PacMan pacman = PacMan.getInstance();
		Ghost pink = Ghost.getGhostByName('P');
		
		pink.setPosition(new Tuple(19,7)); //
		pink.setDirection(Direction.DOWN);
		
		pacman.setPosition(new Tuple(15,11)); //11,11
		pacman.setDirection(Direction.LEFT);
		
		Tuple pinkTargetTile = new ChaseAmbush().behave(pink);
		Tuple expectedTargetTile = new Tuple(11,11);
		
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
		Maze.initMaze("src/Resource/mediumMaze.txt");
		PacMan pacman = PacMan.getInstance();
		Ghost pink = Ghost.getGhostByName('P');
		
		pink.setPosition(new Tuple(9,11)); //
		pink.setDirection(Direction.DOWN);
		
		pacman.setPosition(new Tuple(19,11)); // -> 19,7 -> 15,7
		pacman.setDirection(Direction.UP);
		
		Tuple pinkTargetTile = new ChaseAmbush().behave(pink);
		Tuple expectedTargetTile = new Tuple(15,7);
		
		assertEquals(expectedTargetTile, pinkTargetTile);
	}
	

	
//	test chase patrol-------------------------------------------------------------------
	
	/**
	 * not enough food
	 * */
	@Test
	public void TestChasePatrol01() {
		Maze.initMaze("src/Resource/easyMaze.txt");
		PacMan pacman = PacMan.getInstance();
		Ghost blue = Ghost.getGhostByName('B');
		Ghost red = Ghost.getGhostByName('R');

		blue.setPosition(new Tuple(17,18));
		blue.setDirection(Direction.RIGHT);
		
		red.setPosition(new Tuple(16,24)); // -> 24,18
		red.setDirection(Direction.LEFT);
		
		pacman.setPosition(new Tuple(18,21)); // -> 20,21
		pacman.setDirection(Direction.RIGHT); 
		
		Tuple blueTargetTile = new ChasePatrol().behave(blue);
		Tuple expectedTargetTile = new Tuple(17,18);
		
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
		Maze.initMaze("src/Resource/easyMaze.txt");
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
		
		Tuple blueTargetTile = new ChasePatrol().behave(blue);
		Tuple expectedTargetTile = new Tuple(24,18);
		
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
		Maze.initMaze("src/Resource/hardMaze.txt");
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
		
		Tuple blueTargetTile = new ChasePatrol().behave(blue);
		Tuple expectedTargetTile = new Tuple(18,5);
		
	
		assertEquals(expectedTargetTile, blueTargetTile);
	}
	
	
//	test chase random-------------------------------------------------------------------------------------------
	
	/**
	 * not enough food
	 * */
	@Test
	public void TestChaseRandom01() {
		Maze.initMaze("src/Resource/mediumMaze.txt");
		PacMan pacman = PacMan.getInstance();
		Ghost orange = Ghost.getGhostByName('O');
		
		orange.setPosition(new Tuple(19,7)); //
		orange.setDirection(Direction.DOWN);
		
		pacman.setPosition(new Tuple(15,11)); 
		pacman.setDirection(Direction.LEFT);
		
		Tuple orangeTargetTile = new ChaseRandom().behave(orange);
		Tuple expectedTargetTile = new Tuple(19,7);

		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	
	/**
	 *  enough food
	 *  distance >= 8 tiles
	 * */
	@Test
	public void TestChaseRandom02() {
		Maze.initMaze("src/Resource/mediumMaze.txt");
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
		
		Tuple orangeTargetTile = new ChaseRandom().behave(orange);
		Tuple expectedTargetTile = new Tuple(9,17);
		
		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	
	/**
	 *  enough food
	 *  distance < 8 tiles
	 * */
	@Test
	public void TestChaseRandom03() {
		Maze.initMaze("src/Resource/mediumMaze.txt");
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
		
		Tuple orangeTargetTile = new ChaseRandom().behave(orange);
		Tuple expectedTargetTile = new Tuple(0,31);
		
	
		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	

	
//	test scatter-------------------------------------------------------------------------------------------------------
	
	/**
	 * orange - bottom left
	 * not enough food
	 * */
	@Test
	public void TestScatterBottomLeft01() {
		Maze.initMaze("src/Resource/easyMaze.txt");
		Ghost orange = Ghost.getGhostByName('O');
		
		orange.setPosition(new Tuple(19,8)); 
		orange.setDirection(Direction.RIGHT);
		
		Tuple orangeTargetTile = new ScatterBottomLeftCorner().behave(orange);
		Tuple expectedTargetTile = new Tuple(19,8);
		
		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	
	/**
	 * orange - bottom left
	 * enough food
	 * */
	@Test
	public void TestScatterBottomLeft02() {
		Maze.initMaze("src/Resource/easyMaze.txt");
		Ghost orange = Ghost.getGhostByName('O');
		PacMan pacman = PacMan.getInstance();
		
		orange.setPosition(new Tuple(19,8)); 
		orange.setDirection(Direction.RIGHT);
		
		//pacman ate enough food
		Maze maze = Maze.getInstance();
		int enoughFood = maze.getTotalNumOfFood()/3 + 1;
		pacman.setFood(enoughFood);
		
		Tuple orangeTargetTile = new ScatterBottomLeftCorner().behave(orange);
		Tuple expectedTargetTile = new Tuple(0,31);
		
		assertEquals(expectedTargetTile, orangeTargetTile);
	}
	
	/**
	 * blue - bottom right
	 * not enough food
	 * */
	@Test
	public void TestScatterBottomRight01() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		Ghost blue = Ghost.getGhostByName('B');
		
		blue.setPosition(new Tuple(17,8)); 
		blue.setDirection(Direction.RIGHT);
		
		Tuple blueTargetTile = new ScatterBottomRightCorner().behave(blue);
		Tuple expectedTargetTile = new Tuple(17,8);

		
		assertEquals(expectedTargetTile, blueTargetTile);
	}
	
	/**
	 * blue - bottom right
	 * enough food
	 * */
	@Test
	public void TestScatterBottomRight02() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		Ghost blue = Ghost.getGhostByName('B');
		PacMan pacman = PacMan.getInstance();
		
		blue.setPosition(new Tuple(17,8)); 
		blue.setDirection(Direction.RIGHT);
		
		pacman.setFood(30);//blue will not move unless pacman has eaten at least 30 food items
		
		Tuple blueTargetTile = new ScatterBottomRightCorner().behave(blue);
		Tuple expectedTargetTile = new Tuple(28,31);


	
		assertEquals(expectedTargetTile, blueTargetTile);
	}
	
	/**
	 * pink - top left
	 * */
	@Test
	public void TestScatterTopLeft01() {
		Maze.initMaze("src/Resource/mediumMaze.txt");
		Ghost pink = Ghost.getGhostByName('P');
		
		pink.setPosition(new Tuple(11,23)); 
		pink.setDirection(Direction.RIGHT);
		
		Tuple pinkTargetTile = new ScatterTopLeftCorner().behave(pink);
		Tuple expectedTargetTile = new Tuple(0,0);

		assertEquals(expectedTargetTile, pinkTargetTile);
	}
	
	/**
	 * red - top right
	 * */
	@Test
	public void TestScatterTopRight01() {
		Maze.initMaze("src/Resource/mediumMaze.txt");
		Ghost red = Ghost.getGhostByName('P');
		
		red.setPosition(new Tuple(11,23)); 
		red.setDirection(Direction.RIGHT);
		
		Tuple redTargetTile = new ScatterTopRightCorner().behave(red);
		Tuple expectedTargetTile = new Tuple(28,0);
		
		
		assertEquals(expectedTargetTile, redTargetTile);
	}
}



