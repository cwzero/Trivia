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
public class GameSetupPanel extends JPanel {
	private JSpinner roundCountSpinner = new JSpinner();
	private JSpinner playerCountSpinner = new JSpinner();
	private Game game = new Game();
	private GameFrame gameFrame = null;
	
	public GameSetupPanel(GameFrame gameFrame) {
		this();
		this.gameFrame = gameFrame;
		this.game = gameFrame.getGame();
		roundCountSpinner.setValue(game.getRoundCount());
		playerCountSpinner.setValue(game.getPlayerCount());
	}

	/**
	 * Create the frame.
	 */
	public GameSetupPanel() {
		gameFrame.setTitle("Game Setup");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);

		setBorder(new EmptyBorder(5, 5, 5, 5));
		gameFrame.setContentPane(this);

		this.setLayout(null);
		roundCountSpinner.setModel(new SpinnerNumberModel(3, 3, 10, 1));
		roundCountSpinner.setBounds(324, 95, 39, 31);
		this.add(roundCountSpinner);

		playerCountSpinner.setModel(new SpinnerNumberModel(3, 3, 5, 1));
		playerCountSpinner.setBounds(324, 139, 39, 31);
		this.add(playerCountSpinner);

		JLabel lblNewLabel = new JLabel("Number of Rounds");
		lblNewLabel.setBounds(194, 99, 124, 23);
		this.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Number of Players");
		lblNewLabel_1.setBounds(194, 142, 124, 24);
		this.add(lblNewLabel_1);

		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameSetupPanel.this.continueButtonClick();
			}
		});
		continueButton.setBounds(181, 184, 95, 23);
		this.add(continueButton);

		JButton backButton = new JButton("Back");
		backButton.setBounds(288, 184, 95, 23);
		this.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenuFrame().setVisible(true);
			}
		});
	}

	public void continueButtonClick() {
		int roundCount = (int) roundCountSpinner.getValue();
		game.setRoundCount(roundCount);
		int playerCount = (int) playerCountSpinner.getValue();
		game.setPlayerCount(playerCount);

		new PlayerJoinPanel(gameFrame).setVisible(true);
	}
}
