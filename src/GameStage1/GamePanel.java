package GameStage1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class GamePanel extends JPanel implements Runnable{
    private BufferedImage image_background;
    private BufferedImage image_duck;
    private BufferedImage image_ball;
    private BufferedImage image_win;
    
    protected  int duckCurrentX;
    protected int destination;
    private int duckCurrentY;
    private String duckDirection;
    private int duckAnchorY;
    protected int number;
    private JLabel score;
    
    private Thread thread;
    
    public GamePanel() {
    	setBounds(250, 0, 750, 600);
    	setBackground(Color.cyan);
    	setLayout(null);
    	
    	this.score = new JLabel();
    	this.score.setText("Score: "+this.number);
    	this.score.setBackground(Color.black);
    	this.score.setFont(new Font("", Font.BOLD, 25));
    	this.score.setBounds(570, 5, 150, 40);
    	this.add(this.score);
    	this.duckCurrentX = 220;
    	this.destination = 550;
    	this.duckCurrentY = 230;
    	this.duckAnchorY = 280;
    	this.duckDirection = "up";
    	
    	setImage_right();
    	thread = new Thread(this);
    	thread.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        g.drawImage(this.image_background, 0, 0, null);
        g.drawImage(this.image_ball, this.destination, this.duckAnchorY, null);
        g.drawImage(this.image_duck, this.duckCurrentX-100, this.duckCurrentY, null);
        if(this.destination <= this.duckCurrentX)
        {
        	g.drawImage(this.image_win, this.duckCurrentX-130, this.duckAnchorY-250, null);
        }
    }
    
    public void setImage_right()
    {
    	try
		{
			File background = new File("assignment4_materials/res/h.png");
			File duck = new File("assignment4_materials/res/duck.png");
			File ball = new File("assignment4_materials/res/b.png");
			File win = new File("assignment4_materials/res/win.png");
			this.image_background = ImageIO.read(background);
			this.image_duck = ImageIO.read(duck);
			this.image_ball = ImageIO.read(ball);
			this.image_win = ImageIO.read(win);
		}
		catch(Exception e)
		{
			System.out.println("Game panel Not image be loaded");
			return;
		}
    	
    }
    
    @Override
    public  void run(){
		
		while(this.destination > this.duckCurrentX)
		{
			if(this.duckDirection == "up" && this.duckCurrentY > this.duckAnchorY)
				this.duckDirection = "down";
		    else if(this.duckDirection == "down" && this.duckCurrentY < this.duckAnchorY-50)
		    	this.duckDirection = "up";
		
			if(this.duckDirection == "up")
				this.duckCurrentY++;
			else if(this.duckDirection == "down")
				this.duckCurrentY--;
		    try {
		    	Thread.sleep(30);
		    } 
		    catch(InterruptedException e) {
		    	e.printStackTrace();
		    }
		    this.score.setText("Score: "+this.number);
		    repaint();
		}
		repaint();
    }
    
}

