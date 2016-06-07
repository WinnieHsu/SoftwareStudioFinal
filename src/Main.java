import javax.swing.JFrame;

import Menu.*;

public class Main {
	public static void main(String[] args){
		//JFrame frame = new JFrame();
		GameStage gs = new GameStage();
		//Menu menu = new Menu(gs);
		//gs.add(menu);
		Thread game_thread = new Thread(gs);
		//game_thread.start();
		
	}
}
