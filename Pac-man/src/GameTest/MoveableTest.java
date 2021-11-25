package GameTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.PacMan;
import Game.Direction;
import Game.GameObject;
import Game.Moveable;
import Game.Tuple;
import GameTest.WriteFile;


class MoveableTest {
    private static String pacManInitFile = "src/Resource/TestMoveablePacMan.txt";
	
    @BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		/*
		 * Pacman x=10, y=10, lives=1
		 */
		String [] contents = new String[] {"10", "10", "1"};
		WriteFile.DoWriting(pacManInitFile, contents);
		PacMan.initPacMan(pacManInitFile);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		WriteFile.DeleteFile(pacManInitFile);
	}
	
	// Test Moveable.move()
	@Test
	void test01() {
		
		PacMan pacman = PacMan.getInstance(); // create a new PacMan
		Direction d = Direction.UP;
		pacman.setDirection(d);
		pacman.move();
		Tuple result = pacman.getTuple();
		assertEquals(true, result.equals(new Tuple(10, 9)));
	} 
	
	// Test Moveable.getDirection() and Moveable.setDirection()
	@Test
	void test02() {
		PacMan pacman = PacMan.getInstance();
		Direction d = Direction.DOWN;
		pacman.setDirection(d);
		
		Direction result = pacman.getDirection();
		assertEquals(d, result);
	}
	
	//Test Moveable.resetDirection()
	@Test 
	void test03() {
		PacMan pacman = PacMan.getInstance();
		Direction direction = Direction.LEFT;
		pacman.setDirection(direction);
		
		pacman.resetDirection();  // pacman's initial direction is RIGHT
		
		Direction result = pacman.getDirection();
		assertEquals(Direction.RIGHT, result);
	}
	
	//Test Moveable.resetPosition()
	@Test 
	void test04() {
		PacMan pacman = PacMan.getInstance();
		
		pacman.setPosition(new Tuple(21, 14));
		pacman.resetPosition();
		
		Tuple result = pacman.getTuple();
		assertEquals(true, result.equals(new Tuple(10,10)));
	}
}
