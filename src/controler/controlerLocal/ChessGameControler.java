package controler.controlerLocal;

import model.ChessGame;
import model.Coord;
import model.Couleur;


public class ChessGameControler implements ChessGameControlers{

//	Echiquier echiquierControler;
	//Pieces pieceAbstraite;
	//Echiquier echiquierCourant;
	ChessGame chessGame;
	
	public ChessGameControler(ChessGame c) {
		// TODO Auto-generated constructor stub
		this.chessGame = c;
		//Coord coordonnees = new Coord (pieceAbstraite.getX(), pieceAbstraite.getY());
		//this.pieceAbstraite = new Pieces(namePiece, couleurPiece, coordonnees);
		//this.echiquierCourant = new Echiquier();
	}
	

	public String getMessage() {
		return this.chessGame.getMessage();
	}
	
	public boolean move(Coord initCoord, Coord finalCoord){		
		// Verifie si mouvement possible
		boolean mouvementPossible = false;
		mouvementPossible = this.chessGame.getEchiquier().move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
		
		return mouvementPossible;
		/*
		boolean mouvementPossible = false;
		Echiquier echiquierMove = chessGame.getEchiquier();
		
		if((echiquierMove.isMoveLegal(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y))
			&&(echiquierMove.isMoveOk(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y))){
			
			// Effectue le mouvement si possible
			mouvementPossible = echiquierMove.move(initCoord.x, initCoord.y, finalCoord.y, finalCoord.y);
			
			if(mouvementPossible){
				// Changement de joueur
				echiquierMove.switchJoueur();
				return true;
			}
		}
		return mouvementPossible;
		*/
	}
	
	public boolean isEnd(){
		return this.chessGame.isEnd();

	}
	public Couleur getColorCurrentPlayer(){
		return this.chessGame.getColorCurrentPlayer();
	}
}
