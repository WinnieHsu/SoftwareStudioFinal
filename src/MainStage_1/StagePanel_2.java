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
	
	private BufferedImage image_bg;
	private BufferedImage image_ch;
	
	private int destination_x;
	private int destination_y;
	private int character_x;
	private int character_y;
	
	private Thread thread;
	
	public StagePanel_2() {
		initial();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image_bg, 0, 0, null);
		g.drawImage(image_ch, character_x, character_y, null);
	}
	
	private void loadImage() {
		String str = "materials/MainScene/02/";
		
		bottle = new Image_trigger(770,440,str+"bottle.jpg");
		can = new Image_trigger(385,415,str+"can.jpg");	
		lightoff = new Image_trigger(544,0,str+"lightoff.jpg");
		
		try {
			//File bg = new File(str+"02_sample.jpg");
			File bg = new File(str+"02_VolleyCourt_lightoff.jpg");
			File ch = new File(str+"sleepwalker.jpg");
		    image_bg = ImageIO.read(bg);
		    image_ch = ImageIO.read(ch);
		}
		catch(Exception e) {
			System.out.println("wrong in background");
		}
	}
	
	private void setButtons() {
		but_bottle = new JButton("", bottle.getImage());
		but_can = new JButton("", can.getImage());
		but_lightoff = new JButton("", lightoff.getImage());
				
		but_bottle.setBounds(bottle.getX(), bottle.getY(),
				bottle.getImage().getIconWidth(), bottle.getImage().getIconHeight());
		but_bottle.setBackground(null);
		but_bottle.addActionListener(this);
		
		but_can.setBounds(can.getX(), can.getY(), 
				can.getImage().getIconWidth(), can.getImage().getIconHeight());
		but_can.setBackground(null);
		but_can.addActionListener(this);
		
		but_lightoff.setBounds(lightoff.getX(), lightoff.getY(), 
				lightoff.getImage().getIconWidth(), lightoff.getImage().getIconHeight());
		but_lightoff.setBackground(null);
		but_lightoff.addActionListener(this);
		
		
	}
	
	private void move() {
		if(character_x < destination_x) 
		{		
			character_x+=3;
		}
		if(character_y > destination_y)
		{
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
		if (e.getSource() == but_bottle){
			if(bottle.getOn_off() == false)
			{
				destination_x = bottle.getX();
				bottle.chageOn_off();
			}
			
		}
		if (e.getSource() == but_can){
			if(can.getOn_off() == false)
			{
				destination_x = can.getX()+can.getImage().getIconWidth();
				can.chageOn_off();
			}
		}
		
		if (e.getSource() == but_lightoff) {
			if(lightoff.getOn_off() == false)
			{
				destination_y = 350;
				lightoff.chageOn_off();
			}
			
		}
		
		
	}
	
	@Override
	public void run() {
		while(character_x < 900){
			
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
	}
	
	
	
	private void initial() {
		setBounds(0, 0, 900, 572);
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
        setFocusable(true);
        
        character_x = 10;
        character_y = 500;
        destination_y = 500;
        
        loadImage();
        setButtons();
        add(but_bottle);
		add(but_can);
		add(but_lightoff);
		
		thread = new Thread(this);
		thread.start();
	}

}
