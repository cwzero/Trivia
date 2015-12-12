package trivia.gui;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import trivia.Game;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class QuestionSubmit extends GamePanel {
	private JTextField answerField;
	private JTextField questionField;
	private JTextField textField;
	private JTextField textField_1;
	
	public QuestionSubmit() {
		createGui();
	}
	
	public QuestionSubmit(GameFrame gameFrame){
		super(gameFrame);
		Game game = gameFrame.getGame();
		createGui();
	}

	@Override
	protected void createGui() {
		gameFrame.setTitle("Question Submit");
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
		setLayout(gbl_contentPane);
		
		questionField = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 4;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 1;
		add(questionField, gbc_textField_1);
		questionField.setColumns(10);
		
		answerField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 4;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 3;
		add(answerField, gbc_textField);
		answerField.setColumns(10);
		
	}
}
