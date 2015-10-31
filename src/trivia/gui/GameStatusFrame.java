package trivia.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import trivia.Game;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GameStatusFrame extends JFrame {
	private JPanel contentPane;
	private Game game;

	/**
	 * Create the frame.
	 */
	public GameStatusFrame(Game game) {
		this.game = game;
		setTitle("Current Game Status");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblGameStatus = new JLabel("Game Status");
		lblGameStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameStatus.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lblGameStatus = new GridBagConstraints();
		gbc_lblGameStatus.weighty = 0.05;
		gbc_lblGameStatus.weightx = 1.0;
		gbc_lblGameStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblGameStatus.gridx = 0;
		gbc_lblGameStatus.gridy = 0;
		contentPane.add(lblGameStatus, gbc_lblGameStatus);
		
		// This create the label for the Scoreboard
		
		JLabel lblScoreboard = new JLabel("Scoreboard");
		lblScoreboard.setFont(new Font("Lithos Pro Regular", Font.ITALIC, 16));
		GridBagConstraints gbc_lblScoreboard = new GridBagConstraints();
		gbc_lblScoreboard.weightx = 1.0;
		gbc_lblScoreboard.weighty = 0.05;
		gbc_lblScoreboard.fill = GridBagConstraints.VERTICAL;
		gbc_lblScoreboard.insets = new Insets(0, 0, 5, 0);
		gbc_lblScoreboard.gridx = 0;
		gbc_lblScoreboard.gridy = 2;
		contentPane.add(lblScoreboard, gbc_lblScoreboard);
		
		

		
		
		// This is just to hold rows and columns for now but will need to read from
		// a file or a list before displaying the player and scores
		
		String[][] rowData = new String[game.getPlayerCount()][3];
		Object columnNames[] = {"Player Name", "Score", "Answer"};
		
		for (int i = 0; i < game.getPlayerCount(); i++) {
			rowData[i][0] = game.getPlayerNames()[i];
			rowData[i][1] = game.getPlayerScore()[i] + "";
			String answer = game.getPlayerAnswers()[i];
			if (answer == null || answer.equals("")) {
				answer = "Leader";
			}
			rowData[i][2] = answer;
		}

		
		JTable table = new JTable(rowData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.weighty = 2.0;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		// This button starts new game " Not valid yet" 
		
		JButton btnStartNewGame = new JButton("Start New Game");
		btnStartNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameStatusFrame.this.btnStartNewGame_click();
			}
		});
		GridBagConstraints gbc_btnStartNewGame = new GridBagConstraints();
		gbc_btnStartNewGame.weighty = 0.05;
		gbc_btnStartNewGame.fill = GridBagConstraints.VERTICAL;
		gbc_btnStartNewGame.gridx = 0;
		gbc_btnStartNewGame.gridy = 4;
		contentPane.add(btnStartNewGame, gbc_btnStartNewGame);
		if (game.isOver()) {
			lblGameStatus.setText(game.getPlayerNames()[game.getGameWinner()] + " has won the game.");
		} else {
			lblGameStatus.setText(game.getPlayerNames()[game.getRoundWinner()] + " has won the round.");
		}
		if (!game.isOver()) {
			btnStartNewGame.setText("Next round");
		}
	}
	
	private void btnStartNewGame_click() {
		this.dispose();
		if (game.isOver()) {
			new GameSetupPanel().setVisible(true);
		} else {
			game.nextRound();
			try {
				new QuestionSelectFrame(game).setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
