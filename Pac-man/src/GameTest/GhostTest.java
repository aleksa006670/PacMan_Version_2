package GameTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Game.A_star;
import Game.Direction;
import Game.Game;
import Game.Ghost;
import Game.PacMan;
import Game.SearchAlgorithm;
import Game.Tuple;

class GhostTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Ghost.destroyGhosts();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	// Test Ghost.initGhosts() w/o exception
	@Test
	void test01() {
		String initGhostData = "src/Resource/ghostTestInitData.txt";
		String [] contents = new String[] {"4", "Red", "14", "12", "Pink", "13", "14", "Blue", "14", "14", "Orange", "15", "14"};
		WriteFile.DoWriting(initGhostData, contents);
		
		boolean result = Ghost.initGhosts(initGhostData);
		
		WriteFile.DeleteFile(initGhostData);
		
		assertTrue(result);
	}
	
	// Test Ghost.initGhosts() w/ FileNotFoundException
	@Test
	void test02() {
		boolean result = Ghost.initGhosts("DoesntExist.txt"); // FileNotFoundException, should return false
		
		assertFalse(result);
	}
	
	// Test Ghost.initGhosts() w/ NoSuchElementException : when number of ghosts adn ghost data is inconsistent
	@Test
	void test03() {
		String initGhostData = "src/Resource/ghostTestInitData.txt";
		String [] contents = new String[] {"4", "Red", "14", "12", "Pink", "13", "14", "Blue", "14", "14"};
		WriteFile.DoWriting(initGhostData, contents);
		
		boolean result = Ghost.initGhosts(initGhostData); // declared 4 ghosts
														  // but only 3 ghost data provided
		
		WriteFile.DeleteFile(initGhostData);
		assertFalse(result);
	}
	
	// Test Ghost.destroyGhosts()
	@Test
	void test04() {
		String initGhostData = "src/Resource/ghostTestInitData.txt";
		String [] contents = new String[] {"4", "Red", "14", "12", "Pink", "13", "14", "Blue", "14", "14", "Orange", "15", "14"};
		WriteFile.DoWriting(initGhostData, contents);
		
		Ghost.initGhosts(initGhostData); 
														  		
		WriteFile.DeleteFile(initGhostData);
		Ghost.destroyGhosts();
		ArrayList<Ghost> result = Ghost.getGhosts();
		assertEquals(0, result.size());
	}
	
	// Test Ghost.getGhosts()
	@Test
	void test05() {
		String initGhostData = "src/Resource/ghostTestInitData.txt";
		String [] contents = new String[] {"4", "Red", "14", "12", "Pink", "13", "14", "Blue", "14", "14", "Orange", "15", "14"};
		WriteFile.DoWriting(initGhostData, contents);
		
		Ghost.initGhosts(initGhostData); 
														  		
		WriteFile.DeleteFile(initGhostData);
		ArrayList<Ghost> result = Ghost.getGhosts();
		assertEquals(4, result.size());
	}
	
	// Test Ghost.getGhost()
		@Test
		void test06() {
			String initGhostData = "src/Resource/ghostTestInitData.txt";
			String [] contents = new String[] {"4", "Red", "14", "12", "Pink", "13", "14", "Blue", "14", "14", "Orange", "15", "14"};
			WriteFile.DoWriting(initGhostData, contents);
			
			Ghost.initGhosts(initGhostData); 
															  		
			WriteFile.DeleteFile(initGhostData);
			Ghost result = Ghost.getGhost(3);
			assertEquals('O', result.getSymbol());
		}

		
	// Test Ghost.getGhostByName()
			@Test
			void test07() {
				String initGhostData = "src/Resource/ghostTestInitData.txt";
				String [] contents = new String[] {"4", "Red", "14", "12", "Pink", "13", "14", "Blue", "14", "14", "Orange", "15", "14"};
				WriteFile.DoWriting(initGhostData, contents);
				
				Ghost.initGhosts(initGhostData); 
																  		
				WriteFile.DeleteFile(initGhostData);
				Ghost result = Ghost.getGhostByName('O');
				assertEquals('O', result.getSymbol());
			}

			// Test Ghost.getGhostByName() when the input name is not found
			@Test
			void test08() {
				String initGhostData = "src/Resource/ghostTestInitData.txt";
				String [] contents = new String[] {"4", "Red", "14", "12", "Pink", "13", "14", "Blue", "14", "14", "Orange", "15", "14"};
				WriteFile.DoWriting(initGhostData, contents);
				
				Ghost.initGhosts(initGhostData); 
																  		
				WriteFile.DeleteFile(initGhostData);
				Ghost result = Ghost.getGhostByName('S'); // There is no ghost whose name begins with 'S'
				assertNull(result);
			}
			
			// Test Ghost.reverseDirectionsOfAllGhosts() when the static ghosts list is NOT null
			@Test
			void test09() {
				String initGhostData = "src/Resource/ghostTestInitData.txt";
				String [] contents = new String[] {"4", "Red", "14", "12", "Pink", "13", "14", "Blue", "14", "14", "Orange", "15", "14"};
				WriteFile.DoWriting(initGhostData, contents);
				
				Ghost.initGhosts(initGhostData); 
																  		
				WriteFile.DeleteFile(initGhostData);
				boolean result = Ghost.reverseDirectionsOfAllGhosts();
				assertTrue(result);
				ArrayList<Ghost> allGhosts = Ghost.getGhosts();
				for (Ghost g : allGhosts) {
					assertEquals(Direction.DOWN, g.getDirection());
				}
			}
			
			// Test Ghost.reverseDirectionsOfAllGhosts() when the static ghosts list is null
			@Test
			void test14() {
				// do not initialize ghosts so we have an empty ghosts list
				boolean result = Ghost.reverseDirectionsOfAllGhosts();
				assertFalse(result);
			}
			
			// Test Ghost.resetGhosts()
			@Test
			void test10() {
				String initGhostData = "src/Resource/ghostTestInitData.txt";
				String [] contents = new String[] {"4", "Red", "14", "12", "Pink", "13", "14", "Blue", "14", "14", "Orange", "15", "14"};
				WriteFile.DoWriting(initGhostData, contents);
				
				Ghost.initGhosts(initGhostData); 
																  		
				WriteFile.DeleteFile(initGhostData);
				boolean result = Ghost.resetGhosts();
				assertTrue(result);
			}
			
			// Test Ghost.moveToTarget() with sa=A_Star, doReverse=true and the game is in Hard mode
			@Test
			void test11() {
				Game game = Game.getInstance();
				game.gameInit("Hard", "Chase");
				PacMan pacman = PacMan.getInstance();
				Ghost red = Ghost.getGhostByName('R');
				
				pacman.setPosition(new Tuple(9, 16));
				pacman.setDirection(Direction.UP);
				red.setPosition(new Tuple(9, 13));
				red.setDirection(Direction.UP);
				
				red.moveToTarget(A_star.getInstance(), new Tuple(9, 16), true);
				assertEquals(Direction.DOWN, red.getDirection());
			}
			
			// Test Ghost.moveToTarget() with sa=A_Star, doReverse=false and the game is in Hard mode
