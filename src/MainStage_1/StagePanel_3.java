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

public class StagePanel_3 extends JPanel implements Runnable, ActionListener {
	private JButton but_paper;
	private JButton but_can;
	private JButton but_drink;
	private JButton but_leftoff;
	private JButton but_rightoff;
	
	private Image_trigger paper;
	private Image_trigger can;
	private Image_trigger drink;
	private Image_trigger left_off;
	private Image_trigger right_off;
	
	private BufferedImage image_bg;
	private character ch;
	
	
	private int character_x;
	private int character_y;
	private int destination_x;
	private int destination_y;
	
	private boolean[] trigger_list;
	
	public Thread thread;
	public boolean alive;
	
	public StagePanel_3() {
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
		String str = "materials/MainScene/03/";
		
		paper = new Image_trigger(564,400,str+"paper.jpg");
		drink = new Image_trigger(372,460,str+"drink.jpg");
		can = new Image_trigger(300,400,str+"can.jpg");	
		left_off = new Image_trigger(186,264,str+"aircon_left.jpg");
		right_off = new Image_trigger(470,262,str+"aircon_right.jpg");
		
		try {
			
			File bg = new File(str+"03_Gym_bothoff.jpg");
			
		    image_bg = ImageIO.read(bg);
		}
		catch(Exception e) {
			System.out.println("wrong in background");
		}
	}
	
	private void setButtons() {
		
		but_paper = new JButton("", paper.getImage());
		but_drink = new JButton("", drink.getImage());
		but_can = new JButton("", can.getImage());
		but_leftoff = new JButton("", left_off.getImage());
		but_rightoff = new JButton("", right_off.getImage());
				
		but_paper.setBounds(paper.getX(), paper.getY(),
				paper.getImage().getIconWidth(), paper.getImage().getIconHeight());
		but_paper.setBackground(null);
		but_paper.addActionListener(this);
		trigger_list[0] = false;
		
		but_can.setBounds(can.getX(), can.getY(), 
				can.getImage().getIconWidth(), can.getImage().getIconHeight());
		but_can.setBackground(null);
		but_can.addActionListener(this);
		trigger_list[1] = false;
		
		but_drink.setBounds(drink.getX(), drink.getY(), 
				drink.getImage().getIconWidth(), drink.getImage().getIconHeight());
		but_drink.setBackground(null);
		but_drink.addActionListener(this);
		trigger_list[2] = false;
		
		but_leftoff.setBounds(left_off.getX(), left_off.getY(), 
				left_off.getImage().getIconWidth(), left_off.getImage().getIconHeight());
		but_leftoff.setBackground(null);
		but_leftoff.addActionListener(this);
		trigger_list[3] = false;
		
		but_rightoff.setBounds(right_off.getX(), right_off.getY(), 
				right_off.getImage().getIconWidth(), right_off.getImage().getIconHeight());
		but_rightoff.setBackground(null);
		but_rightoff.addActionListener(this);
		trigger_list[4] = false;
		
	}
	
	private void move() {
		int change = 0;
		if(character_y > destination_y && character_x > destination_x)
		{
			change++;	
			character_x-=2;
			character_y--;
			
		}
		if(character_y < destination_y && character_x > destination_x) 
		{
			change++;
			character_x--;
			character_y++;
		}
		if(change > 0)
			ch.setcounter(ch.getcounter()+1);
	}
	
	private void remove_button() {
		if(Math.abs(character_x - (paper.getX()+paper.getImage().getIconWidth())) < 3 && trigger_list[0] == true)
			remove(but_paper);
		if(Math.abs(character_x - (right_off.getX()+right_off.getImage().getIconWidth())) < 2 && trigger_list[4] == true)
			remove(but_rightoff);
		if(Math.abs(character_x - (drink.getX()+ drink.getImage().getIconWidth())) < 3 && trigger_list[2] == true)
		{
			remove(but_drink);			
		}
		if(Math.abs(character_x - (can.getX()+ can.getImage().getIconWidth())) < 3 && trigger_list[1] == true)
		{
			remove(but_can);
		}
		if(Math.abs(character_x - (left_off.getX()+ left_off.getImage().getIconWidth())) < 3 && trigger_list[3] == true)
		{
			remove(but_leftoff);
		}
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == but_paper){
			if(paper.getOn_off() == false)
			{
				trigger_list[0] = true;
				destination_x = paper.getX() + paper.getImage().getIconWidth();
				destination_y = paper.getY() - paper.getImage().getIconHeight();
				paper.chageOn_off();
			}
		}
		if (e.getSource() == but_can && trigger_list[4] == true){
			if(can.getOn_off() == false)
			{
				trigger_list[1] = true;
				destination_x = can.getX()+can.getImage().getIconWidth();
				destination_y = can.getY();
				can.chageOn_off();
			}
		}
		
		if (e.getSource() == but_drink && trigger_list[4] == true) {
			if(drink.getOn_off() == false)
			{
				trigger_list[2] = true;
			    destination_x = drink.getX()+20; //+ drink.getImage().getIconWidth();
			    destination_y = drink.getY();
			    drink.chageOn_off();
			    
			}
			
		}
		if (e.getSource() == but_leftoff && trigger_list[1] == true && character_x <= 400) {
			if(left_off.getOn_off() == false)
			{
				trigger_list[3] = true;
				destination_x = left_off.getX() + left_off.getImage().getIconWidth();
			    destination_y = left_off.getY() - left_off.getImage().getIconHeight();
			    left_off.chageOn_off();
			}
			
		}
		if (e.getSource() == but_rightoff) {
			if(right_off.getOn_off() == false)
			{
				trigger_list[4] = true;
			    destination_x = right_off.getX() + right_off.getImage().getIconWidth();
			    destination_y = right_off.getY() - right_off.getImage().getIconHeight()/3;
			    right_off.chageOn_off();
			}
		}
		
	}
	
	@Override
	public void run() {
		repaint();
		while(character_x > 326){
			//System.out.println(character_x);
			move();
			remove_button();
			try {
				Thread.sleep(30/3);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
		alive = false;
		System.out.println("out the thread SP3");
	}
	
	private void initial() {
		setBounds(0, 0, 900, 572);
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
        setFocusable(true);
        
        character_x = 850;
        character_y = 455;
        destination_x = 850;
        destination_y = 455;
        alive = true;
        
        loadImage();
        trigger_list = new boolean[5];
        setButtons();
        
        
        ch = new character("materials/small_character");
        add(but_paper);
		add(but_can);
		add(but_drink);
		add(but_leftoff);
		add(but_rightoff);
		
		thread = new Thread(this);
		
		
	}
}
