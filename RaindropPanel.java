import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class RaindropPanel extends JPanel implements ActionListener, KeyListener{
	final private int FRAMEX = 1440, FRAMEY = 800, SQUARESIZE = 100, INCREMENT = 1, FREQUENCY = 5;
	private int bg_width, bg_height;
	private int y_coord = 0;
	private int x = 1, y = 1, xincrement = INCREMENT, yincrement = INCREMENT, freq = FREQUENCY, squaresize = SQUARESIZE;
	Timer t = new Timer(freq, this);
	private Image bg;
	private Image ba;
	private int ba_width;
	private int ba_height;
	private ImageIcon[] images = new ImageIcon[50];
	private int min = 0;
	private int max = 49;
	private int range = max - min + 1;
	private String answer = "";
	private int rand;
	private boolean start = false;
	private JLabel a;
	private int Lives = 3;
	private JPanel cards;
	private int raindropsCleared = 0;
	private JLabel l;
	
	public RaindropPanel(){
		for (int i = 0; i < 50; i++) {
			images[i] = new ImageIcon("src/" + i + ".png");
			
		}
	//	ImageIcon i = new ImageIcon("src/10.png");
		ImageIcon a = new ImageIcon("src/Background.png");
		ba = a.getImage();
		ba_width = ba.getWidth(null);
		ba_height = ba.getHeight(null);
		rand = (int)(Math.random() * range) + min;
		bg = images[rand].getImage();
		
		bg_width = bg.getWidth(null);
		bg_height = bg.getHeight(null);
		t.start();
	}
	
	public int getWidth() {
		return ba_width;
	}
	public int getHeight() {
		return ba_height;
	}

	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswer() {
		return this.answer;
	}
	public void setRand(int rand) {
		this.rand = rand;
	}
	public int getRand() {
		return this.rand;
	}
	public void setStart(boolean start) {
		this.start = start;
		this.t.start();
	}
	public void setRaindropsCleared (int raindropsCleared) {
		this.raindropsCleared = raindropsCleared;
	}
	public int getRaindropsCleared () {
		return this.raindropsCleared;
	}
	public void setLives (int Lives) {
		this.Lives = Lives;
	}
	public int getLives() {
		return this.Lives;
	}
	public void setLabel(JLabel l) {
		this.l = l;
	}
	public void setRaindropClearedLabel (JLabel a) {
		this.a = a;
	}
	//public void setraindropsClearedLabel (label raindropsClearedLabel) {
	//	this.raindropsClearedLabel = raindropsClearedLabel
	//}
	public void actionPerformed(ActionEvent e){
		
		if (start == true) 
			y_coord += 1;
		
		if ((y_coord + bg_height) == FRAMEY) {
		rand = (int)(Math.random() * range) + min;
		bg = images[rand].getImage();
		y_coord = 0;
		Lives = Lives - 1;
	
		}
		//else if (Lives == 1) {
			
		// JOptionPane.showMessageDialog(null , "YOU HAVE LOST LOL");
			
		
	//	}
		if (raindropsCleared >= 25) {
			y_coord +=1.01;
		}
		else if ((y_coord + bg_height) == FRAMEY) {
			rand = (int)(Math.random() * range) + min;
			bg = images[rand].getImage();
			y_coord = 0;
			Lives = Lives - 1;
		}
		else if (raindropsCleared >= 50) {
			y_coord += 1.1;
		}
		else if ((y_coord + bg_height) == FRAMEY) {
			rand = (int)(Math.random() * range) + min;
			bg = images[rand].getImage();
			y_coord = 0;
			Lives = Lives - 1;
		}
		else if (raindropsCleared >= 75) {
			y_coord += 1.2;
		}
		else if ((y_coord + bg_height) == FRAMEY) {
			rand = (int)(Math.random() * range) + min;
			bg = images[rand].getImage();
			y_coord = 0;
			Lives = Lives - 1;
		}
		else if (raindropsCleared >= 95) {
			y_coord +=1.25;
		}
		else if ((y_coord + bg_height) == FRAMEY) {
			rand = (int)(Math.random() * range) + min;
			bg = images[rand].getImage();
			y_coord = 0;
			Lives = Lives - 1;
		}
		this.l.setText("Lives Left: " + String.valueOf(this.Lives));
		this.a.setText("Raindrops Cleared: " + String.valueOf(this.getRaindropsCleared()));
		
		if (Lives == -50) {
			JOptionPane.showMessageDialog(null , "You have lost :(, Press the options menu to play again.");
			this.t.stop();
			this.Lives = 3;
			this.raindropsCleared = 0;
			this.l.setText("Lives Left: " + String.valueOf(this.Lives));
		}
		if (getRaindropsCleared() == 100) {
			JOptionPane.showMessageDialog(null , "You have won!! You cleared 100 RAINDROPS:) Congratulations. Press the Menu Option to play again.");
			this.t.stop();
			this.Lives = 3;
			this.raindropsCleared = 0;
			this.l.setText("Lives Left: " + String.valueOf(this.Lives));
		}
		else
			repaint();	
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.fillRect(x, y, squaresize, squaresize);
		Graphics2D g2d = (Graphics2D) g;
		System.out.println(answer);
		super.paintComponent(g);
		Graphics2D g3d = (Graphics2D) g;
		g3d.drawImage(ba, 0, 0, null);
		if (answer.equals(String.valueOf(rand))){
			rand = (int)(Math.random() * range) + min;
			bg = images[rand].getImage();
			y_coord = 0;
			g2d.drawImage(bg, 1, y_coord, null);
			answer = "";
			System.out.println(images[rand]);
		//	raindropsCleared = raindropsCleared + 1;
		
		}
		else {
			g2d.drawImage(bg, 1, y_coord, null);
		}
	}

	public void setupWindow(RaindropPanel p){
		JFrame f = new JFrame();
		f.setSize(FRAMEX, FRAMEY);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.setVisible(true);
		f.addKeyListener(p);
	}

	public static void main(String[] args) {
		RaindropPanel p = new RaindropPanel();
		p.setupWindow(p);

	}

	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'f'){
			freq /= 2;
			t.stop();
			t.setDelay(freq);
			t.start();
		}
		else if(e.getKeyChar() == 's'){
			freq *= 2;
			t.stop();
			t.setDelay(freq);
			t.start();
		}
		else if(e.getKeyChar()  == 'd'){
			xincrement *= -1;
			yincrement *= -1;
		}
		else if(e.getKeyChar()  == 'b'){
			squaresize += 5;
		}
		else if(e.getKeyChar()  == 'l'){
			squaresize -= 5;
		}
	}

	public void keyPressed(KeyEvent e) { }

	public void keyReleased(KeyEvent e) { }
}

