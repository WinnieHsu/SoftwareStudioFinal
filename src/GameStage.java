import GameStage1.*;
import VolleyGame.*;
import MainStage_1.*;

import java.awt.Color;

import javax.swing.JFrame;

public class GameStage extends JFrame implements Runnable
{	
	private static final long serialVersionUID = 1L;
	 //state=0
	private MapPanel mp;
	private BearPanel bp;
	private static Begin begin;
	private static CarGamePanel cp; //state=1
	private static VolleyPanel vp;
	private static StagePanel_1 P1;
	private static StagePanel_2 P2;
	private static StagePanel_3 P3;
	private static StagePanel_4 P4;
	Thread game_thread;
	Thread bear_thread;
	
	private int state;
	private int score, winScore;
	

	public GameStage(){
		score = 0; winScore = 20;
		state=-1;
		
		this.setTitle("Testtttt");
		this.setLayout(null);
		this.setSize(1200,572);
		this.setLocation(100,100);
		this.setResizable(false);
		this.setBackground(Color.CYAN);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		begin = new Begin();		
		vp = new VolleyPanel();
		cp = new CarGamePanel();
		P1 = new StagePanel_1();
		P2 = new StagePanel_2();
		P3 = new StagePanel_3();
		mp = new MapPanel(this);
		bp = new BearPanel(this);
		Begining();
		//this.add(c);
		
		this.add(mp);
		this.add(bp);
			
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
			if(begin.alive == false && state == -1) {
				open_Stage1();
			}
			if(P1.alive == false && state == 0){
				open_cargame();
			}
			if(cp.alive == false && state == 1) {				
				 open_Stage2();
			}
			if(P2.alive == false && state == 2) {
				open_volleygame(); 
			}
			if(vp.alive == false && state == 3) {
				open_Stage3();
			}
			
			//if()
			
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		//System.exit(0);
	}
	
	private void Begining() {
		System.out.println("In Begining");
		begin.thread.start();
		this.getContentPane().add(begin);
		
	}
	
	private void open_Stage1() {
		System.out.println("In stage1");
		this.getContentPane().remove(begin);
		P1.thread.start();
		mp.setIndex(1);
		this.getContentPane().add(P1);
		state = 0;
	}
	
	private void open_cargame() {
		System.out.println("In open_car");
		this.getContentPane().remove(P1);
		
		cp.thread.start();
		this.getContentPane().add(cp);
		state = 1;
	}
	
	private void open_Stage2() {
		System.out.println("In stage2");
		this.getContentPane().remove(cp);
		
		P2.thread.start();
		mp.setIndex(2);
		mp.repaint();
		this.getContentPane().add(P2);
		state = 2;
	}
	
	private void open_volleygame() {
		System.out.println("open_volley");		
		this.getContentPane().remove(P2);
		vp.thread.start();
		this.getContentPane().add(vp);
		state = 3;
	}
	
	private void open_Stage3() {
		System.out.println("In stage3");
		this.getContentPane().remove(vp);
		
		P3.thread.start();
		mp.setIndex(3);
		mp.repaint();
		this.getContentPane().add(P3);
		state = 4;
	}
}

