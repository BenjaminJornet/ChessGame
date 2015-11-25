package our.sockets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Serveur {
	public static ServerSocket ss = null;
	public static Thread t;
	
	public void sendMessage(Socket socket,String message) throws IOException
	{
		socketToolBox.send(socket,message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	Serveur(){
		t = new Thread(){
			public void run(){

				try {
					ss = new ServerSocket(2009);
					System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());
					Socket socketduserveur = ss.accept();
					
					while(true){
						BufferedReader in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));

						if(in!=null){

							if(in.ready()){
								System.out.println("input stream");
								System.out.println(in.readLine());
								System.out.println("end read");
								sendMessage(socketduserveur,"arrête de spammer");
							}
						}
						
					}


				} catch (UnknownHostException e) {
					System.err.println("Impossible de se connecter à l'adresse "+ ss.getLocalSocketAddress());
				}  catch (IOException e) {
					System.err.println("Le port "+ss.getLocalPort()+" est déjà utilisé !");
				}

			}};
			t.start();

	}
	public static void main(String [] argv){
		new Serveur();
	}


}
