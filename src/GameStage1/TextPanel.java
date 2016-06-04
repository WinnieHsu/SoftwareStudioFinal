package GameStage1;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.util.HashMap;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;

public class TextPanel extends JPanel implements Runnable, KeyListener
{
	protected GamePanel GP;
	private BufferedImage image_word_known;
    private BufferedImage image_word_unknown;
    private HashMap<Integer,String> hmap_known;
    private HashMap<Integer,String> hmap_unknown;
    //private JLabel label;
    private JTextField textField;
    
   
    private int wordCurrentY;
    private int moving;
    private int index_known;
    private int index_unknown;
    private int correct;
    private File word_known;
    private File word_unknown;
       
    private Thread thread;
    private Thread thread_gp; 
    
    public TextPanel()
    {
    	GP = new GamePanel();
    	setBounds(0, 0, 250, 600);
    	setBackground(Color.gray);
    	setLayout(null);
    	this.wordCurrentY = 5;
    	
    	this.correct = 20;
    	this.moving = this.correct;
    		
    	this.textField = new JTextField("",20);
	    this.textField.setBounds(5, 530 , 180, 20);
	    this.textField.addKeyListener(this);
	    
	    this.textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText("");
            	
            }
        }); 
        
	    this.add(this.textField);
    	setImage_left();
    	
    	thread = new Thread(this);
    	

    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(this.image_word_known, 5, this.wordCurrentY -5, null);
        g.drawImage(this.image_word_unknown, 120, this.wordCurrentY +14, null);
        
    }
    
    public void setImage_left()
    {
    	
    	try
		{
    		 this.index_known = 0;
    		 this.index_unknown = 0;
    		 this.hmap_known = new HashMap<Integer,String>();
    		 this.hmap_unknown = new HashMap<Integer,String>();
		     FileReader fr = new FileReader("assignment4_materials/known_words.txt");
		     BufferedReader br = new BufferedReader(fr);
		     String line;
		     int index = 0;
		     String pos;
		     
  	         while ((line=br.readLine()) != null) 
		     {
		    	 pos = line;
		    	 this.hmap_known.put(index, pos);
		         index++;
		     }
  	         index = 0;
  	         fr = new FileReader("assignment4_materials/unknown_words.txt");
		     br = new BufferedReader(fr);
		     while ((line=br.readLine()) != null) 
		     {
		    	 pos = line;
		    	 this.hmap_unknown.put(index, pos);
		         index++;
		     }
		    fr.close();
			this.word_known = new File("assignment4_materials/img/known/"+this.hmap_known.get(this.index_known).substring(0,9));
			this.word_unknown = new File("assignment4_materials/img/unknown/"+this.hmap_unknown.get(this.index_unknown));
			this.image_word_known = ImageIO.read(this.word_known);
			this.image_word_unknown = ImageIO.read(this.word_unknown);
			
			
		}
		catch(Exception e)
		{
			System.out.println("Not image be loaded");
			return;
		}
    	
    }
    
    
    public void addScore()
    {
    	this.GP.number = this.GP.number + 10;
    }
    
    
    public void move()
    {
    	if(this.moving < this.correct)
    	{
    		this.moving++;
    		this.GP.duckCurrentX++;
    	}
    	
    	this.wordCurrentY = (this.wordCurrentY + 1)%400;
    }
    
    @Override
    public void run()
    {
		
		while(this.GP.destination > this.GP.duckCurrentX)
		{
			repaint();
			move();
		    try {
		    	Thread.sleep(20);
		    } 
		    catch(InterruptedException e) {
		    	e.printStackTrace();
		    }
		    
		}
		repaint();
		
    }
    
    public void keyTyped(KeyEvent e) {
    	return;
    }
     
    public void keyPressed(KeyEvent e) {
    	if((e.getKeyCode() == KeyEvent.VK_ENTER))
    	{
    		int length = this.hmap_known.get(this.index_known).length();
    		
    		if(this.textField.getText().substring(0,length-10).equals((this.hmap_known.get(this.index_known).substring(10, length))))
    		{	
    			this.moving = 0;
    			addScore();
    			changeImage();
    			writeinfile();
    		}
    	}
    }
     
    public void keyReleased(KeyEvent e) {
        return;
    }
    
    public void changeImage() {
    	this.index_known++;
    	this.index_unknown++;
    	this.wordCurrentY = 5;
    	
    	try
    	{
    		this.word_known = new File("assignment4_materials/img/known/"+this.hmap_known.get(this.index_known).substring(0,9));
    		this.word_unknown = new File("assignment4_materials/img/unknown/"+this.hmap_unknown.get(this.index_unknown));
    		this.image_word_unknown = ImageIO.read(this.word_unknown);
    		this.image_word_known = ImageIO.read(this.word_known);
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("Not image be loaded when changeImage");
			return;
    	}
    	
    	
    	
    	
    }
    public void writeinfile() {
    	
    	int length_text = this.textField.getText().length();
    	int length = this.hmap_known.get(this.index_known).length()-10;
    	try
    	{
    		FileWriter fileWriter = new FileWriter("assignment4_materials/unknown_write_in.txt",true);
    		fileWriter.write(this.textField.getText().substring(length+1,length_text));
    		fileWriter.write("\r\n");;
    		fileWriter.flush();
    		fileWriter.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println("Not write in file correctly");
			return;
    	}
    }

    
}
