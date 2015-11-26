package our.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Serveur extends SocketPart{
	private Serveur cc=this;
	private ServerSocket ss = null;
	private Socket socket = null;
	private static Thread t;

	@Override
	public void sendMessage(String message) throws IOException {
		socketToolBox.send(socket, message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	Serveur(final String IP,final int port) {
		new Serveur(port);
	}
	public Serveur(final int port){
		t = new Thread() {

			public void run() {

				try {
					ss = new ServerSocket(port);
					System.out.println("Le serveur est � l'�coute du port "
							+ ss.getLocalPort());
					socket = ss.accept();

					while (true) {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(
										socket.getInputStream()));

						if (in != null) {

							if (in.ready()) {
								System.out.println("input stream");
								String message = in.readLine();
								cc.notify(message);
							}
						}

					}

				} catch (UnknownHostException e) {
					System.err
							.println("Impossible de se connecter � l'adresse "
									+ ss.getLocalSocketAddress());
				} catch (IOException e) {
					System.err.println("Le port " + ss.getLocalPort()
							+ " est d�j� utilis� !");
				}

			}
		};
		t.start();

	}

	public static void main(String[] argv) {
		new Serveur(2009);
	}

}
