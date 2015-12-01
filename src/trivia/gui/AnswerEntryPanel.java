package trivia.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import trivia.Game;

@SuppressWarnings("serial")
public class AnswerEntryPanel extends GamePanel {
	private JTextField answerField;
	/*
	 * private JLabel question; private int oneX = 7; private int oneY = 7;
	 */
	// private CountdownLabel countDown;

	public AnswerEntryPanel() {
		createGui();
	}

	public AnswerEntryPanel(GameFrame gameFrame) {
		super(gameFrame);
		Game game = gameFrame.getGame();

		// To create the GUI for a the player who is not the current leader.
		if (game.getCurrentPlayer() == game.getCurrentLeader()) {
			game.setCurrentPlayer(game.getCurrentPlayer() + 1);
		}
		if (game.getCurrentPlayer() > game.getPlayerCount()) {
			// WinnerSelectPanel

			new WinnerSelectPanel(gameFrame);
		} else {
			createGui();
			gameFrame.setVisible(true);
		}
	}

	@Override
	protected void createGui() {
		Game game = gameFrame.getGame();
		gameFrame.setTitle("Enter Answer");

		/*
		 * question = new JLabel("?"); question.setBounds(oneX, oneY, 25, 25);
		 * question.setFont(new Font("Serif", Font.BOLD, 35));
		 * question.setForeground(Color.RED); super.add(question);
		 */

		GridBagLayout gbl_contentPane = new GridBagLayout();
		setLayout(gbl_contentPane);

		/*
		 * countDown = new CountdownLabel(game.getTimeAnswer()) {
		 * 
		 * @Override public void event(int time) { if (time == 0) { Object[]
		 * options = { Game.getRandomNumberInRange(1, 99),
		 * Game.getRandomNumberInRange(1, 99), Game.getRandomNumberInRange(1,
		 * 99) }; try {
		 * 
		 * @SuppressWarnings("unused") int n =
		 * JOptionPane.showOptionDialog(gameFrame, game.choose(new
		 * File("math.txt")), "Buy more time", JOptionPane.YES_NO_CANCEL_OPTION,
		 * JOptionPane.QUESTION_MESSAGE, null, options, options[2]); } catch
		 * (HeadlessException | FileNotFoundException e) { e.printStackTrace();
		 * } } } }; countDown.setFont(new Font("Tahoma", Font.BOLD, 30));
		 * GridBagConstraints gbc_countDown = new GridBagConstraints();
		 * gbc_countDown.fill = GridBagConstraints.BOTH; gbc_countDown.insets =
		 * new Insets(0, 0, 5, 0); gbc_countDown.gridx = 0; gbc_countDown.gridy
		 * = 0; gbc_countDown.gridheight = 1; gbc_countDown.gridwidth = 1;
		 * countDown.setVisible(true); this.add(countDown, gbc_countDown); if
		 * (game.getTimeAnswer() == 0) { countDown.setVisible(false); } else {
		 * 
		 * }
		 */
		JLabel playerLabel = new JLabel(game.getPlayer(game.getCurrentPlayer()).getName() + " enter your answer.");
		playerLabel.setPreferredSize(new Dimension(600, 100));
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(southPark);
		GridBagConstraints gbc_playerLabel = new GridBagConstraints();
		gbc_playerLabel.fill = GridBagConstraints.NONE;
		gbc_playerLabel.insets = new Insets(0, 0, 5, 0);
		gbc_playerLabel.gridx = 0;
		gbc_playerLabel.gridy = 0;
		gbc_playerLabel.gridheight = 1;
		gbc_playerLabel.gridwidth = 1;
		add(playerLabel, gbc_playerLabel);
		JLabel currentQuestionLabel = new JLabel("Question");
		currentQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentQuestionLabel.setPreferredSize(new Dimension(600, 100));
		if (game.getCurrentQuestion() != null && !game.getCurrentQuestion().equals("")) {
			currentQuestionLabel.setText("<html>" + game.getCurrentQuestion().getText() + "</html>");
		}
		currentQuestionLabel.setFont(southPark);
		GridBagConstraints gbc_currentQuestionLabel = new GridBagConstraints();
		gbc_currentQuestionLabel.fill = GridBagConstraints.NONE;
		gbc_currentQuestionLabel.insets = new Insets(0, 0, 5, 0);
		gbc_currentQuestionLabel.gridx = 0;
		gbc_currentQuestionLabel.gridy = 1;
		gbc_currentQuestionLabel.gridheight = 1;
		gbc_currentQuestionLabel.gridwidth = 1;
		add(currentQuestionLabel, gbc_currentQuestionLabel);

		answerField = new JTextField() {
			@Override
			public void setBorder(Border border) {

			}
		};
		answerField.setHorizontalAlignment(SwingConstants.CENTER);
		answerField.setFont(southParkBig);
		answerField.setOpaque(false);
		answerField.setPreferredSize(new Dimension(600, 100));
		answerField.setForeground(Color.WHITE);
		GridBagConstraints gbc_answerField = new GridBagConstraints();
		gbc_answerField.fill = GridBagConstraints.NONE;
		gbc_answerField.insets = new Insets(0, 0, 5, 0);
		gbc_answerField.gridx = 0;
		gbc_answerField.gridy = 2;
		gbc_answerField.gridheight = 1;
		gbc_answerField.gridwidth = 1;
		add(answerField, gbc_answerField);
		answerField.requestFocus();

		answerField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AnswerEntryPanel.this.nextPlayerButton_Click();
			}
		});

		JButton btnNextPlayer = new JButton();
		ImageIcon continueIcon = new ImageIcon("src/images/continue1.png");
		ImageIcon continueHover = new ImageIcon("src/images/continuegif.gif");
		btnNextPlayer.setIcon(continueIcon);
		btnNextPlayer.setFont(southPark);
		btnNextPlayer.setOpaque(false);
		btnNextPlayer.setContentAreaFilled(false);
		btnNextPlayer.setBorderPainted(false);
		btnNextPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNextPlayer.setIcon(continueHover);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnNextPlayer.setIcon(continueIcon);
			}
		});
		btnNextPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Game.playSound(pop, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AnswerEntryPanel.this.nextPlayerButton_Click();
			}
		});
		GridBagConstraints gbc_btnNextPlayer = new GridBagConstraints();
		gbc_btnNextPlayer.anchor = GridBagConstraints.NORTH;
		gbc_btnNextPlayer.gridx = 0;
		gbc_btnNextPlayer.gridy = 3;
		gbc_btnNextPlayer.gridheight = 1;
		gbc_btnNextPlayer.gridwidth = 1;
		btnNextPlayer.setFont(southPark);
		add(btnNextPlayer, gbc_btnNextPlayer);

		/*
		 * Thread animationThread = new Thread() {
		 * 
		 * @Override public void run() { moveIt(); } };
		 * 
		 * animationThread.start();
		 */
	}

	public void nextPlayerButton_Click() {
		// countDown.stop();
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

	/*
	 * private void moveIt() { boolean up = false; boolean down = true; boolean
	 * left = false; boolean right = true;
	 * 
	 * while (true) { if (oneX >= 575) { right = false; left = true; } if (oneX
	 * <= 0) { right = true; left = false; } if (oneY >= 350) { up = true; down
	 * = false; } if (oneY <= 0) { up = false; down = true; } if (up) { oneY--;
	 * } if (down) { oneY++; } if (left) { oneX--; } if (right) { oneX++; } try
	 * { Thread.sleep(20); } catch (Exception exc) { } question.setBounds(oneX,
	 * oneY, 25, 25); gameFrame.repaint();
	 * 
	 * } }
	 */
}
