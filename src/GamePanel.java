import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	private GameStage frame;
	private BufferedImage image_bg;
	private BufferedImage image_net;
	private BufferedImage image_character;
	private BufferedImage image_ball;
	private BufferedImage image_banana, image_ba;
	
	private JLabel label;
	private int score, winScore;
	private boolean isEnding;
	private Random random;
	
	public int chX, chY, bananaX, bananaY;
	public int ballX, ballY, ball_speed;
	public String ball_direction, ch_move;
	private boolean banana_hit;
	private boolean move_left, move_right;

	public GamePanel(GameStage gs)
	{
		frame = gs;
		setBounds(0, 0, 900, 572);
    	setBackground(Color.WHITE);
    	setLayout(null);
    	
    	score = 0; winScore = 60;
    	isEnding = false;
    	
    	setImage();
    	random = new Random();
    	chX = 380; chY = 330;
    	ballX = random.nextInt(800);
    	while(ballX<50){
    		ballX = random.nextInt(800);
    	} ballY = 0;
    	ball_speed = 1;
    	ball_direction = "down";
    	ch_move = "";
    	bananaX = 200; bananaY = 0;
    	banana_hit = false;
    	move_left = false; move_right = false;
    	
    	this.label = new JLabel();
    	this.label.setText("Score: "+getScore());
    	this.label.setBackground(Color.black);
    	this.label.setFont(new Font("Serif",Font.ITALIC+Font.BOLD,24));
    	this.label.setBounds(650, 10, 200, 40);
    	this.add(this.label);
    	
    	frame.addKeyListener(this);
	}
	
	public void setImage(){
		try{
			//image_bg=ImageIO.read(new File("materials/blue-sky.jpg"));
			image_bg=ImageIO.read(new File("materials/VolleyGame/sky_cartoon.png"));
			image_net=ImageIO.read(new File("materials/VolleyGame/net_long.png"));
			image_character=ImageIO.read(new File("materials/VolleyGame/bear1_black.png"));
			image_ball=ImageIO.read(new File("materials/VolleyGame/volleyball.png"));
			image_banana=ImageIO.read(new File("materials/VolleyGame/Banana_Peel.png"));
			image_ba=ImageIO.read(new File("materials/VolleyGame/Banana_Peel_hit.png"));
		}
		catch(IOException ex){
			System.out.println("CANNOT load images in GamePanel");
		}
	}
	public void setScore(int n){
		score = n;
	}
	public int getScore(){
		return score;
	}
	public int getWinScore(){
		return winScore;
	}
	public void setScoreText(){
    	this.label.setText("Score: "+getScore());
    }
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    //g.drawImage(this.image_bg, -50, -300, null);
	    g.drawImage(this.image_bg, 0, 0, null);
	    g.drawImage(this.image_net, -1, 20, null);
	    g.drawImage(this.image_character, chX, chY, null);
	    g.drawImage(this.image_ball, ballX, ballY, null);
	    if(banana_hit==false){
	    	g.drawImage(this.image_banana, bananaX, bananaY, null);
	    } else{
	    	g.drawImage(this.image_ba, bananaX, bananaY, null);
	    	//banana_hit = false;
	    }
	}
	@Override
	public void run(){
		while(!isEnding){
			repaint();
		    setScoreText();
		    
		    //ball and banana move
		    if(ball_direction=="down"){
		    	ballY = (ballY+5)%572;
		    } 
		    if(ball_direction=="up"){
		    	if(ball_speed<0) ball_speed = ball_speed+1;
		    	ballY = (ballY+ball_speed)%572;
		    	
		    	if(ch_move=="right") ballX = ballX+1;
		    	else if(ch_move=="left") ballX = ballX-1;
		    	
		    	if(ball_speed==-1){
		    		ball_direction="down";
		    		ballX= random.nextInt(800);
					while(ballX<50){
			    		ballX = random.nextInt(800);
			    	} ballY = 0;
		    	}
		    }
		    //banana_hit = false;
			bananaY = (bananaY+2)%572;
			
			//character move
			if(move_left){
				chX = (chX+745)%750;
				move_left = false;
			}
			if(move_right){
				chX = (chX+5)%750;
				move_right = false;
			}
			
			//if the ball hits the character
			if((chY-ballY<2) && (chY-ballY>-2) && 
					(ballX-image_ball.getWidth()-chX<50) &&
					(ballX-image_ball.getWidth()-chX>-50) ){
				ball_direction = "up";
				ball_speed = -10;
				setScore(getScore()+30);
			}
			//if the ball hits the ground
			if(ballY==570){
				ballX= random.nextInt(800);
				while(ballX<50){
		    		ballX = random.nextInt(800);
		    	} ballY = 0;
			}
			//if the banana peel hits the character
			if((chY-bananaY<2) && (chY-bananaY>-2) && 
					(bananaX+image_banana.getWidth()/2-chX-image_character.getWidth()/2<75) &&
					(bananaX+image_banana.getWidth()/2-chX-image_character.getWidth()/2>-75) ){
				banana_hit = true;
				bananaX= random.nextInt(750);
				while(bananaX<50){
		    		bananaX = random.nextInt(750);
		    	} bananaY = 0;
				setScore(getScore()-50);
				
			} else{
				banana_hit = false;
			}
			//if the banana hits the ground
			if(bananaY==570){
				bananaX= random.nextInt(750);
				while(bananaX<50){
		    		bananaX = random.nextInt(750);
		    	} bananaY = 0;
			}

		    try {
		    	Thread.sleep(30);
		    } 
		    catch(InterruptedException e) {
		    	//e.printStackTrace();
		    }
		    
		    if(getScore()>=getWinScore()){
		    	isEnding = true;
		    	frame.setState(1);
		    }
		}
		repaint();
		setScoreText();
	}
	
	public void keyTyped(KeyEvent key) {
    	return;
    }
     
    public void keyPressed(KeyEvent key) {
    	if((key.getKeyCode() == KeyEvent.VK_LEFT)){
    		ch_move = "left";
    		move_left = true;
    	}
    	else if((key.getKeyCode() == KeyEvent.VK_RIGHT)){
    		ch_move = "right";
    		move_right = true;
    	}
    	else if((key.getKeyCode()==KeyEvent.VK_SPACE)){
    		//stop = true;
    	}
    }
     
    public void keyReleased(KeyEvent key) {
        return;
    }
}
