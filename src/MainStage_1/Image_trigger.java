package MainStage_1;

import java.io.File;
import java.awt.*;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;

public class Image_trigger {
	
	private int img_x;
	private int img_y;
	private ImageIcon image;
	private boolean on_off;
	
	public Image_trigger(int x, int y, String str) {
		img_x = x;
		img_y = y;
		loadImage(str);
		on_off = false;
	}
	
	public int getX() {
		return img_x;
	}
	
	public int getY() {
		return img_y;
	}
	
	public ImageIcon getImage() {
		return image; 
	}
	
	public boolean getOn_off() {
		return on_off;
	}
	
	public void chageOn_off(){
		if(on_off == true)
			on_off = false;
		else
			on_off = true;
	}
	
	private void loadImage(String str) {
		try
		{
			image = new ImageIcon(str);
			
		}
		catch(Exception e) {
			System.out.println("load image goes wrong in trigger");
		}
	}

}
