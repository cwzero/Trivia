package trivia.gui;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import trivia.Game;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MainMenuPanel extends GamePanel {
	
	public MainMenuPanel() {
		createGui();
	}
	
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
	
	@Override
	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/trivia.png"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
	protected void createGui() {
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

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

		Icon starticon = new ImageIcon("src/images/staticstart.png");
		Icon hoverstart = new ImageIcon("src/images/start.gif");
		
		
		JButton newGameButton = new JButton(starticon);
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuPanel.this.newGameButtonClick();
			}
		});
		// mouse over and exit
		newGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				newGameButton.setIcon(hoverstart);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				newGameButton.setIcon(starticon);
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
		//newGameButton.setIcon(icon);
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
	}
}
