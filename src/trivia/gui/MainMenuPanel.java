package trivia.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MainMenuPanel extends GamePanel {
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
	protected void createGui() {
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/trivia.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		gameFrame.setContentPane(contentPane);

		gameFrame.repaint();
		gameFrame.setTitle("Main Menu");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		gameFrame.setContentPane(contentPane);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0 };

		contentPane.setLayout(gridBagLayout);

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
		contentPane.add(lblTriviaGame, gbc_lblTriviaGame);

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
		contentPane.add(newGameButton, gbc_newGameButton);

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
		contentPane.add(quitButton, gbc_quitButton);
	}
}
