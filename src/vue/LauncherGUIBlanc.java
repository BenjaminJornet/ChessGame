package vue;

import java.net.InetAddress;
import java.net.UnknownHostException;

import model.ChessGame;
import our.sockets.Client;
import our.sockets.SocketControler;
import our.sockets.SocketPart;
import controler.controlerLocal.ChessGameControler;

public class LauncherGUIBlanc {
	public static void main(String[]args){
		ChessGame cg = new ChessGame();

		SocketPart s = null;
		try {
			s = new Client(InetAddress.getLocalHost(),2009);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		if(s!=null){
			SocketControler sc=new SocketControler(s);
			ChessGameControler controler = new ChessGameControler(cg,sc);

			ChessGameGUI frame = new ChessGameGUI(controler,"Jeu Blanc",600,600);
			cg.addObserver(frame);
		}
	}
	public void testSocket(Client s){
		s.notify("(0,6)->(0,5)");
		s.notify("(0,1)->(0,3)");

	}

}
