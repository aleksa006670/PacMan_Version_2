package Game;

import java.util.ArrayList;

public class ModeDestructor {
private final ArrayList<Mode> modes=new ArrayList<Mode>();
private static ModeDestructor instance=new ModeDestructor();


public static ModeDestructor getInstance() {
	return instance;
}


public boolean destroyAllModes() {
	int content = modes.size();
	for(Mode m: modes) {
		if(m.destroyAlgorithms() && m.deleteMode()==null) {
			content--;
		}
	}
	modes.clear();
	return content==0;
}


public Mode addMode(Mode m) {
	modes.add(m);
	return m;
}



}
