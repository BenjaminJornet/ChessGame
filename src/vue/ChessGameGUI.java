package vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Coord;
import model.Couleur;
import model.PieceIHM;
import tools.ChessImageProvider;
import controler.controlerLocal.ChessGameControler;

@SuppressWarnings("serial")
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {
	private static final int TAILLE_CASE = 75;
	private ChessGameControler controler;
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;

	private int xInit;
	private int yInit;

	private ArrayList<Coord> path;

	private void addPiece(String name,Couleur color,Coord coord){
		addPiece(name,color,coord.x,coord.y);
	}

	private void addPiece(String name,Couleur color,int x,int y){

		int c = x+y*8;

		if(c>64){
			return;
		}
		String path = ChessImageProvider.getImageFile(name, color);
		JLabel piece = new JLabel( new ImageIcon(path) );
		JPanel panel = (JPanel)chessBoard.getComponent(c);
		panel.add(piece);
	}


	public ChessGameGUI(ChessGameControler c,String title, int width,int height){

		this.setControler(c);

		Dimension boardSize = new Dimension(width, height);

		//  Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		//Add a chess board to the Layered Pane 

		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout( new GridLayout(8, 8) );
		chessBoard.setPreferredSize( boardSize );
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel( new BorderLayout() );
			chessBoard.add( square );

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground( i % 2 == 0 ? Color.black : Color.white );
			else
				square.setBackground( i % 2 == 0 ? Color.white : Color.black );
		}

		initialise();
		this.setTitle(title);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		this.pack();
		this.setResizable(true);
		this.setLocationRelativeTo( null );
		this.setVisible(true);
		path = new ArrayList<Coord>();
	}

	private void initialise() {

		addPiece("Cavalier",Couleur.BLANC,1,7);
		addPiece("Cavalier",Couleur.BLANC,6,7);
		addPiece("Cavalier",Couleur.NOIR,1,0);
		addPiece("Cavalier",Couleur.NOIR,6,0);
		addPiece("Fou",Couleur.BLANC,2,7);
		addPiece("Fou",Couleur.BLANC,5,7);
		addPiece("Fou",Couleur.NOIR,2,0);
		addPiece("Fou",Couleur.NOIR,5,0);
		addPiece("Reine",Couleur.BLANC,3,7);
		addPiece("Reine",Couleur.NOIR,3,0);
		addPiece("Roi",Couleur.NOIR,4,0);
		addPiece("Roi",Couleur.BLANC,4,7);
		addPiece("Tour",Couleur.BLANC,0,7);
		addPiece("Tour",Couleur.BLANC,7,7);
		addPiece("Tour",Couleur.NOIR,0,0);
		addPiece("Tour",Couleur.NOIR,7,0);

		int r;
		for(r=0;r<=7;r++){
			addPiece("Pion",Couleur.BLANC,r,6);
			addPiece("Pion",Couleur.NOIR,r,1);
		}
	}


	public void mousePressed(MouseEvent e){
		chessPiece = null;
		Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel) 
			return;

		chessPiece = (JLabel)c;
		xInit = e.getX();
		yInit = e.getY();

	}

	//Move the chess piece around

	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null) return;
	}

	//Drop the chess piece back onto the chess board

	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouse released");
		if(chessPiece == null) return;

		//chessPiece.setVisible(false);

		int xEnd = e.getX();
		int yEnd = e.getY();

		Coord initCoord= getCurrentCoord(xInit,yInit);
		Coord finalCoord =getCurrentCoord(xEnd,yEnd);
		
		//verifie que ce n'est pas un clique
		if((!initCoord.equals(finalCoord))&&!controler.move(initCoord, finalCoord)){
			dialog(controler.getMessage());
		}
	}
	//convert mouse coord to chessboard coord
	private Coord getCurrentCoord(int x,int y){
		int X = (int)Math.floor(x/TAILLE_CASE);
		int Y = (int)Math.floor(y/TAILLE_CASE);
		return new Coord(X,Y);
	}
	private Coord getCurrentCoord(MouseEvent e){
		return getCurrentCoord(e.getX(),e.getY());
	}

	public void mouseClicked(MouseEvent e) {
		displayPath(e);

	}
	public void mouseMoved(MouseEvent e) {
		clearPath();
	}
	public void mouseEntered(MouseEvent e){

	}
	public void mouseExited(MouseEvent e) {

	}
	@Override
	public void update(Observable arg0, Object list_pieces) {

		for(int i = 0;i<chessBoard.getComponents().length;i++){
			JPanel c = (JPanel)chessBoard.getComponent(i);
			c.removeAll();
			c.validate();
			c.repaint();
		}
		LinkedList<PieceIHM> l = (LinkedList<PieceIHM>)list_pieces;
		for(PieceIHM p:l){
			List<Coord> allcoord = p.getList();
			for(Coord coord:allcoord){
				addPiece(p.getTypePiece(),p.getCouleur(),coord);
			}
		}
		this.revalidate();
		this.repaint();
	}

	public void setControler(ChessGameControler controler) {
		this.controler = controler;
	}

	public void displayPath(MouseEvent e){
		path = this.controler.getMouvementPossible(getCurrentCoord(e));
		System.out.println("list déplacement valide :"+path);
		for(Coord o:path){
			JPanel panel = (JPanel)chessBoard.getComponent(o.x+o.y*8);
			panel.setBackground(Color.decode("#B39DDB"));
		}
	}

	public void clearPath(){

		for(Coord o:path){
			JPanel panel = (JPanel)chessBoard.getComponent(o.x+o.y*8);
			int row = ( (o.x+o.y*8)/ 8) % 2;
			if (row == 0)
				panel.setBackground( o.x % 2 == 0 ? Color.black : Color.white );
			else
				panel.setBackground( o.x % 2 == 0 ? Color.white : Color.black );
		}
		path.clear();

	}
	private void dialog(String message){
		JOptionPane.showMessageDialog(null, message);
	}

}