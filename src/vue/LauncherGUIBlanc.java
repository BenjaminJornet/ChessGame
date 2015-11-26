package vue;

import model.ChessGame;
import our.sockets.Client;
import our.sockets.SocketControler;
import our.sockets.SocketPart;
import controler.controlerLocal.ChessGameControler;

public class LauncherGUIBlanc {
	public static void main(String[]args){
		ChessGame cg = new ChessGame();

		SocketPart s = new Client("192.168.43.155",2009);

		SocketControler sc=new SocketControler(s);
		ChessGameControler controler = new ChessGameControler(cg,sc);

		ChessGameGUI frame = new ChessGameGUI(controler);
		cg.addObserver(frame);
	}

}
