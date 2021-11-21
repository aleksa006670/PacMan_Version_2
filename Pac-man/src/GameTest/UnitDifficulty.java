package GameTest;

import Game.Difficulty;
import Game.Easy;
import Game.Medium;
import Game.Hard;

import org.junit.Test;
import static org.junit.Assert.*;
 

public class UnitDifficulty {
	
	//Test for doReverse (Easy, Medium, Hard)
	@Test
	public void test_doReverse01() {
		Difficulty e = Easy.getInstance() ;
		boolean result = e.doReverse();
		assertEquals(false, result);
	}
	
	@Test
	public void test_doReverse02() {
		Difficulty m = Medium.getInstance() ;
		boolean result = m.doReverse();
		assertEquals(false, result);
	}
	
	@Test
	public void test_doReverse03() {
		Difficulty h = Hard.getInstance() ;
		boolean result = h.doReverse();
		assertEquals(true, result);
	}
	
	//Test for getGhostFile (Easy, Medium, Hard)
	@Test
	public void test_getGhostFile01() {
		Difficulty e = Easy.getInstance() ;
		String result = e.getGhostFile();
		assertEquals("src/Resource/easyGhostData.txt", result);
	}
	
	@Test
	public void test_getGhostFile02() {
		Difficulty m = Medium.getInstance() ;
		String result = m.getGhostFile();
		assertEquals("src/Resource/mediumGhostData.txt", result);
	}
	
	@Test
	public void test_getGhostFile03() {
		Difficulty h = Hard.getInstance() ;
		String result = h.getGhostFile();
		assertEquals("src/Resource/hardGhostData.txt", result);
	}
	
	//Test for getPacman (Easy, Medium, Hard)
	@Test
	public void test_getPacman01() {
		Difficulty e = Easy.getInstance() ;
		String result = e.getPacMan();
		assertEquals("src/Resource/easyPacmanData.txt", result);
	}
	
	@Test
	public void test_getPacman02() {
		Difficulty m = Medium.getInstance() ;
		String result = m.getPacMan();
		assertEquals("src/Resource/mediumPacMandata.txt", result);
	}
	
	@Test
	public void test_getPacman03() {
		Difficulty h = Hard.getInstance() ;
		String result = h.getPacMan();
		assertEquals("src/Resource/hardPacMandata.txt", result);
	}
	
	//Test for getAlgorithm (Easy, Medium, Hard)
	@Test
	public void test_getAlgorithm01() {
		assertEquals("src/Resource/easyAlgorithmData.txt", Easy.getInstance().getAlgorithm());
	}
	
	@Test
	public void test_getAlgorithm02() {
		assertEquals("src/Resource/mediumAlgorithmData.txt", Medium.getInstance().getAlgorithm());
	}
	
	@Test
	public void test_getAlgorithm03() {
		assertEquals("src/Resource/hardAlgorithmData.txt", Hard.getInstance().getAlgorithm());
	}
	
	//Test for getMaze (Easy, Medium, Hard)
	@Test
	public void test_getMaze01() {
		assertEquals("src/Resource/easyMaze.txt", Easy.getInstance().getMaze());
	}
	
	@Test
	public void test_getMaze02() {
		assertEquals("src/Resource/mediumMaze.txt", Medium.getInstance().getMaze());
	}
	
	@Test
	public void test_getMaze03() {
		assertEquals("src/Resource/hardMaze.txt", Hard.getInstance().getMaze());
	}
}
