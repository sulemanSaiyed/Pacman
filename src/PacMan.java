import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class PacMan extends  JPanel{
  class Block{
    int x;
    int y;
    int width;
    int height;
    Image image;

    int startX;
    int startY;

    Block(Image image, int x, int y, int width, int height){
      this.image=image;
      this.x= x;
      this.y=y;
      this.width=width;
      this.height=height;
      thsi.startX=startX;
      this.startY=startY;

      
    }
  }
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

  

//X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X       X    X",
        "XXXX XXXX XXXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXrXX X XXXX",
        "O       bpo       O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX" 
    };
HashSet<Block>walls;
HashSet<Block>foods;
HashSet<Block>ghosts;
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
    public void loadMap(){
      walls=new HashSet<Block>();
      foods=new HashSet<Block>();
      ghosts=new HashSet<Block>();
    }
}
