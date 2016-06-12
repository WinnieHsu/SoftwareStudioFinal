package Menu;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JTextArea textarea;
	private JLabel label;
	private JButton button;
	private Menu menu;
	
	public Rules(Menu menu) {
		this.setLayout(null);
		this.setBounds(0, 0, 900, 550);
		this.menu = menu;
		setLabel();
		setButton();
		setTextArea();
	}
	
	public void setLabel() {
		label = new JLabel("R  U  L  E  S", SwingConstants.CENTER);
		label.setFont(new Font("新細明體", 10, 60));
		label.setBounds(0, 0, 900, 75);
		label.setBackground(Color.RED);
		label.setOpaque(true);
		this.add(label);
	}
	
	public void setButton() {
		button = new JButton("BACK");
		button.setBounds(10, 10, 150, 40);
		button.setFont(new Font("新細明體", 10, 25));
		button.setBorderPainted(false);
		button.setOpaque(true);
		button.setBackground(Color.BLUE);
		this.add(button);
		button.addActionListener(this);
	}
	
	public void setTextArea() {
		textarea = new JTextArea();
		String content;
		content = "      The main character fell asleep in the handsome professor's class";
		content = content + "\n\n" + "                 She started to sleep walk to some places in NTHU";
		content = content + "\n\n" + "           The goal of the game is to return to the classroom in time";
		content = content + "\n\n" + "                          Don't forget to save the polar bears, too";
		content = content + "\n\n" + "          So, you have to do something to save the polar bears such as";
		content = content + "\n\n" + "    Picking up garbages and turning off the air conditioner and the light";
		content = content + "\n\n" + "       After finishing all things, there will be a little game as your reward";
		content = content + "\n\n" + "          You will be required to finish the little game in a limited time";
		
		textarea.setText(content);
		textarea.setFont(new Font("新細明體", 10, 25));
		textarea.setBounds(0, 75, 900, 475);
		textarea.setBackground(Color.RED);
		this.add(textarea);
	}
	
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		menu.setVisible(true);
	}
	
}