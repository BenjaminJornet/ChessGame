package model;

import utils.Observable;


public class ChessGame extends Observable {
	private Echiquier echiquierCourant;
	
	public ChessGame(){
		this.echiquierCourant = new Echiquier();
	}

	
	public void setEchiquier(Echiquier e){
		this.echiquierCourant = e;
	}
	
	public String toString(){
		return echiquierCourant.toString();
	}
	
	public boolean move (int xInit, int yInit, int xFinal, int yFinal){
		// Verifie si mouvement possible
		boolean mouvementPossible = false;
		
		//MAGIE NOIRE
		boolean move_ok=echiquierCourant.isMoveOk(xInit, yInit, xFinal, yFinal);
		
		if((echiquierCourant.isMoveLegal(xInit, yInit, xFinal, yFinal))&& move_ok){
			
			// Effectue le mouvement si possible
			mouvementPossible = echiquierCourant.move(xInit, yInit, xFinal, yFinal);

			if(mouvementPossible){				
				// Changement de joueur
				echiquierCourant.switchJoueur();
				mouvementPossible = true;
			}
		}
		 notify(echiquierCourant.getPiecesIHM());
		return mouvementPossible;
	}
	
	public boolean isEnd(){
		return this.echiquierCourant.isEnd();
	}
	
	public String getMessage(){
		return this.echiquierCourant.getMessage();
	}
	
	public Couleur getColorCurrentPlayer(){
		return this.echiquierCourant.getColorCurrentPlayer();	
	}
}
