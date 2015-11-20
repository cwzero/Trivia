package trivia.gui;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")
public class GameSetupPanel extends GamePanel {
	private JSpinner roundCountSpinner = new JSpinner();
	private JSpinner playerCountSpinner = new JSpinner();
	private JSpinner answerTimeSpinner = new JSpinner();
	private JCheckBox answerTimeCheckBox = new JCheckBox("Limit Answer Time");

	/**
	 * Create the frame.
	 */
	public GameSetupPanel(final GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
		//roundCountSpinner.setValue(gameFrame.getGame().getRoundCount());
		//playerCountSpinner.setValue(gameFrame.getGame().getPlayerCount());
	}

	public void continueButtonClick() {
		int roundCount = (int) roundCountSpinner.getValue();
		gameFrame.getGame().setRoundCount(roundCount);
		int playerCount = (int) playerCountSpinner.getValue();
		gameFrame.getGame().setPlayerCount(playerCount);
		if (answerTimeCheckBox.isSelected()) {
			int answerTime = (int) answerTimeSpinner.getValue();
			gameFrame.getGame().setAnswerTime(answerTime);
		}
		new PlayerJoinPanel(gameFrame);
	}

	@Override
	protected void createGui() {
		gameFrame.setTitle("Game Setup");
		this.setLayout(null);

		roundCountSpinner.setModel(new SpinnerNumberModel(3, 3, 10, 1));
		roundCountSpinner.setBounds(298, 63, 39, 31);
		this.add(roundCountSpinner);

		playerCountSpinner.setModel(new SpinnerNumberModel(3, 3, 5, 1));
		playerCountSpinner.setBounds(298, 105, 39, 31);
		this.add(playerCountSpinner);

		JLabel lblNewLabel = new JLabel("Number of Rounds");
		lblNewLabel.setBounds(99, 67, 124, 23);
		this.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Number of Players");
		lblNewLabel_1.setBounds(99, 108, 124, 24);
		this.add(lblNewLabel_1);

		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameSetupPanel.this.continueButtonClick();
			}
		});
		continueButton.setBounds(99, 213, 95, 23);
		this.add(continueButton);

		JButton backButton = new JButton("Back");
		backButton.setBounds(257, 213, 95, 23);
		this.add(backButton);

		answerTimeSpinner.setModel(new SpinnerNumberModel(30, 10, 60, 1));
		answerTimeSpinner.setBounds(298, 155, 39, 31);
		answerTimeSpinner.setEnabled(false);
		this.add(answerTimeSpinner);

		answerTimeCheckBox.setBounds(99, 163, 155, 23);
		answerTimeCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {// checkbox has
																// been selected
					answerTimeSpinner.setEnabled(true);
				} else {// checkbox has been deselected
					answerTimeSpinner.setEnabled(false);
				}
			}
		});
		this.add(answerTimeCheckBox);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainMenuPanel(gameFrame);
			}
		});
	}
}
