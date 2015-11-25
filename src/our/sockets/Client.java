package our.sockets;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

	public static Socket socket = null;
	public static Thread t1;
	public static void sendMessage(String message) throws IOException
	{
		socketToolBox.send(socket,message);
	}
	public static void main(String[] args) {


		try {

			System.out.println("Demande de connexion");
			socket = new Socket("127.0.0.1",2009);
			
			sendMessage("Vous êtes connecté zéro");
			sendMessage("Vous êtes connecté un");


		    // socket.close();
		     
		//	t1 = new Thread(new Connexion(socket));
			//t1.start();



		} catch (UnknownHostException e) {
			System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
		} catch (IOException e) {
			System.err.println("Aucun serveur à l'écoute du port "+socket.getLocalPort());
		}



	}

}