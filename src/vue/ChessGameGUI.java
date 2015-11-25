package vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Coord;
import model.Couleur;
import model.PieceIHM;
import tools.ChessImageProvider;
import utils.Observeur;
import controler.controlerLocal.ChessGameControler;

@SuppressWarnings("serial")
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observeur {
	private static final int TAILLE_CASE = 75;
	private ChessGameControler controler;
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;

	private int xInit;
	private int yInit;

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


	public ChessGameGUI(ChessGameControler c){

		this.setControler(c);

		Dimension boardSize = new Dimension(600, 600);

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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		this.pack();
		this.setResizable(true);
		this.setLocationRelativeTo( null );
		this.setVisible(true);

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
		yInit= e.getY();

	}

	//Move the chess piece around

	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null) return;



		// controler.move(new Coord(xStart,yStart),new  Coord(xEnd,yEnd));
		////System.out.println("From " + me.getX() + "," + me.getY()+ " to "+ (me.getX() + xAdjustment)+", " + (me.getY() + yAdjustment));
		//chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);

	}

	//Drop the chess piece back onto the chess board

	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouse released");
		if(chessPiece == null) return;

		chessPiece.setVisible(false);
		//Component c =  chessBoard.findComponentAt(e.getX(), e.getY());


		/* if (c instanceof JLabel){
  Container parent = c.getParent();
  parent.remove(0);
  parent.add( chessPiece );
  }
  else {
  Container parent = (Container)c;
  parent.add( chessPiece );
  }*/
		//Component c =  chessBoard.findComponentAt(e.getX(), e.getY());



		int xEnd = e.getX();
		int yEnd = e.getY();







		Coord initCoord=new Coord((int)Math.floor(xInit/TAILLE_CASE),(int)Math.floor(yInit/TAILLE_CASE));
		Coord finalCoord = new Coord((int)Math.floor(xEnd/TAILLE_CASE),(int)Math.floor(yEnd/TAILLE_CASE));
		//System.out.println(initCoord);
		//System.out.println(finalCoord);
		controler.move(initCoord, finalCoord);


		// chessPiece.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {
		//System.out.println("click on "+Math.floor(e.getX()/TAILLE_CASE)+" ,"+Math.floor(e.getY()/TAILLE_CASE));

	}
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e){

	}
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void update(List<PieceIHM> list_pieces) {

		for(int i = 0;i<chessBoard.getComponents().length;i++){
			JPanel c = (JPanel)chessBoard.getComponent(i);
			c.removeAll();
			c.validate();
			c.repaint();
		}
		for(PieceIHM p:list_pieces){
			List<Coord> allcoord = p.getList();
			for(Coord coord:allcoord){
				addPiece(p.getTypePiece(),p.getCouleur(),coord);
			}
		}
	}

	public void setControler(ChessGameControler controler) {
		this.controler = controler;
	}
}