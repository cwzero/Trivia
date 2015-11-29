package trivia.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import trivia.Player;

@SuppressWarnings("serial")
public class WinnerSelectPanel extends GamePanel {
	protected int[] playerScore;
	protected int increment = 20;
	protected CountdownLabel lblCountdown;
	
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

	private void selectWinner(int winner) {
		gameFrame.getGame().setRoundWinner(winner);
		gameFrame.getGame().setPlayerScore(winner, gameFrame.getGame().getPlayer(winner).getScore() + 1);
		new GameStatusPanel(gameFrame);
	}

	public void selectWinner(Player winner) {
		winner.setWinner(true);
		winner.setScore(winner.getScore() + 1);
		new GameStatusPanel(gameFrame);
	}

	@Override
	protected void createGui() {
		this.setLayout(new GridLayout(0, 1, 0, 0));
		this.setBorder(new EmptyBorder(25, 25, 25, 25));

		lblCountdown = new CountdownLabel(15) {

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

		JLabel lblNewLabel = new JLabel("Select one answer for the question:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblNewLabel);

		JLabel lblSelectedQuestion = new JLabel("New label");
		lblSelectedQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSelectedQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblSelectedQuestion);
		lblSelectedQuestion.setText(gameFrame.getGame().getCurrentQuestion().getText());

		List<PlayerButton> buttons = new ArrayList<PlayerButton>();
		for (Player player : gameFrame.getGame().getPlayers()) {
			buttons.add(new PlayerButton(player, this));
		}

		buttons = shuffle(buttons);

		for (PlayerButton button : buttons) {
			button.setHorizontalAlignment(SwingConstants.CENTER);
			if (gameFrame.getGame().getCurrentLeader() != gameFrame.getGame().getPlayers().indexOf(button.getPlayer()))
			{
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						lblCountdown.stop();
					}
				});
				this.add(button);
			}
		}
	}
}