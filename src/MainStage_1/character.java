package MainStage_1;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class character {
	private BufferedImage image_ch_1;
	private BufferedImage image_ch_2;
	private int counter;
	
	public character(String str) {
		loadImage(str);
		counter = 0;
	}
	
	private void loadImage(String str) {
		System.out.println("character has loaded");
		try {
					
			File ch_1 = new File(str+"0.png");
			File ch_2 = new File(str+"1.png");
		    
		    image_ch_1 = ImageIO.read(ch_1);
		    image_ch_2 = ImageIO.read(ch_2);
		    
		}
		catch(Exception e) {
			System.out.println("wrong in character.java");
		}
	}
	
	public BufferedImage getImage_1() {
		return image_ch_1;
	}
	
	public BufferedImage getImage_2() {
		return image_ch_2;
	}
	
	public void setcounter(int a) {
		counter = a;
	}
	
	public int getcounter() {
		return counter;
	}

}
