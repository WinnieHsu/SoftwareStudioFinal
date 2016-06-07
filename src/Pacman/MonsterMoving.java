package Pacman;
import java.util.Random;

public class MonsterMoving implements Runnable {
	
	private int k;
	private PacManPanel pmp;
	private Random random = new Random();
	private int count = 0;
	private int direction, last_direction=0;
	
	public MonsterMoving(PacManPanel pmp, int k) {
		this.pmp = pmp;
		this.k = k;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		while (pmp.monster_exist[k] == true) {
			int one_block = 0;
			int lock_num = 0;
			if (pmp.m_y[k] == 0 || pmp.obstacle_state[pmp.m_x[k]/25][(pmp.m_y[k]/25)-1] == ObstacleState.LOCK) {
				lock_num++;
			}
			if (pmp.m_y[k] == 525 || pmp.obstacle_state[pmp.m_x[k]/25][(pmp.m_y[k]/25)+1] == ObstacleState.LOCK) {
				lock_num++;
			}
			if (pmp.m_x[k] == 0 || pmp.obstacle_state[(pmp.m_x[k]/25)-1][pmp.m_y[k]/25] == ObstacleState.LOCK) {
				lock_num++;
			}
			if (pmp.m_x[k] == 875 || pmp.obstacle_state[(pmp.m_x[k]/25)+1][pmp.m_y[k]/25] == ObstacleState.LOCK) {
				lock_num++;
			}
			if (lock_num == 3) {
				direction = random.nextInt(4);
				if (direction == 0) {
					pmp.monster_state[k] = CharacterState.UP;
				} else if (direction == 1) {
					pmp.monster_state[k] = CharacterState.RIGHT;
				} else if (direction == 2) {
					pmp.monster_state[k] = CharacterState.DOWN;
				} else {
					pmp.monster_state[k] = CharacterState.LEFT;
				}
			} else {
				if (count%3 == 0) {
					count = 0;
					direction = random.nextInt(4);
					if (direction == 0 && direction != (last_direction+2)%4) {
						pmp.monster_state[k] = CharacterState.UP;
					} else if (direction == 1 && direction != (last_direction+2)%4) {
						pmp.monster_state[k] = CharacterState.RIGHT;
					} else if (direction == 2 && direction != (last_direction+2)%4) {
						pmp.monster_state[k] = CharacterState.DOWN;
					} else if (direction == 3 && direction != (last_direction+2)%4) {
						pmp.monster_state[k] = CharacterState.LEFT;
					} else {
						direction = last_direction;
					}
				}
			}
			last_direction = direction;
			count++;
			switch (pmp.monster_state[k]) {
				case UP:
					if (pmp.m_y[k] != 0 && pmp.obstacle_state[pmp.m_x[k]/25][(pmp.m_y[k]/25)-1] == ObstacleState.UNLOCK) {
						while (one_block != 25) {
							pmp.m_y[k] = pmp.m_y[k] - 1;
							if ((Math.abs(pmp.m_x[k]-pmp.pm_x) < 25 && pmp.m_y[k] == pmp.pm_y) || (pmp.m_x[k] == pmp.pm_x && Math.abs(pmp.m_y[k]-pmp.pm_y) < 25)) {
								pmp.monster_exist[k] = false;
							}
							pmp.repaint();
							one_block++;
							try {
								Thread.sleep(3);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					break;
				case DOWN:
					if (pmp.m_y[k] != 525 && pmp.obstacle_state[pmp.m_x[k]/25][(pmp.m_y[k]/25)+1] == ObstacleState.UNLOCK) {
						while (one_block != 25) {
							pmp.m_y[k] = pmp.m_y[k] + 1;
							if ((Math.abs(pmp.m_x[k]-pmp.pm_x) < 25 && pmp.m_y[k] == pmp.pm_y) || (pmp.m_x[k] == pmp.pm_x && Math.abs(pmp.m_y[k]-pmp.pm_y) < 25)) {
								pmp.monster_exist[k] = false;
							}
							pmp.repaint();
							one_block++;
							try {
								Thread.sleep(3);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					break;
				case LEFT:
					if (pmp.m_x[k] != 0 && pmp.obstacle_state[(pmp.m_x[k]/25)-1][pmp.m_y[k]/25] == ObstacleState.UNLOCK) {
						while (one_block != 25) {
							pmp.m_x[k] = pmp.m_x[k] - 1;
							if ((Math.abs(pmp.m_x[k]-pmp.pm_x) < 25 && pmp.m_y[k] == pmp.pm_y) || (pmp.m_x[k] == pmp.pm_x && Math.abs(pmp.m_y[k]-pmp.pm_y) < 25)) {
								pmp.monster_exist[k] = false;
							}
							pmp.repaint();
							one_block++;
							try {
								Thread.sleep(3);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					break;
				case RIGHT:
					if (pmp.m_x[k] != 875 && pmp.obstacle_state[(pmp.m_x[k]/25)+1][pmp.m_y[k]/25] == ObstacleState.UNLOCK) {
						while (one_block != 25) {
							pmp.m_x[k] = pmp.m_x[k] + 1;
							if ((Math.abs(pmp.m_x[k]-pmp.pm_x) < 25 && pmp.m_y[k] == pmp.pm_y) || (pmp.m_x[k] == pmp.pm_x && Math.abs(pmp.m_y[k]-pmp.pm_y) < 25)) {
								pmp.monster_exist[k] = false;
							}
							pmp.repaint();
							one_block++;
							try {
								Thread.sleep(3);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					break;
				default:
					break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		pmp.pacman.addPoints();
	}
	
}