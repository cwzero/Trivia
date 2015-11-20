package trivia.gui;

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
		gameFrame.setVisible(true);
	}
	@Override
	protected void createGui()
	{
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/mainlogo.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		gameFrame.setContentPane(contentPane);

		gameFrame.repaint();
		gameFrame.setTitle("Initializing Game");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		gameFrame.setContentPane(contentPane);
		gameFrame.setVisible(true);
	}

}
