package MainStage_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StagePanel_5 extends JPanel implements Runnable{
	private BufferedImage image_bg;
	
	
	private int character_x;
	private int character_y;
	
	
	private character ch;
	
	public boolean alive;
	public Thread thread;
	
	public StagePanel_5() {
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
		String str = "materials/MainScene/05/";
		
		try {
			File bg = new File(str+"05_BackDelta.JPG");	
			
		    image_bg = ImageIO.read(bg);
		    
		}
		catch(Exception e) {
			System.out.println("wrong in background, Stage_5");
		}
	}
	
	public void run() {
		repaint();
		while(character_x > 770){
			character_x --;
			ch.setcounter(ch.getcounter()+1);
			try {
				Thread.sleep(10);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
		alive = false;
		
	}
	
	private void initial() {
		setBounds(0, 0, 900, 572);
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
        setFocusable(true);
        
        character_x = 830;
        character_y = 190;
   
        
        alive = true;
        loadImage();
        
        ch = new character("materials/small_character");
		thread = new Thread(this);
		
	}

}
