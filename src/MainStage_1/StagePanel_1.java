package MainStage_1;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class StagePanel_1 extends JPanel implements Runnable, ActionListener {
	
	private JButton but_bottles;
	private JButton but_GarbageBags2;
	private JButton but_leftoff;
	private JButton but_midoff;
	private ImageIcon SwimPool_bothoff;
	
	private ImageIcon SwimPool;
	
	private Image_trigger bottles;
	private Image_trigger GarbageBags2;
	private Image_trigger SwimPool_leftoff;
	private Image_trigger SwimPool_midoff;
	
	private ImageIcon left_tree;
	private ImageIcon right_tree;
	
	private int character_x;
	private int character_y;
	private int destination_x;
	
	private BufferedImage image_bg;
	private BufferedImage image_ch;
	
	private Thread thread;
	
	public boolean alive;
	
	public StagePanel_1() {
        initial();
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == but_bottles){
			if(bottles.getOn_off() == false)
			{
				destination_x = bottles.getX();
				bottles.chageOn_off();
			}
			
		}
		if (e.getSource() == but_GarbageBags2){
			if(GarbageBags2.getOn_off() == false)
			{
				destination_x = GarbageBags2.getX()+GarbageBags2.getImage().getIconWidth();
				GarbageBags2.chageOn_off();
			}
		}
		
		if (e.getSource() == but_leftoff) {
			remove(but_leftoff);
		}
		if (e.getSource() == but_midoff) {
			remove(but_midoff);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image_bg, 0, 0, null);
		g.drawImage(image_ch, character_x, character_y, null);
	}
	
	private void loadImage() {
		String str = "materials/MainScene/01/";
		
		bottles = new Image_trigger(760,460,str+"bottles.jpg");
		GarbageBags2 = new Image_trigger(200,350,str+"GarbageBags2.jpg");
		
		SwimPool_leftoff = new Image_trigger(312,0,str+"01_SwimPool_leftoff.jpg");
		SwimPool_midoff = new Image_trigger(0,0,str+"01_SwimPool_midoff.jpg");
		
		
		try {
			File bg = new File(str+"01_SwimPool_bothoff.jpg");
			File ch = new File(str+"sleepwalker.jpg");
		    image_bg = ImageIO.read(bg);
		    image_ch = ImageIO.read(ch);
		}
		catch(Exception e) {
			System.out.println("wrong in background");
		}
	}
	
	private void setButtons() {
		but_bottles = new JButton("", bottles.getImage());
		but_GarbageBags2 = new JButton("", GarbageBags2.getImage());
		but_leftoff = new JButton("", SwimPool_leftoff.getImage());
		but_midoff = new JButton("",SwimPool_midoff.getImage());
		
		but_bottles.setBounds(bottles.getX(), bottles.getY(),
				bottles.getImage().getIconWidth(), bottles.getImage().getIconHeight());
		but_bottles.setBackground(null);
		but_bottles.addActionListener(this);
		
		but_GarbageBags2.setBounds(GarbageBags2.getX(), GarbageBags2.getY(), 
				GarbageBags2.getImage().getIconWidth(), GarbageBags2.getImage().getIconHeight());
		but_GarbageBags2.setBackground(null);
		but_GarbageBags2.addActionListener(this);
		
		but_leftoff.setBounds(SwimPool_leftoff.getX(), SwimPool_leftoff.getY(), 
				SwimPool_leftoff.getImage().getIconWidth(), SwimPool_leftoff.getImage().getIconHeight());
		but_leftoff.setBackground(null);
		but_leftoff.addActionListener(this);
		
		but_midoff.setBounds(SwimPool_midoff.getX(), SwimPool_midoff.getY(), 
				SwimPool_midoff.getImage().getIconWidth(), SwimPool_midoff.getImage().getIconHeight());
		but_midoff.setBackground(null);
		but_midoff.addActionListener(this);
	}
	
	@Override
	public void run() {
		while(character_x >= 10){
			
			move();
			remove_button();
			try {
				Thread.sleep(10);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
		alive = false;
		repaint();
		System.out.println("Thread out");
	}
	
	private void move() {
		if(character_x > destination_x) 
		{		
			character_x-=3;
		}
		
	}
	
	private void remove_button() {
		if(Math.abs(character_x - (bottles.getX()+bottles.getImage().getIconWidth()/2)) < 2 )
			remove(but_bottles);
		if(Math.abs(character_x - (GarbageBags2.getX()+GarbageBags2.getImage().getIconWidth())) < 2)
		{
			remove(but_GarbageBags2);
			moveout();
		}	
	}
	
	private void moveout() {
		destination_x = 5;
	}
	
	
	private void initial() {
		setBounds(0, 0, 900, 572);
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
        setFocusable(true);
        
        loadImage();
        setButtons();
        
        character_x = 870;
        character_y = 380;
        destination_x = 870;
        alive = true;
        
        add(but_bottles);
		add(but_GarbageBags2);
		add(but_leftoff);
		add(but_midoff);
		thread = new Thread(this);
		thread.start();
	}
}
