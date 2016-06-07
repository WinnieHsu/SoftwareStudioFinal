package Pacman;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class PacManPanel extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	protected PacMan pacman;
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
	
	public PacManPanel(PacMan pacman, JFrame frame) {
		this.pacman = pacman;
		this.pacman.pacman_state = CharacterState.ORIGIN;
		this.setBounds(0, 0, 900, 550);
		this.setLayout(null);
		setLabel();
		obstacle_state = new ObstacleState[36][22];
		obstacle_state_record = new ObstacleStateRecord(this);
		pacman_moving = new PacManMoving(this);
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
		for (int i=0; i<8; i++) {
			monster_state[i] = CharacterState.ORIGIN;
			monster_moving[i] = new MonsterMoving(this, i);
			monster_exist[i] = true;
		}
		frame.addKeyListener(this);
	}
	
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				pacman.pacman_state = CharacterState.UP;
				break;
			case KeyEvent.VK_DOWN:
				pacman.pacman_state = CharacterState.DOWN;
				break;
			case KeyEvent.VK_LEFT:
				pacman.pacman_state = CharacterState.LEFT;
				break;
			case KeyEvent.VK_RIGHT:
				pacman.pacman_state = CharacterState.RIGHT;
				break;
			default:
				break;
		}
	}

	public void keyTyped(KeyEvent event) {}
	
	public void keyReleased(KeyEvent event) {	}
	
	public void setLabel() {
		label = new JLabel("Score:  " + this.pacman.getPoints());
		Font font = new Font(Font.DIALOG_INPUT, Font.ITALIC, 20);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setBounds (780, 15, 100, 40);
		this.add(label);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i=0; i<36; i++) {
			for (int j=0; j<22; j++) {
				if (obstacle_state[i][j] == ObstacleState.UNLOCK) {
					g.drawImage(pacman.image_block, 25*i, 25*j, 25, 25, null);
				} else {
					g.drawImage(pacman.image_block_lock, 25*i, 25*j, 25, 25, null);
				}
			}
		}
		g.drawImage(pacman.image_rectangle, 30*25, 0, 6*25, 3*25, null);
		if (pacman.pacman_state == CharacterState.ORIGIN || mouth_open == false) {
			g.drawImage(pacman.image_pacman_origin, pm_x, pm_y, 25, 25, null);
		} else if (pacman.pacman_state == CharacterState.UP) {
			g.drawImage(pacman.image_pacman_up, pm_x, pm_y, 25, 25, null);
		} else if (pacman.pacman_state == CharacterState.DOWN) {
			g.drawImage(pacman.image_pacman_down, pm_x, pm_y, 25, 25, null);
		} else if (pacman.pacman_state == CharacterState.LEFT) {
			g.drawImage(pacman.image_pacman_left, pm_x, pm_y, 25, 25, null);
		} else if (pacman.pacman_state ==CharacterState.RIGHT) {
			g.drawImage(pacman.image_pacman_right, pm_x, pm_y, 25, 25, null);
		}
		for (int i=0; i<8; i++) {
			if (monster_exist[i] == true) {
				g.drawImage(pacman.image_monster[i], m_x[i], m_y[i], 25, 25, null);
			}
		}
	}
	
}