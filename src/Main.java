import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Testtttt");
		frame.setLayout(null);
		frame.setSize(1200,572);
		frame.setLocation(100,100);
		frame.setResizable(false);
		frame.setBackground(Color.CYAN);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameStage gs = new GameStage(frame);
		Thread game_thread = new Thread(gs);
		game_thread.start();
		frame.setVisible(true);
		
	}
}
