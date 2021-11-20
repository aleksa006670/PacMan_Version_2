package GameTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Gate;
import Game.Tuple;

class GateTest {

	/***************************************
	 * Use these to redirect I/O stream    *
	 ***************************************/
	PrintStream ps;
	ByteArrayOutputStream baos;
	
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
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
		
		Gate.setGate(14, 21);  // the gate is not set before
							   //  the program should not print anything
		
		assertEquals("", baos.toString());
	}
	
	@Test 
	void test02() {
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
		Gate.setGate(14, 21);
		Gate.setGate(10, 10);  // the program should output "The gate has been initialized!"
		assertEquals("The gate has been initialized!\r\n", baos.toString());
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
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
		Gate.setGate(14, 21);
		Gate.deleteGate();
		Gate.setGate(10, 10);  // The program should NOT output anything
		assertEquals("", baos.toString());
	}
}
