//Import swing classes
import java.awt.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class PlayGamePanel extends JPanel {
	//Shows the Game Menu Image
	private Image bb;
	private int bb_height;
	private int bb_width;

	public PlayGamePanel () {
		ImageIcon k = new ImageIcon("src/Raindrops.png");
		bb = k.getImage();
		bb_width = bb.getWidth(null);
		bb_height = bb.getHeight(null);

	}
	public int getWidth() {
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


}
