package trivia.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import trivia.Game;
import trivia.Player;

@SuppressWarnings("serial")
public class WinnerSelectPanel extends GamePanel {
	protected int[] playerScore;
	protected int increment = 20;
	protected CountdownLabel lblCountdown;
	File file = new File("click7.au");

	public WinnerSelectPanel() {
		createGui();
	}

	/**
	 * Create the frame.
	 */
	public WinnerSelectPanel(final GameFrame gameFrame) {
		super(gameFrame);
		createGui();
	}

	private List<PlayerButton> shuffle(List<PlayerButton> orig) {
		Random ran = new Random();
		List<PlayerButton> temp = new ArrayList<PlayerButton>(orig);
		List<PlayerButton> result = new ArrayList<PlayerButton>();

		while (!temp.isEmpty()) {
			result.add(temp.remove(ran.nextInt(temp.size())));
		}
		return result;
	}

	public void selectWinner(Player winner) {
		winner.setWinner(true);
		winner.setScore(winner.getScore() + 1);
		new GameStatusPanel(gameFrame);
	}

	@Override
	protected void createGui() {
		this.setLayout(new GridBagLayout());

/*		lblCountdown = new CountdownLabel(15) {

			@Override
			public void event(int time) {
				if (time == 5) {
					this.setForeground(Color.red);
					this.setFont(new Font("Tahoma", Font.BOLD, 20));
				}
				if (time == 4) {
					this.setForeground(Color.red);
					this.setFont(new Font("Tahoma", Font.BOLD, 30));
				}
				if (time == 3) {
					this.setForeground(Color.red);
					this.setFont(new Font("Tahoma", Font.BOLD, 40));
				}
				if (time == 2) {
					this.setForeground(Color.red);
					this.setFont(new Font("Tahoma", Font.BOLD, 50));
				}
				if (time == 1) {
					this.setForeground(Color.red);
					this.setFont(new Font("Tahoma", Font.BOLD, 60));
				}
				if (time == 0) {
					this.setForeground(Color.red);
					this.setFont(new Font("Tahoma", Font.BOLD, 70));
					Random random = new Random();
					int generated = random.nextInt(gameFrame.getGame().getPlayerCount());
					while (generated == gameFrame.getGame().getCurrentLeader()) {
						generated = random.nextInt(gameFrame.getGame().getPlayerCount());
					}
					selectWinner(generated);
				}
			}
		};

		add(lblCountdown);
		lblCountdown.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCountdown.setHorizontalAlignment(SwingConstants.CENTER);
*/
		JLabel lblNewLabel = new JLabel("Select one answer for the question:");
		lblNewLabel.setFont(southPark);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(600, 100));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 1;
		gbc_lblNewLabel.gridwidth = 1;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		this.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblSelectedQuestion = new JLabel("New label");
		lblSelectedQuestion.setPreferredSize(new Dimension(600, 100));
		lblSelectedQuestion.setFont(southPark);
		lblSelectedQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSelectedQuestion = new GridBagConstraints();
		gbc_lblSelectedQuestion.gridwidth = 1;
		gbc_lblSelectedQuestion.gridheight = 1;
		gbc_lblSelectedQuestion.gridx = 0;
		gbc_lblSelectedQuestion.gridy = 1;
		this.add(lblSelectedQuestion, gbc_lblSelectedQuestion);
		lblSelectedQuestion.setText("<html>" + gameFrame.getGame().getCurrentQuestion().getText() + "</html>");

		List<PlayerButton> buttons = new ArrayList<PlayerButton>();
		for (Player player : gameFrame.getGame().getPlayers()) {
			buttons.add(new PlayerButton(player, this));
		}

		buttons = shuffle(buttons);

		int gridy = 2;
		for (PlayerButton button : buttons) {
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setFont(southPark);
			button.setContentAreaFilled(false);
			button.setOpaque(false);
			button.setBorderPainted(false);
			button.setForeground(Color.WHITE);
			button.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					// btnQuestion2.setBackground(Color.YELLOW);
					button.setForeground(Color.YELLOW);

					try {
						Game.playSound(file, 100);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				@Override
				public void mouseExited(java.awt.event.MouseEvent evt) {
					// btnQuestion2.setBackground(UIManager.getColor("control"));
					button.setForeground(Color.WHITE);
				}
			});
			if (gameFrame.getGame().getCurrentLeader() != gameFrame.getGame().getPlayers()
					.indexOf(button.getPlayer())) {
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// lblCountdown.stop();
						try {
							Game.playSound(pop, 100);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				button.setPreferredSize(new Dimension(600, 100));
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.gridheight = 1;
				gbc_button.gridwidth = 1;
				gbc_button.gridx = 0;
				gbc_button.gridy = gridy++;
				this.add(button, gbc_button);
			}
		}
	}
}