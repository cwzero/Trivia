package trivia.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Game;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PlayerJoinFrame extends JFrame {
	private JPanel contentPane;
	private JTextField playerNameField;
	private JButton btnBack;
	private Game game;
	private int currentPlayer = 0;

	/**
	 * Create the frame.
	 */
	public PlayerJoinFrame(Game game) {
		this.game = game;
		this.currentPlayer = game.getCurrentPlayer();

		setTitle("Enter Player Name");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel playerLabel = new JLabel("Player " + (currentPlayer + 1) + " Enter Name");
		playerLabel.setBounds(10, 11, 98, 14);
		contentPane.add(playerLabel);

		playerNameField = new JTextField();
		playerNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerJoinFrame.this.continueButtonClick();
			}
		});

		playerNameField.setBounds(118, 8, 86, 20);
		contentPane.add(playerNameField);
		playerNameField.setColumns(10);

		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerJoinFrame.this.continueButtonClick();
			}
		});
		btnContinue.setBounds(10, 36, 89, 23);
		contentPane.add(btnContinue);

		btnBack = new JButton("Back");
		btnBack.setBounds(115, 36, 89, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerJoinFrame.this.dispose();
				new GameSetupFrame(game).setVisible(true);
			}
		});
	}

	public void continueButtonClick() {
		String playerName = playerNameField.getText();
		if (playerName == null || playerName.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Please, Enter a Name");
		} else {

			game.setPlayerName(currentPlayer, playerName);

			currentPlayer++;
			game.setCurrentPlayer(currentPlayer);
			this.dispose();

			if (currentPlayer < game.getPlayerCount()) {
				new PlayerJoinFrame(game).setVisible(true);
			} else {
				new SetupSummaryFrame(game).setVisible(true);
			}
		}
	}
}
