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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// This create the label for the Scoreboard
		
		JLabel lblScoreboard = new JLabel("Scoreboard");
		lblScoreboard.setFont(new Font("Lithos Pro Regular", Font.ITALIC, 16));
		lblScoreboard.setBounds(153, 11, 127, 28);
		contentPane.add(lblScoreboard);
		
		// This is just to hold rows and columns for now but will need to read from
		// a file or a list before displaying the player and scores
		
		Object rowData[][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3"},
				{"Row2-Column1", "Row2-Column2", "Row2-Column3"}};
		Object columnNames[] = {"Players", "Scores", "Right Answers"};
		JTable table = new JTable(rowData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(10, 50);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setSize(414, 150);
		
		// This button starts new game " Not valid yet" 
		
		JButton btnStartNewGame = new JButton("Start New Game");
		btnStartNewGame.setBounds(153, 228, 113, 23);
		contentPane.add(btnStartNewGame);
	}
}
