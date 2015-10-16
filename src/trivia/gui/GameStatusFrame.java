package trivia.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Game;

@SuppressWarnings("serial")
public class GameStatusFrame extends JFrame {

	private JPanel contentPane;
	private Game game;

	/**
	 * Create the frame.
	 */
	public GameStatusFrame(Game game) {
		this.game = game;
		setTitle("Current Game Status");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
