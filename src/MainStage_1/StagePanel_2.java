package MainStage_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StagePanel_2 extends JPanel implements Runnable, ActionListener {
	private JButton but_bottle;
	private JButton but_can;
	private JButton but_lightoff;
	
	private Image_trigger bottle;
	private Image_trigger can;
	private Image_trigger lightoff;
	private character ch_right;
	private character ch_left;
	
	private BufferedImage image_bg;
	private boolean[] trigger_list;
	private int destination_x;
	private int destination_y;
	private int character_x;
	private int character_y;
	
	public boolean alive;
	public boolean change_direction;
	
	public Thread thread;
	
	public StagePanel_2() {
		initial();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image_bg, 0, 0, null);
		if(ch_right.getcounter()%2 == 1 && change_direction == false)
		{
			g.drawImage(ch_right.getImage_1(), character_x, character_y, null);
		}
		else if (ch_right.getcounter()%2 == 0 && change_direction == false)
		{
			g.drawImage(ch_right.getImage_2(), character_x, character_y, null);
		}
		
		else if(ch_left.getcounter()%2 == 1 && change_direction == true)
		{
			g.drawImage(ch_left.getImage_1(), character_x, character_y, null);
		}
		else
		{
			g.drawImage(ch_left.getImage_2(), character_x, character_y, null);
		}
	}
	
	private void loadImage() {
		String str = "materials/MainScene/02/";
		
		bottle = new Image_trigger(770,440,str+"bottle.jpg");
		can = new Image_trigger(385,415,str+"can.jpg");	
		lightoff = new Image_trigger(552,0,str+"light_off.jpg");
		
		try {
			File bg = new File(str+"02_VolleyCourt_lightoff.jpg");
			
		    image_bg = ImageIO.read(bg);
		    
		}
		catch(Exception e) {
			System.out.println("wrong in background");
		}
	}
	
	private void setButtons() {
		but_bottle = new JButton("", bottle.getImage());
		but_can = new JButton("", can.getImage());
		but_lightoff = new JButton("", lightoff.getImage());
				
		
		but_can.setBounds(can.getX(), can.getY(), 
				can.getImage().getIconWidth(), can.getImage().getIconHeight());
		but_can.setBackground(null);
		but_can.addActionListener(this);
		trigger_list[0] = false;
		
		but_bottle.setBounds(bottle.getX(), bottle.getY(),
				bottle.getImage().getIconWidth(), bottle.getImage().getIconHeight());
		but_bottle.setBackground(null);
		but_bottle.addActionListener(this);
		trigger_list[1] = false;
		
		but_lightoff.setBounds(lightoff.getX(), lightoff.getY(), 
				lightoff.getImage().getIconWidth(), lightoff.getImage().getIconHeight());
		but_lightoff.setBackground(null);
		but_lightoff.addActionListener(this);
		trigger_list[2] = false;
		
		
	}
	
	private void move() {
		if(character_x < destination_x) 
		{	
			ch_right.setcounter(ch_right.getcounter()+1);
			character_x+=4;
		}
		if(character_y > destination_y)
		{
			ch_left.setcounter(ch_left.getcounter()+1);
			character_y-=3;
		}
		
	}
	
	private void remove_button() {
		if(Math.abs(character_x - (bottle.getX()-100)) < 2 )
			remove(but_bottle);
		if(Math.abs(character_x - (can.getX()-100) )< 2)
		{
			remove(but_can);
			//moveout();
		}
		if(character_y <= 350)
		{
			remove(but_lightoff);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == but_can){
			if(can.getOn_off() == false)
			{
				trigger_list[0] = true;
				destination_x = can.getX();
				can.chageOn_off();
			}	
		}
		if (e.getSource() == but_bottle){
			if(bottle.getOn_off() == false && trigger_list[0] == true)
			{
				trigger_list[1] = true;
				destination_x = bottle.getX();
				bottle.chageOn_off();
			}
		}
		
		if (e.getSource() == but_lightoff) {
			if(lightoff.getOn_off() == false && trigger_list[0] == true && trigger_list[1] == true)
			{
				trigger_list[2] = true;
				destination_y = 250;
				lightoff.chageOn_off();
				change_direction = true;
			}
		}
	}
	
	@Override
	public void run() {
		while(character_y > 260){
			
			move();
			remove_button();
			try {
				Thread.sleep(50);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
		System.out.println("RRR");
		alive = false;
	}
	
	
	
	private void initial() {
		setBounds(0, 0, 900, 572);
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
        setFocusable(true);
        
        character_x = 10;
        character_y = 400;
        destination_y = 400;
        ch_right = new character("materials/re_character");
        ch_left = new character("materials/character");
        
        alive = true;
        change_direction = false;
        
        loadImage();
        trigger_list = new boolean[3];
        setButtons();
        add(but_bottle);
		add(but_can);
		add(but_lightoff);
		
		thread = new Thread(this);
	}

}
