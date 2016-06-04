import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		GameStage gs = new GameStage();
		Thread game_thread = new Thread(gs);
		game_thread.start();
		
	}
}
