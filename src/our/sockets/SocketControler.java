package our.sockets;

import java.io.IOException;

import controler.controlerLocal.ChessGameControler;

public class SocketControler {
	SocketPart client;

	public SocketControler(SocketPart c){
		client = c;
		
	}

	public void sendMove(int x, int y, int x2, int y2) {
		StringBuffer sb = new StringBuffer();
		sb.append("(").append(x).append(",").append(y).append(")");
		sb.append("->");
		sb.append("(").append(x2).append(",").append(y2).append(")");

		String message = sb.toString();
		try {
			client.sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void read(){
		client.readMessage();
	}

	public void add(ChessGameControler c) {
		client.addObserver(c);
		
	}
	

}
