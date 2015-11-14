package trivia.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import trivia.Game;
import trivia.Player;

@SuppressWarnings("serial")
public class WinnerSelectPanel extends GamePanel {
	protected int[] playerScore;
	protected int increment = 20;
	
	File file = new File("timer.wav");

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
		
	
		AudioStream theme = null;
		try
        {
            InputStream a = new FileInputStream ("timer.wav");
            theme = new AudioStream (a);          
          
        }
        catch (java.io.IOException z)  //catching the exception
        {
        }
        // Play audio.
        AudioPlayer.player.start (theme);


		CountdownLabel lblCountdown = new CountdownLabel(11) {

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
				this.add(button);
		}
	}
}