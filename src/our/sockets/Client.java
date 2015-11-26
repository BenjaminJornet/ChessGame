package our.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends SocketPart {

	//
	private Client cc;
		
	/*@Override
	public void sendMessage(String message) throws IOException {
		socketToolBox.send(socket, message);
	}*/

	public Client(final String IP,final int Port) {
		
		cc=this;
		t = new Thread() {

			public void run() {
				try {

					System.out.println("Demande de connexion");
					socket = new Socket(IP,Port);
					System.out.println("connexion entrante");


					/*	sendMessage("Vous �tes connect� z�ro");

					sendMessage("Vous �tes connect� un");

					sendMessage("Vous �tes connect� deux");*/

					while(true){
						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


						if(in != null) {
							if(in.ready()) {
								String message = in.readLine();
								cc.notify(message);

							}

						}
					}

				} catch (UnknownHostException e) {
					System.err.println("Impossible de se connecter � l'adresse "+socket.getLocalAddress());
				} catch (IOException e) {
					System.err.println("Aucun serveur � l'�coute du port "+socket.getLocalPort());
				}
			}

		};
		t.start();
	}

}