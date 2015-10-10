package trivia;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SetupSummaryFrame extends JFrame {

	private JPanel contentPane;
	private Game game;

	/**
	 * Create the frame.
	 */
	public SetupSummaryFrame(Game game) {
		this.game = game;

		setTitle("Game Setup Summary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
