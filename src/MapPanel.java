import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.JPanel;


public class MapPanel extends JPanel implements Runnable
{
	private GameStage frame;
	private BufferedImage image_map;

	public MapPanel(GameStage gs)
	{
		frame = gs;
		setBounds(900, 0, 300, 222);
    	setBackground(Color.cyan);
    	setLayout(null);
   	
    	setImage_right();
	}
	
	public void setImage_right(){
		try{
			image_map=ImageIO.read(new File("materials/manp1.GIF"));
		}
		catch(IOException ex){
			System.out.println("CANNOT load images in GamePanel");
		}
	}
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(this.image_map,0,0,null);
	}
	@Override
	public void run(){
		while(frame.getScore()<frame.getWinScore()){
		    try {
		    	Thread.sleep(30);
		    } 
		    catch(InterruptedException e) {
		    	//e.printStackTrace();
		    }
		    repaint();
		}
		repaint();
	}
}
