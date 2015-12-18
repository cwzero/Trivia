 package trivia;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import trivia.gui.GameFrame;

// Version 1.0.0 //
public class Trivia {

	public static void main(String[] args) {
		
		
//enhancement ideas:
		//-if the player presses enter or continue without entering a name, auto name them from an array of names like "lazy", and  "slow"
		//-limit player names to x characters? (5-7)
		//-new system for scoring, give the leader a text field to assign points to winning player(s)?
		//-rethink questions/answers , take trivia questions, but dont supply 3 possible anwsers, let the users "bs" an answer
		//-provide the leader with the answer, but let leader compare answers, select winner(s) assign points based on opinion
		//what u thinkin?
	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GameFrame window = new GameFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
