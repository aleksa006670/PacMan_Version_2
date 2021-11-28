package GameTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Gate;
import Game.Tuple;

class GateTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		Gate.deleteGate();
	}

	// Test Gate.setGate()
	@Test
	void test01() {
		
		boolean result = Gate.setGate(14, 21);  // the gate is not set before
							   //  the program should not print anything
		assertTrue(result);
	}
	
	@Test 
	void test02() {
		Gate.setGate(14, 21);
		boolean result = Gate.setGate(10, 10);  // the program should output "The gate has been initialized!"
		assertFalse(result);
	}

	// Test Gate.getInstance
	@Test
	void test03() {
		Gate.setGate(14, 21);
		Gate gate = Gate.getInstance();
		assertEquals('G', gate.getSymbol());
		assertEquals(true, gate.getTuple().equals(new Tuple(14, 21)));
	}
	
	// Test Gate.deleteGate()
	@Test 
	void test04() {
		Gate.setGate(14, 21);
		Gate result = Gate.deleteGate();
		assertNull(result);
	}
}
