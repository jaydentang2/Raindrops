//import swing classes
import java.awt.*;			
import java.awt.event.*;	
import javax.swing.*;		

@SuppressWarnings("serial")
public class Raindrops extends JPanel implements ActionListener, KeyListener {
	//Class Member Variables
	private JPanel cards; 
	private JLabel l;	  
	private JButton playButton;		
	private JTextField textField;
	private JButton submitButton; 
	private RaindropPanel card3;
	private boolean start;
	private int rand;
	private String answer = "";
	private Image bb;
	private int bb_height;
	private int bb_width;
	private JLabel label;
	private JLabel liveslabel;


	public Raindrops () {
		//Create image for the raindrops.
		ImageIcon k = new ImageIcon("src/Raindrops.png");
		bb = k.getImage();
		bb_width = bb.getWidth(null);
		bb_height = bb.getHeight(null);

	}
	public int getWidth() {
		//Getting the width and height of the Raindrop images.
		return bb_width;
	}
	public int getHeight() {
		return bb_height;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g4d = (Graphics2D) g;
		g4d.drawImage(bb, 0, 0, null);

	}

	public void actionPerformed(ActionEvent e) {

		//If the player press the Play button, show the Raindrop Panel.
		if (e.getActionCommand().equals("Play")) {
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, "C3");
			card3.setStart(true);

		}
		//If the player chooses the Play Game option on the Options Menu, show the game menu. 
		else if (e.getActionCommand().equals("Play Game")) {
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, "C1");
		}
		//If the player chooses the Instructions option on the Options Menu, show the Instructions Panel. 
		else if (e.getActionCommand().equals("Instructions")) {
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, "C2");
		}

		//If the player presses the submit button, clear the textfield and saves the answer is card3.
		else if (e.getSource() == submitButton) {
			//System.out.println("Text: " + textField.getText());
			card3.setAnswer(textField.getText());
			textField.setText("");
			//raindropsCleared = raindropsCleared + 1;
		}

		//If the player presses the exit option on the Options Menu, close the game. 
		else
			System.exit(0);

	}
	public void keyPressed(KeyEvent e) {
		//If the player presses the enter key, clear the textfield, check if the answer matches the raindrop, Adds 1 to raindropsCleared.
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			card3.setAnswer(textField.getText());
			textField.setText("");
			if (card3.getAnswer().equals(String.valueOf(card3.getRand()))) {
				card3.setRaindropsCleared(card3.getRaindropsCleared() + 1);
			}
			//Reset the value of RaindropsCleared after the player loses or wins and wants to play again. 
			this.label.setText("Raindrops Cleared: " + String.valueOf(card3.getRaindropsCleared()));
			this.label.setForeground(Color.white);


		}
	}
	//Required methods by KeyListener
	public void keyReleased(KeyEvent arg) {
	}
	public void keyTyped(KeyEvent arg) {

	}




	//Setter for boolean "start"
	public void setStart(boolean start) {
		this.start = start;
	}

	//Getter for int "rand"
	public int getRand (int rand) {
		return this.rand = rand;
	}
	//Getter for String "answer"
	public String getAnswer (String answer) {
		return this.answer = answer;
	}
	public void setup () {
		//Create a new JFrame component.
		JFrame f = new JFrame("Card Layout");
		//Create PlayGamePanel
		PlayGamePanel card1 = new PlayGamePanel();
		//Create InstructionsPanel
		InstructionsPanel card2 = new InstructionsPanel();

		//Create RaindropPanel
		card3 = new RaindropPanel();
		//Create label indicating how many raindrops the player has cleared
		this.label = new JLabel("Raindrops Cleared: " + String.valueOf(this.card3.getRaindropsCleared()+ "   "));
		//Setting the text to white
		this.label.setForeground(Color.white);
		//Create label indicating how many lives the player has left.
		this.liveslabel = new JLabel("Lives left: " + this.card3.getLives());
		//Setting the text to white
		this.liveslabel.setForeground(Color.white);
		//Adding labels to card3
		card3.add(label);
		card3.add(liveslabel);
		card3.setLabel(liveslabel);
		card3.setRaindropClearedLabel(this.label);



		//Create menubar
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("Options");
		//Declaring the 3 items.
		JMenuItem eMenuItem = new JMenuItem("Exit");
		JMenuItem eMenuItem2 = new JMenuItem("Play Game");
		JMenuItem eMenuItem3 = new JMenuItem("Instructions");

		eMenuItem.addActionListener(this);
		eMenuItem2.addActionListener(this);
		eMenuItem3.addActionListener(this);


		file.add(eMenuItem2);
		file.add(eMenuItem3);
		file.add(eMenuItem); 
		menubar.add(file);
		f.setJMenuBar(menubar);
		
		//Creating JPanel
		cards = new JPanel(new CardLayout());
		playButton = new JButton("Play");
		playButton.addActionListener(this);
		playButton.setBounds(2500, 2500, 0, 0);
		card1.add(playButton);
		
		//Create Jlabel for the instructions of the game
		l = new JLabel("              Math questions will appear on raindrops. Answer 100 questions on the raindrops before 3 raindrops hit the ground. You have 3 lives, Good luck!");
		
		this.l.setForeground(Color.white);
		l.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
		card2.setLayout (new GridLayout ()); //added
	    this.add(card2,BorderLayout.CENTER);
		card2.add(l);
		JPanel Card3Button = new JPanel();
		card3.add(Card3Button);



		//Add the cards to the high level card container
		cards.add(card1, "C1");
		cards.add(card2, "C2");
		cards.add(card3, "C3");
		
		//Create JTextField for Raindrop answer
		textField = new JTextField(10);
		card3.add(textField);
		submitButton = new JButton("Submit");
		card3.add(submitButton);
		submitButton.addActionListener(this);
		submitButton.addKeyListener(this);
		textField.addKeyListener(this);


		//Add the card layout to the frame. 
		f.add(cards, BorderLayout.CENTER);
		f.setTitle("Raindrops");
		f.setSize(5000, 5000);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);


	}

	public static void main(String[] args) {
		Raindrops cl = new Raindrops();
		cl.setup();

	}
}

