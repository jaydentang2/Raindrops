import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Raindrops implements ActionListener {
	private JPanel cards; //The top level container to hold all of the 'cards'
	private JLabel l;
	private JButton playButton;
	private JTextField textField;
	private JButton submitButton;
	private RaindropPanel card3;
	
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equals("Play")) {
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, "C3");
			card3.setStart(true);
		}
		//Check to see what menu item the user chose
		else if (e.getActionCommand().equals("Play Game")) {
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, "C1");
		}
		else if (e.getActionCommand().equals("Instructions")) {
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, "C2");
		}
		else if (e.getSource() == submitButton) {
			System.out.println("Text: " + textField.getText());
			card3.setAnswer(textField.getText());
		}
		// Assume they chose exit if no others...
		else
			System.exit(0);
		
		
		

	}
	
	
	public void setup () {
		JFrame f = new JFrame("Card Layout");
		JPanel card1 = new JPanel();
		JPanel card2 = new JPanel();
		card3 = new RaindropPanel();
		
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("Options");
		// Declare the 3 menu items.
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
		
		cards = new JPanel(new CardLayout());
		playButton = new JButton("Play");
		playButton.addActionListener(this);
		card1.add(playButton);
		l = new JLabel("Math questions will appear on raindrops. Answer the questions on the bottom before the raindrops hit the ground! Your goal is to clear 50 raindrops before 3 hit the ground.");
		card2.add(l);
		JPanel Card3Button = new JPanel();
		card3.add(Card3Button);
	
		
		// Add the cards to the high level card container
		cards.add(card1, "C1");
		cards.add(card2, "C2");
		cards.add(card3, "C3");
		
		textField = new JTextField(10);
		card3.add(textField);
		submitButton = new JButton("Submit");
		card3.add(submitButton);
		submitButton.addActionListener(this);
	
		// Add the card layout to the frame.
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

