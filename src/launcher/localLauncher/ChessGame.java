package launcher.localLauncher;

import model.Couleur;
import model.Echiquier;

public class ChessGame extends Observable {
	private Echiquier echiquierCourant;
	
	public ChessGame(){
		this.echiquierCourant = new Echiquier();
	}

	public Echiquier getEchiquier(){
		return this.echiquierCourant;
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
		
		if((echiquierCourant.isMoveLegal(xInit, yInit, xFinal, yFinal))
			&&(echiquierCourant.isMoveOk(xInit, yInit, xFinal, yFinal))){
			
			// Effectue le mouvement si possible
			mouvementPossible = echiquierCourant.move(xInit, yInit, xFinal, yFinal);
			
			if(mouvementPossible){
				// Changement de joueur
				echiquierCourant.switchJoueur();
				mouvementPossible = true;
			}
		}
		 _notify();
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
