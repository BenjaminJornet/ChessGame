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
		return this.chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
		
	}
	
	public boolean isEnd(){
		return this.chessGame.isEnd();

	}
	public Couleur getColorCurrentPlayer(){
		return this.chessGame.getColorCurrentPlayer();
	}
	public static void main(String[]args){
		ChessGameControler cont = new ChessGameControler(new ChessGame());
		int a,b,c,d;
		for(a=0;a<10;a++){
			for(b=0;b<10;b++){
				for(c=0;c<10;c++){
					for(d=0;d<10;d++){
				
			
		boolean test= cont.move(new Coord(a,b),new Coord(c,d));
		System.out.println("("+a+","+b+")-->("+c+","+d+")  " + (test?"success":"fail"));
		
		}
	}
			}
			}
		}
	}
