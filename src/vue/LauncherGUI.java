package vue;

import javax.swing.JFrame;

import model.ChessGame;
import controler.controlerLocal.ChessGameControler;

public class LauncherGUI {
	public static void main(String[]args){
		 ChessGame cg = new ChessGame();
		  ChessGameControler controler = new ChessGameControler(cg);
		  JFrame frame = new ChessGameGUI(controler);
	}

}
