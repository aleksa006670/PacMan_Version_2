package GameTest;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import Game.ModeDestructor;
import Game.Mode;

import static org.junit.Assert.*;

public class IntegrationModeDestructor {
	
	@BeforeAll
	public static void execute() {
		Mode.initModes("src/Resource/easyAlgorithmData.txt");
	}
	
	//testing destroyAllModes
	@Test
	public void test01() {
		ModeDestructor.getInstance();
	
	
	}	
	
	//testing addMode
	@Test 
	public void test02() {
		assertEquals(true, ModeDestructor.getInstance().addMode(null));
	}
	
}
