package trivia.gui;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Question;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class QuestionSelectPanel extends JPanel {
	private GameFrame gameFrame;
	private Question[] questions = new Question[3];
	private JButton btnQuestion1;
	private JButton btnQuestion2;
	private JButton btnQuestion3;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */

	public QuestionSelectPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		gameFrame.setTitle("Select a question");
		gameFrame.setContentPane(this);
		gameFrame.repaint();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		// called the new method openfile
		// returns an array into questions
		questions = gameFrame.getGame().getQuestionPool(3);

		// switched button labels to items in the questions array

		// Rather than using separate buttons for each question, maybe a label
		// and radio button, with one button to move on

		// 11/4 changed button size from 31 to 40
		// added set font to size 12 to make sure question fits on buttons

		btnQuestion1 = new JButton(questions[0].getText());
		btnQuestion1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnQuestion1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuestionSelectPanel.this.selectQuestion(0);
			}
		});

		btnQuestion1.setBounds(115, 92, 369, 40);
		this.add(btnQuestion1);

		btnQuestion2 = new JButton(questions[1].getText());
		btnQuestion2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnQuestion2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuestionSelectPanel.this.selectQuestion(1);
			}
		});
		btnQuestion2.setBounds(115, 142, 369, 40);
		this.add(btnQuestion2);

		btnQuestion3 = new JButton(questions[2].getText());
		btnQuestion3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnQuestion3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QuestionSelectPanel.this.selectQuestion(2);
			}
		});
		btnQuestion3.setBounds(115, 192, 369, 40);
		this.add(btnQuestion3);

		JLabel lblPlayerPleaseSelect = new JLabel(
				gameFrame.getGame().getPlayer(gameFrame.getGame().getCurrentLeader()).getName()
						+ ", please select question:");
		lblPlayerPleaseSelect.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPlayerPleaseSelect.setBounds(17, 24, 344, 49);
		this.add(lblPlayerPleaseSelect);
	}

	public void selectQuestion(int question) {
		gameFrame.getGame().setCurrentPlayer(0);
		gameFrame.getGame().setCurrentQuestion(questions[question]);
		new AnswerEntryPanel(gameFrame);
	}
}
