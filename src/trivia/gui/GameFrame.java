package trivia.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import trivia.Game;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private Game game = new Game();

	public GameFrame() {
		this(new Game());
		new StartLogoPanel(this);
		setVisible(true);
	}

	public GameFrame(Game game) {
		this.game = game;
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				Game.stopSounds();
			}
		});
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
