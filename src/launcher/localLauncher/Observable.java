package launcher.localLauncher;

import java.util.ArrayList;

public class Observable{ 
	
	private static ArrayList<Observeur> observeurs = new ArrayList<Observeur>();
	
	public void _notify(){
		int i=0;
		for(i=0;i<observeurs.size();i++){
			observeurs.get(i).update();
		}
		
	}
	
	public void addObserver(Observeur o){
		observeurs.add(o);
	}
}
