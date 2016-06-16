package MainStage_1;
import java.awt.*;
import java.awt.event.*;
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
	
	private Image_trigger bottles;
	private Image_trigger GarbageBags2;
	private Image_trigger SwimPool_leftoff;
	private Image_trigger SwimPool_midoff;
		
	private int character_x;
	private int character_y;
	private int destination_x;
	private int destination_y;
	private character ch;
	
	private BufferedImage image_bg;
	private BufferedImage img_left_tree;
	private BufferedImage img_right_tree;
	
	private boolean[] trigger_list;
	
	public Thread thread;
	
	public int score;
	public boolean alive;
	
	public StagePanel_1() {
        initial();
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == but_bottles){
			if(bottles.getOn_off() == false)
			{
				trigger_list[0] = true;
				destination_x = bottles.getX();
				bottles.chageOn_off();
			}
		}
		if (e.getSource() == but_GarbageBags2){
			if(GarbageBags2.getOn_off() == false)
			{
				trigger_list[1] = true;
				destination_x = GarbageBags2.getX()+GarbageBags2.getImage().getIconWidth();
				GarbageBags2.chageOn_off();
			}
		}
		
		if (e.getSource() == but_leftoff && trigger_list[1] == true && character_x <= 400 ) {
			trigger_list[2] = true;
			remove(but_leftoff);
		}
		if (e.getSource() == but_midoff && trigger_list[1] == true && character_x <= 400) {
			trigger_list[3] = true;
			remove(but_midoff);
		}
	}
	
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
		g.drawImage(img_left_tree, 132, 0, null);
		g.drawImage(img_right_tree, 630, 0, null);
	}
	
	private void loadImage() {
		String str = "materials/MainScene/01/";
		
		bottles = new Image_trigger(760,460,str+"bottles.jpg");
		GarbageBags2 = new Image_trigger(200,350,str+"GarbageBags2.jpg");
		
		SwimPool_leftoff = new Image_trigger(312,0,str+"01_SwimPool_leftoff.jpg");
		SwimPool_midoff = new Image_trigger(0,0,str+"01_SwimPool_midoff.jpg");
		
		
		try {
			File bg = new File(str+"01_SwimPool_bothoff.jpg");
			File left_tree = new File(str+"left_tree.png");
			File right_tree = new File(str+"right_tree.png");
		    image_bg = ImageIO.read(bg);
		    img_left_tree = ImageIO.read(left_tree);
		    img_right_tree = ImageIO.read(right_tree);
		    
		    
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
		but_bottles.setOpaque(true);
		trigger_list[0] = false;
		
		but_GarbageBags2.setBounds(GarbageBags2.getX(), GarbageBags2.getY(), 
				GarbageBags2.getImage().getIconWidth(), GarbageBags2.getImage().getIconHeight());
		but_GarbageBags2.setBackground(null);
		but_GarbageBags2.addActionListener(this);
		trigger_list[1] = false;
		
		but_leftoff.setBounds(SwimPool_leftoff.getX(), SwimPool_leftoff.getY(), 
				SwimPool_leftoff.getImage().getIconWidth(), SwimPool_leftoff.getImage().getIconHeight());
		but_leftoff.setBackground(null);
		but_leftoff.addActionListener(this);
		trigger_list[2] = false;
		
		but_midoff.setBounds(SwimPool_midoff.getX(), SwimPool_midoff.getY(), 
				SwimPool_midoff.getImage().getIconWidth(), SwimPool_midoff.getImage().getIconHeight());
		but_midoff.setBackground(null);
		but_midoff.addActionListener(this);
		trigger_list[3] = false;
		
		
	}
	
	@Override
	public void run() {
		
		while(character_x >= 1){			
			move();
			remove_button();
			moveout();
			try {
				Thread.sleep(50);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
		alive = false;
		check_trigger();
		repaint();
		System.out.println(score);
		System.out.println("Thread out");
	}
	
	private void move() {
		if(character_x > destination_x) 
		{		
			ch.setcounter(ch.getcounter()+1);
			character_x-=3;
			if(character_x <= 400)
				destination_y = 330;
		}
		if(character_y < destination_y)
			character_y++;
		
	}
	
	private void remove_button() {
		if(Math.abs(character_x - (bottles.getX()+bottles.getImage().getIconWidth()/2)) < 2 && trigger_list[0] == true)
			remove(but_bottles);
		if(Math.abs(character_x - (GarbageBags2.getX()+GarbageBags2.getImage().getIconWidth())) < 2)
		{
			remove(but_GarbageBags2);
		}	
	}
	
	private void moveout() {
		if(trigger_list[1] == true)
		destination_x = 1;
	}
	
	private void check_trigger(){
		int i;
		for(i=0; i < 4; i++){
			if(trigger_list[i]  == false)
			{
				score++;
			}
		}
	}
	
	private void initial() {
		setBounds(0, 0, 900, 572);
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
        setFocusable(true);
        
        loadImage();
        trigger_list = new boolean[4];
        setButtons();
        ch = new character("materials/character");
        
        score = 0;
        character_x = 840;
        character_y = 300;
        destination_x = 860;
        destination_y = 300;
        alive = true;
        
        add(but_bottles);
		add(but_GarbageBags2);
		add(but_leftoff);
		add(but_midoff);
		thread = new Thread(this);
		
	}
}
