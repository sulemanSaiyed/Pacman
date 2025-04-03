import  javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax..swing.*;

public class PacMan extends  JPanel{
  private int rowCount=21;
  private int columCount=19;
  private int tileSize=32;
  private  int boardWidth=columCount*tileSize;
  private  int boardHeight=rowCount*tileSize;

    public PacMan() {
     setPreferredSize(new Dimension(boardWidth, boardHeight));
      setBackground(Color.BLACK);
    }

  
}
