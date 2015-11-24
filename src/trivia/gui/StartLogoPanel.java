package trivia.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import trivia.Game;

@SuppressWarnings("serial")
public class StartLogoPanel extends GamePanel {
	
	public StartLogoPanel() {
		createGui();
	}
	
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
		File splash = new File("splash2.wav"); 
		try {
			Game.playSound(splash, 5000);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//opens splash screen in the center of monitor, regardless of size
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		gameFrame.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setBounds(100, 100, 450, 300);
	
		
		
	}

	@Override
	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/splash.gif"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
