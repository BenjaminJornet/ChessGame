package model;

import java.util.ArrayList;
import java.util.Observable;


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
		setChanged();
		notifyObservers(echiquierCourant.getPiecesIHM());
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

	public ArrayList<Coord> getCasesPossibles(Coord entree){
		int row =0, column=0;
		ArrayList<Coord> coordPossible = new ArrayList<Coord>();
		// On verifie les mouvements sur les lignes
		for(row=0;row<8;row++){
			// On verifie les mouvements sur les colonnes
			for(column=0;column<8;column++){
				boolean isMoveLegal = echiquierCourant.isMoveLegal(entree.x, entree.y, row, column);
				boolean isMoveOk = echiquierCourant.isMoveOk(entree.x, entree.y, row, column);
				if(isMoveLegal&&isMoveOk){
					coordPossible.add(new Coord(row,column));
				}
			}
		}
		return coordPossible;
	}
}
