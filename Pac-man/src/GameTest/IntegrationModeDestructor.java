package GameTest;

import org.junit.Test;
import org.junit.jupiter.api.Order;

import Game.ModeDestructor;
import Game.Chase;
import Game.Greedy_Search;
import Game.Mode;

import static org.junit.Assert.*;

public class IntegrationModeDestructor {


	@Test
	@Order(1)
	public void test01() {
		Mode.initModes("src/Resource/easyAlgorithmData.txt");
		boolean result = ModeDestructor.getInstance().destroyAllModes();
		assertEquals(result, true);
		
	
	}	
	
	@Test
	@Order(2)
	public void test02() {
		Chase.setChase(Greedy_Search.getInstance());
		boolean result = ModeDestructor.getInstance().addMode(Chase.getInstance());
		assertEquals(true, result);
		ModeDestructor.getInstance().destroyAllModes();
	}
}
