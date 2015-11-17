package trivia.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import trivia.Player;

public class PlayerButton extends JButton {
	private static final long serialVersionUID = 1L;
	private Player player;
	
	public PlayerButton(final Player player, final WinnerSelectPanel panel) {
		this.setPlayer(player);
		if (player.getAnswer() == null || player.getAnswer().equals(""))
			setVisible(false);
		setText(player.getAnswer());
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.selectWinner(player);
			}
		});
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
