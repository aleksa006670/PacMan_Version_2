package GameTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;

import Game.Direction;
import Game.Game;
import Game.Ghost;
import Game.PacMan;
import Game.Tuple;
import Game.Maze;

public class IntegrationGame { // push
	
	Game game;
	
	@Before
	public void setup() throws Exception {
		game = Game.getInstance();
	}
	
	@After
	public void destroy() {
		game.gameOver();
		Game.destroyGame();
	}

	// <------------------------ gameInit() ------------------------>
	@Test // Test Success
	public void test_01() {
		int res = game.gameInit("Easy", "Scatter");
		assertEquals(res, 0);
	}
	
	@Test // Difficulty Init fail
	public void test_02() {
		int res = game.gameInit("", "Scatter");
		assertEquals(res, 1);
	}
	
	@Test // Mode Init fail
	public void test_03() {
		int res = game.gameInit("Easy", "");
		assertEquals(res, 2);
	}
	
	// <------------------------ gameTick() ------------------------>
	@Test // Test Up
	public void test_04() {
		game.gameInit("Easy", "Chase");
		int res = game.gameTick("Up");
		assertEquals(res, 0);
	}
	
	@Test // Test Down
	public void test_05() {
		game.gameInit("Easy", "Chase");
		int res = game.gameTick("Down");
		assertEquals(res, 0);
	}
	
	@Test // Test Left
	public void test_06() {
		game.gameInit("Easy", "Chase");
		int res = game.gameTick("Left");
		assertEquals(res, 0);
	}
	
	@Test // Test Right
	public void test_07() {
		game.gameInit("Easy", "Chase");
		int res = game.gameTick("Right");
		assertEquals(res, 0);
	}
	
	@Test // Test Reset
	public void test_08() {
		game.gameInit("Easy", "Chase");
		int res = game.gameTick("Reset");
		assertEquals(res, 1);
	}
	
	@Test // Test default
	public void test_09() {
		game.gameInit("Easy", "Chase");
		int res = game.gameTick("");
		assertEquals(res, 0);
	}
	
	@Test // Test Frightened --> Frightened
	public void test_10() {
		game.gameInit("Easy", "Frightened");
		int res = game.gameTick("Right");
		assertEquals(res, 3);
	}
	
	@Test // Test Frightened --> Chase
	public void test_11() {
		game.gameInit("Easy", "Frightened");
		for(int i = 0; i < 9; i++)
			game.gameTick("Right");
		int res = game.gameTick("Right");
		assertEquals(res, 4);
	}
	
	@Test // Test Scatter/Chase --> Scatter/Chase
	public void test_12() {
		game.gameInit("Easy", "Scatter"); // Could be Chase
		int res = game.gameTick("Right");
		assertEquals(res, 0);
	}
	
	@Test // Test Scatter --> Chase
	public void test_13() {
		game.gameInit("Easy", "Scatter");
		for(int i = 0; i < 9; i++)
			game.gameTick("Right");
		int res = game.gameTick("Right");
		assertEquals(res, 5);
	}
	
	@Test // Test Chase --> Scatter
	public void test_14() {
		game.gameInit("Easy", "Chase");
		for(int i = 0; i < 9; i++)
			game.gameTick("Right");
		int res = game.gameTick("Right");
		assertEquals(res, 6);
	}
	
	@Test // Test MC/DC of totalModeChanges
	public void test_15() {
		game.gameInit("Easy", "Chase");
		for(int i = 0; i < 99; i++)
			game.gameTick("Right");
		int res = game.gameTick("Right");
		assertEquals(res, 0);
	}
	
	// <------------------------ setMode() ------------------------>
	@Test // Mode set to Chase
	public void test_16() {
		int res = game.gameInit("Easy", "Chase"); 
		assertEquals(res, 0);
	}
	
	@Test // Mode set to Scatter
	public void test_17() {
		int res = game.gameInit("Easy", "Scatter"); 
		assertEquals(res, 0);
	}
	
	@Test // Mode set to Frightened
	public void test_18() {
		int res = game.gameInit("Easy", "Frightened"); 
		assertEquals(res, 0);
	}
	
	@Test // Mode set to null
	public void test_19() {
		int res = game.gameInit("Easy", null); 
		assertEquals(res, 2);
	}
	
	@Test // Mode set to invalid string
	public void test_20() {
		int res = game.gameInit("Easy", "Invalid");
		assertEquals(res, 2);
	}
	
	@Test // this.mode != null (F)
	public void test_21() {
		int res = game.gameInit("Easy", "Scatter"); // First mode to be set
		assertEquals(res, 0);
	}
	
	@Test // this.mode != null (T) --- !this.mode.getModeName().equals(mode) (F)
	public void test_22() {
		game.gameInit("Easy", "Scatter");
		int res = game.gameInit("Easy", "Scatter");
		assertEquals(res, 0);
	}
	
