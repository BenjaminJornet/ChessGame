package controler.controlerLocal;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ChessGame;
import model.Coord;
import model.Couleur;
import regex.SocketControler;
import utils.Observeur2;


public class ChessGameControler implements ChessGameControlers,Observeur2{


	ChessGame chessGame;
	private SocketControler socketC;

	public ChessGameControler(ChessGame c,SocketControler sc) {
		this.chessGame = c;
		this.socketC = sc;
		sc.add(this);
	}


	public String getMessage() {
		return this.chessGame.getMessage();
	}

	public boolean move(Coord initCoord, Coord finalCoord){		
		// Verifie si mouvement possible
		boolean move_possible = this.chessGame.move(initCoord.x, initCoord.y,finalCoord.x, finalCoord.y);
		if(move_possible){
			this.socketC.sendMove(initCoord.x,initCoord.y,finalCoord.x,finalCoord.y);
		}
		return move_possible;
	}

	public boolean isEnd(){
		return this.chessGame.isEnd();

	}
	public Couleur getColorCurrentPlayer(){
		return this.chessGame.getColorCurrentPlayer();
	}
	
	public static void convert(String line,Coord a,Coord b) throws Exception{

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
	public void update(String message) {
		Coord initCoord = new Coord(-1,-1);
		Coord finalCoord= new Coord(-1,-1);
		
		try {
			convert(message,initCoord,finalCoord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.move(initCoord, finalCoord);
	}


	
}
