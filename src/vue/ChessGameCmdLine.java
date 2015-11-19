package vue;

import model.ChessGame;
import model.Coord;
import model.Echiquier;
import utils.Observeur;
import controler.controlerLocal.ChessGameControler;



/**
 * @author francoise.perrin
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 * 
 */
public class ChessGameCmdLine implements Observeur{
	
	public   ChessGameCmdLine(ChessGameControler chessGameControler) {
		
		//System.out.println(chessGameControler + "\n");
		
		chessGameControler.move(new Coord(3,6), new Coord(3, 4));	// true
		//System.out.print("\n Déplacement de 3,6 vers 3,4 : ");
		//System.out.println(chessGameControler.getMessage() + "\n");	
		//System.out.println(chessGameControler + "\n");
		
		chessGameControler.move(new Coord(3,4), new Coord(3, 6));	// false
		//System.out.print("\n Déplacement de 3,4 vers 3,6 : ");
		//System.out.println(chessGameControler.getMessage() + "\n");	
		//System.out.println(chessGameControler + "\n");
		
		chessGameControler.move(new Coord(4, 1), new Coord(4, 3));	// true
		//System.out.print("\n Déplacement de 4,1 vers 4,3 : ");
		//System.out.println(chessGameControler.getMessage() + "\n");	
		//System.out.println(chessGameControler + "\n");
		
		chessGameControler.move(new Coord(3, 4), new Coord(3, 4));	// false
		//System.out.print("\n Déplacement de 3,4 vers 3,4 : ");
		//System.out.println(chessGameControler.getMessage() + "\n");	
		//System.out.println(chessGameControler + "\n");
		
		chessGameControler.move(new Coord(3, 4), new Coord(4, 3));	// true
		//System.out.print("\n Déplacement de 3,4 vers 4,3 : ");
		//System.out.println(chessGameControler.getMessage() + "\n");	
		//System.out.println(chessGameControler + "\n");
		
	}

	@Override
	public void update(Echiquier c) {
		// TODO Auto-generated method stub
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 * pour retourner le plateau de jeu sous la forme suivante
		 *     0    1    2    3    4    5    6    7 
		 * 0 N_T1 N_C1 N_F1 N_r_ N_R_ N_F2 N_C2 N_T2 
		 * 1 N_P1 N_P2 N_P3 N_P4 N_P5 N_P6 N_P7 N_P8 
		 * 2  __   __   __   __   __   __   __   __  
		 * 3  __   __   __   __   __   __   __   __  
		 * 4  __   __   __   __   __   __   __   __  
		 * 5  __   __   __   __   __   __   __   __  
		 * 6 B_P1 B_P2 B_P3 B_P4 B_P5 B_P6 B_P7 B_P8 
		 * 7 B_T1 B_C1 B_F1 B_r_ B_R_ B_F2 B_C2 B_T2 
		 */
	}

}
