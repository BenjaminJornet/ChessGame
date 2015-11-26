package our.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import utils.Observable2;

public abstract class SocketPart extends Observable2 {

	/*public void sendMessage(String message)throws IOException {
		System.out.println("erreur de codage");		
	}*/
	protected Socket socket = null;
	protected static Thread t;
	
	public void sendMessage(String message) throws IOException{	

		PrintWriter out = new PrintWriter(socket.getOutputStream());
		out.println(message);
		out.flush();
		System.out.println("Socket send :"+message);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
