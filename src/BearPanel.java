import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BearPanel extends JPanel implements Runnable
{
	private GameStage frame;
	private ArrayList<BufferedImage> image_bear;
	private int bear_index; //index for ArrayList image_bear 
	public int bearX, bearY;


	public BearPanel(GameStage gs)
	{
		frame = gs;
		setBounds(900, 222, 300, 350);
    	setBackground(Color.cyan);
    	setLayout(null);
    	
    	bearX = -5;
    	bearY = -17;
    	bear_index = 0;
    	image_bear = new ArrayList<BufferedImage>();
    	setImage_right();
	}
	
	public void setImage_right(){
		try{
			image_bear.add( ImageIO.read(new File("materials/BearPic/01_Bears.jpg")) );
			image_bear.add( ImageIO.read(new File("materials/BearPic/02_Bears.jpg")) );
			image_bear.add( ImageIO.read(new File("materials/BearPic/03_Bears.jpg")) );
			image_bear.add( ImageIO.read(new File("materials/BearPic/04_Bears.jpg")) );
			image_bear.add( ImageIO.read(new File("materials/BearPic/05_Bears.jpg")) );
			image_bear.add( ImageIO.read(new File("materials/BearPic/06_Bears.jpg")) );
			image_bear.add( ImageIO.read(new File("materials/BearPic/07_Bears.jpg")) );
		}
		catch(IOException ex){
			System.out.println("CANNOT load images in BearPanel");
		}
	}

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(image_bear.get(bear_index),bearX,bearY,null);
	}
	
	public void setIndex(int i) {
		bear_index = i;
	}
	
	@Override
	public void run(){
		/*while(frame.getScore()<frame.getWinScore()){
			if(this.duckDirection == "up" && this.duckCurrentY > -20)
				this.duckDirection = "down";
		    else if(this.duckDirection == "down" && this.duckCurrentY < 20)
		    	this.duckDirection = "up";
		
			if(this.duckDirection == "up")
				this.duckCurrentY++;
			else if(this.duckDirection == "down")
				this.duckCurrentY--;
		    try {
		    	Thread.sleep(80);
		    } 
		    catch(InterruptedException e) {
		    	//e.printStackTrace();
		    }
		    repaint();
		}*/
		repaint();
	}
}
