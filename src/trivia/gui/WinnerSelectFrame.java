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
		setBounds(100, 100, 371, 296);
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

		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (int i = 0; i < game.getPlayerCount(); i++) {
			String[] answers = game.getPlayerAnswers();
			listModel.addElement(answers[i]);
		}
		ButtonGroup group = new ButtonGroup();
		
		for (int i = 0; i < 4; i++) {
			
            final JRadioButton button1 = new JRadioButton("test" + i);
            button1.setBounds(233 + move, 82, 109, 23);
            contentPane.add(button1);
            group.add(button1);
           
            }

		JList lstAnswers = new JList<>(listModel);
		lstAnswers.setBounds(24, 101, 203, 106);
		contentPane.add(lstAnswers);

		getContentPane().add(lstAnswers);

		JButton btnSelectWinner = new JButton("Select Winner");
		btnSelectWinner.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selectAnswer(game, lstAnswers);

			}

			private void selectAnswer(Game game, JList lstAnswers) {
				if (!lstAnswers.isSelectedIndex(-1)) {
					int Index;
					Index = lstAnswers.getSelectedIndex();
					game.setPlayerScore(playerScore);

					try {
						new QuestionSelectFrame(game).setVisible(true);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Please Select One Answer");
				}
			}

		});
		btnSelectWinner.setBounds(24, 218, 203, 28);
		contentPane.add(btnSelectWinner);

	}
}
