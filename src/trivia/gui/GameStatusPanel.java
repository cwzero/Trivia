package trivia.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import trivia.Game;
import trivia.Player;

@SuppressWarnings("serial")
public class GameStatusPanel extends GamePanel {
	File pop = new File("pop.au");
	public GameStatusPanel() {
		createGui();
	}

	/**
	 * Create the frame.
	 */
	public GameStatusPanel(GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
	}

	private void newGameButtonClick() {
		if (gameFrame.getGame().isOver()) {
			gameFrame.setGame(new Game());
			new GameSetupPanel(gameFrame);
		} else {
			gameFrame.getGame().nextRound();
			new QuestionSelectPanel(gameFrame);
		}
	}

	@Override
	protected void createGui() {
		setBounds(100, 100, 450, 300);

		gameFrame.repaint();
		gameFrame.setTitle("Current Game Status");
		GridBagLayout gbl_contentPane = new GridBagLayout();

		setLayout(gbl_contentPane);

		JLabel lblGameStatus = new JLabel("Game Status");
		lblGameStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameStatus.setFont(southPark);
		GridBagConstraints gbc_lblGameStatus = new GridBagConstraints();
		gbc_lblGameStatus.weighty = 0.05;
		gbc_lblGameStatus.weightx = 1.0;
		gbc_lblGameStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblGameStatus.gridx = 0;
		gbc_lblGameStatus.gridy = 0;
		add(lblGameStatus, gbc_lblGameStatus);

		// This create the label for the Scoreboard

		JLabel lblScoreboard = new JLabel("Scoreboard");
		lblScoreboard.setFont(southPark);
		GridBagConstraints gbc_lblScoreboard = new GridBagConstraints();
		gbc_lblScoreboard.weightx = 1.0;
		gbc_lblScoreboard.weighty = 0.05;
		gbc_lblScoreboard.fill = GridBagConstraints.NONE;
		gbc_lblScoreboard.insets = new Insets(0, 0, 5, 0);
		gbc_lblScoreboard.gridx = 0;
		gbc_lblScoreboard.gridy = 2;
		add(lblScoreboard, gbc_lblScoreboard);

		// This is just to hold rows and columns for now but will need to read
		// from
		// a file or a list before displaying the player and scores
		String scores = "";
		String answers = "";
		if (!gameFrame.getGame().isOver()) {
			scores = "At the end of round " + gameFrame.getGame().getCurrentRound() + " ";
		}
		for (int playerIndex = 0; playerIndex < gameFrame.getGame().getPlayerCount(); playerIndex++) {
			Player player = gameFrame.getGame().getPlayer(playerIndex);
			scores += player.getName() + " has " + player.getScore();
			if (player.getScore() != 1) {
				scores += " points";
			} else {
				scores += " point";
			}
			if (playerIndex + 2 < gameFrame.getGame().getPlayerCount()) {
				scores += ", ";
			} else if (playerIndex + 1 < gameFrame.getGame().getPlayerCount()) {
				scores += ", and ";
			} else {
				scores += ".";
			}
		}
		for (int playerIndex = 0; playerIndex < gameFrame.getGame().getPlayerCount(); playerIndex++) {
			Player player = gameFrame.getGame().getPlayer(playerIndex);
			if (gameFrame.getGame().getCurrentLeader() != playerIndex) {
				answers += player.getName() + " answered \"" + player.getAnswer() + "\"";
				if (playerIndex + 3 < gameFrame.getGame().getPlayerCount()) {
					answers += ", ";
				} else if (playerIndex + 2 < gameFrame.getGame().getPlayerCount()) {
					answers += ", and ";
				} else {
					answers += ".";
				}
			}
		}

		GridBagConstraints gbc_scoreLabel = new GridBagConstraints();
		gbc_scoreLabel.weightx = 1.0;
		gbc_scoreLabel.weighty = 0.05;
		gbc_scoreLabel.fill = GridBagConstraints.NONE;
		gbc_scoreLabel.insets = new Insets(0, 0, 5, 0);
		gbc_scoreLabel.gridx = 0;
		gbc_scoreLabel.gridy = 3;
		JLabel scoreLabel = new JLabel(scores);
		scoreLabel.setFont(southPark);
		add(scoreLabel, gbc_scoreLabel);

		GridBagConstraints gbc_answerLabel = new GridBagConstraints();
		gbc_answerLabel.weightx = 1.0;
		gbc_answerLabel.weighty = 0.05;
		gbc_answerLabel.fill = GridBagConstraints.NONE;
		gbc_answerLabel.insets = new Insets(0, 0, 5, 0);
		gbc_answerLabel.gridx = 0;
		gbc_answerLabel.gridy = 4;
		JLabel answerLabel = new JLabel(answers);
		answerLabel.setFont(southPark);
		add(answerLabel, gbc_answerLabel);

		/*
		 * String[][] rowData = new String[gameFrame.getGame().getPlayerCount()
		 * + 1][3]; String columnNames[] = { "Player Name", "Score", "Answer" };
		 * 
		 * rowData[0][0] = columnNames[0]; rowData[0][1] = columnNames[1];
		 * rowData[0][2] = columnNames[2];
		 * 
		 * for (int i = 1; i < gameFrame.getGame().getPlayerCount() + 1; i++) {
		 * rowData[i][0] = gameFrame.getGame().getPlayer(i - 1).getName();
		 * rowData[i][1] = gameFrame.getGame().getPlayer(i - 1).getScore() + "";
		 * String answer = gameFrame.getGame().getPlayer(i - 1).getAnswer(); if
		 * (answer == null || answer.equals("")) { answer = "Leader"; }
		 * rowData[i][2] = answer; }
		 * 
		 * JTable table = new JTable(rowData, columnNames); TableCellRenderer
		 * renderer = new DefaultTableCellRenderer() { { setOpaque(false); } };
		 * table.setDefaultRenderer(Object.class, renderer);
		 * table.setOpaque(false); table.setFont(southPark);
		 * table.setShowGrid(false);
		 * table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); TableColumn column =
		 * null; for (int i = 0; i < 3; i++) { column =
		 * table.getColumnModel().getColumn(i); if (i == 0) {
		 * column.setPreferredWidth(50); } else if (i == 1) {
		 * column.setPreferredWidth(10); // Second column is smaller } else {
		 * column.setPreferredWidth(150); } }
		 */

		/*
		 * // This makes the score column smaller to leave more space for answer
		 * TableColumn column = null; for (int i = 0; i < 3; i++) { column =
		 * table.getColumnModel().getColumn(i); if (i == 0) {
		 * column.setPreferredWidth(50); } else if (i == 1) {
		 * column.setPreferredWidth(10); // Second column is smaller } else {
		 * column.setPreferredWidth(150); } }
		 * 
		 * JScrollPane scrollPane = new JScrollPane(table);
		 * scrollPane.setOpaque(false); GridBagConstraints gbc_scrollPane = new
		 * GridBagConstraints(); gbc_scrollPane.weighty = 2.0;
		 * gbc_scrollPane.weightx = 1.0; gbc_scrollPane.insets = new Insets(0,
		 * 60, 15, 60); gbc_scrollPane.gridx = 0; gbc_scrollPane.gridy = 3;
		 * add(scrollPane, gbc_scrollPane);
		 */

		/*
		 * GridBagConstraints gbc_table = new GridBagConstraints();
		 * gbc_table.weightx = 1.0; gbc_table.weighty = 2.0; gbc_table.insets =
		 * new Insets(0, 60, 15, 60); gbc_table.gridx = 0; gbc_table.gridy = 3;
		 * add(table, gbc_table);
		 */
		// This button starts new game " Not valid yet"
		// right now, smaller image as startIcon gives it an effect that pushes
		// exitbutton and logo away from startgame
		
		Icon startIcon = new ImageIcon("src/images/start1.png");
		Icon hoverStart = new ImageIcon("src/images/startgif.gif");

		JButton newGameButton = new JButton(startIcon);
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameStatusPanel.this.newGameButtonClick();
				// pop noise on click
				try {
					Game.playSound(pop, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// mouse over and exit
		newGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				newGameButton.setIcon(hoverStart);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				newGameButton.setIcon(startIcon);
			}
		});
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		GridBagConstraints gbc_newGameButton = new GridBagConstraints();
		gbc_newGameButton.weighty = 0.05;
		gbc_newGameButton.fill = GridBagConstraints.NONE;
		gbc_newGameButton.gridx = 0;
		gbc_newGameButton.gridy = 5;
		// newGameButton.setIcon(icon);
		add(newGameButton, gbc_newGameButton);
		
		if (gameFrame.getGame().isOver()) {
			Player gameWinner = gameFrame.getGame().getGameWinner();
			if (gameWinner == null) {
				lblGameStatus.setText("The game has ended with a tie.");
			} else {
				lblGameStatus.setText(gameWinner.getName() + " has won the game.");
			}
		} else {
			lblGameStatus.setText(gameFrame.getGame().getRoundWinner().getName() + " has won the round.");
			//newGameButton.setText("Next round");
		}
	}
}
