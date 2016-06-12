package TrashThrow;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TrashThrowPanel extends JPanel implements KeyListener {
	
	private static TrashThrow trash_throw;
	private static final long serialVersionUID = 1L;
	protected JLabel label;
	private JLabel[] label_trash = new JLabel[5];
	private int trash_x = 420, trash_y = 490;
	private int[] trash_vertical_path = new int[350];
	private int count = 0;
	private TrashVirticalMoving tv_moving;
	public Thread thread;
	public boolean alive;
	
	public TrashThrowPanel(TrashThrow trash_throw) {
		this.trash_throw = trash_throw;
		this.setBounds(0, 0, 900, 550);
		this.setLayout(null);		
		setLabel();
		setVerticalPath();
		tv_moving = new TrashVirticalMoving();
		alive = true;
        setFocusable(true);
    	requestFocus();
        addKeyListener(this);
        
	}
	
	public void setLabel() {
		label = new JLabel("Score:  " + this.trash_throw.getPoints(), SwingConstants.CENTER);
		label.setBounds (720, 0, 180, 60);
		Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 25);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
		this.add(label);
		setTrashLabel();
	}
	
	public void setTrashLabel() {
		label_trash[0] = new JLabel("PET bottle", SwingConstants.CENTER);
		label_trash[0].setBounds (60, 400, 120, 25);
		label_trash[1] = new JLabel("Aluminum", SwingConstants.CENTER);
		label_trash[1].setBounds (230, 400, 120, 25);
		label_trash[2] = new JLabel("Glass", SwingConstants.CENTER);
		label_trash[2].setBounds (400, 400, 120, 25);
		label_trash[3] = new JLabel("Paper", SwingConstants.CENTER);
		label_trash[3].setBounds (590, 400, 120, 25);
		label_trash[4] = new JLabel("Plastic", SwingConstants.CENTER);
		label_trash[4].setBounds (740, 400, 120, 25);
		for (int i=0; i<5; i++) {
			Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 20);
			label_trash[i].setFont(font);
			label_trash[i].setForeground(Color.WHITE);
			label_trash[i].setBackground(Color.BLACK);
			label_trash[i].setOpaque(true);
			this.add(label_trash[i]);
		}
	}
	
	public void keyPressed(KeyEvent event) {
		System.out.println("asdfgh");
		switch (event.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				trash_x = trash_x - 8;
				break;
			case KeyEvent.VK_RIGHT:
				trash_x = trash_x + 8;
				break;
			default:
				break;
		}
	}

	public void keyTyped(KeyEvent event) {}
	
	public void keyReleased(KeyEvent event) {	}
	
	public class TrashVirticalMoving implements Runnable {
		public TrashVirticalMoving() {
			Thread thread = new Thread(this);
            thread.start();
		}
		public void run() {
			boolean is_first = true;
			while (trash_throw.getPoints() < 8) {
				int num = 0;
				trash_x = 420;
				trash_y = 490;
				if (is_first == true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					is_first = false;
				}
				while (num != 350) {
					trash_y = trash_y + trash_vertical_path[num];
					repaint();
					num++;
					try {
						Thread.sleep(7);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				trash_throw.is_throwed = true;
				if (trash_throw.trash_number == 0) {
					if (trash_x >= 20 && trash_x <= 160) {
						trash_throw.addPoints();
					}
				} else if (trash_throw.trash_number == 1) {
					if (trash_x >= 200 && trash_x <= 320) {
						trash_throw.addPoints();
					}
				} else if (trash_throw.trash_number == 2) {
					if (trash_x >= 360 && trash_x <= 530) {
						trash_throw.addPoints();
					}
				} else if (trash_throw.trash_number == 3) {
					if (trash_x >= 540 && trash_x <= 680) {
						trash_throw.addPoints();
					}
				} else {
					if (trash_x >= 680 && trash_x <= 820) {
						trash_throw.addPoints();
					}
				}
			}
			alive = false;
			//removeKeyListener(this);
		}
	}
	
	public void setVerticalPath() {
		for (int i=0; i<25; i++) {
			trash_vertical_path[0*25+i] = -4;
			trash_vertical_path[1*25+i] = -3;
			trash_vertical_path[2*25+i] = -3;
			trash_vertical_path[3*25+i] = -3;
			trash_vertical_path[4*25+i] = -2;
			trash_vertical_path[5*25+i] = -2;
			trash_vertical_path[6*25+i] = -1;
			trash_vertical_path[7*25+i] = -1;
			trash_vertical_path[8*25+i] = 0;
			trash_vertical_path[9*25+i] = 1;
			trash_vertical_path[10*25+i] = 1;
			trash_vertical_path[11*25+i] = 2;
			trash_vertical_path[12*25+i] = 2;
			trash_vertical_path[13*25+i] = 3;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(trash_throw.image_trash_can[0], 20, 270, 200, 200, null);
		g.drawImage(trash_throw.image_trash_can[1], 200, 260, 180, 180, null);
		g.drawImage(trash_throw.image_trash_can[2], 360, 250, 230, 230, null);
		g.drawImage(trash_throw.image_trash_can[3], 540, 260, 200, 200, null);
		g.drawImage(trash_throw.image_trash_can[4], 680, 250, 200, 200, null);
		g.drawImage(trash_throw.image_trash[trash_throw.trash_number], trash_x, trash_y, 60, 60, null);
	}
	
}