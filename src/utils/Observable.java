package utils;

import java.util.ArrayList;
import java.util.List;

import model.ChessGame;
import model.Echiquier;
import model.PieceIHM;

public class Observable{ 
	
	private static ArrayList<Observeur> observeurs = new ArrayList<Observeur>();
	
	public void notify(List<PieceIHM> list){
		int i=0;
		for(i=0;i<observeurs.size();i++){
			observeurs.get(i).update(list);
		}
		
	}
	
	public void addObserver(Observeur o){
		observeurs.add(o);
	}
}
