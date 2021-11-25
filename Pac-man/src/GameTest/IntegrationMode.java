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
import Game.GhostAlgorithm;
import Game.Mode;
import Game.RandomSearch;
import Game.Scatter;
import Game.Greedy_Search;
import Game.Maze;
import Game.ScatterTopRightCorner;
import Game.SearchAlgorithm;
import Game.ScatterTopLeftCorner;
import Game.ScatterBottomLeftCorner;
import Game.ScatterBottomRightCorner;

public class IntegrationMode {

	// test Mode itself
	// ---------------------------------------------------------------------------

	@Test
	public void TestModeInit01() {
		boolean res = Mode.initModes("false_file_path.txt");
		assertEquals(res, false);
	}

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
		assertEquals(algorithms.isEmpty(), true);

		assertEquals(Frightened.getInstance().getSearchAlgorithm() instanceof RandomSearch, true);

		cleanUpScatter();

		cleanUpFrightened();

		cleanUpChase();
	}

	private void cleanUpScatter() {
		Scatter.getInstance().destroySearch();
		Scatter.getInstance().destroyGA();
		Scatter.getInstance().deleteMode();
	}

	private void cleanUpFrightened() {
		Frightened.getInstance().destroySearch();
		Frightened.getInstance().destroyGA();
		Frightened.getInstance().deleteMode();
	}

	private void cleanUpChase() {
		Chase.getInstance().destroySearch();
		Chase.getInstance().destroyGA();
		Chase.getInstance().deleteMode();
	}

	@Test
	public void TestModeInit03() {
		Maze.initMaze("src/Resource/hardMaze.txt");
		boolean res = Mode.initModes("src/Resource/hardAlgorithmData.txt");
		assertEquals(res, true);

		assertEquals(Chase.getInstance().getSearchAlgorithm() instanceof A_star, true);
		assertEquals(Scatter.getInstance().getSearchAlgorithm() instanceof A_star, true);

		cleanUpChase();
		cleanUpScatter();
	}

	// test Scatter
	// ---------------------------------------------------------------------------

	@Test
	public void TestScatterCleanUp() {
		Scatter.setScatter(Greedy_Search.getInstance());
		Scatter scatter = Scatter.getInstance();
		SearchAlgorithm algorithm = scatter.destroySearch();
		assertEquals(algorithm, null);
		ArrayList<GhostAlgorithm> ghostAlgorithms = scatter.destroyGA();
		assertEquals(ghostAlgorithms, null);
		Mode mode = scatter.deleteMode();
		assertEquals(mode, null);
	}

	@Test
	public void TestScatter00() {
		boolean res = Scatter.setScatter(null);
		assertEquals(res, true);

		res = Scatter.setScatter(null);
		assertEquals(res, false);

		cleanUpScatter();
	}

	@Test
	public void TestScatter01() {
		Scatter.setScatter(null);
		Scatter scatter = Scatter.getInstance();

		scatter.addAlgorithm("ScatterBottomLeftCorner");
		assertEquals(scatter.getAlgorithms().get(0) instanceof ScatterBottomLeftCorner, true);

		cleanUpScatter();
	}

	@Test
	public void TestScatter02() {
		Scatter.setScatter(null);
		Scatter scatter = Scatter.getInstance();

		scatter.addAlgorithm("ScatterBottomRightCorner");
		assertEquals(scatter.getAlgorithms().get(0) instanceof ScatterBottomRightCorner, true);

		cleanUpScatter();
	}

	@Test
	public void TestScatter03() {
		Scatter.setScatter(null);
		Scatter scatter = Scatter.getInstance();

		scatter.addAlgorithm("ScatterTopLeftCorner");
		assertEquals(scatter.getAlgorithms().get(0) instanceof ScatterTopLeftCorner, true);

		cleanUpScatter();
	}

	@Test
	public void TestScatter04() {
		Scatter.setScatter(null);
		Scatter scatter = Scatter.getInstance();

		scatter.addAlgorithm("ScatterTopRightCorner");
		assertEquals(scatter.getAlgorithms().get(0) instanceof ScatterTopRightCorner, true);

		cleanUpScatter();
	}

	@Test
	public void TestScatter05() {
		Scatter.setScatter(null);
		Scatter scatter = Scatter.getInstance();

		boolean res = scatter.addAlgorithm("RandomIncorrectCase");
		assertEquals(res, false);

		cleanUpScatter();
	}

	@Test
	public void TestScatter06() {
		Scatter.setScatter(null);
		boolean res = Scatter.getInstance().resolveCollision();
		assertEquals(res, false);

		cleanUpScatter();
	}

	@Test
	public void TestScatter07() {
		Scatter.setScatter(null);
		String res = Scatter.getInstance().getModeName();
		assertEquals(res, "Scatter");

		cleanUpScatter();
	}

	// test Frightened
	// ---------------------------------------------------------------------------

	@Test
	public void TestFrightenedCleanUp() {
		Frightened.setFrightened(Greedy_Search.getInstance());
		Frightened frightened = Frightened.getInstance();
		SearchAlgorithm algorithm = frightened.destroySearch();
		assertEquals(algorithm, null);
		ArrayList<GhostAlgorithm> ghostAlgorithms = frightened.destroyGA();
		assertEquals(ghostAlgorithms, null);
		Mode mode = frightened.deleteMode();
		assertEquals(mode, null);
	}

	@Test
	public void TestFrightened00() {
		boolean res = Frightened.setFrightened(null);
		assertEquals(res, true);

		res = Frightened.setFrightened(null);
		assertEquals(res, false);

		cleanUpFrightened();
	}

	@Test
	public void TestFrightened01() {
		Frightened.setFrightened(null);
		Frightened frightened = Frightened.getInstance();

		boolean res = frightened.addAlgorithm("AnyString");
		assertEquals(res, false);
	}
	
	@Test
	public void TestFrightened06() {
		Frightened.setFrightened(null);
		boolean res = Frightened.getInstance().resolveCollision();
		assertEquals(res, true);

		cleanUpFrightened();
	}

	@Test
	public void TestFrightened07() {
		Frightened.setFrightened(null);
		String res = Frightened.getInstance().getModeName();
		assertEquals(res, "Frightened");

		cleanUpFrightened();
	}

	// test Chase
	// ---------------------------------------------------------------------------

	@Test
	public void TestChaseCleanUp() {
		Chase.setChase(Greedy_Search.getInstance());
		Chase chase = Chase.getInstance();
		SearchAlgorithm algorithm = chase.destroySearch();
		assertEquals(algorithm, null);
		ArrayList<GhostAlgorithm> ghostAlgorithms = chase.destroyGA();
		assertEquals(ghostAlgorithms, null);
		Mode mode = chase.deleteMode();
		assertEquals(mode, null);
	}

	@Test
	public void TestChase00() {
		boolean res = Chase.setChase(null);
		assertEquals(res, true);

		res = Chase.setChase(null);
		assertEquals(res, false);

		cleanUpChase();
	}

	@Test
	public void TestChase01() {
		Chase.setChase(null);
		Chase chase = Chase.getInstance();

		chase.addAlgorithm("ChaseAggressive");
		assertEquals(chase.getAlgorithms().get(0) instanceof ChaseAggressive, true);

		cleanUpChase();
	}

	@Test
	public void TestChase02() {
		Chase.setChase(null);
		Chase chase = Chase.getInstance();

		chase.addAlgorithm("ChaseAmbush");
		assertEquals(chase.getAlgorithms().get(0) instanceof ChaseAmbush, true);

		cleanUpChase();
	}

	@Test
	public void TestChase03() {
		Chase.setChase(null);
		Chase chase = Chase.getInstance();

		chase.addAlgorithm("ChasePatrol");
		assertEquals(chase.getAlgorithms().get(0) instanceof ChasePatrol, true);

		cleanUpChase();
	}

	@Test
	public void TestChase04() {
		Chase.setChase(null);
		Chase chase = Chase.getInstance();

		chase.addAlgorithm("ChaseRandom");
		assertEquals(chase.getAlgorithms().get(0) instanceof ChaseRandom, true);

		cleanUpChase();
	}

	@Test
	public void TestChase05() {
		Chase.setChase(null);
		Chase chase = Chase.getInstance();

		boolean res = chase.addAlgorithm("RandomIncorrectCase");
		assertEquals(res, false);

		cleanUpChase();
	}


	@Test
	public void TestChase06() {
		Chase.setChase(null);
		boolean res = Chase.getInstance().resolveCollision();
		assertEquals(res, false);

		cleanUpChase();
	}

	@Test
	public void TestChase07() {
		Chase.setChase(null);
		String res = Chase.getInstance().getModeName();
		assertEquals(res, "Chase");

		cleanUpChase();
	}
}
