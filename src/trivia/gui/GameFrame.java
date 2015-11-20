package trivia.gui;

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
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
