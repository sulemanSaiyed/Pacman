import java.awt.*;
import java.awt.event.*;
import java.sql.Blob;

import java.util.HashSet;
import java.util.Random;
import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;

public class PacMan extends  JPanel implements ActionListener, KeyListener{
  class Block{
    int x;
    int y;
    int width;
    int height;
    Image image;

    int startX;
    int startY;

    char direction = 'U'; //udlr
    int velocityX=0;
    int velocityY=0;
    Block(Image image, int x, int y, int width, int height){
      this.image=image;
      this.x= x;
      this.y=y;
      this.width=width;
      this.height=height;
      this.startX=x;
      this.startY=y;
    }
    void updateDirection(char Direction){
      this.direction=Direction;
      updateVelocity();
    }
    void updateVelocity(){
      if(this.direction=='U'){
        this.velocityX=0;
        this.velocityY=-tileSize/4;
      }
      else if (this.direction=='D'){
        this.velocityX=0;
        this.velocityY=tileSize/4;
      }
      else if (this.direction=='L'){
        this.velocityX=-tileSize/4;
        this.velocityY=0;
      }
      else if  (this.direction=='R'){
        this.velocityX=tileSize/4;
        this.velocityY=-0;
      }
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
Block pacMan;
Timer gameLoop;

    public PacMan() {
     setPreferredSize(new Dimension(boardWidth, boardHeight));
      setBackground(Color.BLACK);
      addKeyListener(this);
      setFocusable(true);
    
    //loading image in varibel
wallImage= new ImageIcon(getClass().getResource("/images/wall.png")).getImage(); 
orangeGhostImage= new ImageIcon(getClass().getResource("/images/orangeGhost.png")).getImage(); 
redGhostImage= new ImageIcon(getClass().getResource("/images/redGhost.png")).getImage(); 
pinkGhostImage= new ImageIcon(getClass().getResource("/images/pinkGhost.png")).getImage(); 
blueGhostImage= new ImageIcon(getClass().getResource("/images/blueGhost.png")).getImage(); 

packManDownImage= new ImageIcon(getClass().getResource("/images/pacmanDown.png")).getImage(); 
packManUpImage= new ImageIcon(getClass().getResource("/images/pacmanUp.png")).getImage(); 
packManRightImage = new ImageIcon(getClass().getResource("/images/pacmanRight.png")).getImage();
packManLeftImage = new ImageIcon(getClass().getResource("/images/pacmanLeft.png")).getImage();


loadMap();

gameLoop = new Timer(50, this);
gameLoop.start();
    }
  
    public void loadMap(){
      walls=new HashSet<Block>();
      foods=new HashSet<Block>();
      ghosts=new HashSet<Block>();
  

       for (int r=0; r<rowCount;r++){
        for (int c=0;c<columCount;c++){
          String row=tileMap[r];
          char tileMapChar =row.charAt(c);

          int x=c*tileSize;
          int y =r*tileSize;
          if(tileMapChar=='X'){// block wall
Block wall=new Block(wallImage, x, y, tileSize, tileSize);
walls.add(wall);
          }
          else if (tileMapChar =='b'){// blue ghost ka
Block ghost= new Block(blueGhostImage, x, y, tileSize, tileSize);
ghosts.add(ghost);
          }
          else if (tileMapChar =='o'){// ornge ghost ka
            Block ghost= new Block(orangeGhostImage, x, y, tileSize, tileSize);
            ghosts.add(ghost);
                      }
                      else if (tileMapChar =='p'){// pink ghost ka
                        Block ghost= new Block(pinkGhostImage, x, y, tileSize, tileSize);
                        ghosts.add(ghost);
                                  }
                                  else if (tileMapChar =='r'){// red ghost ka
                                    Block ghost= new Block(redGhostImage, x, y, tileSize, tileSize);
                                    ghosts.add(ghost);
                                              }
                                              else if(tileMapChar=='P'){//packman loda
                                                pacMan=new Block(packManRightImage, x, y, tileSize, tileSize);
                                              }
                                              else if (tileMapChar== ' '){//food
                                                Block food= new Block(null, x+14, y+14, 4, 4);
                                              foods.add(food);}
        }
       }
    }  
   
    public void paintComponent(Graphics g){

     super.paintComponent(g);
      draw(g);
    }
    public void draw(Graphics g){
      g.drawImage(pacMan.image, pacMan.x, pacMan.y, pacMan.width,  pacMan.height, null);
      for(Block ghost:ghosts){
      g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);
      }
      for(Block wall:walls){
        g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }
        g.setColor(Color.white);
        for(Block food:foods){
          g.fillRect(food.x, food.y, food.width, food.height);
          }
    }
public void move(){
  pacMan.x +=pacMan.velocityX;
  pacMan.y +=pacMan.velocityY;
  for(Block wall:walls){
    if(collision(pacMan, wall)){

pacMan.x-=pacMan.velocityX;
pacMan.y-=pacMan.velocityY;
break;

}  }
}
public boolean  collision(Block a, Block b){
  return  a.x<b.x+b.width&&
          a.x+a.width>b.x &&
          a.y< b.y + b.height &&
          a.y+ a.height>b.y;

}
    @Override
    public void actionPerformed(ActionEvent e) {
      move();
      repaint();
      
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
     

    }

    @Override
    public void keyReleased(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_UP){
      pacMan.updateDirection('U');
    }
    else if(e.getKeyCode()==KeyEvent.VK_DOWN){
      pacMan.updateDirection('D');
    }
    else if(e.getKeyCode()==KeyEvent.VK_LEFT){
      pacMan.updateDirection('L');
    }
   else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
      pacMan.updateDirection('R');
    }
    }

    }

