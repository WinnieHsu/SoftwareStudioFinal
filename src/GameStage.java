import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameStage extends JFrame implements Runnable
{	
	private static final long serialVersionUID = 1L;
	private GamePanel gp; //state=0
	private MapPanel mp;
	private BearPanel bp;
	private CarGameFrame c; //state=1
	Thread game_thread;
	Thread bear_thread;
	
	private int state;
	private int score, winScore;

	public GameStage(){
		score = 0; winScore = 300;
		state=0;
		
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
	
	public void setState(int n){
		state = n;
	}

	
	@Override
	public void run(){
		//long lastTime = System.currentTimeMillis();
		int isEnding = 0, delay=40;
		while(isEnding == 0){
			if(score==winScore){
				isEnding=1;
			}
			
			if(state==1){
				this.remove(gp);
				c = new CarGameFrame();
				this.add(c);
			}
			
			try {
				//gp.repaint();
				Thread.sleep(750);
				//lastTime = lastTime + delay;
				//Thread.sleep(Math.max(0, lastTime-System.currentTimeMillis()));
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		System.exit(0);
	}
}
