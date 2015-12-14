package sockets;

import java.io.IOException;
import java.net.Socket;

public class Client extends SocketPart {

	public Client(final String IP,final int Port) {

		cc=this;
		System.out.println("Demande de connexion");
		try {
			socket = new Socket(IP,Port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("connexion entrante");
		createThread();
	}

}