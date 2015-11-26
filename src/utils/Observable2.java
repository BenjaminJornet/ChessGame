package utils;

import java.util.ArrayList;

public abstract class Observable2{ 
	
	public Observable2(){}
	

	private static ArrayList<Observeur2> observeurs = new ArrayList<Observeur2>();
	public void notify(String message) {
		int i=0;
		System.out.println("notify before loop");
		for(i=0;i<observeurs.size();i++){
			observeurs.get(i).update(message);
			System.out.println("il y a un observeur");
		}
		
	}

	public void addObserver(Observeur2 o){
		observeurs.add(o);
	}
}
