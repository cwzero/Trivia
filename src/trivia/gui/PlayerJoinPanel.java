package trivia.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PlayerJoinPanel extends GamePanel {
	private JTextField playerNameField;
	private JButton btnBack;
	private int currentPlayer = 0;
	
	public PlayerJoinPanel() {
		createGui();
	}

	/**
	 * Create the frame.
	 */
	public PlayerJoinPanel(final GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
	}

	public void backButtonClick() {
		if (currentPlayer > 0) {
			gameFrame.getGame().setCurrentPlayer(currentPlayer - 1);
			new PlayerJoinPanel(gameFrame);
		} else {
			gameFrame.getGame().reset();
			new MainMenuPanel(gameFrame);
		}
	}

	public void continueButtonClick() {
		String playerName = playerNameField.getText();
		if (playerName == null || playerName.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Please, Enter a Name");
		} else {

			gameFrame.getGame().setPlayerName(currentPlayer, playerName);

			currentPlayer++;
			gameFrame.getGame().setCurrentPlayer(currentPlayer);

			if (currentPlayer < gameFrame.getGame().getPlayerCount()) {
				new PlayerJoinPanel(gameFrame);
			} else {
				new SetupSummaryPanel(gameFrame);
			}
		}
	}

	@Override
	protected void createGui() {

		this.currentPlayer = gameFrame.getGame().getCurrentPlayer();

		gameFrame.setTitle("Enter Player Name");
		this.setLayout(null);

		JLabel playerLabel = new JLabel("Player " + (currentPlayer + 1) + " Enter Name");
		playerLabel.setBounds(143, 134, 136, 20);
		this.add(playerLabel);

		playerNameField = new JTextField();
		playerNameField.setText(gameFrame.getGame().getPlayer(currentPlayer).getName());
		playerNameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerJoinPanel.this.continueButtonClick();
			}
		});

		playerNameField.setBounds(284, 134, 158, 20);
		this.add(playerNameField);
		playerNameField.setColumns(10);
		playerNameField.requestFocus();

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
				PlayerJoinPanel.this.backButtonClick();
			}
		});
	}
}
