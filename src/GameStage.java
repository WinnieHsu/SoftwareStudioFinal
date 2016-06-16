import GameStage1.*;
import VolleyGame.*;
import MainStage_1.*;
import Pacman.*;
import Menu.*;
import TrashThrow.*;

import java.awt.Color;

import javax.swing.JFrame;

public class GameStage extends JFrame implements Runnable
{	
	private static final long serialVersionUID = 1L;
	 //state=0
	private Menu menu;
	private Rules rules;
	private MapPanel mp;
	private BearPanel bp;
	protected static Begin begin;
	private static Win win;
	private static Lose lose;
	private static CarGamePanel cp; //state=1
	private static VolleyPanel vp;
	private static PacManPanel pap;
	private static TrashThrowPanel tp;
	private static StagePanel_1 P1;
	private static StagePanel_2 P2;
	private static StagePanel_3 P3;
	private static StagePanel_4 P4;
	private static StagePanel_5 P5;
	Thread game_thread;
	Thread bear_thread;
	
	private int state;
	private int score, winScore;
	
	public GameStage(){
		score = 0; winScore = 20;
		state=-1;
		
		this.setTitle("Hao Chuan wants to call you but he won't say");
		this.setLayout(null);
		this.setSize(1200,572);
		this.setLocation(100,100);
		this.setResizable(false);
		this.setBackground(Color.CYAN);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new Menu(this);
		rules = new Rules(menu);
		begin = new Begin();
		win  = new Win();
		lose = new Lose();
		vp = new VolleyPanel();
		cp = new CarGamePanel();
		pap = new PacManPanel();
		tp = new TrashThrowPanel();
		P1 = new StagePanel_1();
		P2 = new StagePanel_2();
		P3 = new StagePanel_3();
		P4 = new StagePanel_4();
		P5 = new StagePanel_5();
		mp = new MapPanel(this);
		bp = new BearPanel(this);
		Begining();	
		//this.add(menu);
		this.add(mp);
		this.add(bp);
			
		bear_thread = new Thread(bp);
		//bear_thread.start();
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
			
			if(P3.alive == false && state == 4) {
				open_trashgame();
			}
			if(tp.alive == false && state == 5) {
				open_Stage4();
			}
			if(P4.alive == false && state == 6) {
				open_pacman();
			}
			if(pap.alive == false && state == 7) {
				open_Stage5();
			}
			if(P5.alive == false && state == 8) {
				
				if(bp.getIndex() < 3)
					open_Win();
				else
					open_Lose();
			}
			/*
			if(pap.alive == false && state == 5) {
				open_Lose();
			}
			*/
			
			
			try {
				Thread.sleep(100);
				
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
		mp.repaint();
		this.getContentPane().add(P1);
		state = 0;
	}
	
	private void open_cargame() {
		System.out.println("In open_car");
		bp.setIndex(P1.score);
		bp.repaint();
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
	
	
	private void open_trashgame(){
		System.out.println("In trash");
		this.getContentPane().remove(P3);
		tp.thread.start();
		this.getContentPane().add(tp);
		state = 5;
	}
	
	private void open_Stage4() {
		System.out.println("In Stage4");
		this.getContentPane().remove(tp);
		P4.thread.start();
		mp.setIndex(4);
		mp.repaint();
		this.getContentPane().add(P4);
		state = 6;
	}
	
	private void open_pacman() {
		System.out.println("In pacman");
		bp.setIndex(P1.score+P4.score);
		bp.repaint();
		this.getContentPane().remove(P4);
		pap.thread.start();
		this.getContentPane().add(pap);
		
		state = 7;
	}
	
	private void open_Stage5() {
		System.out.println("In stage5");
		this.getContentPane().remove(pap);
		mp.setIndex(0);
		mp.repaint();
		P5.thread.start();
		this.getContentPane().add(P5);
		state = 8;
	}
	
	private void open_Win() {
		System.out.println("In Win");
		this.getContentPane().remove(P5);
		
		win.thread.start();
		this.getContentPane().add(win);
		state = 10;
	}
	private void open_Lose() {
		System.out.println("In Lose");
		this.getContentPane().remove(P5);
		
		lose.thread.start();
		//mp.setIndex(0);
		//mp.repaint();
		this.getContentPane().add(lose);
		state = 11;
	}
}

