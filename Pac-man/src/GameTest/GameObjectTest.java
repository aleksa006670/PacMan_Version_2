package GameTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.GameObject;
import Game.Wall;
import Game.Tile;
import Game.Food;
import Game.PowerUp;

import Game.Tuple;


class GameObjectTest {
	private GameObject go;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	// Test GameObject.getSymbol and Wall
	@Test
	void test01() {
		char result;
		go = new Wall(10, 10); // new Wall
		result = go.getSymbol();
		assertEquals('W', result);
	}
	
	// Test GameObject.getSymbol and Tile
	@Test
	void test02() {
		char result;
		go = new Tile(10, 10); // new Tile
		result = go.getSymbol();
		assertEquals(' ', result);
	}

	
	// Test GameObject.getSymbol and Food
	@Test
	void test03() {
		char result;
		go = new Food(10, 10); // new Food
		result = go.getSymbol();
		assertEquals('F', result);
	}
	
	// Test GameObject.getSymbol and Power Up
	@Test
	void test04() {
		char result;
		go = new PowerUp(10, 10); // new Power Up
		result = go.getSymbol();
		assertEquals('U', result);
	}
	
	// Test GameObject.getTuple()
	@Test
	void test05() {
		Tuple result;
		go = new Wall(10, 10);
		result = go.getTuple();
		assertEquals(true, result.equals(new Tuple(10, 10)));
	}
	
	// Test GameObject.setPosition()
	@Test
	void test06() {
		boolean result ;
		Tuple valueToChange = new Tuple(20, 20);
		go = new Wall(10, 10);
		result = go.setPosition(valueToChange);
		assertEquals(true, result);
		assertEquals(true, go.getTuple().equals(valueToChange));
	}
	
	@Test
	void test07() {
		boolean result ;
		Tuple valueToChange = null;
		go = new Wall(10, 10);
		result = go.setPosition(valueToChange);
		assertEquals(false, result);
	}

}
