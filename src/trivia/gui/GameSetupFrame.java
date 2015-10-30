package trivia.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Game;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GameSetupFrame extends JFrame {

	private JPanel contentPane;
	private final JSpinner roundCountSpinner = new JSpinner();
	private JSpinner playerCountSpinner = new JSpinner();
	private Game game = new Game();
	
	public GameSetupFrame(Game game) {
		this();
		this.game = game;
		roundCountSpinner.setValue(game.getRoundCount());
		playerCountSpinner.setValue(game.getPlayerCount());
	}

	/**
	 * Create the frame.
	 */
	public GameSetupFrame() {
		setTitle("Game Setup");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 180);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		roundCountSpinner.setModel(new SpinnerNumberModel(3, 3, 10, 1));
		roundCountSpinner.setBounds(109, 11, 39, 31);
		contentPane.add(roundCountSpinner);

		playerCountSpinner.setModel(new SpinnerNumberModel(3, 3, 5, 1));
		playerCountSpinner.setBounds(109, 55, 39, 31);
		contentPane.add(playerCountSpinner);

		JLabel lblNewLabel = new JLabel("Number of Rounds");
		lblNewLabel.setBounds(10, 19, 89, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Number of Players");
		lblNewLabel_1.setBounds(10, 63, 89, 14);
		contentPane.add(lblNewLabel_1);

		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameSetupFrame.this.continueButtonClick();
			}
		});
		continueButton.setBounds(10, 101, 89, 23);
		contentPane.add(continueButton);

		JButton backButton = new JButton("Back");
		backButton.setBounds(109, 101, 89, 23);
		contentPane.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameSetupFrame.this.dispose();
				new MainMenuFrame().setVisible(true);
			}
		});
	}

	public void continueButtonClick() {
		int roundCount = (int) roundCountSpinner.getValue();
		game.setRoundCount(roundCount);
		int playerCount = (int) playerCountSpinner.getValue();
		game.setPlayerCount(playerCount);

		this.dispose();
		new PlayerJoinFrame(game).setVisible(true);
	}
}
