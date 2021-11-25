package GameTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Maze;
import Game.Tuple;

class MazeTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		// clear maze instance before every test case
		if (Maze.getInstance() != null)
		    Maze.destroyMaze();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	// Test Maze.initMaze() when the maze has not been generated
	@Test
	void test01() {
		boolean result = Maze.initMaze("src/Resource/hardMaze.txt");
		assertTrue(result);
	}
	
	// Test Maze.initMaze() when the maze has already been generated
	@Test
	void test02() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		boolean result =Maze.initMaze("src/Resource/easyMaze.txt");
		assertTrue(result);
	}
	
	// Test Maze.isInHouse() when the tuple is in ghost house
	@Test 
	void test03() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		boolean result = Maze.getInstance().isInHouse(new Tuple(15, 14));
		assertTrue(result);
	}
	
	// Test Maze.isInHouse() when the tuple is not in ghost house
	@Test
	void test04() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		boolean result = Maze.getInstance().isInHouse(new Tuple(9, 14));
		assertFalse(result);
	}
	
	// Test Maze.getInstance()
	@Test
	void test05() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		Maze result = Maze.getInstance();
		assertNotNull(result);
	}

	// Test Maze.getN()
	@Test
	void test06() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		int result = Maze.getInstance().getN();
		assertEquals(29, result);
	}
	
	//Test Maze.getM()
	@Test
	void test07() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		int result = Maze.getInstance().getM();
		assertEquals(32, result);
	}
	
	// Test Maze.removeObject() when matrix[i][j] is not null
	@Test
	void test08() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		// remove the Food object at matrix(1, 5) or (x=5, y=1)
		boolean result = Maze.getInstance().removeObject(new Tuple(5, 1));
		// see after removal if this position becomes null
//		Maze.getInstance().getSymbol(1, 5);
		assertTrue(result);
	}
	
	// Test Maze.removeObject() when matrix[i][j] is  null
	@Test 
	void test09() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		// remove the Food object at matrix(1, 5) or (x=5, y=1)
		Maze.getInstance().removeObject(new Tuple(5, 1));
		// now try to remove it again, i.e. to remove a null position
		boolean result = Maze.getInstance().removeObject(new Tuple(5, 1));
		// see after removal if this position is really null
//		Maze.getInstance().getSymbol(1, 5);
		assertFalse(result);
	}
	
	// Test Maze.getSymbol() when we want to get a NON-null object
	@Test
	void test10() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		char result = Maze.getInstance().getSymbol(1, 5);
		// if everything is OK this position is Food
		assertEquals('F', result);
	}
	
	// Test Maze.getSymbol() when we want to get a null object
	@Test
	void test24() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		// pick wherever an object and remove it 
		// so we get an null at this tile
		Maze.getInstance().removeObject(new Tuple(5, 1));
		char result = Maze.getInstance().getSymbol(1, 5);
		// if everything is OK this position should be null
		// and getSymbol() should return ' '
		assertEquals(' ', result);
	}
	
	
	// Test Maze.destroyMaze()
	@Test
	void test11() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		Maze.destroyMaze();
		Maze result = Maze.getInstance();
		assertNull(result);
	}
	
	// Test Maze.resetMaze() when for loop is NOT executed
	@Test
	void test12() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		boolean result = Maze.getInstance().resetMaze();
		assertTrue(result);
	}
	
	// Test Maze.resetMaze() when for loop is executed at least onece
	@Test
	void test13() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		Maze.getInstance().removeObject(new Tuple(9, 10));
		boolean result = Maze.getInstance().resetMaze();
		assertTrue(result);
	}
	
	// Test Maze.getBottomLeftCorner()
	@Test 
	void test14() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		Tuple result = Maze.getInstance().getBottomLeftCorner();
		assertTrue(result.equals(new Tuple(0, 31)));
	}
	
	// Test Maze.getBottomRightCorner()
	@Test 
	void test15() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		Tuple result = Maze.getInstance().getBottomRightCorner();
		assertTrue(result.equals(new Tuple(28, 31)));
	}
	
	// Test Maze.getTopLeftCorner()
	@Test 
	void test16() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		Tuple result = Maze.getInstance().getTopLeftCorner();
		assertTrue(result.equals(new Tuple(0, 0)));
	}

	// Test Maze.getTopRightCorner()
	@Test 
	void test17() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		Tuple result = Maze.getInstance().getTopRightCorner();
		assertTrue(result.equals(new Tuple(28, 0)));
	}
	
	// Test Maze.getTotalNumOfFood()
	@Test
	void test18() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		// when use hardMaze, initially there are 321 Food objects
		int result = Maze.getInstance().getTotalNumOfFood();
		assertEquals(321 ,result);
	}
	
	// Test Maze.generateMaze() when FileNotFoundException occurs
	// In this case, console should output "Could not read the file %s further"
	@Test
	void test19() {
		boolean result = Maze.initMaze("src/Resource/DoesntExist.txt");
		assertFalse(result);
	}
	
	// Test Maze.generateMaze() when there is a dummy line (appended to the end of hardMaze)
	@Test
	void test20(){
		// The MazeTestFile01.txt file contains a dummy line
		// initialize Maze class against this file
		// maze map should be read in normally
		// just the if condition for dummy line will be covered
		boolean result = Maze.initMaze("src/Resource/MazeTestFile/MazeTestFile01.txt");
		assertTrue(result);
	}
	
	// Test Maze.generateMaze() when there is a Power Up in the maze
	@Test
	void test21(){
		// The MazeTestFile02.txt file contains some power up
		// initialize Maze class against this file
		// and the if condition for POWER UP will be covered
		boolean result = Maze.initMaze("src/Resource/MazeTestFile/MazeTestFile02.txt");
		assertTrue(result);
	}
	
	// Test Maze.generateMaze() when there is a <1 ghost house
	@Test
	void test22() {
		// Normally a maze map should contain exactly 2 ghost houses 
		// The MazeTestFile03.txt file contains only one ghost house
		// initialize Maze class against this file
		// and the if condition for <1 ghost house will be covered
		boolean result = Maze.initMaze("src/Resource/MazeTestFile/MazeTestFile03.txt");
		assertFalse(result);
	}
	
	// Test Maze.generateMaze() when there is a >2 ghost house
	@Test
	void test23() {
		// Normally a maze map should contain exactly 2 ghost houses 
		// The MazeTestFile03.txt file contains 4 ghost houses
		// initialize Maze class against this file
		// and the if condition for >2 ghost house will be covered
		boolean result = Maze.initMaze("src/Resource/MazeTestFile/MazeTestFile04.txt");
		assertTrue(result);
	}
}
