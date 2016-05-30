import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameStage extends JFrame implements Runnable
{	
	private static final long serialVersionUID = 1L;
	private GamePanel gp;
	private MapPanel mp;
	private BearPanel bp;
	Thread game_thread;
	Thread bear_thread;
	
	private int score, winScore;

	public GameStage(){
		this.score = 0; this.winScore = 300; //need to modify later
		
		this.setTitle("Testtttt");
		this.setLayout(null);
		this.setSize(1200,572);
		this.setLocation(100,100);
		this.setResizable(false);
		this.setBackground(Color.CYAN);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gp = new GamePanel(this);
		mp = new MapPanel(this);
		bp = new BearPanel(this);
		this.add(gp);
		this.add(mp);
		this.add(bp);
    	
		game_thread = new Thread(gp);
		game_thread.start();
		bear_thread = new Thread(bp);
		bear_thread.start();
		
		this.setVisible(true);
	}
	
	public void setScore(int n){
		score = n;
	}
	public int getScore(){
		return score;
	}
	public int getWinScore(){
		return winScore;
	}
	
	@Override
	public void run(){
		long lastTime = System.currentTimeMillis();
		int isEnding = 0, delay=40;
		while(isEnding == 0){
			try {
				gp.repaint();
				
				if(score==winScore){
					isEnding=1;
					Thread.sleep(750);
				}
				lastTime = lastTime + delay;
				Thread.sleep(Math.max(0, lastTime-System.currentTimeMillis()));
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		System.exit(0);
	}
}
