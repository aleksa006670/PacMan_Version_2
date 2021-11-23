package GameTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import Game.Direction;
import Game.Greedy_Search;
import Game.Maze;
import Game.Tuple;
@TestMethodOrder(OrderAnnotation.class)
public class IntegrationGreedySearch {
	private ArrayList<Direction> accessibleDirections;
	
	@BeforeAll
	public static void execute1() {
		Maze.initMaze("src/Resource/easyMaze.txt");
	}
	
	@BeforeEach
	public void setUp() {
		
		accessibleDirections = new ArrayList<Direction>();
		this.accessibleDirections.add(Direction.UP);
		this.accessibleDirections.add(Direction.LEFT);
		this.accessibleDirections.add(Direction.RIGHT);
		this.accessibleDirections.add(Direction.DOWN);
		
	}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(1)
public void test1() {
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(12,15);
	Tuple end = new Tuple(11,15);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, Direction.UP);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(2)
public void test2() {
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(14,12);
	Tuple end = new Tuple(14,11);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, Direction.LEFT);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)

@Test
@Order(3)
public void test3() {
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(13,18);
	Tuple end = new Tuple(15, 18);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, Direction.RIGHT);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(4)
public void test4() {
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(19,14);
	Tuple end = new Tuple(28, 15);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, Direction.RIGHT);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(5)
public void test5() {
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(9,14);
	Tuple end = new Tuple(14, 18);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, Direction.DOWN);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(6)
public void test6() {
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(1,1);
	Tuple end = new Tuple(1,1);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, null);
}
//////////////////////////////////////////////////////////////////
 
//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(7)
public void test7() {
	accessibleDirections.remove(Direction.UP);
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(12,15);
	Tuple end = new Tuple(11,15);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, Direction.RIGHT);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(8)
public void test8() {
	accessibleDirections.remove(Direction.LEFT);
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(14,12);
	Tuple end = new Tuple(14,11);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, Direction.RIGHT);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(9)
public void test9() {
	accessibleDirections.remove(Direction.LEFT);
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(13,18);
	Tuple end = new Tuple(11, 18);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, Direction.RIGHT);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
//the ghosts is in a dead end
@Test
@Order(10)
public void test10() {
	accessibleDirections.remove(Direction.LEFT);
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(27,21);
	Tuple end = new Tuple(0, 21);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, null);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(11)
public void test11() {
	accessibleDirections.remove(Direction.DOWN);
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(9,14);
	Tuple end = new Tuple(14, 18);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, Direction.UP);
}

//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(12)
public void test12() {
	accessibleDirections.remove(Direction.UP);
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(1,1);
	Tuple end = new Tuple(1,1);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, null);
}

@Test
@Order(13)
public void test13() {
	boolean result = Greedy_Search.getInstance().destroySearch();
	assertEquals(result,true);
}

}
