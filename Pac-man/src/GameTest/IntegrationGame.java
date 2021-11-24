package GameTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;


import Game.Game;

public class IntegrationGame { // push
	
	Game game;
	
	@Before
	public void setup() throws Exception {
		game = Game.getInstance();
	}
	
	@After
	public void destroy() {
		Game.GameConstructor();
		System.out.println("hey");
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
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
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
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		int res = game.gameTick("Right");
		assertEquals(res, 5);
	}
	
	@Test // Test Chase --> Scatter
	public void test_14() {
		game.gameInit("Easy", "Chase");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
		game.gameTick("Right");
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
}
