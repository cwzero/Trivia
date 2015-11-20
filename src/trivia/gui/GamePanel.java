package trivia.gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	protected GameFrame gameFrame;
	
	public GamePanel() {
		
	}

	public GamePanel(GameFrame gameFrame) {
		this();
		this.gameFrame = gameFrame;
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBounds(100, 100, 600, 400);
		gameFrame.setContentPane(this);
		gameFrame.setVisible(true);
	}
	
	protected abstract void createGui();
}
