package Game;

public class Frightened extends Mode {

	private static Frightened instance;
	
	private Frightened(SearchAlgorithm sa) {
		super(sa);
		
	}

	public static Frightened getInstance() {
		return instance;
	}
	

	public static boolean setFrightened(SearchAlgorithm sa) {
		if (instance==null){
		instance = new Frightened(sa);
		return true;}
		else{
			return false;
		}
	}

	@Override
	public boolean addAlgorithm(String algorithmName) {
		switch (algorithmName) {
		case "FrightenedWandering":
			algorithms.add(new FrightenedWandering());
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean resolveCollision() {
		return true;
		
	}

	@Override
	public String getModeName() {
		return "Frightened";
	}

	@Override
	public Mode deleteMode(){
		instance =null;
		return instance;
	}

}
