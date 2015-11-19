package controler.controlerLocal;

import launcher.localLauncher.ChessGame;
import model.AbstractPiece;
import model.Coord;
import model.Couleur;
import model.Echiquier;
import model.Pieces;

public class ChessGameControler implements ChessGameControlers{

//	Echiquier echiquierControler;
	Pieces pieceAbstraite;
	//Echiquier echiquierCourant;
	ChessGame chessGame;
	
	public ChessGameControler(String namePiece, Couleur couleurPiece, int X, int Y) {
		// TODO Auto-generated constructor stub
		this.chessGame = new ChessGame();
		Coord coordonnees = new Coord (pieceAbstraite.getX(), pieceAbstraite.getY());
		this.pieceAbstraite = new Pieces(namePiece, couleurPiece, coordonnees);
		//this.echiquierCourant = new Echiquier();
	}
	

	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean move(Coord initCoord, Coord finalCoord){		
		// Verifie si mouvement possible
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
	}
	
	public boolean isEnd(){
		// TODO Auto-generated constructor stub
		return false;

	}
	public Couleur getColorCurrentPlayer(){
		// TODO Auto-generated constructor stub
		return null;
	}
}
