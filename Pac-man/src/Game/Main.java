package Game;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		
		Game game = Game.getInstance();
		ScoreSystem ss = ScoreSystem.getInstance();
		// Starting the game loop
		boolean repeat = true;
		Scanner s = new Scanner(System.in);
		System.out.println("Please, specify your name:");
		String name = s.next();
		while(repeat) {
			System.out.println("Please specify the difficulty (Easy, Medium, Hard):");
			String difficulty = s.next();
			
			int res = game.gameInit(difficulty, "Scatter");
			while(res == 1) {
				System.out.println("Please specify the difficulty properly (Easy, Medium, Hard):");
				difficulty = s.next();
				res = game.gameInit(difficulty, "Scatter");
			}
			
			while (game.isGameOver()==false) {
				System.out.println(game.printMaze());
				System.out.println("\n\nType in a command (Up, Down, Left, Right, Reset): ");
				String cmd = s.next();
				game.gameTick(cmd);
			}
			ss.printScore("Score.txt", PacMan.getInstance().getScore(), name, difficulty);
			System.out.println("Do you want to play again?");
			String answer = s.next();
			repeat = answer.equals("Yes");
			game.gameOver();
		}
		System.out.println("Out of the loop");
		s.close();
		
		
	}
	
	
}