	@Test // !this.mode.getModeName().equals(mode) (T) --- !this.mode.getModeName().equals("Frightened") (F)
	public void test_23() {
		game.gameInit("Easy", "Frightened");
		int res = game.gameInit("Easy", "Chase");
		assertEquals(res, 0);
	}
	
	@Test // !this.mode.getModeName().equals("Frightened") (T)
	public void test_24() {
		game.gameInit("Easy", "Chase");
		int res = game.gameInit("Easy", "Frightened");
		assertEquals(res, 0);
	}
	
	// <------------------------ setDifficulty() ------------------------>
	@Test // Difficulty set to Easy
	public void test_25() {
		int res = game.gameInit("Easy", "Chase"); 
		assertEquals(res, 0);
	}
	
	@Test // Difficulty set to Medium
	public void test_26() {
		int res = game.gameInit("Medium", "Chase"); 
		assertEquals(res, 0);
	}
	
	@Test // Difficulty set to Hard
	public void test_27() {
		int res = game.gameInit("Hard", "Chase"); 
		assertEquals(res, 0);
	}
	
	@Test // Difficulty set to null
	public void test_28() {
		int res = game.gameInit(null, "Chase"); 
		assertEquals(res, 1);
	}
	
	@Test // Difficulty set to Invalid
	public void test_29() {
		int res = game.gameInit("Invalid", "Chase"); 
		assertEquals(res, 1);
	}
	
	// <------------------------ resetGame() ------------------------>
	@Test // Reset the game
	public void test_30() {
		game.gameInit("Easy", "Chase");
		boolean res = game.resetGame();
		assertEquals(res, true);
	}
	
	// <------------------------ handleMovements(Direction pacPotDir) ------------------------>
	@Test // Pacman stays in position --- Pacman does not collide with Ghosts
	public void test_31() {
		game.gameInit("Easy", "Chase");
		int res = game.handleMovements(Direction.DOWN);
		assertEquals(res, 0);
	}
	
	@Test // Pacman hits a Food
	public void test_32() {
		game.gameInit("Easy", "Chase");
		int res = game.handleMovements(Direction.RIGHT);
		assertEquals(res, 3);
	}
	
	@Test // Pacman hits a powerup
	public void test_33() {
		game.gameInit("Easy", "Chase");
		PacMan.getInstance().setPosition(new Tuple(3, 30));
		int res = game.handleMovements(Direction.LEFT);
		assertEquals(res, 2);
	}
	
	@Test // Pacman moves into a null position
	public void test_34() {
		game.gameInit("Easy", "Chase");
		game.handleMovements(Direction.RIGHT);
		int res = game.handleMovements(Direction.LEFT);
		assertEquals(res, 1);
	}
	
	@Test // Ghost collides with Pacman during Chase or Scatter --- MC/DC for pacmanNoLose
	public void test_35() {
		game.gameInit("Easy", "Chase");
		Ghost.getGhostByName('R').setPosition(new Tuple(15, 18));
		int res = game.handleMovements(Direction.RIGHT);
		assertEquals(res, 40);
	}
	
	@Test // Ghost collides with Pacman during Frightened
	public void test_36() {
		game.gameInit("Easy", "Frightened");
		PacMan.getInstance().setPosition(new Tuple(2, 21));
		Ghost.getGhostByName('R').setPosition(new Tuple(1, 21));
		int res = game.handleMovements(Direction.LEFT);
		assertEquals(res, 33);
	}
	
	// <------------------------ isCollision(Ghost g) ------------------------>
	@Test // No collision
	public void test_37() {
		game.gameInit("Easy", "Chase");
		int res = game.handleMovements(Direction.RIGHT);
		assertEquals(res, 3);
	}
	
	@Test // Same tile collision
	public void test_38() {
		game.gameInit("Easy", "Chase");
		Ghost.getGhostByName('R').setPosition(new Tuple(16, 18));
		int res = game.handleMovements(Direction.RIGHT);
		assertEquals(res, 40);
	}
	
	@Test // Swap tile collision
	public void test_39() {
		game.gameInit("Easy", "Chase");
		Ghost.getGhostByName('R').setPosition(new Tuple(15, 18));
		int res = game.handleMovements(Direction.RIGHT);
		assertEquals(res, 40);
	}
	
	@Test // No collision MC/DC --- following each other
	public void test_40() {
		game.gameInit("Easy", "Chase");
		Ghost.getGhostByName('R').setPosition(new Tuple(13, 18));
		int res = game.handleMovements(Direction.RIGHT);
		assertEquals(res, 3);
	}
	
	// <------------------------ moveGhosts() ------------------------>
	@Test // Move Ghosts in Chase mode
	public void test_41() {
		game.gameInit("Easy", "Chase");
		boolean res = game.moveGhosts();
		assertEquals(res, true);
	}
	
	@Test // Move Ghosts in Frightened mode
	public void test_42() {
		game.gameInit("Easy", "Frightened");
		boolean res = game.moveGhosts();
		assertEquals(res, false);
	}
	
