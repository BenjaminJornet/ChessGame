package our.sockets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

	public static Socket socket = null;
	public static Thread t;
	public void sendMessage(String message) throws IOException
	{
		socketToolBox.send(socket,message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	Client() {

		t = new Thread(){
			public void run(){
				try {

					System.out.println("Demande de connexion");
					socket = new Socket("127.0.0.1",2009);


					sendMessage("Vous êtes connecté zéro");
					
					sendMessage("Vous êtes connecté un");
									
					sendMessage("Vous êtes connecté deux");
					while(true){
						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


						if(in!=null){
							if(in.ready()){
								System.out.println(in.readLine());
								
							}

						}
					}

				} catch (UnknownHostException e) {
					System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
				} catch (IOException e) {
					System.err.println("Aucun serveur à l'écoute du port "+socket.getLocalPort());
				}
			}};
			t.start();
	}
	public static void main(String[] args){
		new Client();
	}

}