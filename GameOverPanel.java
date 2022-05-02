import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameOverPanel {
	
	public void setupWindow(Component p){
		JFrame f = new JFrame();
		f.setSize(5000, 5000);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		f.setVisible(true);
	//	f.addKeyListener(p);
	}

}
