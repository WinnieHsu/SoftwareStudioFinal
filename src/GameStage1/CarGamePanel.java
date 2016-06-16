package GameStage1;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class CarGamePanel extends JPanel implements Runnable, KeyListener, ActionListener {
	
	private JButton button_start;
	private JButton button_exit;
	private JLabel label_truck;
	private JLabel label_blue_car;
	private JLabel label_time;
	
	private BufferedImage image_over;
	private BufferedImage image_begin;
	
	private int time;
	private String str_truck;
	private String str_truck_mirror;
	private String str_blue_car;
	private String str_blue_car_mirror;
	private String str_begin;
	private String str_over;
	
	private String direction;
	private int stage;
	
	private Car blue_car;
	private Car truck;
	
	public boolean alive;
	
	public Thread thread; 
	
	public CarGamePanel() {
		initial();
		setBounds(0, 0, 900, 572);
        setBackground(Color.white);
        setFocusable(true);
        setLayout(null);
        setFocusable(true);
    	requestFocus();
        addKeyListener(this);
        thread = new Thread(this);
        
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
			
		if(stage == 1)
		{
			
			 float lineWidth = 3.0f;
		     ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
		     ((Graphics2D)g).drawLine(10, 10, 680, 10);
		     ((Graphics2D)g).drawLine(10, 10, 10, 470);
		     ((Graphics2D)g).drawLine(680, 10, 680, 470);
		     ((Graphics2D)g).drawLine(10, 470, 680, 470);
			drawTruck(g);
			drawCar(g);
		}
		else if(stage == 2)
		{
			g.drawImage(image_begin, 0, 0, null);
		}
		else if(stage == 3)
		{
			g.drawImage(image_over,0,0,null);
		}
		
    }
	
	private void drawCar(Graphics g) {		
		double degree;
		double arctangent;
		if(blue_car.getCar_x()==truck.getCar_x())
		{
			degree = 0;
		}
		else
		{
			arctangent = Math.atan((double)(blue_car.getCar_y()-truck.getCar_y())/
					(double)(blue_car.getCar_x()-truck.getCar_x())); 
		    degree = Math.toDegrees(arctangent);
		    	    
		}
			
		AffineTransform at = 
        		AffineTransform.getTranslateInstance(blue_car.getCar_x(), blue_car.getCar_y());
		at.rotate(Math.toRadians(degree), blue_car.getImage().getWidth()/2, 
				blue_car.getImage().getHeight()/2);
        Graphics2D g2d = (Graphics2D)g;
        if(truck.getCar_x() <= blue_car.getCar_x())
        	g2d.drawImage(blue_car.getImage(), at, null);
        else
        	g2d.drawImage(blue_car.getImage_mirror(), at, null);
		
	
	}
	
	private void drawTruck(Graphics g) {
		AffineTransform at = 
        		AffineTransform.getTranslateInstance(truck.getCar_x(), truck.getCar_y());
		at.rotate(Math.toRadians(truck.getDegree()), truck.getImage().getWidth()/2, 
				truck.getImage().getHeight()/2);
        Graphics2D g2d = (Graphics2D)g;
        if(direction.equals("right"))
        	g2d.drawImage(truck.getImage(), at, null);
        else
        	g2d.drawImage(truck.getImage_mirror(), at, null);
		
	}
	
	public void keyPressed(KeyEvent e) {
    	if((e.getKeyCode() == KeyEvent.VK_RIGHT))
    	{
    		direction = "right";
    		int x;
    		int y;
    		if(truck.getDegree() <= 90 && truck.getDegree() >= -90)  
    		{
    			x = truck.getCar_x() +
    					(int)(8*Math.cos(Math.toRadians(truck.getDegree())));
    			truck.setCar_x(x);
    			y = truck.getCar_y() +
    					(int)(8*Math.sin(Math.toRadians(truck.getDegree())));
    		    truck.setCar_y(y);
    		}
    		
    	}
    	
    	else if((e.getKeyCode() == KeyEvent.VK_LEFT))
    	{
    		
    		direction = "left"; 
    		
    		int x;
    		int y;
    		if(truck.getDegree() <= 90 && truck.getDegree() >= -90)  
    		{
    			x = truck.getCar_x() - 
    					(int)(8*Math.cos(Math.toRadians(truck.getDegree())));
    			truck.setCar_x(x);
    			y = truck.getCar_y() -
    					(int)(8*Math.sin(Math.toRadians(truck.getDegree())));
    		    truck.setCar_y(y);
    		}
    		
    	}
    	else if((e.getKeyCode() == KeyEvent.VK_UP))
    	{
    		double temp = truck.getDegree();
    		if(direction.equals("right"))
    		{
    			temp = temp - 5;
    		    if(temp < 85 && temp > -85)
    		    	truck.setDegree(truck.getDegree() - 5);
    		}
    		else
    		{
    			temp = temp + 5;
    		    if(temp < 85 && temp > -85)
    		    	truck.setDegree(truck.getDegree() + 5);
    		}
	
    	}
    	else if((e.getKeyCode() == KeyEvent.VK_DOWN))
    	{
    		double temp = truck.getDegree();
    		if(direction.equals("left"))
    		{
    			temp = temp - 5;
        		if(temp < 85 && temp > -85)
        			truck.setDegree(truck.getDegree() - 5);
    		}
    		else
    		{
    			temp = temp + 5;
        		if(temp < 85 && temp > -85)
        			truck.setDegree(truck.getDegree() + 5);
    		}
    		
    	}
    	
    }
	
	public void keyTyped(KeyEvent e) { return; }    	
    public void keyReleased(KeyEvent e) { return; }
        
    public void actionPerformed(ActionEvent e){
		if (e.getSource() == button_start){
			stage = 1;
			replay();
			repaint();
			button_start.setEnabled(false);
			button_exit.setEnabled(true);
		}
		else if(e.getSource() == button_exit) {
			stage = 2;
			replay();
			repaint();
			button_start.setEnabled(true);
			button_exit.setEnabled(false);
		}
    }
    
	
	
    @Override
	public  void run(){
		repaint();
		while(stage < 3) {		
			if(stage == 1)
			{		
				
				try {
					repaint();
					check_boundary();		    	    
		    	    move_car();
		    	    crush_check();
		    	    repaint();
		    	    check_boundary();
		    	    victory_check();
		    	    time_decrease();
		    	    Thread.sleep(40);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
			{
				replay();
			}
		}
		alive = false;
		//exit();
    }
	
	public void check_boundary() {
		
    	if(truck.getCar_x() > 660-truck.getImage().getWidth())
    	{
    		damage(truck);
    		label_truck.setText("HP "+truck.getHP());
    		truck.setCar_x(660-truck.getImage().getWidth());
    	}
    		
    	if(truck.getCar_x() < 15)
    	{
    		damage(truck);
    		label_truck.setText("HP "+truck.getHP());
    		truck.setCar_x(15);
    	}
    		
    	if(truck.getCar_y() < 30)
    	{
    		damage(truck);
    		label_truck.setText("HP "+truck.getHP());
    		truck.setCar_y(30);
    	}
    		
    	if(truck.getCar_y()> 450-truck.getImage().getHeight())
    	{
    		damage(truck);
    		label_truck.setText("HP "+truck.getHP());
    		truck.setCar_y(450-truck.getImage().getHeight());
    	}
    	   
    	
    	if(blue_car.getCar_x() > 660-blue_car.getImage().getWidth())
    	{
    		damage(blue_car);
    		label_blue_car.setText("HP "+blue_car.getHP());
    		blue_car.setCar_x(660-blue_car.getImage().getWidth());
    	}
    		
    	if(blue_car.getCar_x() < 15)
    	{
    		damage(blue_car);
    		label_blue_car.setText("HP "+blue_car.getHP());
    		blue_car.setCar_x(15);
    	}
    		
    	if(blue_car.getCar_y() < 30)
    	{
    		damage(blue_car);
    		label_blue_car.setText("HP "+blue_car.getHP());
    		blue_car.setCar_y(30);
    	}
    		
    	if(blue_car.getCar_y()> 450-blue_car.getImage().getHeight())
    	{
    		damage(blue_car);
    		label_blue_car.setText("HP "+blue_car.getHP());
    		blue_car.setCar_y(450-blue_car.getImage().getHeight());
    	}
    		
		
	}
	
	private void move_car() {
		if(time%3 == 0 ){
			int v_x;
		    int v_y;
		    int temp = Math.abs(blue_car.getCar_x()-truck.getCar_x())*Math.abs(blue_car.getCar_x()-truck.getCar_x())
					 + Math.abs(blue_car.getCar_y()-truck.getCar_y())*Math.abs(blue_car.getCar_y()-truck.getCar_y());
					 
		    if(temp > 400*400)
		    {
		    	v_x = blue_car.getCar_x() + (int)(0.04*(truck.getCar_x()-blue_car.getCar_x()));
		    	v_y = blue_car.getCar_y() + (int)(0.04*(truck.getCar_y()-blue_car.getCar_y()));
		    }
		    
		    else if(temp >= 100*100 && temp < 300*300)
		    {
		    	v_x = blue_car.getCar_x() + (int)(0.25*(truck.getCar_x()-blue_car.getCar_x()));
		    	v_y = blue_car.getCar_y() + (int)(0.25*(truck.getCar_y()-blue_car.getCar_y()));
		    }
		    else
		    {
		    	v_x = blue_car.getCar_x()+ (int)(0.08*(truck.getCar_x()-blue_car.getCar_x()));
		    	v_y = blue_car.getCar_y()+ (int)(0.08*(truck.getCar_y()-blue_car.getCar_y()));
		    }
		    
		    
		    blue_car.setCar_x(v_x);
		    blue_car.setCar_y(v_y);
		   
		    
		}
		
		
	}
	
	private void crush_check() {
		if(Math.abs(blue_car.getCar_x()-truck.getCar_x())*Math.abs(blue_car.getCar_x()-truck.getCar_x())
			+ Math.abs(blue_car.getCar_y()-truck.getCar_y())*Math.abs(blue_car.getCar_y()-truck.getCar_y())
			< blue_car.getImage().getWidth()*blue_car.getImage().getWidth())
		{
			int v_x;
			int v_y;
			v_x = (int)(0.35*(truck.getCar_x()-blue_car.getCar_x()));
			v_y = (int)(0.35*(truck.getCar_y()-blue_car.getCar_y()));
			truck.setCar_x(truck.getCar_x()+(v_x));
			truck.setCar_y(truck.getCar_y()+(v_y));
			blue_car.setCar_x(blue_car.getCar_x()-(v_x));
			blue_car.setCar_y(blue_car.getCar_y()-(v_y));
		}
	}
	
	private void initial() {
		
		str_truck = "materials/CarGame/truck.png";
		str_truck_mirror = "materials/CarGame/truck_left.jpg";
		str_blue_car = "materials/CarGame/blue_car.jpg";
		str_blue_car_mirror = "materials/CarGame/blue_car_right.jpg";
		str_begin = "materials/CarGame/western-trucks-yellow.jpg";
		str_over = "materials/CarGame/over.png";
		
		truck = new Car(400,300,str_truck,str_truck_mirror);
		blue_car = new Car(450,300, str_blue_car,str_blue_car_mirror);
		
		
		loadimage();
		
		button_start = new JButton();
        button_exit = new JButton();
        
        button_start.setText("Start");
        button_start.setBounds(700, 10, 100, 30);
        button_start.addActionListener(this);
        
        button_exit.setText("Exit");
        button_exit.setBounds(700, 50, 100, 30);
        button_exit.setEnabled(false);
        button_exit.addActionListener(this);
        
        label_truck = new JLabel();
        label_blue_car = new JLabel();
    	label_time = new JLabel();
        
        label_truck.setText("HP "+truck.getHP());
    	label_truck.setBackground(Color.black);
    	label_truck.setFont(new Font("", Font.BOLD, 25));
    	label_truck.setBounds(20, 500, 180, 40);
    	
    	label_blue_car.setText("HP "+blue_car.getHP());
    	label_blue_car.setBackground(Color.red);
    	label_blue_car.setFont(new Font("", Font.BOLD, 25));
    	label_blue_car.setBounds(600, 500, 180, 40);
    	
    	time = 2000;
    	label_time.setText("Time : "+time/10);
    	label_time.setBackground(Color.black);
    	label_time.setFont(new Font("", Font.BOLD, 25));
    	label_time.setBounds(300, 500, 180, 40);
    	
    	add(label_truck);
    	add(label_blue_car);
        add(label_time);
        
        
        alive = true;
        direction = "right";
        stage = 2;
        
        add(button_start);
        add(button_exit);
        
	}
	
	private void replay() {
		truck.setCar_x(15);
		truck.setCar_y(30);
		truck.setDegree(45);
		truck.setHP(100);
		blue_car.setCar_x(570);
		blue_car.setCar_y(400);
		blue_car.setHP(100);
		direction = "right";
		time = 2005; // set 3000, will see 299 first
		label_truck.setText("HP "+truck.getHP());
		label_blue_car.setText("HP "+blue_car.getHP());
		label_time.setText("Time : "+time/10);
		
	}
	
	private void damage(Car c) {
		c.setHP(c.getHP()-10);
	}
	
	private void victory_check() {
		if(truck.getHP()*blue_car.getHP() <= 0)
		{
			stage = 3;
			label_truck.setText("");
			label_blue_car.setText("");
			label_time.setText("");
			if(truck.getHP() <= 0)
				JOptionPane.showMessageDialog(null, "你已經盡力了");
			else
				JOptionPane.showMessageDialog(null, "恭喜你");
		}
		
	}
	
	private void time_decrease() {
		time--;
		label_time.setText("Time : "+time/10);
	}
	
	private void loadimage() {
		try
		{
			File begin = new File(str_begin);
			File over = new File(str_over);
			
			image_begin = ImageIO.read(begin);
			image_over = ImageIO.read(over);
			
		}
		catch(Exception e) {
			System.out.println("load overimage goes wrong");
			return;
		}
		
	}
	
	private void exit(){
		remove(label_truck);
		remove(label_blue_car);
		remove(label_time);
	}
}


