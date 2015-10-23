package trivia.gui;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Game;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class QuestionSelectFrame extends JFrame {

	private JPanel contentPane;
	private Game game;
	
	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public QuestionSelectFrame(Game game) throws IOException {
		this.game = game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// called the new method openfile
		// returns an array into questions
		String[] questions = game.getQuestionPool();
		
		// switched button labels to items in the questions array

		// Rather than using separate buttons for each question, maybe a label
		// and radio button, with one button to move on

		JButton btnQuestion1 = new JButton(questions[0]);
		btnQuestion1.addActionListener(new ActionListener() {
			private String currentQuestion = btnQuestion1.getText();

			@Override
			public void actionPerformed(ActionEvent arg0) {

				/*
				 * the following code will eventually need to pass the question
				 * being chosen the leader will also have to be taken into
				 * consideration for whos turn it is to answer the question
				 */
				// added for ruth
				// --------------------------------------------------------------------------//
				game.setCurrentPlayer(0); // just before
				game.setCurrentQuestion(currentQuestion);
				QuestionSelectFrame.this.dispose();
				new AnswerEntryFrame(game).setVisible(true);
				// --------------------------------------------------------------------------//
			}
		});

		btnQuestion1.setBounds(86, 92, 369, 31);
		contentPane.add(btnQuestion1);

		JButton btnQuestion2 = new JButton(questions[1]);
		btnQuestion2.addActionListener(new ActionListener() {
			private String currentQuestion = btnQuestion2.getText();

			@Override
			public void actionPerformed(ActionEvent arg0) {

				/*
				 * the following code will eventually need to pass the question
				 * being chosen the leader will also have to be taken into
				 * consideration for whos turn it is to answer the question
				 */
				// added for ruth
				// --------------------------------------------------------------------------//
				game.setCurrentPlayer(0); // just before
				game.setCurrentQuestion(currentQuestion);
				QuestionSelectFrame.this.dispose();
				new AnswerEntryFrame(game).setVisible(true);
				// --------------------------------------------------------------------------//
			}
		});
		btnQuestion2.setBounds(86, 142, 369, 31);
		contentPane.add(btnQuestion2);

		JButton btnQuestion3 = new JButton(questions[2]);
		btnQuestion3.addActionListener(new ActionListener() {
			private String currentQuestion = btnQuestion3.getText();

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * the following code will eventually need to pass the question
				 * being chosen the leader will also have to be taken into
				 * consideration for whos turn it is to answer the question
				 */
				// added for ruth
				// --------------------------------------------------------------------------//
				game.setCurrentPlayer(0); // just before
				game.setCurrentQuestion(currentQuestion);
				QuestionSelectFrame.this.dispose();
				new AnswerEntryFrame(game).setVisible(true);
				// --------------------------------------------------------------------------//
			}
		});
		btnQuestion3.setBounds(86, 192, 369, 31);
		contentPane.add(btnQuestion3);

		JLabel lblPlayerPleaseSelect = new JLabel(
				game.getPlayerNames()[game.getCurrentLeader()] + ", please select question:");
		lblPlayerPleaseSelect.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPlayerPleaseSelect.setBounds(17, 24, 344, 49);
		contentPane.add(lblPlayerPleaseSelect);
	}
}
