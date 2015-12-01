package trivia.gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	protected GameFrame gameFrame;
	protected Font southPark;
	protected Font bradyBunch;
	
	public GamePanel() {
		try {
			// create the font to use. Specify the size!
			southPark = Font.createFont(Font.TRUETYPE_FONT, new File("src/images/south park.ttf")).deriveFont(25f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/images/south park.ttf")));
			// create the font to use. Specify the size!
			southPark = Font.createFont(Font.TRUETYPE_FONT, new File("src/images/BradBunR.ttf")).deriveFont(25f);
			// register the font
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/images/BradBunR.ttf")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
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
