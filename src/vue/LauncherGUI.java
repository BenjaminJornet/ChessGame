package vue;

import model.ChessGame;
import regex.SocketControler;
import controler.controlerLocal.ChessGameControler;

public class LauncherGUI {
	public static void main(String[]args){
		ChessGame cg = new ChessGame();
		SocketControler sc=new SocketControler();
		ChessGameControler controler = new ChessGameControler(cg,sc);
		ChessGameGUI frame = new ChessGameGUI(controler);
		cg.addObserver(frame);
	}

}
