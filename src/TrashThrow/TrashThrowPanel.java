package TrashThrow;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import java.util.Random;

public class TrashThrowPanel extends JPanel implements KeyListener, Runnable, ActionListener {
	
	private static final long serialVersionUID = 1L;
	protected JLabel label;
	private JLabel[] label_trash = new JLabel[5];
	private int trash_x = 420, trash_y = 490;
	private int[] trash_vertical_path = new int[350];
	private TrashVirticalMoving tv_moving;
	
	protected BufferedImage[] image_trash_can = new BufferedImage[5];
	protected BufferedImage[] image_trash = new BufferedImage[5];
	private int points = 0;
	protected int trash_number = 0;
	protected boolean is_throwed = true;
	private Random random = new Random();
	
	private JButton start;
	private boolean pressed_start;
	public boolean alive;
	public Thread thread;
	
	public TrashThrowPanel() {
		this.setBounds(0, 0, 900, 550);
		this.setLayout(null);		
		setLabel();
		setVerticalPath();
		start = new JButton();
    	start.setText("Start");
    	start.setFont(new Font("Serif",Font.ITALIC+Font.BOLD,40));
        start.setBounds(350, 180, 200, 100);
        start.addActionListener(this);
    	add(start);
		
    	pressed_start = false;
		alive = true;
		thread = new Thread(this);
		setFocusable(true);
    	requestFocus();
        addKeyListener(this);
	}
	
	public void setLabel() {
		
		try {
			for (int i=0; i<5; i++) {
				String str = "materials/TrashThrow/trash_can" + i + ".png";
				image_trash_can[i] = ImageIO.read(new File(str));
				str = "materials/TrashThrow/trash" + i + ".png";
				image_trash[i] = ImageIO.read(new File(str));
			}
		} catch (IOException e) {
			System.out.println("No Picture");
		}
		
		label = new JLabel("Score:  " + this.getPoints(), SwingConstants.CENTER);
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
			while (getPoints() < 8) {
				if(pressed_start == true)
				{
					int num = 0;
					trash_x = 420;
					trash_y = 490;
					if (is_first == true) {
						try {
							Thread.sleep(100);
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
					is_throwed = true;
					if (trash_number == 0) {
						if (trash_x >= 20 && trash_x <= 160) {
							addPoints();
						}
					} else if (trash_number == 1) {
						if (trash_x >= 200 && trash_x <= 320) {
							addPoints();
						}
					} else if (trash_number == 2) {
						if (trash_x >= 360 && trash_x <= 530) {
							addPoints();
						}
					} else if (trash_number == 3) {
						if (trash_x >= 540 && trash_x <= 680) {
							addPoints();
						}
					} else {
						if (trash_x >= 680 && trash_x <= 820) {
							addPoints();
						}
					}
				}
			}
				
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
	
	public int getPoints() {
		return points;
	}
	
	public void addPoints() {
		points = points + 1;
		label.setText("Score:  " + getPoints());
	}
	
	public void run() {
		repaint();
		int create = 0;
		while (getPoints() < 8) {
			if(pressed_start == true)
			{
				if(create == 0)
				{
					tv_moving = new TrashVirticalMoving();
					create = 1;
				}
				if (is_throwed == true) {
					trash_number = random.nextInt(5);
					is_throwed = false;
				}
				
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		alive = false;
		setFocusable(false);
		removeKeyListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image_trash_can[0], 20, 270, 200, 200, null);
		g.drawImage(image_trash_can[1], 200, 260, 180, 180, null);
		g.drawImage(image_trash_can[2], 360, 250, 230, 230, null);
		g.drawImage(image_trash_can[3], 540, 260, 200, 200, null);
		g.drawImage(image_trash_can[4], 680, 250, 200, 200, null);
		g.drawImage(image_trash[trash_number], trash_x, trash_y, 60, 60, null);
	}
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == start){		
			pressed_start = true;
			repaint();
			start.setEnabled(false);
			remove(start);
			
		}
    }
	
}