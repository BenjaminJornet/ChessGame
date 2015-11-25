package our.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class socketToolBox {
	
	public static void send(Socket socket,String message) throws IOException{	
       // System.out.println(message);
		
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		
        out.println(message);
        out.flush();
		
                
        //socket.close();
	}
	/*public static void received(Socket socket) throws IOException{
		System.out.println("Connexion établie avec le serveur"); // Si le message s'affiche c'est que je suis connecté
		BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	    String message_distant = in.readLine();
	    System.out.println(message_distant);
	}*/

}
