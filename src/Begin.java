import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
//import sun.audio.*;

public class Begin extends JPanel implements Runnable
{
	//private GameStage frame;
	private ArrayList<BufferedImage> image_bg;
	private int bg_index;
	private int bgX, bgY;
	private int state;
	private boolean isEnding;
	
	public boolean alive;
	public Thread thread;
	//private FileInputStream soundFile;
	//private AudioStream sound;
	
	public Begin()
	{
		//frame = gs;
		setBounds(0, 0, 900, 572);
    	setBackground(Color.WHITE);
    	setLayout(null);
		
		bg_index = 0;
		bgX = 0; bgY = 0;
		image_bg = new ArrayList<BufferedImage>();
		setImage();
		alive = true;
		state = 0;
		isEnding = false;
		thread = new Thread(this);
	}
	
	private void setImage(){
		try{
			image_bg.add( ImageIO.read(new File("materials/BeginPic/00.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/01.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/02.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/03.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/04.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/05.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/06.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/07.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/08.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/09.jpg")) );
			
			image_bg.add( ImageIO.read(new File("materials/BeginPic/04-1.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/04-2.jpg")) );
			
			image_bg.add( ImageIO.read(new File("materials/BeginPic/rules.png")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/rules1.png")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/rules2.png")) );
			image_bg.add( ImageIO.read(new File("materials/BeginPic/rules3.png")) );
		}
		catch(IOException ex){
			System.out.println("CANNOT load images in Begin Panel");
		}
	}
	/*private void setSound(){
		try{
			soundFile = new FileInputStream("materials/BeginPic/bell.mp3");
			sound = new AudioStream(soundFile);
		}
		catch (Exception e){
			System.out.println("CANNOT load sound in Begin Panel");
		}
	}*/
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(image_bg.get(bg_index), bgX, bgY, null);
	}
	
	@Override
	public void run(){
		while(!isEnding){
			
			//System.out.println("state = "+state);
			if(state==0){
				//AudioPlayer.player.start(sound);
				try {
			    	Thread.sleep(2500);
			    } 
			    catch(InterruptedException e) {}
				state=1;
			}
			if(state==1){
				bg_index=1;
				repaint();
				try {
			    	Thread.sleep(6000);
			    } 
			    catch(InterruptedException e) {}
				state=2;
			}
			if(state==2){
				bg_index=2;
				repaint();
				try {
			    	Thread.sleep(3500);
			    } 
			    catch(InterruptedException e) {}
				state=3;
			}
			if(state==3){
				bg_index=3;
				repaint();
				try {
			    	Thread.sleep(4000);
			    } 
			    catch(InterruptedException e) {}
				state=4;
			}
			if(state==4){
				bg_index=4;
				repaint();
				try {
			    	Thread.sleep(500);
			    } 
			    catch(InterruptedException e) {}
				state=10;
			}
			if(state==10){
				bg_index=10;
				repaint();
				try {
			    	Thread.sleep(500);
			    } 
			    catch(InterruptedException e) {}
				state=11;
			}
			if(state==11){
				bg_index=11;
				repaint();
				try {
			    	Thread.sleep(500);
			    } 
			    catch(InterruptedException e) {}
				state=5;
			}
			if(state==5){
				bg_index=5;
				repaint();
				try {
			    	Thread.sleep(1000);
			    } 
			    catch(InterruptedException e) {}
				state=6;
			}
			if(state==6){
				bg_index=6;
				repaint();
				try {
			    	Thread.sleep(3500);
			    } 
			    catch(InterruptedException e) {}
				state=7;
			}
			if(state==7){
				bg_index=7;
				repaint();
				try {
			    	Thread.sleep(600);
			    } 
			    catch(InterruptedException e) {}
				state=8;
			}
			if(state==8){
				bg_index=8;
				repaint();
				try {
			    	Thread.sleep(300);
			    } 
			    catch(InterruptedException e) {}
				state=9;
			}
			if(state==9){
				bg_index=9;
				repaint();
				try {
			    	Thread.sleep(2000);
			    } 
			    catch(InterruptedException e) {}
				state=12;
			}
			if(state==12){
				bg_index=12;
				repaint();
				try {
			    	Thread.sleep(25000);
			    } 
			    catch(InterruptedException e) {}
				state=13;
			}
			if(state==13){
				bg_index=13;
				repaint();
				try {
			    	Thread.sleep(400);
			    } 
			    catch(InterruptedException e) {}
				state=14;
			}
			if(state==14){
				bg_index=14;
				repaint();
				try {
			    	Thread.sleep(150);
			    } 
			    catch(InterruptedException e) {}
				state=15;
			}
			if(state==15){
				bg_index=15;
				repaint();
				try {
			    	Thread.sleep(80);
			    } 
			    catch(InterruptedException e) {}
				bg_index=9;
				repaint();
				try {
			    	Thread.sleep(1500);
			    } 
			    catch(InterruptedException e) {}
				isEnding = true;
			}
			
			repaint();
		}
		System.out.println("begining over");
		alive = false;
		repaint();
	}
}
