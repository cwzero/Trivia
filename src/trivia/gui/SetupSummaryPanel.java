package trivia.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

@SuppressWarnings("serial")
public class SetupSummaryPanel extends JPanel {
	private GameFrame gameFrame;
	private JButton buttonNext;
	private JButton buttonBack;

	/**
	 * Create the frame.
	 */
	public SetupSummaryPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		gameFrame.setContentPane(this);
		gameFrame.repaint();

		gameFrame.setTitle("Game Setup Summary");
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridBagLayout());

		JLabel lblNumberOfRounds = new JLabel("Number of Rounds: " + gameFrame.getGame().getRoundCount());
		GridBagConstraints gbc_lblNumberOfRounds = new GridBagConstraints();
		gbc_lblNumberOfRounds.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNumberOfRounds.gridx = 0;
		gbc_lblNumberOfRounds.gridy = 0;
		gbc_lblNumberOfRounds.gridheight = 1;
		gbc_lblNumberOfRounds.gridwidth = 2;
		gbc_lblNumberOfRounds.weightx = 1;
		gbc_lblNumberOfRounds.weighty = 1;
		this.add(lblNumberOfRounds, gbc_lblNumberOfRounds);

		JLabel lblPlayers = new JLabel("Players:");
		GridBagConstraints gbc_lblPlayers = new GridBagConstraints();
		gbc_lblPlayers.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayers.gridx = 0;
		gbc_lblPlayers.gridy = 1;
		gbc_lblPlayers.gridheight = 1;
		gbc_lblPlayers.gridwidth = 2;
		gbc_lblPlayers.weightx = 1;
		gbc_lblPlayers.weighty = 1;
		this.add(lblPlayers, gbc_lblPlayers);

		for (int playerNumber = 0; playerNumber < gameFrame.getGame().getPlayerCount(); playerNumber++) {
			JLabel playerLabel = new JLabel(gameFrame.getGame().getPlayerNames()[playerNumber]);
			GridBagConstraints gbc_playerLabel = new GridBagConstraints();
			gbc_playerLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_playerLabel.gridx = 0;
			gbc_playerLabel.gridy = 2 + playerNumber;
			gbc_playerLabel.gridheight = 1;
			gbc_playerLabel.gridwidth = 2;
			gbc_playerLabel.weightx = 1;
			gbc_playerLabel.weighty = 1;
			this.add(playerLabel, gbc_playerLabel);
		}

		buttonNext = new JButton("Start Game");
		buttonBack = new JButton("Back to Main Menu");

		GridBagConstraints gbc_buttonNext = new GridBagConstraints();
		gbc_buttonNext.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonNext.gridx = 0;
		gbc_buttonNext.gridy = 3 + gameFrame.getGame().getPlayerCount();
		gbc_buttonNext.gridheight = 1;
		gbc_buttonNext.gridwidth = 1;
		gbc_buttonNext.weightx = 1;
		gbc_buttonNext.weighty = 1;
		this.add(buttonNext, gbc_buttonNext);

		GridBagConstraints gbc_buttonBack = new GridBagConstraints();
		gbc_buttonBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonBack.gridx = 1;
		gbc_buttonBack.gridy = 3 + gameFrame.getGame().getPlayerCount();
		gbc_buttonBack.gridheight = 1;
		gbc_buttonBack.gridwidth = 1;
		gbc_buttonBack.weightx = 1;
		gbc_buttonBack.weighty = 1;
		this.add(buttonBack, gbc_buttonBack);

		buttonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SetupSummaryPanel.this.buttonNext_click();
			}
		});

		buttonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SetupSummaryPanel.this.buttonBack_click();
			}
		});
	}

	private void buttonNext_click() {
		gameFrame.getGame().start();

		// Select leader and start game

		// ---------------------------------------------------------------------//
		// added by Brian to test question select frame

		try {
			new QuestionSelectPanel(gameFrame);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ---------------------------------------------------------------------//
	}

	private void buttonBack_click() {
		// Eventually this should go back to edit setup
		new MainMenuPanel(gameFrame);
	}
}
