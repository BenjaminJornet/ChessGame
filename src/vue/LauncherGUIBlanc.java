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

		SocketPart s;
		try {
			s = new Client(InetAddress.getLocalHost().toString(),2009);
		} catch (UnknownHostException e) {
			s = new Client("192.168.43.155",2009);
			e.printStackTrace();
		}
		

		SocketControler sc=new SocketControler(s);
		ChessGameControler controler = new ChessGameControler(cg,sc);

		ChessGameGUI frame = new ChessGameGUI(controler);
		cg.addObserver(frame);
		
		s.notify("(0,6)->(0,5)");
		s.notify("(0,1)->(0,3)");

	}

}
