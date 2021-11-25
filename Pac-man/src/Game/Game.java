package Game;

import java.util.ArrayList;

//Game is used as the Facade for the Main class
//It will handle all changes related to the states of Pacman, Ghosts and the Maze
public class Game {
	private Mode mode;
	
	private Difficulty difficulty;

	private static Game instance = null;
	
	private int totalTicks = 0;
	private int totalModeChanges = 0;
	private int totalFrightenedTicks = 0;

	public static Game getInstance() {
		if(instance == null) instance = new Game();
		return instance;
	}
	
	public static boolean destroyGame() {
		instance = null;
		return true;
	}

	private Game() {
		
	}

	public int gameInit(String difficultyStr, String mode) {
		boolean state = setDifficulty(difficultyStr);
		if(!state) {
			return 1; // Difficulty Init failed
		}
		
		Ghost.initGhosts(difficulty.getGhostFile());
		PacMan.initPacMan(difficulty.getPacMan());
		Maze.initMaze(difficulty.getMaze());
		Mode.initModes(difficulty.getAlgorithm());
		
		state = setMode(mode);
		if(!state) {
			return 2; // Game mode Init failed
		}
		return 0; // Success
	}

	public int gameTick(String cmd) {
		//s.close(); solution to bug 8)
		Direction pacPotDir = Direction.RIGHT;
		switch (cmd) {
		case "Up":
			pacPotDir = Direction.UP;
			//pac.setDirection(Direction.UP);
			break;
		case "Down":
			//pac.setDirection(Direction.DOWN);
			pacPotDir = Direction.DOWN;
			break;
		case "Left":
			//pac.setDirection(Direction.LEFT);
			pacPotDir = Direction.LEFT;
			break;
		case "Right":
			//pac.setDirection(Direction.RIGHT);
			pacPotDir = Direction.RIGHT;
			break;
		case "Reset":
			resetGame(); // Return after this
			return 1;
		default:
			pacPotDir = PacMan.getInstance().getDirection();
			break;
		}
		
		handleMovements(pacPotDir); // Check return of this
		
		if(mode.getModeName().equals("Frightened")) {
			totalFrightenedTicks++;
			if(totalFrightenedTicks % 10 == 0) {
				setMode("Chase");
				return 4;
			}
			return 3;
		} else {
			totalTicks++;
			if(totalTicks % 10 == 0 && totalModeChanges < 9) {
				if(mode.getModeName().equals("Scatter")) {
					setMode("Chase");
					totalModeChanges++;
					return 5;
				}
				else {
					setMode("Scatter");
					totalModeChanges++;
					return 6;
				}
			}
		}
		return 0;
	}


	public boolean setMode(String mode) {
		if (mode != null) {
			// if mode is changed, revere ghosts' direction
			if (this.mode != null && !this.mode.getModeName().equals(mode) && !this.mode.getModeName().equals("Frightened")) {
				Ghost.reverseDirectionsOfAllGhosts();
			}
			
			if (mode.equals("Chase"))
				this.mode = Chase.getInstance();
			else if (mode.equals("Scatter"))
				this.mode = Scatter.getInstance();
			else if (mode.equals("Frightened"))
				this.mode = Frightened.getInstance();
			else {
				// handle the exception in the testing phase
				System.out.println("Mode selected is invalid!");
				return false;
			}
			// Success
			return true;
		}
		// handle the exception in the testing phase
		System.out.println("Mode selected is null!");
		return false;
	}
	
	private boolean setDifficulty(String difficultyStr) {
		if(difficultyStr == null) {
			return false;
		} else if(difficultyStr.equals("Easy")) {
			this.difficulty = Easy.getInstance();
		} else if(difficultyStr.equals("Medium")) {
			this.difficulty = Medium.getInstance();
		} else if(difficultyStr.equals("Hard")) {
			this.difficulty = Hard.getInstance();
		} else {
			return false;
		}
		return true;
	}

	public boolean resetGame() {
		PacMan pac = PacMan.getInstance();
		Maze maze = Maze.getInstance();
		pac.resetPacMan();
		Ghost.resetGhosts();
		maze.resetMaze();
		return true;
	}

