package GameTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;

import Game.Direction;
import Game.Game;

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
	@Test // Reset the game
	public void test_31() {
		game.gameInit("Easy", "Chase");
		boolean res = game.handleMovements(Direction.DOWN);
		assertEquals(res, true);
	}
}
