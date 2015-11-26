package utils;

import java.util.ArrayList;
import java.util.List;

import model.PieceIHM;

public abstract class Observable{ 
	
	private static ArrayList<Observeur> observeurs = new ArrayList<Observeur>();
	
	public void notify(List<PieceIHM> list){
		int i=0;
		for(i=0;i<observeurs.size();i++){
			System.out.println("dans la boucle de notify de Observable");
			observeurs.get(i).update(list);
		}
		
	}
	
	public void addObserver(Observeur o){
		observeurs.add(o);
	}
}