	public int handleMovements(Direction pacPotDir) {
		int x = 0;
		moveGhosts();
		char symbol = movePacMan(pacPotDir);
		int ghost_eaten_num = 1;
		
		ArrayList<Ghost> ghosts = Ghost.getGhosts();
		PacMan pac = PacMan.getInstance();
		Maze maze = Maze.getInstance();
			
		boolean pacmanNoLose = true;
		
		for(int i=0;i<ghosts.size();i++) {
			if(this.isCollision(ghosts.get(i))) {
				x += 10;
				pacmanNoLose  = mode.resolveCollision();
				if(pacmanNoLose) { // Collision in Frightened mode
					x += 20;
					this.PacDefeatGhost(ghosts.get(i), ghost_eaten_num);
					ghost_eaten_num *= 2;
				} else { // Collision in Chase or Scatter modes
					x += 30;
					this.GhostDefeatPac();
					break;
				}
			}
		}
			
		if(pacmanNoLose && symbol!='W') { // Pacman hits wall
			x++;
			if(symbol=='U') { // Pacman hits a powerup
				x++;
				setMode("Frightened");
				pac.changeScore(50);
			} else if(symbol=='F') { // Pacman hits a food
				x += 2;
				pac.incrementFood();
				pac.changeScore(10);
			}
			maze.removeObject(PacMan.getInstance().getTuple());
		}
		return x;
	}
	
		
	public boolean isCollision(Ghost g) {
		PacMan pac = PacMan.getInstance();
		Tuple pacPosition = pac.getTuple();
		Tuple ghostPosition = g.getTuple();
		Tuple pacDTuple = pac.getDirection().DirectionToTuple();
		Tuple ghostDTuple = g.getDirection().DirectionToTuple();
		boolean isTileSwap =  ghostPosition.sum(pacDTuple).equals(pacPosition) && pacPosition.sum(ghostDTuple).equals(ghostPosition);
		boolean sameTile = pacPosition.equals(ghostPosition);
		return isTileSwap || sameTile;
	}
	
	
	
	

	//Testing Logic: Does the mode have any algorithms currently - e.g. Frightened mode does not, the others do
	public boolean moveGhosts() {
		ArrayList<Ghost> ghosts=Ghost.getGhosts();
		ArrayList<GhostAlgorithm> algs = mode.getAlgorithms();
		
		if (algs.size()!=0) {
			for(int i=0;i<ghosts.size();i++) {
				// After
				Tuple targetTile = algs.get(i).behave(ghosts.get(i));
				ghosts.get(i).moveToTarget(mode.getSearchAlgorithm(), targetTile, difficulty.doReverse());
					
			}
			return true;
		} else {
			for(int i=0;i<ghosts.size();i++) {
				// After
				ghosts.get(i).moveToTarget(mode.getSearchAlgorithm(), null, difficulty.doReverse());
			}
			return false;
		}
	}
	

	
	public char movePacMan(Direction pacPotDir) {
		PacMan pac = PacMan.getInstance();
		Tuple new_pac=pac.getTuple().sum((pacPotDir).DirectionToTuple());
		Maze maze = Maze.getInstance();
		char symbol = maze.getSymbol(new_pac.getSecond(), new_pac.getFirst());
		
		if(symbol !='W') {
			pac.setDirection(pacPotDir);
			pac.move();
		}
		
		return symbol;
	}
	
	
	//void
	public boolean gameOver() {
		//Score system should be called from here
		PacMan.destroyPacman();
		Ghost.destroyGhosts();
		ModeDestructor.getInstance().destroyAllModes();
		Maze.destroyMaze();
		return true;
	
	}
	
	public boolean isGameOver() {
		return PacMan.getInstance().getLives()<1 || PacMan.getInstance().getFood()==Maze.getInstance().getTotalNumOfFood();
	}
	
	public boolean PacDefeatGhost(Ghost g, int ghost_eaten_num) {
		g.resetPosition();
		PacMan.getInstance().changeScore(200 * ghost_eaten_num);
		return true;
	}
	
	public boolean GhostDefeatPac() {
		PacMan pac = PacMan.getInstance();
		pac.resetPosition();
		pac.changeLives(-1);
		return true;
	}
	
	public String printMaze() {
		//Get pacman's position
		String mazeText="";
		int pacmanX =  PacMan.getInstance().getTuple().getFirst();
		int pacmanY =  PacMan.getInstance().getTuple().getSecond();
	
		//Tell the positions of the 4 ghosts
		ArrayList<Ghost> ghosts = Ghost.getGhosts();
		
		Maze maze = Maze.getInstance();

		for(int i=0;i<maze.getM();i++) {
			for(int j=0;j<maze.getN();j++) {
			    
				// see if pacman is here
				if (i == pacmanY && j ==pacmanX) {
					//System.out.print("P ");
					mazeText+="P ";
				}
				else {
					// see if any of the four ghosts is here
					boolean isGhostHere = false;
					
					for (Ghost g: ghosts) {
						if (i == g.getTuple().getSecond() && j == g.getTuple().getFirst()) {
							
							/**
							 * Here is some quick fix. The Pink ghost's symbol is 'P', same as Pacman.
							 * So to make distinction I use letter 'I' to print the Pink ghost
							 */
							if (g.getSymbol() == 'P') {	// only for Pink ghost						
								//System.out.print("I"+" ");
								mazeText+="I ";
							}
							else {						// for other ghosts - print normally
								//System.out.print(g.getSymbol()+" ");
								mazeText+=g.getSymbol() + " ";
							}
							
							isGhostHere = true;
							break;
						}
					}
					if (!isGhostHere) {
						mazeText+=maze.getSymbol(i, j)+" ";
					}
				}
				
			}
			//System.out.println();
			mazeText+='\n';
		}
		return mazeText;
}
}