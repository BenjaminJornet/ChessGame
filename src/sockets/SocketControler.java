package sockets;

import java.io.IOException;
import java.util.Observer;

public class SocketControler {
	SocketPart client;
	Thread t;

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


	public void add(Observer c) {
		client.addObserver(c);

	}


}
