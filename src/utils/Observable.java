package utils;

import java.util.ArrayList;

import model.ChessGame;
import model.Echiquier;

public class Observable{ 
	
	private static ArrayList<Observeur> observeurs = new ArrayList<Observeur>();
	
	public void _notify(Echiquier echiquierCourant){
		int i=0;
		for(i=0;i<observeurs.size();i++){
			observeurs.get(i).update(echiquierCourant);
		}
		
	}
	
	public void addObserver(Observeur o){
		observeurs.add(o);
	}
}
