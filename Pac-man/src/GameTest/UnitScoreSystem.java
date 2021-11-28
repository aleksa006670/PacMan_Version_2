package GameTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Game.ScoreSystem;

public class UnitScoreSystem {
//public boolean printScore(String filename, int score, String name, String difficulty)
@Test
public void test01() {
	boolean result = ScoreSystem.getInstance().printScore("Score.txt", 100, "Omar", "Hard");
	assertEquals(result, true);
}

@Test
public void test02() {
	boolean result = ScoreSystem.getInstance().printScore("Score.txt", 150, "Aleksa", "Medium");
	assertEquals(result, true);
}

@Test
public void test03() {
	boolean result = ScoreSystem.getInstance().printScore("Score.txt", 200, "Kaylee", "Easy");
	assertEquals(result, true);
}

@Test
public void test04() {
	boolean result = ScoreSystem.getInstance().printScore("Score.txt", 10000, "Robbin", "Dummy");
	assertEquals(result, false);
}

}
