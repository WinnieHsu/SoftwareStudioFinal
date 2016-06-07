package MainStage_1;

import java.io.File;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Image_mouse {
	private int img_x;
	private int img_y;
	private BufferedImage image;
	
	public Image_mouse(int x, int y, String str) {
		img_x = x;
		img_y = y;
		loadImage(str);	
	}
	
	public int getX() {
		return img_x;
	}
	
	public int getY() {
		return img_y;
	}
	
	public void setX(int x) {
		img_x = x;
	}
	
	public void setY(int y) {
		img_y = y;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	private void loadImage(String str) {
		try
		{
			File file = new File(str);
			image = ImageIO.read(file);
			
		}
		catch(Exception e) {
			System.out.println("load image goes wrong in trigger");
		}
	}

}
