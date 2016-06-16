import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.*;
import javax.imageio.*;
import javax.swing.JPanel;

public class MapPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private GameStage frame;
	private ArrayList<BufferedImage> image_map;
	private int map_index; //index for ArrayList image_map
	public int mapX, mapY;

	public MapPanel(GameStage gs)
	{
		frame = gs;
		setBounds(900, 0, 300, 222);
    	setBackground(Color.cyan);
    	setLayout(null);
   	
    	mapX = -6;
    	mapY = -20;
    	map_index = 0;
    	image_map = new ArrayList<BufferedImage>();
    	setImage_right();
	}
	
	public void setImage_right(){
		try{
			image_map.add( ImageIO.read(new File("materials/MapPic/00_Delta.jpg")) );
			image_map.add( ImageIO.read(new File("materials/MapPic/01_Swim.jpg")) );
			image_map.add( ImageIO.read(new File("materials/MapPic/02_Volley.jpg")) );
			image_map.add( ImageIO.read(new File("materials/MapPic/03_Gym.jpg")) );
			image_map.add( ImageIO.read(new File("materials/MapPic/04_Crossroad.jpg")) );
		}
		catch(IOException ex){
			System.out.println("CANNOT load images in MapPanel");
		}
	}
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(image_map.get(map_index),mapX,mapY,null);
	}
	
	public void setIndex(int i) {
		map_index = i;
	}
	
}
