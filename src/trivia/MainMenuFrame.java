package trivia;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainMenuFrame extends JFrame {
	/**
	 * Create the application.
	 */
	public MainMenuFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Main Menu");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel lblTriviaGame = new JLabel("TRIVIA GAME");
		lblTriviaGame.setBackground(new Color(240, 240, 240));
		lblTriviaGame.setFont(new Font("Tahoma", Font.BOLD, 38));
		lblTriviaGame.setBounds(78, 11, 274, 75);
		this.getContentPane().add(lblTriviaGame);

		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame.this.newGameButtonClick();
			}
		});
		newGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newGameButton.setBounds(149, 97, 123, 52);
		this.getContentPane().add(newGameButton);

		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuFrame.this.quitButtonClick();
			}
		});
		quitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		quitButton.setBounds(149, 160, 123, 52);
		this.getContentPane().add(quitButton);
	}

	public void newGameButtonClick() {
		// Here we will close the main menu, then show a new game setup frame

		this.dispose();

		new GameSetupFrame().setVisible(true);
	}

	public void quitButtonClick() {
		System.exit(0);
	}
}
