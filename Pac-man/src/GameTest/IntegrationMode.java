package GameTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import Game.A_star;
import Game.Chase;
import Game.ChaseAggressive;
import Game.ChaseAmbush;
import Game.ChasePatrol;
import Game.ChaseRandom;
import Game.Frightened;
import Game.FrightenedWandering;
import Game.GhostAlgorithm;
import Game.Mode;
import Game.RandomSearch;
import Game.Scatter;
import Game.Greedy_Search;
import Game.Maze;
import Game.ScatterTopRightCorner;
import Game.ScatterTopLeftCorner;
import Game.ScatterBottomLeftCorner;
import Game.ScatterBottomRightCorner;

public class IntegrationMode {
	
	// test Mode itself ---------------------------------------------------------------------------
	
//	@Test
//	public void TestModeInit01() {
//		boolean res = Mode.initModes("false_file_path.txt");
//		assertEquals(res, false);
//	}
	
	@Test
	public void TestModeInit02() {
		boolean res = Mode.initModes("src/Resource/easyAlgorithmData.txt");
		assertEquals(res, true);
		
		ArrayList<GhostAlgorithm> algorithms = Scatter.getInstance().getAlgorithms();
		
		assertEquals(algorithms.get(0) instanceof ScatterTopRightCorner, true);
		assertEquals(algorithms.get(1) instanceof ScatterTopLeftCorner, true);
		assertEquals(algorithms.get(2) instanceof ScatterBottomRightCorner, true);
		assertEquals(algorithms.get(3) instanceof ScatterBottomLeftCorner, true);
		
		assertEquals(Scatter.getInstance().getSearchAlgorithm() instanceof Greedy_Search, true);
		
		algorithms = Chase.getInstance().getAlgorithms();
		
		assertEquals(algorithms.get(0) instanceof ChaseAggressive, true);
		assertEquals(algorithms.get(1) instanceof ChaseAmbush, true);
		assertEquals(algorithms.get(2) instanceof ChasePatrol, true);
		assertEquals(algorithms.get(3) instanceof ChaseRandom, true);
		
		assertEquals(Chase.getInstance().getSearchAlgorithm() instanceof Greedy_Search, true);
		
		algorithms = Frightened.getInstance().getAlgorithms();
		
		assertEquals(algorithms.get(0) instanceof FrightenedWandering, true);
		assertEquals(algorithms.get(1) instanceof FrightenedWandering, true);
		assertEquals(algorithms.get(2) instanceof FrightenedWandering, true);
		assertEquals(algorithms.get(3) instanceof FrightenedWandering, true);
		
		assertEquals(Frightened.getInstance().getSearchAlgorithm() instanceof RandomSearch, true);
		
		Scatter scatter = Scatter.getInstance();
		scatter.destroyAlgorithms();
		assertEquals(scatter.getAlgorithms(), null);
		scatter.deleteMode();
		assertEquals(Scatter.getInstance(), null);
		
		Frightened frightened = Frightened.getInstance();
		frightened.destroyAlgorithms();
		assertEquals(frightened.getAlgorithms(), null);
		frightened.deleteMode();
		assertEquals(Frightened.getInstance(), null);
		
		Chase chase = Chase.getInstance();
		chase.destroyAlgorithms();
		assertEquals(chase.getAlgorithms(), null);
		chase.deleteMode();
		assertEquals(Chase.getInstance(), null);
		
	}
	
	private void cleanUp() {
		Scatter.getInstance().destroyAlgorithms();
		Scatter.getInstance().deleteMode();
		
		Frightened.getInstance().destroyAlgorithms();
		Frightened.getInstance().deleteMode();
		
		Chase.getInstance().destroyAlgorithms();
		Chase.getInstance().deleteMode();
	}
	
	@Test
	public void TestModeInit03() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		boolean res = Mode.initModes("src/Resource/hardAlgorithmData.txt");		// Bug that where error reporting does not work correctly
		assertEquals(res, true);
		
		assertEquals(Chase.getInstance().getSearchAlgorithm() instanceof A_star, true);	// Potentially a bug where it is still GreedySearch
		assertEquals(Scatter.getInstance().getSearchAlgorithm() instanceof A_star, true);
		
		cleanUp();
	}
	
	// test Scatter ---------------------------------------------------------------------------
	
	@Test
	public void TestScatter01() {
		Scatter.setScatter(null);
		Scatter scatter = Scatter.getInstance();
		
		scatter.addAlgorithm("ScatterBottomLeftCorner");
		assertEquals(scatter.getAlgorithms().get(0) instanceof ScatterBottomLeftCorner, true);
		
		scatter.destroyAlgorithms();
		scatter.deleteMode(); 
	}
}















