package controler.controlerLocal;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ChessGame;
import model.Coord;
import model.Couleur;
import our.sockets.SocketControler;


public class ChessGameControler implements ChessGameControlers,Observer{


	ChessGame chessGame;
	private SocketControler socketC;
	private Couleur couleurJeu;

	public ChessGameControler(ChessGame c,SocketControler sc, Couleur couleur) {
		this.chessGame = c;
		this.socketC = sc;
		this.couleurJeu = couleur;
		sc.add(this);
	}


	public String getMessage() {
		return this.chessGame.getMessage();
	}

	public boolean move(Coord initCoord, Coord finalCoord){		
		//verifie si on joue la bonne couleur
		
		if(!couleurJeu.equals(chessGame.getColorCurrentPlayer())){
			//return false;
		}
		// Verifie si mouvement possible
		boolean move_possible = this.chessGame.move(initCoord.x, initCoord.y,finalCoord.x, finalCoord.y);
		if(move_possible){
			this.socketC.sendMove(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
		}else{
			System.out.println("mouvement refuser");
		}
		return move_possible;
	}

	public boolean isEnd(){
		return this.chessGame.isEnd();

	}
	public Couleur getColorCurrentPlayer(){
		return this.chessGame.getColorCurrentPlayer();
	}
	public ArrayList<Coord>getMouvementPossible(Coord c){
		return this.chessGame.getCasesPossibles(c);
	}

	private static void convert(String line,Coord a,Coord b) throws Exception{

		String pattern = "\\((\\d+),(\\d+)\\)\\s*->\\s*\\((\\d+),(\\d+)\\)";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(line);
		if (m.find()) {
			a.x=Integer.valueOf( m.group(1) ).intValue();
			a.y=Integer.valueOf(m.group(2) ).intValue();
			b.x=Integer.valueOf(m.group(3) ).intValue();
			b.y=Integer.valueOf( m.group(4) ).intValue();
		} else {
			throw new Exception("NO MATCH");
		}
	}


	@Override
	public void update(Observable arg0, Object message) {
		System.out.println("on recoit le message :" + message);
		Coord initCoord = new Coord(-1,-1);
		Coord finalCoord= new Coord(-1,-1);

		try {			
			convert((String)message,initCoord,finalCoord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(initCoord);
		System.out.println(finalCoord);
		this.chessGame.move(initCoord.x,initCoord.y, finalCoord.x,finalCoord.y);
		
	}
}
