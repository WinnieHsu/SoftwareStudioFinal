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

public class StagePanel_4 extends JPanel implements Runnable, ActionListener {
	private JButton but_bottle;
	private JButton but_can;
	private JButton but_leftoff;
	
	private Image_trigger bottle;
	private Image_trigger can;
	private Image_trigger leftoff;
	
	private BufferedImage image_bg;
	
	private int character_x;
	
	private Thread thread;
	
	public StagePanel_4() {
		initial();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image_bg, 0, 0, null);
		
	}
	
	private void loadImage() {
		String str = "material/MainScene/02/";
		
		bottle = new Image_trigger(770,440,str+"bottle.jpg");
		can = new Image_trigger(385,415,str+"can.jpg");	
		leftoff = new Image_trigger(552,0,str+"lightoff.jpg");
		
		try {
			//File bg = new File(str+"02_sample.jpg");
			File bg = new File(str+"02_VolleyCourt_lightoff.jpg");
			//File ch = new File(str+"sleepwalker.jpg");
		    image_bg = ImageIO.read(bg);
		    //image_ch = ImageIO.read(ch);
		}
		catch(Exception e) {
			System.out.println("wrong in background");
		}
	}
	
	private void setButtons() {
		but_bottle = new JButton("", bottle.getImage());
		but_can = new JButton("", can.getImage());
		but_leftoff = new JButton("", leftoff.getImage());
				
		but_bottle.setBounds(bottle.getX(), bottle.getY(),
				bottle.getImage().getIconWidth(), bottle.getImage().getIconHeight());
		but_bottle.setBackground(null);
		but_bottle.addActionListener(this);
		
		but_can.setBounds(can.getX(), can.getY(), 
				can.getImage().getIconWidth(), can.getImage().getIconHeight());
		but_can.setBackground(null);
		but_can.addActionListener(this);
		
		but_leftoff.setBounds(leftoff.getX(), leftoff.getY(), 
				leftoff.getImage().getIconWidth(), leftoff.getImage().getIconHeight());
		but_leftoff.setBackground(null);
		but_leftoff.addActionListener(this);
		
		
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
        add(but_bottle);
		add(but_can);
		add(but_leftoff);
		
		thread = new Thread(this);
		thread.start();
	}
}