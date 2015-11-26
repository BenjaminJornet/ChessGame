package our.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class socketToolBox {

	public static void send(Socket socket,String message) throws IOException{	

		PrintWriter out = new PrintWriter(socket.getOutputStream());
		out.println(message);
		out.flush();

		System.out.println("Socket send :"+message);

	}

}
