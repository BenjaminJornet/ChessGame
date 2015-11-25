package vue;

import our.sockets.Client;
import our.sockets.SocketControler;
import our.sockets.SocketPart;
import model.ChessGame;
import controler.controlerLocal.ChessGameControler;

public class LauncherGUI {
	public static void main(String[]args){
		ChessGame cg = new ChessGame();
		SocketPart s = new Client("127.0.0.1",2009);
		SocketControler sc=new SocketControler(s);
		ChessGameControler controler = new ChessGameControler(cg,sc);
	
		ChessGameGUI frame = new ChessGameGUI(controler);
		cg.addObserver(frame);
	}

}
