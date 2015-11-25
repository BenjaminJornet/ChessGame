package our.sockets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	public static ServerSocket ss = null;
	public static Thread t;



	public static void main(String[] args) {
		t = new Thread(){
			public void run(){

				try {
					ss = new ServerSocket(2009);
					System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());
					Socket socketduserveur = ss.accept();
					while(true){
						BufferedReader in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
						OutputStreamWriter out = new OutputStreamWriter(socketduserveur.getOutputStream());
						//socketToolBox.send(socketduserveur,"Vous êtes connecté zero");


						if(in!=null){
							if(in.ready()){
								System.out.println("input stream");
								System.out.println(in.readLine());
								System.out.println("end read");
								//in.close();
							}
						}
						if(out!=null){
							//System.out.println("output stream");			
						}

						// ss.close();
						//System.out.println("echo");
					}


				} catch (IOException e) {
					System.err.println("Le port "+ss.getLocalPort()+" est déjà utilisé !");
				}

			}};
			t.start();

	}


}
