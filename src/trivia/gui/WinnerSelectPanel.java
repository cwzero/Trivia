package trivia.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WinnerSelectPanel extends JPanel {
	private GameFrame gameFrame;
	protected int[] playerScore;
	protected JButton[] buttons = new JButton[4];
	protected int[] playerIndex;

	/**
	 * Create the frame.
	 */
	public WinnerSelectPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		gameFrame.setTitle("Select Winner");
		setBounds(100, 100, 600, 400);
		gameFrame.setContentPane(this);
		gameFrame.repaint();
		this.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("Select one answer for the question:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblNewLabel);

		JLabel lblSelectedQuestion = new JLabel("New label");
		lblSelectedQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSelectedQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblSelectedQuestion);
		lblSelectedQuestion.setText(gameFrame.getGame().getCurrentQuestion());

		String[] answers = gameFrame.getGame().getPlayerAnswers();
		playerIndex = new int[gameFrame.getGame().getPlayerCount()];
		int answerIndex = 0;
		int buttonIndex = 0;

		int[] playerOrder = new int[gameFrame.getGame().getPlayerCount()];
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < gameFrame.getGame().getPlayerCount(); i++) {
			temp.add(i);
		}

		Random ran = new Random();
		int playerNum = 0;
		while (!temp.isEmpty()) {
			playerOrder[playerNum] = temp.remove(ran.nextInt(temp.size()));
			playerNum++;
		}

		while (answerIndex < answers.length) {
			String currentAnswer = answers[playerOrder[answerIndex]];
			if (currentAnswer != null && !currentAnswer.equals("")) {
				playerIndex[buttonIndex] = playerOrder[answerIndex];
				buttons[buttonIndex] = new JButton(currentAnswer);
				final int bIndex = buttonIndex;
				buttons[buttonIndex].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						WinnerSelectPanel.this.selectWinner(playerIndex[bIndex]);
					}
				});
				buttons[buttonIndex].setHorizontalAlignment(SwingConstants.CENTER);
				this.add(buttons[buttonIndex]);
				buttonIndex++;
			}
			answerIndex++;
		}
	}

	private void selectWinner(int winner) {
		gameFrame.getGame().setRoundWinner(winner);
		gameFrame.getGame().setPlayerScore(winner, gameFrame.getGame().getPlayerScore()[winner] + 1);
		new GameStatusPanel(gameFrame);
	}

}