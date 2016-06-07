import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
//import sun.audio.*;

public class Lose extends JPanel implements Runnable
{
	//private GameStage frame;
	private ArrayList<BufferedImage> image_bg;
	private int bg_index;
	private int bgX, bgY;
	private int state;
	private boolean isEnding;
	private int i, j, k;
	
	public boolean alive;
	public Thread thread;
	//private FileInputStream soundFile;
	//private AudioStream sound;
	
	public Lose()
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
		i=0; j=0; k=0;
		isEnding = false;
		thread = new Thread(this);
	}
	
	private void setImage(){
		try{
			image_bg.add( ImageIO.read(new File("materials/GameOver/00.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/01.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/02.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/03.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/04.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/05.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/06.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/07.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/08.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/09.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/10.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/11.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/12.jpg")) );
			image_bg.add( ImageIO.read(new File("materials/GameOver/13.jpg")) );
		}
		catch(IOException ex){
			System.out.println("CANNOT load images in Lose Panel");
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

			if(state==0){
				//AudioPlayer.player.start(sound);
				bg_index=0;
				try {
			    	Thread.sleep(600);
			    } 
			    catch(InterruptedException e) {}
				state=1;
			}
			if(state==1){
				bg_index=1;
				repaint();
				try {
			    	Thread.sleep(600);
			    } 
			    catch(InterruptedException e) {}
				if(i==0){
					state=0;
					i++;
				} else state=2;
			}
			if(state==2){ //G
				bg_index=2;
				repaint();
				try {
			    	Thread.sleep(400);
			    } 
			    catch(InterruptedException e) {}
				state=3;
			}
			if(state==3){
				bg_index=3;
				repaint();
				try {
			    	Thread.sleep(350);
			    } 
			    catch(InterruptedException e) {}
				state=4;
			}
			if(state==4){
				bg_index=4;
				repaint();
				try {
			    	Thread.sleep(300);
			    } 
			    catch(InterruptedException e) {}
				state=5;
			}
			if(state==5){ //E
				bg_index=5;
				repaint();
				try {
			    	Thread.sleep(600);
			    } 
			    catch(InterruptedException e) {}
				if(j==0){
					state=6;
					j++;
				}
				else state=7;
			}
			if(state==6){
				bg_index=6;
				repaint();
				try {
			    	Thread.sleep(600);
			    } 
			    catch(InterruptedException e) {}
				state=5;
			}

			if(state==7){
				bg_index=7;
				repaint();
				try {
			    	Thread.sleep(200);
			    } 
			    catch(InterruptedException e) {}
				state=8;
			}
			if(state==8){
				bg_index=8;
				repaint();
				try {
			    	Thread.sleep(200);
			    } 
			    catch(InterruptedException e) {}
				state=9;
			}
			if(state==9){
				bg_index=9;
				repaint();
				try {
			    	Thread.sleep(200);
			    } 
			    catch(InterruptedException e) {}
				state=10;
			}
			if(state==10){
				bg_index=10;
				repaint();
				try {
			    	Thread.sleep(200);
			    } 
			    catch(InterruptedException e) {}
				state=11;
			}
			if(state==11){
				bg_index=11;
				repaint();
				try {
			    	Thread.sleep(600);
			    } 
			    catch(InterruptedException e) {}
				state=12;
			}
			if(state==12){
				bg_index=12;
				repaint();
				try {
			    	Thread.sleep(600);
			    } 
			    catch(InterruptedException e) {}
				if(k<3){
					state=11;
					k++;
				}
				else state=13;
			}
			if(state==13){
				bg_index=13;
				repaint();
				try {
			    	Thread.sleep(600);
			    } 
			    catch(InterruptedException e) {}
				isEnding = true;
			}
			
			repaint();
		}
		alive = false;
		repaint();
	}
}
