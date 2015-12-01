package trivia.gui;

import java.io.File;
import java.io.IOException;

import trivia.Game;
import trivia.Question;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class QuestionSelectPanel extends GamePanel {
	private Question[] questions = new Question[3];
	private JButton btnQuestion1;
	private JButton btnQuestion2;
	private JButton btnQuestion3;

	public QuestionSelectPanel() {
		createGui();
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */

	public QuestionSelectPanel(GameFrame gameFrame) {
		super(gameFrame);
		createGui();
		gameFrame.setVisible(true);
	}

	public void selectQuestion(int question) {
		gameFrame.getGame().setCurrentPlayer(0);
		gameFrame.getGame().setCurrentQuestion(questions[question]);
		new AnswerEntryPanel(gameFrame);
	}

	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/backgroundgif.gif"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	File file = new File("click7.au");
	File pop = new File("pop.au");
	File music = new File("answerquestionmusic.wav");

	@Override
	protected void createGui() {
		this.setLayout(null);

		try {
			Game.playSound(music, (120000));

		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();

		}

		// called the new method openfile
		// returns an array into questions
		questions = gameFrame.getGame().getQuestionPool(3);

		// switched button labels to items in the questions array

		// Rather than using separate buttons for each question, maybe a label
		// and radio button, with one button to move on

		// 11/4 changed button size from 31 to 40
		// added set font to size 12 to make sure question fits on buttons

		btnQuestion1 = new JButton(questions[0].getText());
		btnQuestion1.setFont(southPark);

		btnQuestion1.setOpaque(false);
		btnQuestion1.setContentAreaFilled(false);
		btnQuestion1.setBorderPainted(false);
		btnQuestion1.setForeground(Color.WHITE);

		// mouse over and exit
		btnQuestion1.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// btnQuestion1.setBackground(Color.YELLOW);
				btnQuestion1.setForeground(Color.YELLOW);

				try {
					Game.playSound(file, 100);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				// btnQuestion1.setBackground(UIManager.getColor("control"));
				btnQuestion1.setForeground(Color.WHITE);
			}
		});
		// pop file

		btnQuestion1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuestionSelectPanel.this.selectQuestion(0);

				// pop noise on click
				try {
					Game.playSound(pop, 100);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnQuestion1.setBounds(((gameFrame.getWidth() / 2) - 400), (gameFrame.getHeight() / 2) - 100, 800, 75);
		this.add(btnQuestion1);

		btnQuestion2 = new JButton(questions[1].getText());
		btnQuestion2.setFont(southPark);
		btnQuestion2.setOpaque(false);
		btnQuestion2.setContentAreaFilled(false);
		btnQuestion2.setBorderPainted(false);
		btnQuestion2.setForeground(Color.WHITE);
		// mouse over and exit
		btnQuestion2.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// btnQuestion2.setBackground(Color.YELLOW);
				btnQuestion2.setForeground(Color.YELLOW);

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
				btnQuestion2.setForeground(Color.WHITE);
			}
		});

		btnQuestion2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuestionSelectPanel.this.selectQuestion(1);
				// pop noise on click
				try {
					Game.playSound(pop, 100);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnQuestion2.setBounds(btnQuestion1.getX(), btnQuestion1.getY() + 75, 800, 75);
		this.add(btnQuestion2);

		btnQuestion3 = new JButton(questions[2].getText());
		btnQuestion3.setFont(southPark);
		btnQuestion3.setOpaque(false);
		btnQuestion3.setContentAreaFilled(false);
		btnQuestion3.setBorderPainted(false);
		btnQuestion3.setForeground(Color.WHITE);
		// mouse over and exit
		btnQuestion3.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// btnQuestion3.setBackground(Color.YELLOW);
				btnQuestion3.setForeground(Color.YELLOW);

				try {
					Game.playSound(file, 100);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				// btnQuestion3.setBackground(UIManager.getColor("control"));
				btnQuestion3.setForeground(Color.WHITE);
			}
		});

		btnQuestion3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QuestionSelectPanel.this.selectQuestion(2);
				// pop noise on click
				try {
					Game.playSound(pop, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnQuestion3.setBounds(btnQuestion2.getX(), btnQuestion2.getY() + 75, 800, 75);
		this.add(btnQuestion3);

		JLabel lblPlayerPleaseSelect = new JLabel(
				gameFrame.getGame().getPlayer(gameFrame.getGame().getCurrentLeader()).getName()
						+ ", please select question:");
		lblPlayerPleaseSelect.setFont(southPark);
		lblPlayerPleaseSelect.setBounds(((gameFrame.getWidth() / 2) - 320), (gameFrame.getHeight() / 2) - 200, 800, 75);
		lblPlayerPleaseSelect.setForeground(Color.BLACK);

		this.add(lblPlayerPleaseSelect);

	}
}
