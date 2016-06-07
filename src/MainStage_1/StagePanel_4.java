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

public class StagePanel_4 extends JPanel implements Runnable, MouseListener {
	private Image_mouse pyramidal;
	private Image_mouse sprinkler;
	private Image_mouse garbage;
	
	private BufferedImage image_bg;
	
	private int character_x;
	private int character_y;
	private int destination_x;
	private int destination_y;
	
	
	private character ch;
	
	public Thread thread;
	
	public StagePanel_4() {
		initial();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image_bg, 0, 0, null);
		g.drawImage(garbage.getImage(), garbage.getX(), garbage.getY(), null);
		g.drawImage(sprinkler.getImage(), sprinkler.getX(), sprinkler.getY(),null);
		g.drawImage(pyramidal.getImage(), pyramidal.getX(), pyramidal.getY(), null);
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
		pyramidal = new Image_mouse(335,340,str+"pyramidal.png");
		sprinkler = new Image_mouse(600,470,str+"sprinkler.png");
		garbage = new Image_mouse(-50,0,str+"garbage.png");
		
		try {
			File bg = new File(str+"04_Crossroad_oldTree.jpg");	
		    image_bg = ImageIO.read(bg);
		}
		catch(Exception e) {
			System.out.println("wrong in background");
		}
	}
		
	private void move() {
		int change = 0;
		if(character_x > destination_x)
		{
			character_x-=2;
			change++;
		}
		if(character_y > destination_y)
		{
			character_y--;
			change++;
		}
		if(character_y < destination_y)
		{
			character_y++;
			change++;
		}
		if(change > 0)
		{
			ch.setcounter(ch.getcounter()+1);
		}
	}
	

	public void mouseClicked(MouseEvent e) { }
	
	public void	mouseEntered(MouseEvent e) { }
	
	public void	mouseExited(MouseEvent e) { }

	public void	mousePressed(MouseEvent e) {
		if(e.getY() < 340 && e.getY() > 270 && e.getX() > 590 && e.getX() < 660)
		{
			garbage.setX(500);
			destination_x = 660;
			destination_y = 300;	
		}
		if(e.getY() < 550 && e.getY() > 500 && e.getX() > 650 && e.getX() < 680)
		{
			garbage.setX(500);
			destination_x = 660;
			destination_y = 500-ch.getImage_2().getHeight();
		}
		System.out.println(e.getX()+" "+e.getY());
		
		repaint();
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private void remove() {
		
	}
	
	@Override
	public void run() {
		while(character_x < 900){
			
			move();
			remove();
			try {
				Thread.sleep(10);
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
        
        character_x = 830;
        character_y = 350;
        destination_x = 830;
        destination_y = 350;
        
        ch = new character("materials/character");
        
        loadImage();
        addMouseListener(this);

		thread = new Thread(this);
		
	}
}