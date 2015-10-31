package trivia.gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Game;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PlayerJoinPanel extends JPanel {
	private JTextField playerNameField;
	private JButton btnBack;
	private Game game = new Game();
	private GameFrame gameFrame = null;
	private int currentPlayer = 0;
	

	/**
	 * Create the frame.
	 */
	public PlayerJoinPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.game = gameFrame.getGame();
		this.currentPlayer = game.getCurrentPlayer();

		gameFrame.setTitle("Enter Player Name");
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		gameFrame.setContentPane(this);
		this.setLayout(null);

		JLabel playerLabel = new JLabel("Player " + (currentPlayer + 1) + " Enter Name");
		playerLabel.setBounds(143, 134, 136, 20);
		this.add(playerLabel);

		playerNameField = new JTextField();
		playerNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerJoinPanel.this.continueButtonClick();
			}
		});

		playerNameField.setBounds(284, 134, 158, 20);
		this.add(playerNameField);
		playerNameField.setColumns(10);

		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerJoinPanel.this.continueButtonClick();
			}
		});
		btnContinue.setBounds(190, 166, 89, 23);
		this.add(btnContinue);

		btnBack = new JButton("Back");
		btnBack.setBounds(288, 166, 89, 23);
		this.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GameSetupPanel(gameFrame);
			}
		});
	}

	public void continueButtonClick() {
		String playerName = playerNameField.getText();
		if (playerName == null || playerName.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Please, Enter a Name");
		} else {

			game.setPlayerName(currentPlayer, playerName);

			currentPlayer++;
			game.setCurrentPlayer(currentPlayer);

			if (currentPlayer < game.getPlayerCount()) {
				new PlayerJoinPanel(gameFrame).setVisible(true);
			} else {
				new SetupSummaryPanel(game).setVisible(true);
			}
		}
	}
}