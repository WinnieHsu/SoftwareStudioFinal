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
	
	public int bearCurrentX;
	public int bearCurrentY;
	public String bearDirection;


	public BearPanel(GameStage gs)
	{
		frame = gs;
		setBounds(900, 222, 300, 350);
    	//setBackground(Color.cyan);
    	setLayout(null);
    	
    	this.bearCurrentX = -10;
    	this.bearCurrentY = -20;
    	this.bearDirection = "up";
    	
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
	    g.drawImage(this.image_bear1,bearCurrentX,bearCurrentY,null);
	}
	@Override
	public void run(){
		while(frame.getScore()<frame.getWinScore()){
			if(this.bearDirection == "up" && this.bearCurrentY > -10)
				this.bearDirection = "down";
		    else if(this.bearDirection == "down" && this.bearCurrentY < -50)
		    	this.bearDirection = "up";
		
			if(this.bearDirection == "up")
				this.bearCurrentY++;
			else if(this.bearDirection == "down")
				this.bearCurrentY--;
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
