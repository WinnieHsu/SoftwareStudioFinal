import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class BearPanel extends JPanel implements Runnable
{
	private GameStage frame;
	private BufferedImage image_bear1;
	
	public int duckCurrentX;
	public int duckCurrentY;
	public String duckDirection;


	public BearPanel(GameStage gs)
	{
		frame = gs;
		setBounds(900, 222, 300, 350);
    	//setBackground(Color.cyan);
    	setLayout(null);
    	
    	this.duckCurrentX = -10;
    	this.duckCurrentY = -20;
    	this.duckDirection = "up";
    	
    	setImage_right();
	}
	
	public void setImage_right(){
		try{
			image_bear1=ImageIO.read(new File("materials/01_DancingBears.jpg"));
		}
		catch(IOException ex){
			System.out.println("CANNOT load images in GamePanel");
		}
	}

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(this.image_bear1,duckCurrentX,duckCurrentY,null);
	}
	@Override
	public void run(){
		while(frame.getScore()<frame.getWinScore()){
			if(this.duckDirection == "up" && this.duckCurrentY > -10)
				this.duckDirection = "down";
		    else if(this.duckDirection == "down" && this.duckCurrentY < -50)
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
		}
		repaint();
	}
}
