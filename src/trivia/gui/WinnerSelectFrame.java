package trivia.gui;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import trivia.Game;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class WinnerSelectFrame extends JFrame {

	private JPanel contentPane;
	private Game game;
	protected int[] playerScore;
	private int move = 15;

	/**
	 * Create the frame.
	 */
	public WinnerSelectFrame(Game game) {
		this.game = game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Select one answer for the question:");
		lblNewLabel.setBounds(36, 11, 178, 38);
		contentPane.add(lblNewLabel);

		// Could use one label per question and radio buttons //
		JLabel lblSelectedQuestion = new JLabel("New label");
		lblSelectedQuestion.setBounds(46, 51, 157, 38);
		contentPane.add(lblSelectedQuestion);
		String CurrentQuestion = game.getCurrentQuestion().toString();
		lblSelectedQuestion.setText(CurrentQuestion);

		/*DefaultListModel<String> listModel = new DefaultListModel<>();
		for (int i = 0; i < game.getPlayerCount(); i++) {
			String[] answers = game.getPlayerAnswers();
			listModel.addElement(answers[i]);
		}
		
		
		for (int i = 0; i < 4; i++) {
			
            final JRadioButton button1 = new JRadioButton("test" + i);
            button1.setBounds(233 + move, 82, 109, 23);
            contentPane.add(button1);
            group.add(button1);
           
            }
		
		
		
		JList lstAnswers = new JList<>(listModel);
		lstAnswers.setBounds(24, 101, 203, 38);
		contentPane.add(lstAnswers);
		getContentPane().add(lstAnswers);
		
		*/

		
		
		JRadioButton rdotbnAnswer1 = new JRadioButton("New radio button");
		rdotbnAnswer1.setBounds(63, 157, 109, 23);
		contentPane.add(rdotbnAnswer1);
		
		JRadioButton rdobtnAnswer2 = new JRadioButton("New radio button");
		rdobtnAnswer2.setBounds(63, 189, 109, 23);
		contentPane.add(rdobtnAnswer2);
		
		JRadioButton rdobtnAnswer3 = new JRadioButton("New radio button");
		rdobtnAnswer3.setBounds(63, 222, 109, 23);
		contentPane.add(rdobtnAnswer3);
		
		JRadioButton rdobtnAnswer4 = new JRadioButton("New radio button");
		rdobtnAnswer4.setBounds(63, 257, 109, 23);
		contentPane.add(rdobtnAnswer4);
		
		String[] answers = game.getPlayerAnswers();
		int[] playerIndex = new int[game.getPlayerCount()];
		JRadioButton[] buttons = {rdotbnAnswer1, rdobtnAnswer2, rdobtnAnswer3, rdobtnAnswer4};
		int answerIndex = 0;
		int buttonIndex = 0;
		
		while (answerIndex < answers.length) {
			String currentAnswer = answers[answerIndex];
			if (currentAnswer != null && !currentAnswer.equals("")) {
				buttons[buttonIndex].setText(currentAnswer);
				buttonIndex++;
				playerIndex[buttonIndex] = answerIndex;
			}
			answerIndex++;
		}
		
		
		if (game.getPlayerCount() == 3)
		{
			rdobtnAnswer3.setVisible(false);
			
			
		}
		if (game.getPlayerCount() == 4)
		{
			rdobtnAnswer4.setVisible(false);
		
		}

		JButton btnSelectWinner = new JButton("Select Winner");
		btnSelectWinner.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				int winner = -1;
				for (int buttonIndex = 0; buttonIndex < buttons.length; buttonIndex++) {
					if (buttons[buttonIndex].isSelected()) {
						winner = playerIndex[buttonIndex];
					}
				}
				game.setWinner(winner);
				
				
				if (winner == -1)
				{
					JOptionPane.showMessageDialog(null, "Select one answer");
				}
				else
				{
				// We need to add scores to the winner before moving to next screen, don't we?
				//game.setPlayerScore(); 
					
				WinnerSelectFrame.this.dispose();
				new GameStatusFrame(game).setVisible(true);
				}
				
				
			}
		});
		btnSelectWinner.setBounds(20, 287, 203, 28);
		contentPane.add(btnSelectWinner);
	}

	}