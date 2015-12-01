package trivia.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import trivia.Game;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PlayerJoinPanel extends GamePanel {
	private JTextField playerNameField;
	private int currentPlayer = 0;

	public PlayerJoinPanel() {
		createGui();
	}

	File click = new File("click7.au");
	File pop = new File("pop.au");

	/**
	 * Create the frame.
	 */
	public PlayerJoinPanel(final GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/backgroundgif.gif"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public void backButtonClick() {
		if (currentPlayer > 0) {
			gameFrame.getGame().setCurrentPlayer(currentPlayer - 1);
			new PlayerJoinPanel(gameFrame);
		} else {
			gameFrame.getGame().setCurrentPlayer(0);
			gameFrame.getGame().reset();
			new MainMenuPanel(gameFrame);
		}
	}

	public void continueButtonClick() {
		String playerName = playerNameField.getText();
		if (playerName == null || playerName.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Please, Enter a Name");
		} else {

			gameFrame.getGame().setPlayerName(currentPlayer, playerName);

			currentPlayer++;
			gameFrame.getGame().setCurrentPlayer(currentPlayer);

			if (currentPlayer < gameFrame.getGame().getPlayerCount()) {
				new PlayerJoinPanel(gameFrame);
			} else {
				new SetupSummaryPanel(gameFrame);
			}
		}
	}

	@Override
	protected void createGui() {

		this.currentPlayer = gameFrame.getGame().getCurrentPlayer();
		gameFrame.repaint();
		gameFrame.setTitle("Enter Player Name");
		this.setLayout(null);

		JLabel playerLabel = new JLabel("Player " + (currentPlayer + 1) + ", Enter Name");
		playerLabel.setBounds((gameFrame.getWidth() / 2) - 370, (gameFrame.getHeight() / 2) - 200, 750, 100);
		this.add(playerLabel);
		playerLabel.setFont(southPark);
		playerLabel.setForeground(Color.BLACK);
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		playerNameField = new JTextField() {
			@Override
			public void setBorder(Border border) {
				
			}
		};
		playerNameField.setForeground(Color.WHITE);
		playerNameField.setText(gameFrame.getGame().getPlayer(currentPlayer).getName());
		playerNameField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {
				try {
					Game.playSound(click, 100);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					continueButtonClick();
					try {
						Game.playSound(pop, 100);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		playerNameField.setBounds(playerLabel.getX(), playerLabel.getY() + 150, 750, 80);
		playerNameField.setFont(southParkBig);
		playerNameField.setHorizontalAlignment(SwingConstants.CENTER);
		playerNameField.setOpaque(false);
		this.add(playerNameField);
		playerNameField.requestFocus();

		/*
		 * JButton btnContinue = new JButton("Continue");
		 * btnContinue.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * PlayerJoinPanel.this.continueButtonClick(); } });
		 * btnContinue.setBounds(190, 166, 89, 23); this.add(btnContinue);
		 * 
		 * btnBack = new JButton("Back"); btnBack.setBounds(288, 166, 89, 23);
		 * this.add(btnBack); btnBack.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * PlayerJoinPanel.this.backButtonClick(); } });
		 */

		Icon backIcon = new ImageIcon("src/images/back1.png");
		Icon backHover = new ImageIcon("src/images/backgif.gif");
		JButton btnBack = new JButton(backIcon);
		btnBack.setBounds(((gameFrame.getWidth() / 2) - 400), ((gameFrame.getHeight() / 2) + 65),
				backIcon.getIconWidth(), backIcon.getIconHeight());
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Game.playSound(pop, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PlayerJoinPanel.this.backButtonClick();
			}
		});
		btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnBack.setIcon(backHover);

			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnBack.setIcon(backIcon);
			}
		});
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);

		btnBack.setFont(southPark);
		add(btnBack);

		Icon continueIcon = new ImageIcon("src/images/continue1.png");
		Icon continueHover = new ImageIcon("src/images/continuegif.gif");
		JButton continueButton = new JButton(continueIcon);

		continueButton.setBounds(btnBack.getX() + (btnBack.getWidth()), btnBack.getY(), continueIcon.getIconWidth(),
				continueIcon.getIconHeight()); // continueButton.setFont(customFont);
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Game.playSound(pop, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PlayerJoinPanel.this.continueButtonClick();
			}
		});
		// continueButton.setBounds(99, 213, 95, 23);
		continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				continueButton.setIcon(continueHover);

			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				continueButton.setIcon(continueIcon);
			}
		});

		continueButton.setOpaque(false);
		continueButton.setContentAreaFilled(false);
		continueButton.setBorderPainted(false);

		// newGameButton.setIcon(icon);
		add(continueButton);
	}
}
