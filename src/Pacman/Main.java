package Pacman;

//import TrashThrow.TrashThrow;
import Menu.Menu;

import javax.swing.JFrame;
import java.awt.Color;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("PacMan");
		frame.setLayout(null);
		frame.setSize(1200,572);
		frame.setLocation(100,100);
		frame.setResizable(false);
		frame.setBackground(Color.CYAN);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PacMan pacman = new PacMan(frame);
		pacman.thread.start();
		//Thread pm_thread = new Thread(pacman);
		//pm_thread.start();
		/*Menu menu = new Menu(frame);
		frame.add(menu);*/
		/*TrashThrow trash_throw = new TrashThrow(frame);
		Thread trash_throw_thread = new Thread(trash_throw);
		trash_throw_thread.start();*/
		frame.setVisible(true);
	}
}