package trivia.gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import trivia.Game;

@SuppressWarnings("serial")
public class MainMenuPanel extends GamePanel {

	public MainMenuPanel() {
		createGui();
	}

	// titlemusic from playonloop.com
	File file = new File("click7.au");
	File pop = new File("pop.au");
	File music = new File("titlemusic.wav");

	/**
	 * Create the application.
	 */
	public MainMenuPanel(GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
	}

	public void newGameButtonClick() {
		// Here we will close the main menu, then show a new game setup frame
		new GameSetupPanel(gameFrame);
	}

	public void quitButtonClick() {
		System.exit(0);
	}

	int count = 0;

	@Override
	protected void createGui() {
		gameFrame.setVisible(false);

		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		gameFrame.repaint();
		// gameFrame.setTitle("Main Menu");

		try {
			Game.playSound(music, (120000));

		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();

		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0 };

		setLayout(gridBagLayout);
		Icon headericon = new ImageIcon("src/images/headergif.gif");

		JLabel lblTriviaGame = new JLabel(headericon);
		lblTriviaGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblTriviaGame.setBackground(new Color(240, 240, 240));

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

		// right now, smaller image as startIcon gives it an effect that pushes
		// exitbutton and logo away from startgame
		Icon startIcon = new ImageIcon("src/images/staticstart.png");
		Icon hoverStart = new ImageIcon("src/images/startgif.gif");

		JButton newGameButton = new JButton(startIcon);
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuPanel.this.newGameButtonClick();
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
		gbc_newGameButton.fill = GridBagConstraints.BOTH;
		gbc_newGameButton.insets = new Insets(0, 0, 5, 0);
		gbc_newGameButton.gridx = 1;
		gbc_newGameButton.gridy = 1;
		gbc_newGameButton.fill = GridBagConstraints.BOTH;
		gbc_newGameButton.gridwidth = 1;
		gbc_newGameButton.weightx = 0;
		gbc_newGameButton.weighty = 0;
		// newGameButton.setIcon(icon);
		add(newGameButton, gbc_newGameButton);

		Icon exitIcon = new ImageIcon("src/images/exit1.png");
		Icon hoverExit = new ImageIcon("src/images/exitgif.gif");

		JButton quitButton = new JButton(exitIcon);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Game.playSound(pop, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainMenuPanel.this.quitButtonClick();
				// pop noise on click

			}
		});

		// mouse over and exit
		quitButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				quitButton.setIcon(hoverExit);

			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				quitButton.setIcon(exitIcon);
			}
		});
		quitButton.setOpaque(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setBorderPainted(false);

		GridBagConstraints gbc_quitButton = new GridBagConstraints();
		gbc_quitButton.fill = GridBagConstraints.BOTH;
		gbc_quitButton.gridheight = 1;
		gbc_quitButton.gridwidth = 1;
		gbc_quitButton.gridx = 1;
		gbc_quitButton.gridy = 2;
		gbc_quitButton.weightx = 0;
		gbc_quitButton.weighty = 0;
		add(quitButton, gbc_quitButton);

		// on EACH PAGE click question mark for instructions on game, have one
		// main that tells how the game works from start to finish
		// have another one broken up into each section of the game in the view
		// point of the user

		gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// gameFrame.setVisible(true);
	}
}
