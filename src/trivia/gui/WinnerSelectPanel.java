package trivia.gui;

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

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

import java.awt.Button;
import java.awt.Font;

@SuppressWarnings("serial")
public class WinnerSelectPanel extends JPanel {
	private GameFrame gameFrame;
	protected int[] playerScore;
	protected JButton[] buttons = null;
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

		JButton rdotbnAnswer1 = new JButton("New radio button");
		JButton rdobtnAnswer2 = new JButton("New radio button");
		JButton rdobtnAnswer3 = new JButton("New radio button");
		JButton rdobtnAnswer4 = new JButton("New radio button");
		
		buttons = new JButton[] { rdotbnAnswer1, rdobtnAnswer2, rdobtnAnswer3, rdobtnAnswer4 };

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
		
		
		rdotbnAnswer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectWinner(playerIndex[0]);
			}
		});
		rdotbnAnswer1.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(rdotbnAnswer1);

		
		rdobtnAnswer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectWinner(playerIndex[1]);
			}
		});
		rdobtnAnswer2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(rdobtnAnswer2);

		
		rdobtnAnswer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectWinner(playerIndex[2]);
			}
		});
		rdobtnAnswer3.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(rdobtnAnswer3);

		
		rdobtnAnswer4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectWinner(playerIndex[3]);
			}
		});
		rdobtnAnswer4.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(rdobtnAnswer4);

		

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
	
	

	private void selectWinner(int winner) {
		gameFrame.getGame().setRoundWinner(winner);
		gameFrame.getGame().setPlayerScore(winner, gameFrame.getGame().getPlayerScore()[winner] +1);
		new GameStatusPanel(gameFrame);
		
	}

}