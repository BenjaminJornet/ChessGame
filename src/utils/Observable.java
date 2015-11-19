package utils;

import java.util.ArrayList;

import model.ChessGame;

public class Observable{ 
	
	private static ArrayList<Observeur> observeurs = new ArrayList<Observeur>();
	
	public void _notify(ChessGame c){
		int i=0;
		for(i=0;i<observeurs.size();i++){
			observeurs.get(i).update(c);
		}
		
	}
	
	public void addObserver(Observeur o){
		observeurs.add(o);
	}
}
