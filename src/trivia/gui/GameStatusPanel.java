package trivia.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import trivia.Game;
import trivia.Player;

@SuppressWarnings("serial")
public class GameStatusPanel extends GamePanel {
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

	private void btnStartNewGame_click() {
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
		setBorder(new EmptyBorder(25, 25, 25, 25));
		setLayout(new BorderLayout(0, 0));

		gameFrame.repaint();
		gameFrame.setTitle("Current Game Status");
		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0 };

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
		gbc_lblScoreboard.fill = GridBagConstraints.VERTICAL;
		gbc_lblScoreboard.insets = new Insets(0, 0, 5, 0);
		gbc_lblScoreboard.gridx = 0;
		gbc_lblScoreboard.gridy = 2;
		add(lblScoreboard, gbc_lblScoreboard);

		// This is just to hold rows and columns for now but will need to read
		// from
		// a file or a list before displaying the player and scores

		String[][] rowData = new String[gameFrame.getGame().getPlayerCount() + 1][3];
		String columnNames[] = { "Player Name", "Score", "Answer" };

		rowData[0][0] = columnNames[0];
		rowData[0][1] = columnNames[1];
		rowData[0][2] = columnNames[2];

		for (int i = 1; i < gameFrame.getGame().getPlayerCount() + 1; i++) {
			rowData[i][0] = gameFrame.getGame().getPlayer(i - 1).getName();
			rowData[i][1] = gameFrame.getGame().getPlayer(i - 1).getScore() + "";
			String answer = gameFrame.getGame().getPlayer(i - 1).getAnswer();
			if (answer == null || answer.equals("")) {
				answer = "Leader";
			}
			rowData[i][2] = answer;
		}

		JTable table = new JTable(rowData, columnNames);
		TableCellRenderer renderer = new DefaultTableCellRenderer() {
			{
				setOpaque(false);
			}
		};
		table.setDefaultRenderer(Object.class, renderer);
		table.setOpaque(false);
		table.setFont(southPark);
		table.setShowGrid(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn column = null;
		for (int i = 0; i < 3; i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(50);
			} else if (i == 1) {
				column.setPreferredWidth(10); // Second column is smaller
			} else {
				column.setPreferredWidth(150);
			}
		}

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

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.weightx = 1.0;
		gbc_table.weighty = 2.0;
		gbc_table.insets = new Insets(0, 60, 15, 60);
		gbc_table.gridx = 0;
		gbc_table.gridy = 3;
		add(table, gbc_table);

		// This button starts new game " Not valid yet"

		JButton btnStartNewGame = new JButton("Start New Game");
		btnStartNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameStatusPanel.this.btnStartNewGame_click();
			}
		});
		GridBagConstraints gbc_btnStartNewGame = new GridBagConstraints();
		gbc_btnStartNewGame.weighty = 0.05;
		gbc_btnStartNewGame.fill = GridBagConstraints.VERTICAL;
		gbc_btnStartNewGame.gridx = 0;
		gbc_btnStartNewGame.gridy = 4;
		add(btnStartNewGame, gbc_btnStartNewGame);
		if (gameFrame.getGame().isOver()) {
			Player gameWinner = gameFrame.getGame().getGameWinner();
			if (gameWinner == null) {
				lblGameStatus.setText("The game has ended with a tie.");
			} else {
				lblGameStatus.setText(gameWinner.getName() + " has won the game.");
			}
		} else {
			lblGameStatus.setText(gameFrame.getGame().getRoundWinner().getName() + " has won the round.");
			btnStartNewGame.setText("Next round");
		}
	}
}
