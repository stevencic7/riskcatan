import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RiskCatan extends JPanel implements MouseListener{
   
   public RiskCatan(){addMouseListener(this);}
   
   public Tile[][] tiles = new Tile[14][10];
   public int mouseX = -1;
   public int mouseY = -1;
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      g.setColor(Color.BLACK);
      Font myFont = new Font ("Courier New", 1, 25);
      g.setFont(myFont);
      paintBoard(g);       
   }
   
   public void paintBoard(Graphics g){
      drawHex(g);
   }
   
   public void drawHex(Graphics g){
     int xAdj = 0;    
     for(int j = 0; j<14; j++){  
         xAdj = (int)(j*39*1.15);
         for(int i = 0; i<10; i++){
            int adj = i*60;
            if(j%2==1){
               adj+=30;
            }
            if(tiles[j][i] == null){
               tiles[j][i] = new Tile(pickColor(g), null, 0, (int)((60+xAdj)*1.15), 60+adj);
            }
            int[] x = { (int)((0+xAdj)*1.15) , (int)((15+xAdj) * 1.15) , (int)((45+xAdj)*1.15), (int)((60+xAdj)* 1.15),
            (int)((45+xAdj)* 1.15), (int)((15+xAdj)* 1.15)};
            int[] y = {30+adj,0+adj,0+adj,30+adj,60+adj,60+adj};
            g.setColor(tiles[j][i].getColor());
            g.fillPolygon(x,y,6);
            g.setColor(Color.BLACK);
            g.drawPolygon(x,y,6);
            
            g.drawLine( (int) ((15+xAdj)*1.15) , 0, (int) ((15+xAdj)*1.15) ,650);
            g.drawLine( (int) ((45+xAdj)*1.15) , 0, (int) ((45+xAdj)*1.15) ,650);

         
         }
      }
      
//       for(int a = 0; a<14; a++){
//          g.drawLine( 17 + (a*3*17), 0,          17 + (a*3*17),600);
//          g.drawLine( 51 + (a*3*17), 0,          51 + (a*3*17),600);
//      }
      
    }
   
    public Color pickColor(Graphics g){
       Color[] colors = {new Color(97,192,0), new Color(90,9,12), new Color(217,238,83), new Color(19,36,58), new Color(1,24,7)};
       //                light green,         brick               yellow               ore                    dark green 
       //, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE       
       //g.setColor(colors[(int)(Math.random()*5)]);
       return( colors[(int)(Math.random()*5)]);
    }
   
   
   public void mouseExited(MouseEvent arg0){} //required MouseListener overrides
   public void mouseEntered(MouseEvent arg0) {}
   public void mousePressed(MouseEvent arg0) {}
   public void mouseReleased(MouseEvent arg0) {}
   
   public void mouseClicked(MouseEvent e){  
      mouseX = e.getX();
      mouseY = e.getY();
      int x = determineTileX(mouseX);
      int y = (int)(mouseY/60);
      
      System.out.println("x: " + x + " y: " + y);
      System.out.println("Resource: " + tiles[x][y].resource());
      System.out.println("Playername: " + tiles[x][y].getPlayerName());
      System.out.println("Troops: " + tiles[x][y].getTroops());
   }
   
   public int determineTileX(int x){
      return 1;
   }
   

   public static void main(String[] args){
      JFrame window = new JFrame("CatanBoard");
      window.setBounds(100,100,800,800);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel gamePanel = new RiskCatan();
      gamePanel.setBackground(Color.WHITE);
      window.getContentPane().add(gamePanel);
      window.setVisible(true);
      gamePanel.repaint();
      //while(true){gamePanel.repaint();}
      

      
   }
}