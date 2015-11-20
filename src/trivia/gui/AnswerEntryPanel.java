package trivia.gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Game;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AnswerEntryPanel extends JPanel {
	private GameFrame gameFrame;
	private JTextField answerField;
	private String s;

	public AnswerEntryPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		Game game = gameFrame.getGame();

		// To create the GUI for a the player who is not the current leader.
		if (game.getCurrentPlayer() == game.getCurrentLeader()) {
			game.setCurrentPlayer(game.getCurrentPlayer() + 1);
		}
		if (game.getCurrentPlayer() > game.getPlayerCount()) {
			// WinnerSelectPanel

			new WinnerSelectPanel(gameFrame);
		} else {
			createGUI();
		}
	}

	public void createGUI() {
		gameFrame.setContentPane(this);
		gameFrame.repaint();
		
		Game game = gameFrame.getGame();
		gameFrame.setTitle("Enter Answer");
		setBounds(100, 100, 600, 400);

		setBorder(new EmptyBorder(25, 25, 25, 25));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		setLayout(gbl_contentPane);
		
		
		
		CountdownLabel countDown = new CountdownLabel(game.getTimeAnswer()){
			
			

			@Override
			public void event(int time) {
				if (time == 0) {
					Object[] options = {Game.getRandomNumberInRange(1, 99),
										Game.getRandomNumberInRange(1, 99),
										Game.getRandomNumberInRange(1, 99)};
								try {
									int n = JOptionPane.showOptionDialog(gameFrame,
									s = game.choose(new File("math.txt")),
									"Buy more time",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null,
									options,
									options[2]);
								} catch (HeadlessException | FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
			}
		};
		countDown.setFont(new Font("Tahoma", Font.BOLD, 30));
		GridBagConstraints gbc_countDown = new GridBagConstraints();
		gbc_countDown.fill = GridBagConstraints.BOTH;
		gbc_countDown.insets = new Insets(0, 0, 5, 0);
		gbc_countDown.gridx = 0;
		gbc_countDown.gridy = 0;
		gbc_countDown.gridheight = 1;
		gbc_countDown.gridwidth = 1;
		countDown.setVisible(true);
	    this.add(countDown, gbc_countDown);
	    if(game.getTimeAnswer()== 0){
			countDown.setVisible(false);
		}
	    else
	    {
	    	
	    }
	    
		JLabel currentQuestionLabel = new JLabel("Question");
		currentQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentQuestionLabel.setMaximumSize(new Dimension(600, 400));
		if (game.getCurrentQuestion() != null && !game.getCurrentQuestion().equals("")) {
			currentQuestionLabel.setText("<html>" + game.getCurrentQuestion().getText() + "</html>");
		}
		currentQuestionLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_currentQuestionLabel = new GridBagConstraints();
		gbc_currentQuestionLabel.fill = GridBagConstraints.BOTH;
		gbc_currentQuestionLabel.insets = new Insets(0, 0, 5, 0);
		gbc_currentQuestionLabel.gridx = 0;
		gbc_currentQuestionLabel.gridy = 1;
		gbc_currentQuestionLabel.gridheight = 1;
		gbc_currentQuestionLabel.gridwidth = 1;
		add(currentQuestionLabel, gbc_currentQuestionLabel);

		JLabel playerLabel = new JLabel(game.getPlayer(game.getCurrentPlayer()).getName() + " enter your answer.");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_playerLabel = new GridBagConstraints();
		gbc_playerLabel.fill = GridBagConstraints.VERTICAL;
		gbc_playerLabel.insets = new Insets(0, 0, 5, 0);
		gbc_playerLabel.gridx = 0;
		gbc_playerLabel.gridy = 2;
		gbc_playerLabel.gridheight = 1;
		gbc_playerLabel.gridwidth = 1;
		add(playerLabel, gbc_playerLabel);

		answerField = new JTextField();
		answerField.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_answerField = new GridBagConstraints();
		gbc_answerField.fill = GridBagConstraints.HORIZONTAL;
		gbc_answerField.insets = new Insets(0, 0, 5, 0);
		gbc_answerField.gridx = 0;
		gbc_answerField.gridy = 3;
		gbc_answerField.gridheight = 1;
		gbc_answerField.gridwidth = 1;
		add(answerField, gbc_answerField);
		answerField.requestFocus();
		answerField.setColumns(10);

		answerField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AnswerEntryPanel.this.nextPlayerButton_Click();
			}
		});

		JButton btnNextPlayer = new JButton("Next Player");
		btnNextPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AnswerEntryPanel.this.nextPlayerButton_Click();
			}
		});
		GridBagConstraints gbc_btnNextPlayer = new GridBagConstraints();
		gbc_btnNextPlayer.anchor = GridBagConstraints.NORTH;
		gbc_btnNextPlayer.gridx = 0;
		gbc_btnNextPlayer.gridy = 4;
		gbc_btnNextPlayer.gridheight = 1;
		gbc_btnNextPlayer.gridwidth = 1;
		add(btnNextPlayer, gbc_btnNextPlayer);
	}

	public void nextPlayerButton_Click() {
		Game game = gameFrame.getGame();
		String playerAnswer = answerField.getText();
		if (!playerAnswer.equals("")) {
			game.setPlayerAnswer(game.getCurrentPlayer(), playerAnswer);
			game.setCurrentPlayer(game.getCurrentPlayer() + 1);
			if (game.getCurrentPlayer() == game.getCurrentLeader())
				game.setCurrentPlayer(game.getCurrentPlayer() + 1);
		}

		if (game.getCurrentPlayer() < game.getPlayerCount()) {
			new AnswerEntryPanel(gameFrame);
		} else {
			game.setCurrentPlayer(0);
			new WinnerSelectPanel(gameFrame);
		}
	}
}
