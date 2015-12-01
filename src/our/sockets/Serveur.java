package our.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Serveur extends SocketPart{
	
	private ServerSocket ss = null;

	public Serveur(final int port){
		
		cc = this;
		try {
			ss = new ServerSocket(port);
			System.out.println("Le serveur est � l'�coute du port " + ss.getLocalPort());
			socket = ss.accept();
		} catch (UnknownHostException e) {
			System.err.println("Impossible de se connecter � l'adresse " + ss.getLocalSocketAddress());
		} catch (IOException e) {
			System.err.println("Le port " + ss.getLocalPort());
		}
		createThread();
	}
	
	public Serveur(final String IP,final int port) {
		new Serveur(port);
	}

}
