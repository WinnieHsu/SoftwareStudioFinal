package GameStage1;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class SleepWalker extends JPanel implements Runnable{
	
	private BufferedImage image_character;
	private File file;
	private boolean Ingameing;
	private int character_x;
	private int character_y;
	private Thread thread;
	private int swing;
	
	private JButton button;
	private JLabel label;
	
	public SleepWalker() {
		setBounds(0, 0, 800, 600);
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
        setFocusable(true);
    	requestFocus();
        
    	button = new JButton();
    	button.setText("RRRRR");
    	button.setBounds(600, 10, 200, 30);
    	label = new JLabel();
    	label.setText("1/8");
    	label.setBounds(10, 10, 50, 20);
    	add(button);
    	add(label);
    	
    	Ingameing = true;
        character_x = 700;
        character_y = 400;
        
       setImage();
       
		thread = new Thread(this);
        thread.start();
	}
	
	@Override
	public  void run(){
		while(Ingameing) {
			
			move();
			try {
				Thread.sleep(150);
			}
			catch(Exception e) {
				System.out.println("Sleep");
			}
			repaint();
		}
	
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		AffineTransform at = 
        		AffineTransform.getTranslateInstance(character_x, character_y);
		at.rotate(Math.toRadians(swing), image_character.getWidth()/2, 
				image_character.getHeight()/2);
        Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image_character, at, null);
		
	}
	private void setImage() {
		try {
			
			file = new File("material/CarGame/sleepwalker.jpg");
			image_character = ImageIO.read(file);
		}
		catch(Exception e) {
			System.out.println("load overimage goes wrong");
			return;
		}
	}
	
	private void move() {
		character_x = character_x -3;
		if(swing < 20 && swing >= 0)
			swing = swing - 10;
		else if(swing < 0 && swing >= -20)
			swing = swing + 10;
		
	}

}
