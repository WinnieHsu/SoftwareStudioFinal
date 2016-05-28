package GameStage1;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Car {
	private int car_x;
	private int car_y;
	private double degree;
	private int HP;
	private File file1, file2;
	private BufferedImage image, image_mirror;
	
	public Car(int x, int y, String str1, String str2) {
		car_x = x;
		car_y = y;
		degree = 0;
		HP = 100;
		setImage(str1, str2);
	}

	public int getCar_x() {
		return car_x;
	}
	
	public int getCar_y() {
		return car_y;
	}
	
	public double getDegree() {
		return degree;
	}
	
	public int getHP() {
		return HP;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public BufferedImage getImage_mirror() {
		return image_mirror;
	}
	
	public void setCar_x(int x) {
		car_x = x ;
	}
	
	public void setCar_y(int y) {
		car_y = y;
	}
	
	public void setDegree(double D) {
		degree = D;
	}
	
	public void setHP(int hp) {
		HP = hp;
	}
	
	public void setImage(String str1, String str2) {
		try
		{
			file1 = new File(str1);
			file2 = new File(str2);
			image = ImageIO.read(file1);
			image_mirror = ImageIO.read(file2);
		}
		catch(Exception e) {
			System.out.println("load image goes wrong");
			return;
		}
	}
	
	
	
}
