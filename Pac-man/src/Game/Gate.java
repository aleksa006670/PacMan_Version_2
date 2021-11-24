package Game;

public class Gate extends GameObject{
    private Gate(int x_coordinate, int y_coordinate) {
		super(x_coordinate, y_coordinate, 'G');
	}
    
	private static Gate instance;
	
	public static Gate setGate(int x_coordinate, int y_coordinate){
		if(instance==null){
			instance = new Gate(x_coordinate, y_coordinate);
		}
		else{
			System.out.println("The gate has been initialized!");
		}
		
		return instance;
	}

	public static Gate getInstance(){
		return instance;
	}

	public static boolean deleteGate(){
		instance = null;
		
		return instance == null;
	}
}
