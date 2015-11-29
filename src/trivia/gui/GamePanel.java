package trivia.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

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
		this.setBorder(new EmptyBorder(200, 200, 200, 200));
		this.setBounds(100, 100, 600, 400);
		gameFrame.setContentPane(this);
		gameFrame.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/backgroundgif.gif"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	protected abstract void createGui();
}
