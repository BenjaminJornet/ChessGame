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
		boolean move_legal = echiquierCourant.isMoveLegal(xInit, yInit, xFinal, yFinal);
		
		if(!move_ok){
			System.out.println(echiquierCourant.getColorCurrentPlayer().toString());
			System.out.println(echiquierCourant.getMessage());
			}
		
		System.out.println("move ok: "+move_ok+"\nmove legal: "+move_legal);
		
		if(move_legal && move_ok){
			
			// Effectue le mouvement si possible
			mouvementPossible = echiquierCourant.move(xInit, yInit, xFinal, yFinal);

			if(mouvementPossible){				
				// Changement de joueur
				echiquierCourant.switchJoueur();
			}else{
				System.out.println("mouvement impossible");
			}
		}
		//System.out.println("echiquierCourantgetPiecesIHM() : " + echiquierCourant.getPiecesIHM());
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


	public void read(String message) {
		// TODO Auto-generated method stub
		
	}
}
