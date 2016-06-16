package MainStage_1;

import VolleyGame.*;
import TrashThrow.*;
import Pacman.*;
import java.awt.*;
import javax.swing.*;

public class StageFrame extends JFrame{
	private static StagePanel_1 P1;
	private static StagePanel_2 P2;
	private static StagePanel_3 P3;
	private static StagePanel_4 P4;
	private static StagePanel_5 P5;
	private static VolleyPanel vp;
	private static TrashThrowPanel tp;
	private static PacManPanel pp; 
	
	
	public StageFrame() {
		//vp = new VolleyPanel();
		//tp = new TrashThrowPanel();
		//pp = new PacManPanel();
		P5 = new StagePanel_5();
		//P4 = new StagePanel_4();
		//tp.thread.start();
		//pp.thread.start();
		//vp.thread.start();
		P5.thread.start();
		getContentPane().add(P5);
		setTitle("Stage");
		setLayout(null);
		setSize(1000,600);
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	}
	
	public static void main(String[] args){
		StageFrame gs = new StageFrame();
	}

}
