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
	private int currentPlayer;
	private String[] playerNames;
	private JTextField answerField;

	/**
	 * Create the frame.
	 */
	public AnswerEntryFrame(Game game) {
		this.game = game;
		this.currentPlayer = game.getCurrentPlayer();
		this.playerNames = game.getPlayerNames();

		/*
		 * To make this logic more straightforward: Two places for logic, in the
		 * constructor and in the click method In constructor: - check if
		 * current player is leader, if so just increment currentplayer
		 * (game.setCurrentPlayer) and create a new answer entry frame - create
		 * gui
		 * 
		 * In click method: - Check if we just did the last player, if so move
		 * to next part of the game - If not, increment
		 * player(game.setcurrentplayer) and then create a new answer entry
		 * frame
		 * 
		 * 
		 */

		// To create the GUI for a the player who is not the current leader.
		if (currentPlayer != game.getCurrentLeader()) {
			createGUI();
		}

		// To ship creating the GUI for the current leader.
		else if (currentPlayer < game.getPlayerCount() - 1) {
			game.setPlayerAnswer(currentPlayer, "");

			// Then create the window for the next player after the current
			// leader
			currentPlayer++;
			createGUI();

			// When the current leader is the last player pass to
			// WinnerSelectFrame
		} else {
			game.setPlayerAnswer(currentPlayer, "");
			this.dispose();
			new WinnerSelectFrame(game).setVisible(true);
		}
	}

	public void createGUI() {
		setTitle("Enter Answer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 217);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel currentQuestionLabel = new JLabel(game.getCurrentQuestion());
		currentQuestionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		currentQuestionLabel.setBounds(26, 11, 265, 14);
		contentPane.add(currentQuestionLabel);

		JLabel playerLabel = new JLabel(
				playerNames[currentPlayer] + " enter your answer.");
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		playerLabel.setBounds(26, 54, 265, 14);
		contentPane.add(playerLabel);

		answerField = new JTextField();
		answerField.setBounds(26, 79, 265, 20);
		contentPane.add(answerField);
		answerField.setColumns(10);

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
			game.setPlayerAnswer(currentPlayer, playerAnswer);
			currentPlayer++;
			game.setCurrentPlayer(currentPlayer);
		}
		this.dispose();

		if (currentPlayer < game.getPlayerCount()) {
			new AnswerEntryFrame(game).setVisible(true);
		} else {
			new WinnerSelectFrame(game).setVisible(true);
		}
	}
}
