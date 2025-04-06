import  javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class PacMan extends  JPanel{
  private int rowCount=21;
  private int columCount=19;
  private int tileSize=32;
  private  int boardWidth=columCount*tileSize;
  private  int boardHeight=rowCount*tileSize;

  private  Image wallImage;
  private Image blueGhostImage;
  private Image redGhostImage;
  
  private Image pinkGhostImage;
  
  private Image orangeGhostImage;
  

  private Image packManUpImage;
  
  private Image packManDownImage;
  
  private Image packManLeftImage;
  
  private Image packManRightImage;

    public PacMan() {
     setPreferredSize(new Dimension(boardWidth, boardHeight));
      setBackground(Color.BLACK);
    
    //loading image in varibel
wallImage= new ImageIcon(getClass().getResource("./wall.png")).getImage(); 
orangeGhostImage= new ImageIcon(getClass().getResource("./orangeGhost.png")).getImage(); 
redGhostImage= new ImageIcon(getClass().getResource("./redGhost.png")).getImage(); 
pinkGhostImage= new ImageIcon(getClass().getResource("./pinkGhost.png")).getImage(); 
blueGhostImage= new ImageIcon(getClass().getResource("./blueGhost.png")).getImage(); 

packManDownImage= new ImageIcon(getClass().getResource("./pacmanDown.png")).getImage(); 
packManUpImage= new ImageIcon(getClass().getResource("./pacmanUp.png")).getImage(); 
packManRightImage= new ImageIcon(getClass().getResource("./pacmanLeft.png")).getImage(); 
packManLeftImage= new ImageIcon(getClass().getResource("./pacmanRight.png")).getImage(); 

    }
}
