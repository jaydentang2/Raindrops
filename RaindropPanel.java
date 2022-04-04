
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
	private int max = 37;
	private int range = max - min + 1;
	private String answer = "";
	private int rand;
	private boolean start = false;
	
	public RaindropPanel(){
		for (int i = 0; i < 38; i++) {
			images[i] = new ImageIcon("src/" + i + ".png");
		}
	//	ImageIcon i = new ImageIcon("src/10.png");
	//	ImageIcon a = new ImageIcon("src/Water.jpeg");
		rand = (int)(Math.random() * range) + min;
		bg = images[rand].getImage();
		bg_width = bg.getWidth(null);
		bg_height = bg.getHeight(null);
		t.start();
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	
	public void actionPerformed(ActionEvent e){
		if((x+squaresize)==FRAMEX)
			xincrement *=-1;
		else if ((y+squaresize) == (FRAMEY - 20))
			yincrement *= -1;
		else if (x == 0) {
			xincrement *= -1;
		}
		else if (y == 0) {
			yincrement *= -1;
		}
		x += xincrement;
		y += yincrement;
		if (start == true) 
			y_coord += 1;
		if ((y_coord + bg_height) == FRAMEY) {
			rand = (int)(Math.random() * range) + min;
			System.out.println("2 Rand is" + rand);
			System.out.println("2 Answer is" + answer);
			bg = images[rand].getImage();
			y_coord = 0;
		}
		repaint();
		
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.fillRect(x, y, squaresize, squaresize);
		Graphics2D g2d = (Graphics2D) g;
		System.out.println("Answer is" + answer);
		if (answer.equals(String.valueOf(rand))){
			rand = (int)(Math.random() * range) + min;
			System.out.println("Rand is" + rand);
			bg = images[rand].getImage();
			y_coord = 0;
			g2d.drawImage(bg, 1, y_coord, null);
			answer = "";
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
