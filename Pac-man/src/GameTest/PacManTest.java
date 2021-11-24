package GameTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.Direction;
import Game.PacMan;
import Game.Tuple;
import GameTest.WriteFile;
import GameTest.OSType;

class PacManTest {
	/***************************************
	 * Use these to redirect I/O stream    *
	 ***************************************/
	PrintStream ps;
	ByteArrayOutputStream baos;
	
	@BeforeEach
	void setUp() throws Exception{
		
	}
	
	@AfterEach
	void tearDown() throws Exception{
		PacMan.destroyPacman();
	}

	//Test PacMan.initPacMan()
	@Test
	void test01() {
		String pacManInitFile = "pacManTestInitData.txt";
		String [] contents  = new String[] {"10", "10", "1"};
		
		WriteFile.DoWriting(pacManInitFile, contents);
		
		boolean result = PacMan.initPacMan(pacManInitFile);
		
		WriteFile.DeleteFile(pacManInitFile);
		assertTrue(result);
	}
	
	// Test throwing FileNotFoundException in initPacMan()
	@Test
	void test02() {
		boolean result = PacMan.initPacMan("DoesntExist.txt");
		assertFalse(result);
	}
	
	// Test duplicate calling initPacMan()
	@Test 
	void test03() {
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		System.setOut(ps);
		
		String pacManInitFile = "src/Resource/pacManTestInitData.txt";
		String [] contents  = new String[] {"10", "10", "1"};
		
		WriteFile.DoWriting(pacManInitFile, contents);
		PacMan.initPacMan(pacManInitFile);
		WriteFile.DeleteFile(pacManInitFile);
		
		String pacManInitFile2 = "src/Resource/pacManTestInitData2.txt";
		contents  = new String[] {"21", "14", "2"};
		
		WriteFile.DoWriting(pacManInitFile, contents);
		boolean result = PacMan.initPacMan(pacManInitFile2);
		WriteFile.DeleteFile(pacManInitFile2);
		
		assertTrue(result);
		if (OSType.isWindows())
			assertEquals("PacMan has already been initiliazed\r\n", baos.toString());
		else if (OSType.isMac())
			assertEquals("PacMan has already been initiliazed\r", baos.toString());
		else if (OSType.isLinux())
			assertEquals("PacMan has already been initiliazed\n", baos.toString());
	}
	
	// 	Test PacMan.resetPacMan()
	@Test
	void test04() {
		String pacManInitFile = "src/Resource/pacManTestInitData.txt";
		String [] contents  = new String[] {"10", "10", "1"};
		
		WriteFile.DoWriting(pacManInitFile, contents);
		
		PacMan.initPacMan(pacManInitFile);
		
		PacMan.getInstance().setDirection(Direction.UP);
		PacMan.getInstance().move();
		boolean result = PacMan.getInstance().resetPacMan();
		
		WriteFile.DeleteFile(pacManInitFile);
		
		assertTrue(result);
		assertEquals(true, PacMan.getInstance().getTuple().equals(new Tuple(10, 10)));
		assertEquals(Direction.RIGHT, PacMan.getInstance().getDirection());
		assertEquals(0 ,PacMan.getInstance().getFood());
		assertEquals(1, PacMan.getInstance().getLives());
		assertEquals(0, PacMan.getInstance().getScore());
	}
	
	// Test PacMan.changeScore() and PacMan.getScore()
	@Test
	void test05() {
		String pacManInitFile = "src/Resource/pacManTestInitData.txt";
		String [] contents  = new String[] {"10", "10", "1"};
		
		WriteFile.DoWriting(pacManInitFile, contents);
		
		PacMan.initPacMan(pacManInitFile);
		
		boolean result  = PacMan.getInstance().changeScore(1);
		WriteFile.DeleteFile(pacManInitFile);		
		assertTrue(result);
		assertEquals(1, PacMan.getInstance().getScore());
	}
	
	// Test PacMan.changeLives() and PacMan.getLives()
	@Test
	void test06() {
		String pacManInitFile = "src/Resource/pacManTestInitData.txt";
		String [] contents  = new String[] {"10", "10", "1"};
		
		WriteFile.DoWriting(pacManInitFile, contents);
		
		PacMan.initPacMan(pacManInitFile);
		
		boolean result  = PacMan.getInstance().changeLives(-1);
		WriteFile.DeleteFile(pacManInitFile);
		
		assertTrue(result);
		assertEquals(0, PacMan.getInstance().getLives());
	}
	
	// Test PacMan.incrementFood()
	@Test
	void test07() {
		String pacManInitFile = "src/Resource/pacManTestInitData.txt";
		String [] contents  = new String[] {"10", "10", "1"};
		
		WriteFile.DoWriting(pacManInitFile, contents);
		
		PacMan.initPacMan(pacManInitFile);
		
		boolean result  = PacMan.getInstance().incrementFood();
		WriteFile.DeleteFile(pacManInitFile);

		assertTrue(result);
		assertEquals(1, PacMan.getInstance().getFood());
	}
	
	//Test PacMan.setFood()
	
	@Test
	void test08() {
		String pacManInitFile = "src/Resource/pacManTestInitData.txt";
		String [] contents  = new String[] {"10", "10", "1"};
		
		WriteFile.DoWriting(pacManInitFile, contents);
		
		PacMan.initPacMan(pacManInitFile);
		
		PacMan.getInstance().setFood(3);
		WriteFile.DeleteFile(pacManInitFile);
		assertEquals(3, PacMan.getInstance().getFood());
	}
	
}
