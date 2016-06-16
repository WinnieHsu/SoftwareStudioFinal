package Pacman;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class PacManPanel extends JPanel implements KeyListener, Runnable, ActionListener {
	
	private static final long serialVersionUID = 1L;
	//protected PacMan pacman;
	protected JLabel label;
	protected int pm_x = 0, pm_y = 0;
	protected int[] m_x = new int[8];
	protected int[] m_y = new int [8];
	protected CharacterState[] monster_state = new CharacterState[8];
	protected ObstacleState[][] obstacle_state;
	private ObstacleStateRecord obstacle_state_record;
	private PacManMoving pacman_moving;
	private MonsterMoving[] monster_moving = new MonsterMoving[8];
	protected boolean mouth_open = false;
	protected boolean[] monster_exist = new boolean[8];
	
	
	
	protected BufferedImage image_block, image_block_lock, image_rectangle;
	protected BufferedImage image_pacman_origin;
	protected BufferedImage image_pacman_up;
	protected BufferedImage image_pacman_down;
	protected BufferedImage image_pacman_left;
	protected BufferedImage image_pacman_right;
	protected BufferedImage[] image_monster = new BufferedImage[8];
	protected CharacterState pacman_state;
	private int points = 0;
	//protected int pm_x, pm_y;
	public boolean alive;
	
	private JButton start;
	private boolean pressed_start;
	public Thread thread;
	
	public PacManPanel() {
		
		alive = true;
		pressed_start = false;
		start = new JButton();
    	start.setText("Start");
    	start.setFont(new Font("Serif",Font.ITALIC+Font.BOLD,40));
        start.setBounds(350, 180, 200, 100);
        start.addActionListener(this);
    	add(start);
		try {
			image_block =  ImageIO.read(new File("materials/Pacman/block.png"));
			image_block_lock =  ImageIO.read(new File("materials/Pacman/block_lock.png"));
			image_rectangle =  ImageIO.read(new File("materials/Pacman/rectangle.png"));
			image_pacman_origin =  ImageIO.read(new File("materials/Pacman/pacman_origin.png"));
			image_pacman_up =  ImageIO.read(new File("materials/Pacman/pacman_up.png"));
			image_pacman_down =  ImageIO.read(new File("materials/Pacman/pacman_down.png"));
			image_pacman_left =  ImageIO.read(new File("materials/Pacman/pacman_left.png"));
			image_pacman_right =  ImageIO.read(new File("materials/Pacman/pacman_right.png"));
			for (int i=0; i<8; i++) {
				String str = "materials/Pacman/monster" + i + ".png";
				image_monster[i] = ImageIO.read(new File(str));
			}
		} catch (IOException e) {
			System.out.println("No Picture");
		}
		
		thread = new Thread(this);
		//this.pacman = pacman;
		this.pacman_state = CharacterState.ORIGIN;
		this.setBounds(0, 0, 900, 550);
		this.setLayout(null);
		setLabel();
		obstacle_state = new ObstacleState[36][22];
		obstacle_state_record = new ObstacleStateRecord(this);
		//pacman_moving = new PacManMoving(this);
		m_x[0] = 10*25;
		m_y[0] = 9*25;
		m_x[1] = 30*25;
		m_y[1] = 3*25;
		m_x[2] = 0*25;
		m_y[2] = 20*25;
		m_x[3] = 32*25;
		m_y[3] = 21*25;
		m_x[4] = 14*25;
		m_y[4] = 9*25;
		m_x[5] = 32*25;
		m_y[5] = 9*25;
		m_x[6] = 7*25;
		m_y[6] = 21*25;
		m_x[7] = 15*25;
		m_y[7] = 11*25;
		
		 setFocusable(true);
	    requestFocus();
		addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				pacman_state = CharacterState.UP;
				break;
			case KeyEvent.VK_DOWN:
				pacman_state = CharacterState.DOWN;
				break;
			case KeyEvent.VK_LEFT:
				pacman_state = CharacterState.LEFT;
				break;
			case KeyEvent.VK_RIGHT:
				pacman_state = CharacterState.RIGHT;
				break;
			default:
				break;
		}
	}

	public void keyTyped(KeyEvent event) {}
	
	public void keyReleased(KeyEvent event) {	}
	
	public void setLabel() {
		label = new JLabel("Score:  " + this.getPoints());
		Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 20);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setBounds (770, 15, 120, 40);
		this.add(label);
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
		while (getPoints() < 8)
		{
			if(pressed_start == true)
			{
				if(create == 0)
				{
					pacman_moving = new PacManMoving(this);
					for (int i=0; i<8; i++) {
						monster_state[i] = CharacterState.ORIGIN;
						monster_moving[i] = new MonsterMoving(this, i);
						monster_exist[i] = true;
					}
					create = 1;
				}
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		alive = false;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setBackground(null);
		repaint();
		System.out.println("shit");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i=0; i<36; i++) {
			for (int j=0; j<22; j++) {
				if (obstacle_state[i][j] == ObstacleState.UNLOCK) {
					g.drawImage(image_block, 25*i, 25*j, 25, 25, null);
				} else {
					g.drawImage(image_block_lock, 25*i, 25*j, 25, 25, null);
				}
			}
		}
		g.drawImage(image_rectangle, 30*25, 0, 6*25, 3*25, null);
		if (pacman_state == CharacterState.ORIGIN || mouth_open == false) {
			g.drawImage(image_pacman_origin, pm_x, pm_y, 25, 25, null);
		} else if (pacman_state == CharacterState.UP) {
			g.drawImage(image_pacman_up, pm_x, pm_y, 25, 25, null);
		} else if (pacman_state == CharacterState.DOWN) {
			g.drawImage(image_pacman_down, pm_x, pm_y, 25, 25, null);
		} else if (pacman_state == CharacterState.LEFT) {
			g.drawImage(image_pacman_left, pm_x, pm_y, 25, 25, null);
		} else if (pacman_state ==CharacterState.RIGHT) {
			g.drawImage(image_pacman_right, pm_x, pm_y, 25, 25, null);
		}
		for (int i=0; i<8; i++) {
			if (monster_exist[i] == true) {
				g.drawImage(image_monster[i], m_x[i], m_y[i], 25, 25, null);
			}
		}
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