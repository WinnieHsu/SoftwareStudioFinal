package TrashThrow;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import java.util.Random;

public class TrashThrow implements Runnable {
	
	private TrashThrowPanel ttp;
	protected BufferedImage[] image_trash_can = new BufferedImage[5];
	protected BufferedImage[] image_trash = new BufferedImage[5];
	private int points = 0;
	protected int trash_number = 0;
	protected boolean is_throwed = true;
	private Random random = new Random();
	public Thread thread;
	
	public TrashThrow() {
		try {
			for (int i=0; i<5; i++) {
				String str = "materials/TrashThrow/trash_can" + i + ".png";
				image_trash_can[i] = ImageIO.read(new File(str));
				str = "materials/TrashThrow/trash" + i + ".png";
				image_trash[i] = ImageIO.read(new File(str));
			}
		} catch (IOException e) {
			System.out.println("No Picture");
		}
		thread = new Thread(this);
		//ttp = new TrashThrowPanel(this);
		//frame.add(ttp);
	}
	
	public int getPoints() {
		return points;
	}
	
	public void addPoints() {
		points = points + 1;
		ttp.label.setText("Score:  " + getPoints());
	}
	
	public void run() {
		ttp = new TrashThrowPanel(this);
		while (getPoints() < 8) {
			if (is_throwed == true) {
				trash_number = random.nextInt(5);
				is_throwed = false;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}