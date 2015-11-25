package utils;

import java.util.ArrayList;

import our.sockets.SocketPart;

public abstract class Observable2 extends SocketPart{ 
	
	
	public Observable2(){}
	public Observable2(final String IP, int port) {
		super(IP, port);
	}

	private static ArrayList<Observeur2> observeurs = new ArrayList<Observeur2>();
	public void notify(String message) {
		int i=0;
		for(i=0;i<observeurs.size();i++){
			observeurs.get(i).update(message);
		}
		
	}

	public void addObserver(Observeur2 o){
		observeurs.add(o);
	}
}
