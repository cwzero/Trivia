package trivia.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Game;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class WinnerSelectFrame extends JFrame {

	private JPanel contentPane;
	private Game game;

	/**
	 * Create the frame.
	 */
	public WinnerSelectFrame(Game game) {
		this.game = game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select one answer for the question:");
		lblNewLabel.setBounds(36, 11, 178, 38);
		contentPane.add(lblNewLabel);
		
		JButton btnSelectWinner = new JButton("Select Winner");
		btnSelectWinner.setBounds(24, 218, 203, 28);
		contentPane.add(btnSelectWinner);
		
		JLabel lblSelectedQuestion = new JLabel("New label");
		lblSelectedQuestion.setBounds(24, 51, 203, 28);
		contentPane.add(lblSelectedQuestion);
		String CurrentQuestion = game.getCurrentQuestion().toString();
		lblSelectedQuestion.setText(CurrentQuestion);
		
		

		DefaultListModel<String> listModel = new DefaultListModel<>();
		for (int i=0; i<game.getPlayerCount(); i++)
		{
		String[] answers = game.getPlayerAnswers();
		listModel.addElement(answers[i]);
		}
        
        JList lstAnswers = new JList<>(listModel);
		lstAnswers.setBounds(24, 92, 203, 115);
		contentPane.add(lstAnswers);
        
        add(lstAnswers);
		
	}
}
