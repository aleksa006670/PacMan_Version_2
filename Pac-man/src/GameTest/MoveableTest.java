package GameTest;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.Assert.*;

//import org.junit.After;
//import org.junit.Before;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Test;

import Game.PacMan;
import Game.Direction;
import Game.Tuple;


class MoveableTest {
    private static String pacManInitFile = "src/Resource/TestMoveablePacMan.txt";
	
//    @Before
//    static void setup() {
//    	PacMan.initPacMan(pacManInitFile);    	
//    }
//    
//    @After
//    static void tearDown() {
//    	PacMan.destroyPacman();
//    }

    @BeforeEach
	public void setUpBeforeClass() throws Exception {
		
		/*
		 * Pacman x=10, y=10, lives=1
		 */
    	PacMan.destroyPacman();
		String [] contents = new String[] {"10", "10", "1"};
		WriteFile.DoWriting(pacManInitFile, contents);
		PacMan.initPacMan(pacManInitFile);  
	}
    
	@AfterEach
	public void tearDownAfterClass() throws Exception {
		
		WriteFile.DeleteFile(pacManInitFile);
	}
	
	// Test Moveable.move()
	@Test
	void test01() {
		PacMan.initPacMan(pacManInitFile);  
		PacMan pacman = PacMan.getInstance(); // create a new PacMan
		Direction d = Direction.UP;
		pacman.setDirection(d);
		pacman.move();
		Tuple result = pacman.getTuple();
		System.out.println(result.getFirst());
		System.out.println(result.getSecond());
		PacMan.destroyPacman();
		assertEquals(true, result.equals(new Tuple(10, 9)));
	} 
	
	// Test Moveable.getDirection() and Moveable.setDirection()
	@Test
	void test02() {
		PacMan.initPacMan(pacManInitFile);  
		PacMan pacman = PacMan.getInstance();
		Direction d = Direction.DOWN;
		pacman.setDirection(d);
		
		Direction result = pacman.getDirection();
		PacMan.destroyPacman();
		assertEquals(d, result);
	}
	
	//Test Moveable.resetDirection()
	@Test 
	void test03() {
		PacMan.initPacMan(pacManInitFile);  
		PacMan pacman = PacMan.getInstance();
		Direction direction = Direction.LEFT;
		pacman.setDirection(direction);
		
		pacman.resetDirection();  // pacman's initial direction is RIGHT
		
		Direction result = pacman.getDirection();
		PacMan.destroyPacman();
		assertEquals(Direction.RIGHT, result);
	}
	
	//Test Moveable.resetPosition()
	@Test 
	void test04() {
		PacMan.initPacMan(pacManInitFile);  
		PacMan pacman = PacMan.getInstance();
		
		pacman.setPosition(new Tuple(21, 14));
		pacman.resetPosition();
		
		Tuple result = pacman.getTuple();
		PacMan.destroyPacman();
		assertEquals(true, result.equals(new Tuple(10,10)));
	}
}
