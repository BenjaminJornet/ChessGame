package vue;

import model.ChessGame;
import model.Couleur;
import sockets.Serveur;
import sockets.SocketControler;
import sockets.SocketPart;
import controler.controlerLocal.ChessGameControler;

public class LauncherGUINoir {
	public static void main(String[]args){
		ChessGame cg = new ChessGame();

		SocketPart s = new Serveur(2009);

		SocketControler sc=new SocketControler(s);
		ChessGameControler controler = new ChessGameControler(cg,sc,Couleur.NOIR);

		ChessGameGUI frame = new ChessGameGUI(controler,"Jeu Noir",600,600);
		cg.addObserver(frame);
	}

}
