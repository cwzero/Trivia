package trivia.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

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

	@Override
	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/main-logo.png"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
