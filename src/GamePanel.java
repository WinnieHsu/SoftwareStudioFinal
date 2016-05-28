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

public class GamePanel extends JPanel implements Runnable
{
	private GameStage frame;
	private BufferedImage image_bg;
	private JLabel label;
	
	public int bgCurrentX;


	public GamePanel(GameStage gs)
	{
		frame = gs;
		setBounds(0, 0, 900, 572);
    	setBackground(Color.cyan);
    	setLayout(null);
    	
    	this.bgCurrentX = 0;
    	
    	setImage_right();
    	
    	this.label = new JLabel();
    	this.label.setText("Score: "+frame.getScore());
    	this.label.setBackground(Color.black);
    	this.label.setFont(new Font("Serif",Font.ITALIC+Font.BOLD,24));
    	this.label.setBounds(650, 10, 200, 40);
    	this.add(this.label);
	}
	
	public void setImage_right(){
		try{
			image_bg=ImageIO.read(new File("materials/DSC_0219.JPG"));
		}
		catch(IOException ex){
			System.out.println("CANNOT load images in GamePanel");
		}
	}
	
	public void setScoreText(){
    	this.label.setText("Score: "+frame.getScore());
    }
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(this.image_bg,bgCurrentX,0,null);
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
		    setScoreText();
		}
		repaint();
		setScoreText();
	}
}
