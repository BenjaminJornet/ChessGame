package vue;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import controler.controlerLocal.ChessGameControler;
import model.ChessGame;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import utils.Observeur;
import tools.ChessImageProvider;
 
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observeur {
  private ChessGameControler controler;
JLayeredPane layeredPane;
  JPanel chessBoard;
  JLabel chessPiece;
  int xAdjustment;
  int yAdjustment;
  
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
	  this.controler = c;
	  setDefaultCloseOperation(DISPOSE_ON_CLOSE );
	  pack();
	  setResizable(true);
	  setLocationRelativeTo( null );
	  setVisible(true);
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
  
  }
 
  private void initialise() {
	  addPiece("Cavalier",Couleur.BLANC,1,0);
	  addPiece("Cavalier",Couleur.BLANC,6,0);
	  addPiece("Cavalier",Couleur.NOIR,1,7);
	  addPiece("Cavalier",Couleur.NOIR,6,7);
	  addPiece("Fou",Couleur.BLANC,2,0);
	  addPiece("Fou",Couleur.BLANC,5,0);
	  addPiece("Fou",Couleur.NOIR,2,7);
	  addPiece("Fou",Couleur.NOIR,5,7);
	  addPiece("Reine",Couleur.BLANC,3,0);
	  addPiece("Reine",Couleur.NOIR,3,7);
	  addPiece("Roi",Couleur.NOIR,4,7);
	  addPiece("Roi",Couleur.BLANC,4,0);
	  addPiece("Tour",Couleur.BLANC,0,0);
	  addPiece("Tour",Couleur.BLANC,7,0);
	  addPiece("Tour",Couleur.NOIR,0,7);
	  addPiece("Tour",Couleur.NOIR,7,7);

	  int r;
	  for(r=0;r<=7;r++){
		  addPiece("Pion",Couleur.BLANC,r,1);
		  addPiece("Pion",Couleur.NOIR,r,6);
	  }
	
}


public void mousePressed(MouseEvent e){
  chessPiece = null;
  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
 
  if (c instanceof JPanel) 
  return;
 
  Point parentLocation = c.getParent().getLocation();
  xAdjustment = parentLocation.x - e.getX();
  yAdjustment = parentLocation.y - e.getY();
  chessPiece = (JLabel)c;
  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
  }
 
  //Move the chess piece around
  
  public void mouseDragged(MouseEvent me) {
  if (chessPiece == null) return;
  int xStart = me.getX(), yStart = me.getY();
  int xEnd = xStart + xAdjustment,yEnd = yStart + yAdjustment;
  
  controler.move(new Coord(xStart,yStart),new  Coord(xEnd,yEnd));
  System.out.println("From " + me.getX() + "," + me.getY()+ " to "+ (me.getX() + xAdjustment)+", " + (me.getY() + yAdjustment));
  //chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
 
 }
 
  //Drop the chess piece back onto the chess board
 
  public void mouseReleased(MouseEvent e) {
  if(chessPiece == null) return;
 
  chessPiece.setVisible(false);
  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
 
  if (c instanceof JLabel){
  Container parent = c.getParent();
  parent.remove(0);
  parent.add( chessPiece );
  }
  else {
  Container parent = (Container)c;
  parent.add( chessPiece );
  }
 
  chessPiece.setVisible(true);
  }
 
  public void mouseClicked(MouseEvent e) {
  
  }
  public void mouseMoved(MouseEvent e) {
 }
  public void mouseEntered(MouseEvent e){
  
  }
  public void mouseExited(MouseEvent e) {
  
  }
 
@Override
	public void update(ChessGame c) {
		List<PieceIHM> list_pieces = c.getEchiquier().getPiecesIHM();
		chessBoard.removeAll();
		for(PieceIHM p:list_pieces){
				List<Coord> allcoord = p.getList();
				for(Coord coord:allcoord){
					addPiece(p.getTypePiece(),p.getCouleur(),coord);
				}
		}
	}
}