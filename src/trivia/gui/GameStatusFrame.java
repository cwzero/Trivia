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

@SuppressWarnings("serial")
public class GameStatusFrame extends JFrame {

	private JPanel contentPane;
	private Game game;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public GameStatusFrame(Game game) {
		this.game = game;
		setTitle("Current Game Status");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// This create the label for the Scoreboard
		
		JLabel lblScoreboard = new JLabel("Scoreboard");
		lblScoreboard.setFont(new Font("Lithos Pro Regular", Font.ITALIC, 16));
		lblScoreboard.setBounds(150, 72, 127, 28);
		contentPane.add(lblScoreboard);
		
		
		// This is just to hold rows and columns for now but will need to read from
		// a file or a list before displaying the player and scores
		
		String[][] rowData = new String[game.getPlayerCount()][3];
		Object columnNames[] = {"Player Name", "Score", "Answer"};
		
		for (int i = 0; i < game.getPlayerCount(); i++) {
			rowData[i][0] = game.getPlayerNames()[i];
			rowData[i][1] = game.getPlayerScore()[i] + "";
			rowData[i][2] = game.getPlayerAnswers()[i];
		}
		
		
		JTable table = new JTable(rowData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(10, 111);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setSize(414, 150);
		
		// This button starts new game " Not valid yet" 
		
		JButton btnStartNewGame = new JButton("Start New Game");
		btnStartNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameStatusFrame.this.btnStartNewGame_click();
			}
		});
		if (!game.isOver()) {
			btnStartNewGame.setText("Next round");
		}
		btnStartNewGame.setBounds(164, 268, 113, 23);
		contentPane.add(btnStartNewGame);
		
		JLabel lblGameStatus = new JLabel("Game Status");
		if (game.isOver()) {
			lblGameStatus.setText(game.getPlayerNames()[game.getGameWinner()] + " has won the game.");
		} else {
			lblGameStatus.setText(game.getPlayerNames()[game.getWinner()] + " has won the round.");
		}
		lblGameStatus.setFont(new Font("Dialog", Font.BOLD, 16));
		lblGameStatus.setBounds(10, 11, 414, 14);
		contentPane.add(lblGameStatus);
	}
	
	private void btnStartNewGame_click() {
		this.dispose();
		if (game.isOver()) {
			new GameSetupFrame().setVisible(true);
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
