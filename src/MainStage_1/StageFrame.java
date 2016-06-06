package MainStage_1;

import java.awt.*;
import javax.swing.*;

public class StageFrame extends JFrame{
	private static StagePanel_1 P1;
	private static StagePanel_2 P2;
	private static StagePanel_3 P3;
	private static StagePanel_4 P4;
	
	public StageFrame() {
		P4 = new StagePanel_4();
		P4.thread.start();
		getContentPane().add(P4);
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
