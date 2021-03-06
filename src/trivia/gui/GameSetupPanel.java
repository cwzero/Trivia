package trivia.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import trivia.Game;

@SuppressWarnings("serial")
public class GameSetupPanel extends GamePanel {
	private JSpinner roundCountSpinner = new JSpinner();
	private JSpinner playerCountSpinner = new JSpinner();
	private JSpinner answerTimeSpinner = new JSpinner();
	private JCheckBox answerTimeCheckBox = new JCheckBox("Limit Answer Time");

	public GameSetupPanel() {

		createGui();
	}

	// create the font

	/**
	 * Create the frame.
	 */
	public GameSetupPanel(GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
		// roundCountSpinner.setValue(gameFrame.getGame().getRoundCount());
		// playerCountSpinner.setValue(gameFrame.getGame().getPlayerCount());
	}

	public void continueButtonClick() {
		int roundCount = (int) roundCountSpinner.getValue();
		gameFrame.getGame().setRoundCount(roundCount);
		int playerCount = (int) playerCountSpinner.getValue();
		gameFrame.getGame().setPlayerCount(playerCount);
		if (answerTimeCheckBox.isSelected()) {
			int answerTime = (int) answerTimeSpinner.getValue();
			gameFrame.getGame().setAnswerTime(answerTime);
		}
		new PlayerJoinPanel(gameFrame);
	}

	@Override
	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/backgroundgif.gif"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
	protected void createGui() {

		setBounds(100, 100, 450, 300);

		gameFrame.repaint();
		gameFrame.setTitle("Game Setup");
		this.setLayout(null);
		// this.setLayout(null);

		JLabel lblRounds = new JLabel("Number of Rounds");
		lblRounds.setBounds(((gameFrame.getWidth() / 2) - 300), (gameFrame.getHeight() / 2) - 200, 500, 75);
		lblRounds.setHorizontalAlignment(SwingConstants.CENTER);
		lblRounds.setFont(southPark);
		lblRounds.setForeground(Color.WHITE);
		lblRounds.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblRounds);

		roundCountSpinner.setModel(new SpinnerNumberModel(3, 3, 10, 1));
		roundCountSpinner.setBounds((lblRounds.getX() + lblRounds.getWidth()), lblRounds.getY() + 23, 66, 45);
		roundCountSpinner.setFont(southPark);
		this.add(roundCountSpinner);

		JLabel lblPlayers = new JLabel("Number of Players");
		lblPlayers.setBounds(lblRounds.getX(), lblRounds.getY() + 95, 500, 75);
		lblPlayers.setFont(southPark);
		lblPlayers.setForeground(Color.WHITE);
		lblPlayers.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(lblPlayers);

		playerCountSpinner.setModel(new SpinnerNumberModel(3, 3, 5, 1));
		playerCountSpinner.setBounds((lblPlayers.getX() + lblPlayers.getWidth()), lblPlayers.getY() + 23, 66, 45);
		playerCountSpinner.setFont(southPark);
		this.add(playerCountSpinner);

		Icon backIcon = new ImageIcon("src/images/back1.png");
		Icon backHover = new ImageIcon("src/images/backgif.gif");
		JButton backButton = new JButton(backIcon);
		backButton.setBounds(((gameFrame.getWidth() / 2) - 400), ((gameFrame.getHeight() / 2) + 65),
				backIcon.getIconWidth(), backIcon.getIconHeight());

		backButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				backButton.setIcon(backHover);

			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				backButton.setIcon(backIcon);
			}
		});
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);

		backButton.setFont(southPark);
		add(backButton);

		Icon continueIcon = new ImageIcon("src/images/continue1.png");
		Icon continueHover = new ImageIcon("src/images/continuegif.gif");
		JButton continueButton = new JButton(continueIcon);

		continueButton.setBounds(backButton.getX() + (backButton.getWidth()), backButton.getY(),
				continueIcon.getIconWidth(), continueIcon.getIconHeight());
		// continueButton.setFont(customFont);
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Game.playSound(pop, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				GameSetupPanel.this.continueButtonClick();
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

		Icon checked = new ImageIcon("src/images/checked.png");
		Icon unchecked = new ImageIcon("src/images/unchecked.png");
		answerTimeCheckBox.setIcon(unchecked);
		answerTimeCheckBox.setSelectedIcon(checked);
		answerTimeCheckBox.setEnabled(false);
		answerTimeCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		// this.add(backButton);
		answerTimeCheckBox.setBounds(lblPlayers.getX() - 50, lblPlayers.getY() + 95, 500, 75);
		answerTimeCheckBox.setOpaque(false);
		answerTimeCheckBox.setContentAreaFilled(false);
		answerTimeCheckBox.setBorderPainted(false);
		answerTimeCheckBox.setFont(southPark);
		answerTimeCheckBox.setForeground(Color.WHITE);
		answerTimeCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					Game.playSound(click, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (e.getStateChange() == ItemEvent.SELECTED) {// checkbox has
																// been selected
					answerTimeSpinner.setEnabled(true);
				} else {// checkbox has been deselected
					answerTimeSpinner.setEnabled(false);
				}
			}
		});

		this.add(answerTimeCheckBox);

		answerTimeSpinner.setModel(new SpinnerNumberModel(30, 10, 60, 1));
		answerTimeSpinner.setBounds((answerTimeCheckBox.getX() + answerTimeCheckBox.getWidth() + 50),
				answerTimeCheckBox.getY() + 23, 66, 45);
		answerTimeSpinner.setEnabled(false);
		answerTimeSpinner.setFont(southPark);
		this.add(answerTimeSpinner);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Game.playSound(pop, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new MainMenuPanel(gameFrame);
			}
		});
	}
}
