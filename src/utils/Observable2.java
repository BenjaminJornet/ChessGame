package utils;

import java.util.ArrayList;
import java.util.List;

import model.ChessGame;
import model.Echiquier;
import model.PieceIHM;

public class Observable2{ 
	
	private static ArrayList<Observeur2> observeurs = new ArrayList<Observeur2>();
	public void notify(String message) {
		int i=0;
		for(i=0;i<observeurs.size();i++){
			observeurs.get(i).update(message);
		}
		
	}

	public void addObserver(Observeur2 o){
		observeurs.add(o);
	}
}
