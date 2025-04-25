
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
     int rowCount=21;
     int columCount=19;
     int tileSize=32;
     int boardWidth=columCount*tileSize;
     int boardHeight=rowCount*tileSize;
   
     JFrame frame=new JFrame("pac man");
     //frame.setVisible(true);
     frame.setSize(boardWidth, boardHeight);
frame.setLocationRelativeTo(null);
frame.setResizable(false);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
PacMan man=new PacMan();
frame.add(man);
frame.pack(); // full size making window
   frame.setVisible(true);
    }   
   
} 
       