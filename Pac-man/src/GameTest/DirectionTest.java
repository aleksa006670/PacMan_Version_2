package GameTest;

import Game.Direction;
import Game.Tuple;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirectionTest {

	
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	// Test the Direction.opposite()
	@Test
	void test01() {
		Direction up = Direction.UP;
		Direction opp = up.opposite();
		assertEquals(Direction.DOWN, opp);
	}
	
	@Test
	void test02() {
		Direction left = Direction.LEFT;
		Direction opp = left.opposite();
		assertEquals(Direction.RIGHT, opp);
	}
	
	@Test
	void test03() {
		Direction down = Direction.DOWN;
		Direction opp = down.opposite();
		assertEquals(Direction.UP, opp);
	}
	
	@Test
	void test04() {
		Direction right = Direction.RIGHT;
		Direction opp = right.opposite();
		assertEquals(Direction.LEFT, opp);
	}
	
	//Test Direction.random()
	@Test
	void test05() {
		assertEquals(true, Direction.random() instanceof Direction);
	}
	
	// Test Direction.DirectionToTuple()
	@Test
	void test06() {
		assertEquals(true, Direction.UP.DirectionToTuple().equals(new Tuple(0, -1)));
	}
	
	@Test
	void test07() {
		assertEquals(true, Direction.DOWN.DirectionToTuple().equals(new Tuple(0, 1)));
	}
	
	@Test
	void test08() {
		assertEquals(true, Direction.LEFT.DirectionToTuple().equals(new Tuple(-1, 0)));
	}
	
	@Test
	void test09() {
		assertEquals(true, Direction.RIGHT.DirectionToTuple().equals(new Tuple(1, 0)));
	}
	
	// Test Direction.tupleToDirection()
	@Test
	void test10() {
		assertEquals(Direction.UP, Direction.tupleToDirection(new Tuple(0, -1)));
	}
	
	@Test
	void test11() {
		assertEquals(Direction.DOWN, Direction.tupleToDirection(new Tuple(0, 1)));
	}
	
	@Test
	void test12() {
		assertEquals(Direction.LEFT, Direction.tupleToDirection(new Tuple(-1,0)));
	}
	@Test 
	void test13() {
		assertEquals(Direction.RIGHT, Direction.tupleToDirection(new Tuple(1,0)));
	}

}