//			@Test
//			void test12() {
//				Game game = Game.getInstance();
//				game.gameInit("Hard", "Chase");
//				PacMan pacman = PacMan.getInstance();
//				Ghost red = Ghost.getGhostByName('R');
//				
//				pacman.setPosition(new Tuple(9, 16));
//				pacman.setDirection(Direction.UP);
//				red.setPosition(new Tuple(9, 13));
//				red.setDirection(Direction.UP);
//				A_star aStarIns = A_star.getInstance();
//				assertNotNull(aStarIns);
//				red.moveToTarget(aStarIns, new Tuple(9, 16), false);
//				assertEquals(Direction.UP, red.getDirection());
//			}
//			
			// Test Ghost.moveToTarget() with sa=A_Star, doReverse=false and sa.getNextDirection() returns null
			@Test
			void test13() {
				
				// implement a test stub of search algorithm
				// it only fakes the getNextDirection() method so that it always return null
				class stubSearchAlgorithm implements SearchAlgorithm{
					public SearchAlgorithm destroySearch() {
						return null;
					}
					public Direction getNextDirection(ArrayList<Tuple> points, ArrayList<Direction> accebileDirections) {
						return null;
					}
				}
				
				Game game = Game.getInstance();
				game.gameInit("Hard", "Chase");
				PacMan pacman = PacMan.getInstance();
				Ghost red = Ghost.getGhostByName('R');
				
				pacman.setPosition(new Tuple(12, 13));
				pacman.setDirection(Direction.UP);
				red.setPosition(new Tuple(9, 13));
				red.setDirection(Direction.UP);
				
				// stubSearchAlgorithm.getNextDirection() returns null, the Red ghost shouldn't move
				red.moveToTarget(new stubSearchAlgorithm(), new Tuple(28, 12), false);
				assertEquals(Direction.UP, red.getDirection());
				assertTrue(red.getTuple().equals(new Tuple(9, 13)));
			}
}