	// <------------------------ movePacMan(Direction pacPotDir) ------------------------>
	@Test // Move Pacman into food
	public void test_43() {
		game.gameInit("Easy", "Chase");
		char res = game.movePacMan(Direction.RIGHT);
		assertEquals(res, 'F');
	}
	
	@Test // Move Pacman into wall
	public void test_44() {
		game.gameInit("Easy", "Chase");
		char res = game.movePacMan(Direction.DOWN);
		assertEquals(res, 'W');
	}
	
	// <------------------------ gameOver() ------------------------>
	@Test // End Game
	public void test_45() {
		game.gameInit("Easy", "Chase");
		boolean res = game.gameOver();
		assertEquals(res, true);
	}
	
	// <------------------------ isGameOver() ------------------------>
	@Test // F || F
	public void test_46() {
		game.gameInit("Easy", "Chase");
		boolean res = game.isGameOver();
		assertEquals(res, false);
	}
	
	@Test // F || T
	public void test_47() {
		game.gameInit("Easy", "Chase");
		PacMan.getInstance().setFood(Maze.getInstance().getTotalNumOfFood());
		boolean res = game.isGameOver();
		assertEquals(res, true);
	}
	
	@Test // T || F
	public void test_48() {
		game.gameInit("Easy", "Chase");
		PacMan.getInstance().changeLives(-3);
		boolean res = game.isGameOver();
		assertEquals(res, true);
	}
	
	// <------------------------ PacDefeatGhost() ------------------------>
	// Check test case number : 36
	
	// <------------------------ GhostDefeatPac() ------------------------>
	// Check test case number : 35
	
	// <------------------------ printMaze() ------------------------>
	@Test
	public void test_49() {
		game.gameInit("Easy", "Scatter");
		String res = game.printMaze();
		String mze = "W W W W W W W W W W W W W W W W W W W W W W W W W W W W W \n"
				+ "W U F F F F F F F F F F F F F F F F F F F F F F F F F U W \n"
				+ "W F W W W W W W W W W W F W W W F W W W W W W W W W W F W \n"
				+ "W F W W W W W W W W W W F W W W F W W W W W W W W W W F W \n"
				+ "W F W W W W W W W F F F F W W W F F F F W W W W W W W F W \n"
				+ "W F F F F F F F W F W W W W W W W W W F W F F F F F F F W \n"
				+ "W W W W W W W F W F W W W W W W W W W F W F W W W W W W W \n"
				+ "n n n n n n W F W F W W W W W W W W W F W F W n n n n n n \n"
				+ "n n n n n n W F F F F F F F F F F F F F F F W n n n n n n \n"
				+ "n n n n n n W F W F W W W W W W W W W F W F W n n n n n n \n"
				+ "n n n W W W W F W F W W W W W W W W W F W F W W W W n n n \n"
				+ "n n n W F F F F W F W W W W W W W W W F W F F F F W n n n \n"
				+ "n n n W F W W W W F F F F F R F F F F F W W W W F W n n n \n"
				+ "n W W W F W W W W F W W W W G W W W W F W W W W F W W W n \n"
				+ "n W F F F F F F F F W n n I B O n n W F F F F F F F F W n \n"
				+ "n W F W W W F W W F W n n n n n n n W F W W F W W W F W n \n"
				+ "n W F W W W F W W F W n n n n n n n W F W W F W W W F W n \n"
				+ "n W F F F W F W W F W W W W W W W W W F W W F W F F F W n \n"
				+ "n W W W F W F W W F F F F F P F F F F F W W F W F W W W n \n"
				+ "n n n W F W F W W W W F W W W W W F W W W W F W F W n n n \n"
				+ "W W W W F W F W W W W F W W W W W F W W W W F W F W W W W \n"
				+ "W F F F F F F F F F F F W W W W W F F F F F F F F F F F W \n"
				+ "W W W W F W W W W F W W W W W W W W W F W W W W F W W W W \n"
				+ "n n n W F W W W W F W W W W W W W W W F W W W W F W n n n \n"
				+ "n n n W F W F F F F F F F F F F F F F F F F F W F W n n n \n"
				+ "n n n W F W F W W W W F W W W W W F W W W W F W F W n n n \n"
				+ "n W W W F W F W W W W F W W W W W F W W W W F W F W W W n \n"
				+ "n W F F F F F W W F F F W W W W W F F F W W F F F F F W n \n"
				+ "n W F W W W F W W F W W W W W W W W W F W W F W W W F W n \n"
				+ "n W F W W W F W W F W W W W W W W W W F W W F W W W F W n \n"
				+ "n W U F F F F W W F F F F F F F F F F F W W F F F F U W n \n"
				+ "n W W W W W W W W W W W W W W W W W W W W W W W W W W W n \n";
		assertEquals(res, mze);
	}
}
