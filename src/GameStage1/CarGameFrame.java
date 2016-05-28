package GameStage1;

import java.awt.*;
import javax.swing.*;


public class CarGameFrame extends JFrame{
	
	private static CarGamePanel CarGP;
	private static TextPanel TP;
	
	public CarGameFrame() {
		CarGP = new CarGamePanel();
		//TP = new TextPanel();
		//SleepWalker SW = new SleepWalker();
		//getContentPane().add(SW);
		getContentPane().add(CarGP);
		//getContentPane().add(TP);
		//getContentPane().add(TP.GP);
		//thread_CarGP.start();
		//thread_TP.start();
		
		setTitle("Game_Car");
		setLayout(null);
		setSize(1000,600);
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    
	    
	}
	
	private void change_scenes() {
		//thread_CarGP = new Thread(CarGP);
	    //thread_TP = new Thread(TP);
		//thread_CarGP.start();
		//thread_TP.start();
		
	}
}
