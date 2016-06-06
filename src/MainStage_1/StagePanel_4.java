package MainStage_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StagePanel_4 extends JPanel implements Runnable, MouseMotionListener {
	private Image_trigger pyramidal;
	private Image_trigger sprinkler;
	private Image_trigger garbage;
	
	private BufferedImage image_bg;
	
	private int character_x;
	private int character_y;
	
	private character ch;
	
	public Thread thread;
	
	public StagePanel_4() {
		initial();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image_bg, 0, 0, null);
		if(ch.getcounter()%2 == 1)
		{
			g.drawImage(ch.getImage_1(), character_x, character_y, null);
		}
		else
		{
			g.drawImage(ch.getImage_2(), character_x, character_y, null);
		}
	}
	
	private void loadImage() {
		String str = "materials/MainScene/04/";
		
		pyramidal = new Image_trigger(564,400,str+"pyramidal.png");
		sprinkler =new Image_trigger(564,400,str+"sprinkler.png");
		garbage = new Image_trigger(564,400,str+"garbage.png");
		
		
		
		try {
			File bg = new File(str+"04_sample.jpg");
			//File bg = new File(str+"02_VolleyCourt_lightoff.jpg");
			//File ch = new File(str+"sleepwalker.jpg");
		    image_bg = ImageIO.read(bg);
		    //image_ch = ImageIO.read(ch);
		}
		catch(Exception e) {
			System.out.println("wrong in background");
		}
	}
		
	private void move() {
			
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Get mouse dragged position and change suqare's position
		character_x = e.getX();
		character_y = e.getY();
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// No need to implement
	}
	
	@Override
	public void run() {
		while(character_x < 900){
			
			move();
			
			try {
				Thread.sleep(100);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	private void initial() {
		setBounds(0, 0, 900, 572);
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
        setFocusable(true);
        
        character_x = 10;
        
        ch = new character("materials/character");
        
        loadImage();
		
		thread = new Thread(this);
		
	}
}