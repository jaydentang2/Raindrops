//Import swing classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RaindropPanel extends JPanel implements ActionListener {
	//Class Member Variables
	final private int FRAMEX = 1440, FRAMEY = 800;
	private int bg_height;
	private int y_coord = 0;
	Timer t = new Timer(5, this);
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
	private int raindropsCleared = 0;
	private JLabel l;

	public RaindropPanel() {
		//Calculate image file names for raindrop questions
		for (int i = 0; i < 50; i++) {
			images[i] = new ImageIcon("src/" + i + ".png");

		}
		//Creating Background Image in RaindropPanel
		ImageIcon a = new ImageIcon("src/Background.png");
		ba = a.getImage();
		ba_width = ba.getWidth(null);
		ba_height = ba.getHeight(null);
		//Generating random questions for raindrops falling
		rand = (int)(Math.random() * range) + min;
		bg = images[rand].getImage();

		bg.getWidth(null);
		bg_height = bg.getHeight(null);
		//Starting the timer
		t.start();
	}

	public int getWidth() {
		return ba_width;
	}
	public int getHeight() {
		return ba_height;
	}

	//Setter for String "answer"
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	//Getter for String "answer"
	public String getAnswer() {
		return this.answer;
	}
	//Setter for int "rand"
	public void setRand(int rand) {
		this.rand = rand;
	}
	//Getter for int "rand"
	public int getRand() {
		return this.rand;
	}
	//Setter for boolean "start"
	public void setStart(boolean start) {
		this.start = start;
		this.t.start();
	}
	//Setter for int "raindropsCleared"
	public void setRaindropsCleared (int raindropsCleared) {
		this.raindropsCleared = raindropsCleared;
	}
	//Getter for int "raindropsCleared
	public int getRaindropsCleared () {
		return this.raindropsCleared;
	}
	//Setter for int "Lives"
	public void setLives (int Lives) {
		this.Lives = Lives;
	}
	//Getter for int "Lives"
	public int getLives() {
		return this.Lives;
	}
	//Setter for RaindropsCleared JLabel
	public void setLabel(JLabel l) {
		this.l = l;
	}
	//Setter for Lives JLabel
	public void setRaindropClearedLabel (JLabel a) {
		this.a = a;
	}

	public void actionPerformed(ActionEvent e){
		//If the player presses the play button, increment the raindrops y coordinate by 1. 
		if (start == true) 
			y_coord += 1;
		//If the raindrop hits the bottom of the panel, generate a new random image that will be falling. 
		if ((y_coord + bg_height) == FRAMEY) {
			rand = (int)(Math.random() * range) + min;
			bg = images[rand].getImage();
			y_coord = 0;
			Lives = Lives - 1;

		}
		//If the player answers 95 questions correctly or clears 95 raindrops, speed up the raindrops by incrementing the y coordinate by 1.2;
		if (raindropsCleared >= 95) {
			y_coord +=1.2;
		}
		//If the player answers 95 questions correctly or clears 75 raindrops, speed up the raindrops by incrementing the y coordinate by 1.15;
		else if (raindropsCleared >= 75) {
			y_coord += 1.15;
		}
		//If the player answers 95 questions correctly or clears 50 raindrops, speed up the raindrops by incrementing the y coordinate by 1.08;
		else if (raindropsCleared >= 50) {
			y_coord += 1.08;
		}
		//If the player answers 95 questions correctly or clears 25 raindrops, speed up the raindrops by incrementing the y coordinate by 1.03;
		else if (raindropsCleared >= 25) {
			y_coord +=1.03;
		}
		//If the raindrop hits the bottom of the panel, generate a new random image that will be falling. 
		if ((y_coord + bg_height) == FRAMEY) {
			rand = (int)(Math.random() * range) + min;
			bg = images[rand].getImage();
			y_coord = 0;
			Lives = Lives - 1;
		}
		//Reset both JLabels for Lives left and Raindrops Cleared when the player either loses or wins and plays again. 
		this.l.setText("Lives Left: " + String.valueOf(this.Lives));
		this.a.setText("Raindrops Cleared: " + String.valueOf(this.getRaindropsCleared()));

		if (Lives == 0) {
			//if 3 raindrops hit the bottom, show a JOptionPane telling the player they have lost. 
			JOptionPane.showMessageDialog(null , "You have lost :(  Press the Options Menu to play again.");
			//Stopping the timer and resetting the value of Lives and RaindropsCleared
			this.t.stop();
			this.Lives = 3;
			this.raindropsCleared = 0;
			//Resetting the text in the JLabel
			this.l.setText("Lives Left: " + String.valueOf(this.Lives));
		}
		//if the player answers 100 questions correctly or clears 100 raindrops, show a JOptionPane telling the player they have won. 
		if (getRaindropsCleared() == 100) {
			JOptionPane.showMessageDialog(null , "You have won!! You cleared 100 RAINDROPS ☺☺☺☺☺☺☺☺☺ Congratulations. Press the Options Menu to play again.");
			//Stopping the timer and resetting the value of Lives and RaindropsCleared
			this.t.stop();
			this.Lives = 3;
			this.raindropsCleared = 0;
			//Resetting the text in the JLabel
			this.l.setText("Lives Left: " + String.valueOf(this.Lives));
		}
		else {
			//Call the method "Paint Component" 
			repaint();	
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Draw the raindrop images
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		Graphics2D g3d = (Graphics2D) g;
		g3d.drawImage(ba, 0, 0, null);
		//Checking if the player answers the question correctly
		if (answer.equals(String.valueOf(rand))){
			rand = (int)(Math.random() * range) + min;
			bg = images[rand].getImage();
			y_coord = 0;
			g2d.drawImage(bg, 1, y_coord, null);
			//Clear the value of answer 
			answer = "";
		}
		else {
			//Continues to draw the image if the player does not answer the question on the raindrop correctly.
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

	}

	public static void main(String[] args) {
		RaindropPanel p = new RaindropPanel();
		p.setupWindow(p);


	}
}

