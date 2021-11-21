package GameTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Node;
import Game.Tuple;

public class IntegrationNode {
	private Node n;
	private Node k;
	//testing Tuple getID()
	@BeforeEach
	public void setUp() {
		Tuple id = new Tuple (20, 40);
		double value = 1.5;
		n= new Node(id ,null, value);
		Tuple id2 = new Tuple (30, 50);
		double value2= 4.781;
		k = new Node(id2, n, value2);
	}
	
	
	
	@Test
	//testing Tuple getID()
	public void test1() {
		Tuple result = n.getID();
		assertEquals(result, new Tuple(20, 40));
	}
	
	//testing double getValue()
	@Test
	public void test2() {
		double result = n.getValue();
		assertEquals(result, 1.5, 0.0);
	}
	
	//testing boolean equals()
	@Test
	public void test3() {
		Node t = new Node(new Tuple(30, 50), n, 34.789);
		boolean result = t.equals(k);
		assertEquals(result, true);
	}
	
	//testing boolean equals()
	@Test
	public void test4() {
		Node t = new Node(new Tuple(30, 80),null, 34.789);
		boolean result = t.equals(n);
		assertEquals(result, false);
	}
	
	//testing Node getParent()
	@Test
	public void test5a() {
		Node parent = k.getParent();
		assertEquals(parent, n);
	}
	
	//testing Node getParent() null version
	@Test
	public void test5b() {
		Node parent = n.getParent();
		assertEquals(parent, null);
	}
	
	//testing void setParent()
	@Test
	public void test6a() {
		n.setParent(k);
		assertEquals(n.getParent(), k);
	}
	
	//testing setParent() null version
	@Test
	public void test6b() {
		k.setParent(null);
		assertEquals(k.getParent(), null);
	}
	//testing void setValue()
	@Test
	public void test7() {
		n.setValue(98.765);
		assertEquals(n.getValue(), 98.765, 0.000);
	}
	
	//testing int compare()
	@Test
	public void test8() {
		int result = n.compareTo(k);
		assertEquals(result,-1);
	}
	
	//testing int compare()
	@Test
	public void test9() {
		int result = k.compareTo(n);
		assertEquals(result, 1);
	}
	
	//testing int compare()
	@Test
	public void test10() {
		Node t = new Node(new Tuple(30, 80),null, 4.781);
		int result = k.compareTo(t);
		assertEquals(result, 0);
	}
}
