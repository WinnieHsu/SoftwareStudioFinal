package Pacman;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class PacMan implements Runnable {
	
	private PacManPanel pmp;
	protected BufferedImage image_block, image_block_lock, image_rectangle;
	protected BufferedImage image_pacman_origin;
	protected BufferedImage image_pacman_up;
	protected BufferedImage image_pacman_down;
	protected BufferedImage image_pacman_left;
	protected BufferedImage image_pacman_right;
	protected BufferedImage[] image_monster = new BufferedImage[8];
	protected CharacterState pacman_state;
	private int points = 0;
	protected int pm_x, pm_y;
	public boolean alive;
	
	public PacMan(JFrame frame) {
		alive = true;
		try {
			image_block =  ImageIO.read(new File("materials/Pacman/block.png"));
			image_block_lock =  ImageIO.read(new File("materials/Pacman/block_lock.png"));
			image_rectangle =  ImageIO.read(new File("materials/Pacman/rectangle.png"));
			image_pacman_origin =  ImageIO.read(new File("materials/Pacman/pacman_origin.png"));
			image_pacman_up =  ImageIO.read(new File("materials/Pacman/pacman_up.png"));
			image_pacman_down =  ImageIO.read(new File("materials/Pacman/pacman_down.png"));
			image_pacman_left =  ImageIO.read(new File("materials/Pacman/pacman_left.png"));
			image_pacman_right =  ImageIO.read(new File("materials/Pacman/pacman_right.png"));
			for (int i=0; i<8; i++) {
				String str = "materials/Pacman/monster" + i + ".png";
				image_monster[i] = ImageIO.read(new File(str));
			}
		} catch (IOException e) {
			System.out.println("No Picture");
		}
		pmp = new PacManPanel(this, frame);
		frame.add(pmp);
	}
	
	public int getPoints() {
		return points;
	}
	
	public void addPoints() {
		points = points + 1;
		pmp.label.setText("Score:  " + getPoints());
	}
	
	public void run() {
		while (getPoints() != 8) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		alive = false;
	}
	
}