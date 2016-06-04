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
	
	private int character_x;
	
	private Thread thread;
	
	public StagePanel_3() {
		initial();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image_bg, 0, 0, null);
		
	}
	
	private void loadImage() {
		String str = "material/MainScene/03/";
		
		paper = new Image_trigger(564,400,str+"paper.jpg");
		drink = new Image_trigger(372,460,str+"drink.jpg");
		can = new Image_trigger(300,400,str+"can.jpg");	
		left_off = new Image_trigger(186,264,str+"aircon_left.jpg");
		right_off = new Image_trigger(470,262,str+"aircon_right.jpg");
		
		try {
			//File bg = new File(str+"03_sample.jpg");
			File bg = new File(str+"03_Gym_bothoff.jpg");
			//File ch = new File(str+"sleepwalker.jpg");
		    image_bg = ImageIO.read(bg);
		    //image_ch = ImageIO.read(ch);
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
		
		but_can.setBounds(can.getX(), can.getY(), 
				can.getImage().getIconWidth(), can.getImage().getIconHeight());
		but_can.setBackground(null);
		but_can.addActionListener(this);
		
		but_drink.setBounds(drink.getX(), drink.getY(), 
				drink.getImage().getIconWidth(), drink.getImage().getIconHeight());
		but_drink.setBackground(null);
		but_drink.addActionListener(this);
		
		but_leftoff.setBounds(left_off.getX(), left_off.getY(), 
				left_off.getImage().getIconWidth(), left_off.getImage().getIconHeight());
		but_leftoff.setBackground(null);
		but_leftoff.addActionListener(this);
		
		but_rightoff.setBounds(right_off.getX(), right_off.getY(), 
				right_off.getImage().getIconWidth(), right_off.getImage().getIconHeight());
		but_rightoff.setBackground(null);
		but_rightoff.addActionListener(this);
		
		
	}
	
	private void move() {
			
	}
	
	private void remove_button() {
		
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
	@Override
	public void run() {
		while(character_x < 900){
			
			move();
			remove_button();
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
        
        loadImage();
        setButtons();
        add(but_paper);
		add(but_can);
		add(but_drink);
		add(but_leftoff);
		add(but_rightoff);
		
		thread = new Thread(this);
		thread.start();
	}
}
