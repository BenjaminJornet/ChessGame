package vue;

import model.ChessGame;
import model.Couleur;
import sockets.Client;
import sockets.SocketControler;
import sockets.SocketPart;
import controler.controlerLocal.ChessGameControler;

public class LauncherGUIBlanc {
	public static void main(String[]args){
		ChessGame cg = new ChessGame();

		String IP = "192.168.0.25";
		//localhost
		IP="127.0.0.1";
		SocketPart s = new Client(IP,2009);

		SocketControler sc=new SocketControler(s);
		ChessGameControler controler = new ChessGameControler(cg,sc,Couleur.BLANC);

		ChessGameGUI frame = new ChessGameGUI(controler,"Jeu Blanc",600,600);
		cg.addObserver(frame);

	}

}
