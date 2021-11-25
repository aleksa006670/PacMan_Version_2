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
public void test1a() {
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
public void test2a() {
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
public void test3a() {
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
@Order(4)
public void test1b() {
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
@Order(5)
public void test2b() {
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
@Order(6)
public void test3b() {
	accessibleDirections.remove(Direction.UP);
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	Tuple start = new Tuple(1,1);
	Tuple end = new Tuple(1,1);
	points.add(start);
	points.add(end);
	Direction result = Greedy_Search.getInstance().getNextDirection(points, accessibleDirections);
	assertEquals(result, null);
}
//Direction getNextDirection(ArrayList<Tuple> , ArrayList<Direction>)
@Test
@Order(7)
public void test4() {
	Greedy_Search result = (Greedy_Search)Greedy_Search.getInstance().destroySearch();
	assertEquals(result,null);
}

}
