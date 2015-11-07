package trivia.gui;



import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;
import javax.swing.JLabel;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
	private GameFrame gameFrame;

	public MainMenuPanel() {
		this(new GameFrame());
		gameFrame.setVisible(true);
	}
	/**
	 * Create the application.
	 */
	public MainMenuPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		gameFrame.setContentPane(this);
		gameFrame.repaint();
		gameFrame.setTitle("Main Menu");
		
		

	
		
		
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0 };

		setLayout(gridBagLayout);

		JLabel lblTriviaGame = new JLabel("TRIVIA GAME");
		lblTriviaGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblTriviaGame.setBackground(new Color(240, 240, 240));
		lblTriviaGame.setFont(new Font("Tahoma", Font.BOLD, 38));

		GridBagConstraints gbc_lblTriviaGame = new GridBagConstraints();
		gbc_lblTriviaGame.fill = GridBagConstraints.BOTH;
		gbc_lblTriviaGame.insets = new Insets(0, 0, 5, 0);
		gbc_lblTriviaGame.gridx = 0;
		gbc_lblTriviaGame.gridy = 0;
		gbc_lblTriviaGame.fill = GridBagConstraints.BOTH;
		gbc_lblTriviaGame.gridwidth = 3;
		gbc_lblTriviaGame.weightx = 0;
		gbc_lblTriviaGame.weighty = 0;
		add(lblTriviaGame, gbc_lblTriviaGame);

		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuPanel.this.newGameButtonClick();
			}
		});
		newGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_newGameButton = new GridBagConstraints();
		gbc_newGameButton.fill = GridBagConstraints.BOTH;
		gbc_newGameButton.insets = new Insets(0, 0, 5, 0);
		gbc_newGameButton.gridx = 1;
		gbc_newGameButton.gridy = 1;
		gbc_newGameButton.fill = GridBagConstraints.BOTH;
		gbc_newGameButton.gridwidth = 1;
		gbc_newGameButton.weightx = 0;
		gbc_newGameButton.weighty = 0;
		add(newGameButton, gbc_newGameButton);

		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuPanel.this.quitButtonClick();
			}
		});
		quitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_quitButton = new GridBagConstraints();
		gbc_quitButton.fill = GridBagConstraints.BOTH;
		gbc_quitButton.gridheight = 1;
		gbc_quitButton.gridwidth = 1;
		gbc_quitButton.gridx = 1;
		gbc_quitButton.gridy = 2;
		gbc_quitButton.weightx = 0;
		gbc_quitButton.weighty = 0;
		add(quitButton, gbc_quitButton);
		gameFrame.setVisible(true);
	}

	
	
	
	

	
	public void newGameButtonClick() {
		// Here we will close the main menu, then show a new game setup frame
		new GameSetupPanel(gameFrame);
	}

	public void quitButtonClick() {
		System.exit(0);
	}
}
