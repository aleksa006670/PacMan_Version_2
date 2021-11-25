package GameTest;

import org.junit.Test;

import Game.ModeDestructor;
import Game.Chase;
import Game.Mode;

import static org.junit.Assert.*;

public class IntegrationModeDestructor {


	@Test
	public void test01() {
		Mode.initModes("src/Resource/easyAlgorithmData.txt");
		boolean result = ModeDestructor.getInstance().destroyAllModes();
		assertEquals(result, true);
		
	
	}	
	
	@Test
	public void test02() {
		Chase.setChase(null);
		boolean result = ModeDestructor.getInstance().addMode(Chase.getInstance());
		assertEquals(true, result);
	}
}
