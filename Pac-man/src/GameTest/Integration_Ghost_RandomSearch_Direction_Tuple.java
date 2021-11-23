package GameTest;

import Game.Game;
import Game.Ghost;
import Game.PacMan;
import org.junit.Test;

import static org.junit.Assert.*;

public class Integration_Ghost_RandomSearch_Direction_Tuple {

	@Test
	public void TestRandomSearch01() {
		Game game = Game.getInstance();
		int res = game.gameInit("Easy", "Chase");
		PacMan pacman = PacMan.getInstance();
		Ghost red = Ghost.getGhostByName('R');
	}
}
