package Game;

import java.util.ArrayList;

//Game is used as the Facade for the Main class
//It will handle all changes related to the states of Pacman, Ghosts and the Maze
public class Game {
	private Mode mode;
	
	private Difficulty difficulty;

	private static Game instance = new Game();
	
	private int totalTicks = 0;
	private int totalModeChanges = 0;
	private int totalFrightenedTicks = 0;

	public static Game getInstance() {
		return instance;
	}

	private Game() {
		
	}

	public int gameInit(String difficultyStr, String mode) {
		boolean state = setDifficulty(difficultyStr);
		if(!state) {
			return 1; // Difficulty Init failed
		}
		state = Ghost.initGhosts(difficulty.getGhostFile());
		if(!state) {
			return 2; // Ghost Init failed
		}
		state = PacMan.initPacMan(difficulty.getPacMan());
		if(!state) {
			return 3; // Pacman Init failed
		}
		state = Maze.initMaze(difficulty.getMaze());
		if(!state) {
			return 4; // Maze Init failed
		}
		state = Mode.initModes(difficulty.getAlgorithm());
		if(!state) {
			return 5; // Algorithm Init failed
		}
		state = setMode(mode);
		if(!state) {
			return 6; // Game mode Init failed
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
			if(!resetGame()) // Return after this
				return 1; // Problem with Game Reset
			break;
		default:
			pacPotDir = PacMan.getInstance().getDirection();
			break;
		}
		
		if(!handleMovements(pacPotDir)) // Check return of this
			return 2; // Problem with the regular movement handling
		
		if(mode.getModeName().equals("Frightened")) {
			totalFrightenedTicks++;
			if(totalFrightenedTicks % 10 == 0)
				if(!setMode("Chase"))
					return 3; // Problem with Setting mode to Chase after Frightened
		} else {
			totalTicks++;
			if(totalTicks % 10 == 0 && totalModeChanges < 7) {
				boolean s;
				if(mode.getModeName().equals("Scatter"))
					s = setMode("Chase");
				else
					s = setMode("Scatter");
				if(s)
					totalModeChanges++;
				if(!s)
					return 4; // Problem with alternating Chase and Scatter modes
			}
		}
		return 0;
	}


	public boolean setMode(String mode) {
		if (mode != null) {
			// if mode is changed, revere ghosts' direction
			if (this.mode != null && !this.mode.getModeName().equals(mode) && (
					mode.equals("Frightened") ||
					(mode.equals("Chase") && this.mode.getModeName().equals("Scatter")) ||
					(mode.equals("Scatter") && this.mode.getModeName().equals("Chase")) )) {
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
	
	public boolean setDifficulty(String difficultyStr) {
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
		if(pac == null || maze == null)
			return false;
		if(!pac.resetPacMan() || !Ghost.resetGhosts() || !maze.resetMaze())
			return false;
		System.out.println("Game is reset");
		return true;
	}

	public boolean handleMovements(Direction pacPotDir) {
		moveGhosts();
		char symbol = movePacMan(pacPotDir);
		int ghost_eaten_num = 1;
		
		ArrayList<Ghost> ghosts = Ghost.getGhosts();
		PacMan pac = PacMan.getInstance();
		Maze maze = Maze.getInstance();
		
		if(ghosts == null || pac == null || maze == null) {
//			System.out.println("Instances are empty");
			return false;
		}
			
		
		boolean pacmanNoLose = true;
		
		for(int i=0;i<ghosts.size();i++) {
			if(this.isCollision(ghosts.get(i))) {
				pacmanNoLose  = mode.resolveCollision();
				if(pacmanNoLose) {
					if(!this.PacDefeatGhost(ghosts.get(i), ghost_eaten_num)) {
//						System.out.println("Pac cant defeat Ghosts");
						return false; 
					}
					ghost_eaten_num *= 2;
				} else {
					if(!this.GhostDefeatPac()) {
//						System.out.println("Ghosts cant defeat Pac");
						return false; 
					}
					// No score given to Pacman
					break;
				}
				return true;
			}
		}
			
		if(pacmanNoLose && symbol!='W') {
			if(symbol=='U') {
				if(!setMode("Frightened") || !pac.changeScore(50)) {
//					System.out.println("Can't switch the mode");
					return false;
				}
			} else if(symbol=='F') {
				if(!pac.incrementFood() || !pac.changeScore(10)) {
//					System.out.println("Can't eat food");
					return false;
				}
			}
			maze.removeObject(PacMan.getInstance().getTuple());
		}
//		System.out.println("movement successful");
		return true;
	}
	
		
	public boolean isCollision(Ghost g) {
		PacMan pac = PacMan.getInstance();
		Tuple pacPosition = pac.getTuple();
		Tuple ghostPosition = g.getTuple();
		Tuple pacDTuple = pac.getDirection().DirectionToTuple();
		Tuple ghostDTuple = g.getDirection().DirectionToTuple();
		boolean isTileSwap = pacPosition.sum(ghostDTuple).equals(ghostPosition) && ghostPosition.sum(pacDTuple).equals(pacPosition);
		boolean sameTile = pacPosition.equals(ghostPosition);
		return isTileSwap || sameTile;
		
	}
	
	
	
	
	//How to test: compare the number of ghosts which actually moved to a different tile
	public int moveGhosts() {
		int numberMoved = 0;
		ArrayList<Ghost> ghosts=Ghost.getGhosts();
		ArrayList<GhostAlgorithm> algs = mode.getAlgorithms();
		for(int i=0;i<ghosts.size();i++) {
			// After
			Tuple targetTile = algs.get(i).behave(ghosts.get(i));
			if(ghosts.get(i).moveToTarget(mode.getSearchAlgorithm(), targetTile, difficulty.doReverse()))
				numberMoved++;
		}
		return numberMoved;
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
	
	
	
	public boolean gameOver() {
		//Score system should be called from here
		boolean outcomePac = PacMan.destroyPacman();
		boolean outcomeG = Ghost.destroyGhosts();
		boolean outcomeMode = ModeDestructor.getInstance().destroyAllModes();
		boolean outcomeMaze = Maze.destroyMaze();
		return outcomePac && outcomeG && outcomeMode && outcomeMaze;
	
	}
	
	public boolean isGameOver() {
		return PacMan.getInstance().getLives()<1 || PacMan.getInstance().getFood()==Maze.getInstance().getTotalNumOfFood();
	}
	
	public boolean PacDefeatGhost(Ghost g, int ghost_eaten_num) {
		if(g == null || !g.resetPosition() || !PacMan.getInstance().changeScore(200 * ghost_eaten_num))
			return false;
		return true;
	}
	
	public boolean GhostDefeatPac() {
		PacMan pac = PacMan.getInstance();
		if(pac == null || !pac.resetPosition() || !pac.changeLives(-1))
			return false;
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
						// Neither Pacman nor ghost is here. We print maze 
						if (maze.getSymbol(i, j) == 'N') {
							//System.out.print("n");
							mazeText+="n";
						} else {
							//System.out.print(maze.getSymbol(i, j)+" ");
							mazeText+=maze.getSymbol(i, j)+" ";
						}
					}
				}
				
			}
			//System.out.println();
			mazeText+='\n';
		}
		return mazeText;
}
}
