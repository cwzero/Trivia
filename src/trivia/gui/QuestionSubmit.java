package trivia.gui;

import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class QuestionSubmit extends GamePanel {
	private JTextField answerField;
	private JTextField questionField;
	private JLabel answerLabel;
	private JLabel questionLabel;
	
	
	public QuestionSubmit() {
		createGui();
	}
	
	public QuestionSubmit(GameFrame gameFrame){
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
	}

	@Override
	protected void createGui() {
		gameFrame.setTitle("Question Submit");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 };
		setLayout(gridBagLayout);
		
		questionLabel = new JLabel();
		questionLabel.setText("Enter Question");
		GridBagConstraints gbc_questionLabel = new GridBagConstraints();
		gbc_questionLabel.fill = GridBagConstraints.BOTH;
		gbc_questionLabel.gridheight = 1;
		gbc_questionLabel.gridwidth = 1;
		gbc_questionLabel.gridx = 0;
		gbc_questionLabel.gridy = 0;
		gbc_questionLabel.weightx = 0;
		gbc_questionLabel.weighty = 0;
		add(questionLabel, gbc_questionLabel);
		
		
		questionField = new JTextField();
		GridBagConstraints gbc_questionField = new GridBagConstraints();
		gbc_questionField.gridwidth = 2;
		gbc_questionField.fill = GridBagConstraints.BOTH;
		gbc_questionField.gridheight = 1;
		gbc_questionField.gridx = 0;
		gbc_questionField.gridy = 1;
		gbc_questionField.weightx = 0;
		gbc_questionField.weighty = 0;
		add(questionField, gbc_questionField);
		
		answerLabel = new JLabel();
		answerLabel.setText("Enter Answer");
		GridBagConstraints gbc_answerLabel = new GridBagConstraints();
		gbc_answerLabel.fill = GridBagConstraints.BOTH;
		gbc_answerLabel.gridheight = 1;
		gbc_answerLabel.gridwidth = 1;
		gbc_answerLabel.gridx = 0;
		gbc_answerLabel.gridy = 2;
		gbc_questionLabel.weightx = 0;
		gbc_questionLabel.weighty = 0;
		add(answerLabel, gbc_answerLabel);		
		
		answerField = new JTextField();
		GridBagConstraints gbc_answerField = new GridBagConstraints();
		gbc_answerField.fill = GridBagConstraints.BOTH;
		gbc_answerField.gridheight = 1;
		gbc_answerField.gridwidth = 2;
		gbc_answerField.gridx = 0;
		gbc_answerField.gridy = 3;
		gbc_questionField.weightx = 0;
		gbc_questionField.weighty = 0;
		add(answerField, gbc_answerField);
		
		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.gridx = 0;
		gbc_backButton.gridy = 4;
		gbc_backButton.gridwidth = 1;
		gbc_backButton.fill = GridBagConstraints.BOTH;
		gbc_backButton.weightx = 0;
		gbc_backButton.weighty = 0;

		Icon backIcon = new ImageIcon("src/images/back1.png");
		Icon backHover = new ImageIcon("src/images/backgif.gif");
		JButton backButton = new JButton(backIcon);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QuestionSubmit.this.backButton_Click();
			}
		});
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backHover);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backIcon);
			}
		});
		add(backButton, gbc_backButton);
		
		GridBagConstraints gbc_submitButton = new GridBagConstraints();
		gbc_submitButton.gridx = 1;
		gbc_submitButton.gridy = 4;
		gbc_submitButton.gridwidth = 1;
		gbc_submitButton.gridheight = 1;
		gbc_submitButton.fill = GridBagConstraints.BOTH;
		gbc_submitButton.weightx = 0;
		gbc_submitButton.weighty = 0;

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(southPark);
		submitButton.setOpaque(false);
		submitButton.setContentAreaFilled(false);
		submitButton.setBorderPainted(false);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QuestionSubmit.this.submitButton_Click();
			}
		});
		add(submitButton, gbc_submitButton);
	}
	
	public void backButton_Click() {
		new MainMenuPanel(gameFrame);
	}
	
	public void submitButton_Click(){
		//insert entries to database
	}
}
