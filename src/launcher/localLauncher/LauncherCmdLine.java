package launcher.localLauncher;

import vue.ChessGameCmdLine;
import controler.controlerLocal.ChessGameControler;


/**
 * @author francoise.perrin
 * Lance l'exécution d'un jeu d'échec en mode console.
 */
public class LauncherCmdLine implements Observeur{	
	
	public static void main(String[] args) {		
		
		ChessGame chessGame;
		ChessGameControler chessGameControler;		
		
		chessGame = new ChessGame();	
		chessGameControler = new ChessGameControler(chessGame);
				
		new ChessGameCmdLine(chessGameControler);	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		 
		
	}

}
