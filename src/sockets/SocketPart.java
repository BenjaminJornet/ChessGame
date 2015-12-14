package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

public abstract class SocketPart extends Observable {

	/*public void sendMessage(String message)throws IOException {
		System.out.println("erreur de codage");		
	}*/
	protected Socket socket = null;
	protected static Thread t;
	protected SocketPart cc;

	public void sendMessage(String message) throws IOException{	
		if(socket==null){
			System.err.println("socket not linked");
			return;
		}
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
	public void createThread(){

		t = new Thread() {

			public void run() {


				while (true) {
					BufferedReader in = null;
					try {
						in = new BufferedReader(
								new InputStreamReader(socket.getInputStream()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (in != null) {
						try {
							if(in.ready()) {
								String message = in.readLine();
								setChanged();
								cc.notifyObservers(message);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}
		};
		t.start();

	}

}
