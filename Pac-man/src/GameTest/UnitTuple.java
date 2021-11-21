package GameTest;

import org.junit.Test;

import Game.Direction;
import Game.Tuple;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class UnitTuple {

	//testing int getFirst()
	@Test
	public void test_01() {
		Tuple t = new Tuple(3, 4);
		int argument_1 = t.getFirst();
		assertEquals(argument_1, 3);
		
	}
	
	//testing int getSecond()
	@Test 
	public void test_02() {
		Tuple t = new Tuple(3,4);
		int argument_2 = t.getSecond();
		assertEquals(argument_2, 4);
	}
	
	//testing int tuppleToInt(Tuple, int)
	@Test
	public void test_03() {
		Tuple t = new Tuple(3,4);
		int result = Tuple.tuppleToInt(t, 6);
		assertEquals(result, 27);
	}
	
	//testing boolean Equals(Tuple)
	@Test
	public void test_04() {
		Tuple t1 = new Tuple(4,5);
		Tuple t2 = new Tuple(4,3);
		boolean result = t1.equals(t2);
		assertEquals(result, false);
		
	}
	
	//testing boolean Equals(Tuple)
		@Test
		public void test_05() {
			Tuple t1 = new Tuple(4,5);
			Tuple t2 = new Tuple(4,5);
			boolean result = t1.equals(t2);
			assertEquals(result, true);
			
		}
		
		//testing boolean Equals(Tuple)
		@Test
		public void test_06() {
			Tuple t1 = new Tuple(3,5);
			Tuple t2 = new Tuple(4,3);
			boolean result = t1.equals(t2);
			assertEquals(result, false);
			
		}
	
		
	//testing Tuple difference(Tuple)
	@Test
	public void test_07() {
		Tuple t1 = new Tuple(20, -5);
		Tuple t2  = new Tuple(-9, 7);
		Tuple t= t1.difference(t2);
		Tuple result = new Tuple(29, -12);
		assertEquals(result, t);
	}
	
	//Testing Tuple sum(Tuple)
	
	@Test
	public void test_08() {
		Tuple t1 = new Tuple(5,6);
		Tuple t2 = new Tuple(7, 8);
		Tuple result = t1.sum(t2);
		assertEquals(result, new Tuple(12, 14));
	}
	
	//testing Tuple getXTilesAHead(int, Direction, Tuple) {
	@Test
	public void test_09() {
		Tuple currentPos = new Tuple(23, 25);
		Direction currentDirection = Direction.UP;
		int x= 8;
		Tuple result = Tuple.getXTilesAHead(x, currentDirection, currentPos);
		assertEquals(result, new Tuple(23, 17));
	}
	
	//testing Tuple getXTilesAHead(int, Direction, Tuple) {
	@Test
	public void test_10() {
		Tuple currentPos = new Tuple(23, 25);
		Direction currentDirection = Direction.DOWN;
		int x= 8;
		Tuple result = Tuple.getXTilesAHead(x, currentDirection, currentPos);
		assertEquals(result, new Tuple(23, 33));
	}
	
	//testing Tuple getXTilesAHead(int, Direction, Tuple) {
	@Test
	public void test_11() {
		Tuple currentPos = new Tuple(23, 25);
		Direction currentDirection = Direction.LEFT;
		int x= 8;
		Tuple result = Tuple.getXTilesAHead(x, currentDirection, currentPos);
		assertEquals(result, new Tuple(15, 25));
	}
	
	//testing Tuple getXTilesAHead(int, Direction, Tuple) {
	@Test
	public void test_12() {
		Tuple currentPos = new Tuple(23, 25);
		Direction currentDirection = Direction.RIGHT;
		int x= 8;
		Tuple result = Tuple.getXTilesAHead(x, currentDirection, currentPos);
		assertEquals(result, new Tuple(31, 25));
	}
	
	//testing double distance(Tuple, Tuple)
	@Test
	public void test_13() {
		Tuple t1 = new Tuple(23, -47);
		Tuple t2 = new Tuple (-12, 26);
		double result = Tuple.distance(t1, t2);
		System.out.println(result);
		assertEquals( 0.0000000, 80.9567785, result);
	}
	
	//testing boolean change(int, int)
	@Test
	public void test_14() {
		Tuple t = new Tuple(0, 9);
		t.changeTuple(-87, 100);
		boolean result = t.equals(new Tuple(-87, 100));
		assertEquals(result, true);
	}
	
	//Testing public boolean tupleBelong(ArrayList<Tuple> tuples)
	//loop coverage: 0
		@Test
		public void test_15() {
			ArrayList<Tuple> tuples = new ArrayList<Tuple>();
			Tuple t= new Tuple(1,4);
			boolean result = t.tupleBelong(tuples);
			assertEquals(result, false);
		}
	
	//Testing public boolean tupleBelong(ArrayList<Tuple> tuples)
	//loop coverage: 1
	@Test
	public void test_16() {
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		tuples.add(new Tuple(1, 4));
		tuples.add(new Tuple(2, 8));
		tuples.add(new Tuple(3, -9));
		Tuple t= new Tuple(1,4);
		boolean result = t.tupleBelong(tuples);
		assertEquals(result, true);
	}
	
//Testing public boolean tupleBelong(ArrayList<Tuple> tuples)
	//loop coverage: >1
	@Test
	public void test_17() {
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		tuples.add(new Tuple(1, 4));
		tuples.add(new Tuple(2, 8));
		tuples.add(new Tuple(3, -9));
		Tuple t= new Tuple(2,8);
		boolean result = t.tupleBelong(tuples);
		assertEquals(result, true);
	}
	
	//testing public boolean toClip(Tuple min, Tuple max)
	@Test
	public void test_18() {
		Tuple t = new Tuple (15, 20);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		boolean result = t.toClip(min, max);
		assertEquals(result, false);
	}
	
	//testing public boolean toClip(Tuple min, Tuple max)
	@Test
	public void test_19() {
		Tuple t = new Tuple (14, 20);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		boolean result = t.toClip(min, max);
		assertEquals(result, true);
	}

	//testing public boolean toClip(Tuple min, Tuple max)
	@Test
	public void test_20() {
		Tuple t = new Tuple (21, 20);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		boolean result = t.toClip(min, max);
		assertEquals(result, true);
	}
		
	
	//testing public boolean toClip(Tuple min, Tuple max)
	@Test
	public void test_21() {
		Tuple t = new Tuple (17, 13);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		boolean result = t.toClip(min, max);
		assertEquals(result, true);
	}
		
	//testing public boolean toClip(Tuple min, Tuple max)
	@Test
	public void test_22() {
		Tuple t = new Tuple (17, 28);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		boolean result = t.toClip(min, max);
		assertEquals(result, true);
	}
	
	//testing void clip(Tuple, Tuple)
	@Test
	public void test_23() {
		Tuple t = new Tuple (15, 20);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		t.clip(min, max);
		boolean result = t.equals(new Tuple(15, 20));
		assertEquals(result, true);
	}
	
	//testing void clip(Tuple, Tuple)
	@Test
	public void test_24() {
		Tuple t = new Tuple (14, 20);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		t.clip(min, max);
		boolean result = t.equals(new Tuple(15, 20));
		assertEquals(result, true);
	}
	

	///testing void clip(Tuple, Tuple)
	@Test
	public void test_25() {
		Tuple t = new Tuple (21, 20);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		t.clip(min, max);
		boolean result = t.equals(new Tuple(20, 20));
		assertEquals(result, true);
	}
		
	
	////testing void clip(Tuple, Tuple)
	@Test
	public void test_26() {
		Tuple t = new Tuple (17, 13);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		t.clip(min, max);
		boolean result = t.equals(new Tuple(17, 15));
		assertEquals(result, true);
	}
		
	////testing void clip(Tuple, Tuple)
	@Test
	public void test_27() {
		Tuple t = new Tuple (17, 28);
		Tuple min = new Tuple (15, 15);
		Tuple max = new Tuple (20,20);
		t.clip(min, max);
		boolean result = t.equals(new Tuple(17, 20));
		assertEquals(result, true);
	}
	
}
