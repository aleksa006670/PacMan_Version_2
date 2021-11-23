package GameTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import Game.A_star;
import Game.Direction;
import Game.Maze;
import Game.Tuple;
@TestMethodOrder(OrderAnnotation.class)
public class IntegrationA_star {
	private static ArrayList<Direction> possibleDirections=new ArrayList<Direction>();
@BeforeAll
public static void execute() {
	//A* does not directly use this method;thus I do not need to test it
	Maze.initMaze("src/Resource/easyMaze.txt");
	possibleDirections.add(Direction.UP);
	possibleDirections.add(Direction.DOWN);
	possibleDirections.add(Direction.LEFT);
	possibleDirections.add(Direction.RIGHT);
}

@Test
@Order(1)
public void test0a() {
	boolean result = A_star.setA_star();
	assertEquals(result, true);
}

@Test
@Order(2)
public void test0b() {
	boolean result = A_star.setA_star();
	result = A_star.setA_star();
	assertEquals(result, false);
}
@Test
@Order(3)
public void test0c() {
	boolean result = A_star.getInstance().destroySearch();
	assertEquals(result, true);
}


@Test
@Order(4)
//testing ArrayList<Tuple> getPath(Tuple, Tuple, ArrayList<Direction>)
public void test1() {
	A_star.setA_star();
	Tuple start = new Tuple (1,1);
	Tuple end = new Tuple(1,1);
	ArrayList<Tuple> path = A_star.getInstance().getPath(start, end, possibleDirections);
	ArrayList<Tuple> solution = new ArrayList<Tuple>();
	solution.add(new Tuple(1,1));
	assertEquals(path, solution);
}

//testing ArrayList<Tuple> getPath(Tuple, Tuple, ArrayList<Direction>)
@Test
@Order(5)
public void test2() {
	A_star.setA_star();
	Tuple start = new Tuple(1,2);
	Tuple end = new Tuple(1,1);
	ArrayList<Tuple> path = A_star.getInstance().getPath(start, end, possibleDirections);
	ArrayList<Tuple> solution = new ArrayList<Tuple>();
	solution.add(start);
	solution.add(end);
	assertEquals(solution, path);
}


//testing ArrayList<Tuple> getPath(Tuple, Tuple, ArrayList<Direction>)
@Test
@Order(6)
public void test3() {
	A_star.setA_star();
	Tuple start = new Tuple(1,1);
	Tuple end = new Tuple(26, 30);
	ArrayList<Tuple> path = A_star.getInstance().getPath(start, end, possibleDirections);
	ArrayList<Tuple> solution = new ArrayList<Tuple>();
	solution.add(start);
	solution.add(new Tuple(2 ,1));
	solution.add(new Tuple(3,1));
	
	solution.add(new Tuple(4,1));
	
	solution.add(new Tuple(5,1));
	
	solution.add(new Tuple(6,1));
	
	solution.add(new Tuple(7,1));
	
	solution.add(new Tuple(8, 1));
	
	solution.add(new Tuple(9,1));
	
	solution.add(new Tuple(10, 1));
	
	solution.add(new Tuple(11, 1));
	
	solution.add(new Tuple(12, 1));
	
	solution.add(new Tuple(13, 1));
	
	solution.add(new Tuple(14, 1));
	
	solution.add(new Tuple(15, 1));
	
	solution.add(new Tuple(16, 1));
	
	solution.add(new Tuple(16, 2));
	
	solution.add(new Tuple(16, 3));
	
	solution.add(new Tuple(16, 4));
	
	solution.add(new Tuple(17, 4));
	
	solution.add(new Tuple(18, 4));
	
	solution.add(new Tuple(19, 4));
	
	solution.add(new Tuple(19, 5));
	
	solution.add(new Tuple(19, 6));
	
	solution.add(new Tuple(19, 7));
	
	solution.add(new Tuple(19, 8));
	
	solution.add(new Tuple(19, 9));
	
	solution.add(new Tuple(19, 10));
	
	solution.add(new Tuple(19, 11));
	
	solution.add(new Tuple(19, 12));
	
	solution.add(new Tuple(19, 13));
	
	solution.add(new Tuple(19, 14));
	
	solution.add(new Tuple(20, 14));
	
	solution.add(new Tuple(21, 14));
	
	solution.add(new Tuple(22, 14));
	
	solution.add(new Tuple(22, 15));
	
	solution.add(new Tuple(22, 16));
	
	solution.add(new Tuple(22, 17));
	
	solution.add(new Tuple(22, 18));
	
	solution.add(new Tuple(22, 19));
	
	solution.add(new Tuple(22, 20));
	
	solution.add(new Tuple(22, 21));
	
	solution.add(new Tuple(23, 21));
	
	solution.add(new Tuple(24, 21));
	
	solution.add(new Tuple(24, 22));
	
	solution.add(new Tuple(24, 23));
	
	solution.add(new Tuple(24, 24));
	
	solution.add(new Tuple(24, 25));
	
	solution.add(new Tuple(24, 26));
	
	solution.add(new Tuple(24, 27));
	
	solution.add(new Tuple(25, 27));
	
	solution.add(new Tuple(26, 27));
	
	solution.add(new Tuple(26, 28));
	
	solution.add(new Tuple(26, 29));
	
	solution.add(end);
	
	assertEquals(solution, path);
}


//testing Tuple BFS(Tuple, ArrayList<Direction>)
@Test
@Order(7)
public void test4() {
		A_star.setA_star();
		Tuple t = new Tuple(1,1);
		Tuple result = A_star.getInstance().BFS(t, possibleDirections);
		assertEquals(result, new Tuple(1,1));
}

//testing Tuple BFS(Tuple, ArrayList<Direction>)
@Test
@Order(8)
public void test5() {
	A_star.setA_star();
	Tuple t = new Tuple(14,19);
	Tuple result = A_star.getInstance().BFS(t, possibleDirections);
	assertEquals(result, new Tuple(14, 18));
}


//testing Tuple BFS(Tuple, ArrayList<Direction>)

@Test
@Order(9)
public void test6() {
	A_star.setA_star();
	Tuple t= new Tuple(28, 31);
	Tuple result = A_star.getInstance().BFS(t, possibleDirections);
	assertEquals(result, new Tuple(26, 30));
}
 
//testing Tuple BFS(Tuple, ArrayList<Direction>)
@Test
@Order(10)
public void test7() {
	A_star.setA_star();
	Tuple t = new Tuple(0,0);
	Tuple result = A_star.getInstance().BFS(t, possibleDirections);
	assertEquals(result, new Tuple(1,1));
	
}



//Tuple findClosestGoal(Tuple t, ArrayList<Direction> possibleDirections)
@Test
@Order(11)
public void test8() {
	A_star.setA_star();
	Tuple t = new Tuple(-1, -1);
	Tuple result = A_star.getInstance().findClosestGoal(t, possibleDirections);
	assertEquals(result, new Tuple(1,1));
}

//Tuple findClosestGoal(Tuple t, ArrayList<Direction> possibleDirections)
@Test
@Order(12)
public void test9() {
	A_star.setA_star();
	Tuple t = new Tuple(33, 40);
	Tuple result = A_star.getInstance().findClosestGoal(t, possibleDirections);
	assertEquals(result, new Tuple(26, 30));
}
//Tuple findClosestGoal(Tuple t, ArrayList<Direction> possibleDirections)
@Test
@Order(13)
public void test10() {
	A_star.setA_star();
	Tuple t= new Tuple (1,1);
	Tuple result = A_star.getInstance().findClosestGoal(t, possibleDirections);
	assertEquals(result, new Tuple(1,1));
}

@Test
@Order(14)
public void test11() {
	A_star.setA_star();
	Tuple t= new Tuple (14, 19);
	Tuple result = A_star.getInstance().findClosestGoal(t, possibleDirections);
	assertEquals(result, new Tuple(14,18));
}


//Direction getNextDirection(ArrayList<Tuple>, ArrayList<Direction>)
@Test
@Order(15)
public void test12() {
	A_star.setA_star();
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	points.add(new Tuple(1,1));
	points.add(new Tuple(33, 40));
	Direction next = A_star.getInstance().getNextDirection(points, possibleDirections);
	assertEquals(next, Direction.RIGHT);
	
}

//Direction getNextDirection(ArrayList<Tuple>, ArrayList<Direction>)
@Test
@Order(16)
public void test13() {
	A_star.setA_star();
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	points.add(new Tuple(1,2));
	points.add(new Tuple(-1, -1));
	Direction next = A_star.getInstance().getNextDirection(points, possibleDirections);
	assertEquals(next, Direction.UP);
}

//Direction getNextDirection(ArrayList<Tuple>, ArrayList<Direction>)
@Test
@Order(17)
public void test14() {
	A_star.setA_star();
	ArrayList<Tuple> points = new ArrayList<Tuple>();
	points.add(new Tuple(1,1));
	points.add(new Tuple(1,1));
	Direction next = A_star.getInstance().getNextDirection(points, possibleDirections);
	assertEquals(next, null);
}


}