package trivia.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Game;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class AnswerEntryFrame extends JFrame {

	private JPanel contentPane;
	private Game game;
	private JTextField answerField;

	/**
	 * Create the frame.
	 */
	public AnswerEntryFrame(Game game) {
		this.game = game;

		// To create the GUI for a the player who is not the current leader.
		if (game.getCurrentPlayer() == game.getCurrentLeader()) {
			game.setCurrentPlayer(game.getCurrentPlayer() + 1);
		}
		if (game.getCurrentPlayer() > game.getPlayerCount()) {
			this.dispose();
			new WinnerSelectFrame(game).setVisible(true);
		} else {
			createGUI();
		}
	}

	public void createGUI() {
		setTitle("Enter Answer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel currentQuestionLabel = new JLabel(game.getCurrentQuestion());
		currentQuestionLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		currentQuestionLabel.setBounds(26, 11, 265, 14);
		contentPane.add(currentQuestionLabel);

		JLabel playerLabel = new JLabel(
				game.getPlayerNames()[game.getCurrentPlayer()]
						+ " enter your answer.");
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		playerLabel.setBounds(26, 54, 265, 14);
		contentPane.add(playerLabel);

		answerField = new JTextField();
		answerField.setBounds(26, 79, 265, 20);
		contentPane.add(answerField);
		answerField.setColumns(10);
		
		answerField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AnswerEntryFrame.this.nextPlayerButton_Click();
			}
		});

		JButton btnNextPlayer = new JButton("Next Player");
		btnNextPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AnswerEntryFrame.this.nextPlayerButton_Click();
			}
		});
		btnNextPlayer.setBounds(26, 110, 89, 23);
		contentPane.add(btnNextPlayer);
	}

	public void nextPlayerButton_Click() {
		String playerAnswer = answerField.getText();
		if (!playerAnswer.equals("")) {
			game.setPlayerAnswer(game.getCurrentPlayer(), playerAnswer);
			game.setCurrentPlayer(game.getCurrentPlayer() + 1);
			if (game.getCurrentPlayer() == game.getCurrentLeader())
				game.setCurrentPlayer(game.getCurrentPlayer() + 1);
		}
		this.dispose();

		if (game.getCurrentPlayer() < game.getPlayerCount()) {
			new AnswerEntryFrame(game).setVisible(true);
		} else {
			game.setCurrentPlayer(0);
			new WinnerSelectFrame(game).setVisible(true);
		}
	}
}
