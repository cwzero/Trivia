package trivia.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class StartLogoPanel extends GamePanel {
	/**
	 * Create the frame.
	 */
	public StartLogoPanel(GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new MainMenuPanel(gameFrame);
			}
		}.start();
	}
	
	@Override
	protected void createGui()
	{
		setBounds(100, 100, 450, 300);
	}

	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/main-logo.png"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
