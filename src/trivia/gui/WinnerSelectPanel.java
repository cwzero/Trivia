package trivia.gui;

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import trivia.Game;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.Font;

@SuppressWarnings("serial")
public class WinnerSelectPanel extends JPanel {
	private GameFrame gameFrame;
	protected int[] playerScore;
	protected JRadioButton[] buttons = null;
	protected int[] playerIndex;

	/**
	 * Create the frame.
	 */
	public WinnerSelectPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;

		JButton btnSelectWinner = new JButton("Select Winner");
		btnSelectWinner.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WinnerSelectPanel.this.btnSelectWinner_click();
			}
		});
		this.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("Select one answer for the question:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblNewLabel);

		JLabel lblSelectedQuestion = new JLabel("New label");
		lblSelectedQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSelectedQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblSelectedQuestion);
		lblSelectedQuestion.setText(gameFrame.getGame().getCurrentQuestion());

		JRadioButton rdotbnAnswer1 = new JRadioButton("New radio button");
		rdotbnAnswer1.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(rdotbnAnswer1);

		JRadioButton rdobtnAnswer2 = new JRadioButton("New radio button");
		rdobtnAnswer2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(rdobtnAnswer2);

		JRadioButton rdobtnAnswer3 = new JRadioButton("New radio button");
		rdobtnAnswer3.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(rdobtnAnswer3);

		JRadioButton rdobtnAnswer4 = new JRadioButton("New radio button");
		rdobtnAnswer4.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(rdobtnAnswer4);
		this.add(btnSelectWinner);

		String[] answers = gameFrame.getGame().getPlayerAnswers();
		playerIndex = new int[gameFrame.getGame().getPlayerCount()];
		int answerIndex = 0;
		int buttonIndex = 0;

		buttons = new JRadioButton[] { rdotbnAnswer1, rdobtnAnswer2, rdobtnAnswer3, rdobtnAnswer4 };

		int[] playerOrder = new int[gameFrame.getGame().getPlayerCount()];
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < gameFrame.getGame().getPlayerCount(); i++) {
			temp.add(i);
		}

		generateRandom(playerOrder, temp);

		while (answerIndex < answers.length) {
			String currentAnswer = answers[playerOrder[answerIndex]];
			if (currentAnswer != null && !currentAnswer.equals("")) {
				buttons[buttonIndex].setText(currentAnswer);
				buttonIndex++;
				playerIndex[buttonIndex] = playerOrder[answerIndex];
			}
			answerIndex++;
		}

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdotbnAnswer1);
		buttonGroup.add(rdobtnAnswer2);
		buttonGroup.add(rdobtnAnswer3);
		buttonGroup.add(rdobtnAnswer4);

		if (gameFrame.getGame().getPlayerCount() <= 3) {
			rdobtnAnswer3.setVisible(false);

		}
		if (gameFrame.getGame().getPlayerCount() <= 4) {
			rdobtnAnswer4.setVisible(false);
		}
	}

	private void generateRandom(int[] playerOrder, List<Integer> temp) {
		Random ran = new Random();
		int playerNum = 0;
		while (!temp.isEmpty()) {
			playerOrder[playerNum] = temp.remove(ran.nextInt(temp.size()));
			playerNum++;
		}
	}

	public void btnSelectWinner_click() {
		int winner = -1;
		for (int buttonIndex = 0; buttonIndex < buttons.length; buttonIndex++) {
			if (buttons[buttonIndex].isSelected()) {
				winner = playerIndex[buttonIndex];
			}
		}
		gameFrame.getGame().setRoundWinner(winner);

		if (winner == -1) {
			JOptionPane.showMessageDialog(null, "Select one answer");
		} else {
			gameFrame.getGame().setPlayerScore(winner, gameFrame.getGame().getPlayerScore()[winner] + 1);

			new GameStatusPanel(gameFrame);
		}
	}

}