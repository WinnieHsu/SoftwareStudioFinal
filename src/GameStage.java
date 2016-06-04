import GameStage1.*;
import MainStage_1.*;

import java.awt.Color;

import javax.swing.JFrame;

public class GameStage extends JFrame implements Runnable
{	
	private static final long serialVersionUID = 1L;
	private static GamePanel gp; //state=0
	private MapPanel mp;
	private BearPanel bp;
	private static CarGamePanel c; //state=1
	private static StagePanel_1 P1;
	Thread game_thread;
	Thread bear_thread;
	
	private int state;
	private int score, winScore;
	

	public GameStage(){
		score = 0; winScore = 20;
		state=0;
		
		this.setTitle("Testtttt");
		this.setLayout(null);
		this.setSize(1200,572);
		this.setLocation(100,100);
		this.setResizable(false);
		this.setBackground(Color.CYAN);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		startgame();		
		mp = new MapPanel(this);
		bp = new BearPanel(this);
		gp = new GamePanel();
		c = new CarGamePanel();
		//this.add(c);
		
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

	public int getScore() {
		return score;
	}
	
	public int getWinScore() {
		return winScore;
	}
	
	@Override
	public void run(){
		
		int isEnding = 0;
		while(isEnding == 0){
			if(P1.alive == false && state == 0){
				open_volleygame();
				//System.out.println("In sub_if");
				//System.out.println("call car");
			}
			 if(gp.alive == false && state == 1) {
				 open_cargame();
			 }
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		//System.exit(0);
	}
	
	private void startgame() {
		P1 = new StagePanel_1();
		this.getContentPane().add(P1);
	}
	
	private void open_cargame() {
		System.out.println("In open_car");
		state = 2;
		this.getContentPane().remove(gp);
		c.thread.start();
		this.getContentPane().add(c);
	}
	
	private void open_volleygame() {
		System.out.println("open_volley");		
		this.getContentPane().remove(P1);
		gp.thread.start();
		this.getContentPane().add(gp);
		state = 1;
	}
}

