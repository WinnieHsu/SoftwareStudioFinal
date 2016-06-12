package Menu;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Menu extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel label_game_name_English;
	private JTextArea text_game_name_Chinese1, text_game_name_Chinese2;
	private JFrame frame;
	public JButton[] button;
	private BufferedImage image_teacher;
	protected boolean is_reading_rules = false;
	
	public Menu(JFrame frame) {
		this.frame = frame;
		this.setBounds(0, 0, 900, 550);
		this.setLayout(null);
		try {
			image_teacher =  ImageIO.read(new File("materials/Menu/teacher.png"));
		} catch (IOException e) {
			System.out.println("No Picture");
		}
		setLabel();
		setTextArea();
		setButton();
	}
	
	public void setLabel() {
		label_game_name_English = new JLabel("Hao Chuan Wants to Call You But He Won't Say", SwingConstants.CENTER);
		label_game_name_English.setBounds(50, 20, 800, 60);
		label_game_name_English.setFont(new Font("新細明體", 10, 35));
		label_game_name_English.setOpaque(true);
		this.add(label_game_name_English);
	}
	
	public void setTextArea() {
		text_game_name_Chinese1 = new JTextArea();
		text_game_name_Chinese1.setBounds(40, 150, 65, 360);
		text_game_name_Chinese1.setText("浩" + "\n" + "全" + "\n" + "想" + "\n" + "點" + "\n" + "你");
		text_game_name_Chinese1.setFont(new Font("新細明體", 10, 60));
		text_game_name_Chinese1.setBackground(Color.BLUE);
		text_game_name_Chinese1.setEditable(false);
		
		text_game_name_Chinese2 = new JTextArea();
		text_game_name_Chinese2.setBounds(790, 150, 65, 360);
		text_game_name_Chinese2.setText("但" + "\n" + "浩" + "\n" + "全" + "\n" + "不" + "\n" + "說");
		text_game_name_Chinese2.setFont(new Font("新細明體", 10, 60));
		text_game_name_Chinese2.setBackground(Color.BLUE);
		text_game_name_Chinese2.setEditable(false);
		
		this.add(text_game_name_Chinese1);
		this.add(text_game_name_Chinese2);
	}
	
	public void setButton() {
		button = new JButton[5];
		
		button[0] = new JButton("S T A R T");
		button[1] = new JButton("S C O R E B O A R D");
		button[2] = new JButton("R U L E S");
		button[3] = new JButton("E X I T");
		button[4] = new JButton("N O T H I N G");
		
		for (int i=0; i<5; i++) {
			button[i].setBounds(250, 240+60*i, 400, 55);
			button[i].setFont(new Font("新細明體", 10, 25));
			button[i].setBorderPainted(false);
			button[i].setOpaque(true);
			button[i].addActionListener(this);
		}
		
		button[0].setBackground(Color.RED);
		button[1].setBackground(Color.ORANGE);
		button[2].setBackground(Color.BLACK);
		button[3].setBackground(Color.YELLOW);
		button[4].setBackground(Color.BLUE);
		
		button[0].setForeground(Color.YELLOW);
		button[1].setForeground(Color.BLACK);
		button[2].setForeground(Color.GREEN);
		button[3].setForeground(Color.BLUE);
		button[4].setForeground(Color.BLACK);
		
		for (int i=0; i<5; i++) {
			this.add(button[i]);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button[0]) { //Start
			this.setVisible(false);
		} else if (e.getSource() == button[1]) { //Scoreboard
			
		} else if (e.getSource() == button[2]) {
			this.setVisible(false);
			Rules rules = new Rules(this);
			frame.add(rules);
		} else if (e.getSource() == button[3]) {
			System.exit(0);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i=0; i<4; i++) {
			for (int j=0; j<2; j++) {
				g.drawImage(image_teacher, 225*i, 275*j, 225, 275, null);
			}
		}
	}
	
}