package trivia.gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import trivia.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class SetupSummaryPanel extends GamePanel {
	private JButton buttonNext;
	private JButton buttonBack;
	private static Font customFont ;
	private static Font customFont2 ;
	public SetupSummaryPanel() {
		createGui();
	}

	/**
	 * Create the frame.
	 */
	public SetupSummaryPanel(GameFrame gameFrame) {
		super(gameFrame);
	
		createGui();
		gameFrame.setVisible(true);
	}
	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/images/backgroundgif.gif"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	private void buttonNext_click() {
		gameFrame.getGame().start();

		// Select leader and start game

		// ---------------------------------------------------------------------//
		// added by Brian to test question select frame
		new QuestionSelectPanel(gameFrame);
		// ---------------------------------------------------------------------//
	}
	File pop = new File("pop.au");
	private void buttonBack_click() {
		// Eventually this should go back to edit setup
		new MainMenuPanel(gameFrame);
	}

	@Override
	protected void createGui() {
		
		gameFrame.repaint();
	
		//setBounds(100, 100, 450, 300);
		//setBorder(new EmptyBorder(5, 5, 5, 5));


		
		
		
		

		  try {
	            //create the font to use. Specify the size!
	            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/images/south park.ttf")).deriveFont(30f);
	            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            //register the font
	            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/images/south park.ttf")));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        catch(FontFormatException e)
	        {
	            e.printStackTrace();
	        }
		
		
		

		  try {
	            //create the font to use. Specify the size!
	            customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("src/images/south park.ttf")).deriveFont(40f);
	            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            //register the font
	            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/images/south park.ttf")));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        catch(FontFormatException e)
	        {
	            e.printStackTrace();
	        }
			
			
		
	
		String playerLabel1="";
//label says "Player1, player2, player3 want to play a game of x rounds?"
		for (int playerNumber = 0; playerNumber < gameFrame.getGame().getPlayerCount(); playerNumber++) {
			if(playerNumber != gameFrame.getGame().getPlayerCount()-1){
			playerLabel1 = playerLabel1 + gameFrame.getGame().getPlayer(playerNumber).getName()+", ";
			}
			else{playerLabel1 = playerLabel1 + gameFrame.getGame().getPlayer(playerNumber).getName()+"...";
			}
			
			}
			setLayout(null);
		
	//	playerLabel1 = playerLabel1;
		
			JLabel playerLabel = new JLabel(playerLabel1);
		playerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		playerLabel.setFont(customFont2);
		
		playerLabel.setForeground(Color.WHITE);
		playerLabel.setBounds(((gameFrame.getWidth()/2)-400), (gameFrame.getHeight()/2)-100, 700, 100);
		
		
			
			this.add(playerLabel);
			JLabel secondline  = new JLabel(" Are you sure you want to play a game of " +gameFrame.getGame().getRoundCount()+" rounds?");
			secondline.setHorizontalAlignment(SwingConstants.LEFT);
			secondline.setFont(customFont);
			secondline.setForeground(Color.WHITE);
			secondline.setBounds(playerLabel.getX()-50, playerLabel.getY()+50, 1000, 100);
			this.add(secondline);
		

			//right now, smaller image as startIcon gives it an effect that pushes exitbutton and logo away from startgame
		
			
	
		
		//buttonBack = new JButton("Back to Main Menu");
		
			Icon continueIcon = new ImageIcon("src/images/continue1.png");
			Icon continueHover = new ImageIcon("src/images/continuegif.gif");

			Icon backIcon = new ImageIcon("src/images/back1.png");
			Icon backHover = new ImageIcon("src/images/backgif.gif");
		JButton buttonBack = new JButton(backIcon);
		
		
		buttonBack.setBounds(((gameFrame.getWidth()/2)-400), ((gameFrame.getHeight()/2)+65), backIcon.getIconWidth(), backIcon.getIconHeight());
		
		buttonBack.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				buttonBack.setIcon(backHover);
			
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				buttonBack.setIcon(backIcon);
			}
		});
		buttonBack.setOpaque(false);
		buttonBack.setContentAreaFilled(false);
		buttonBack.setBorderPainted(false);
		
		buttonBack.setFont(customFont);
		add(buttonBack);
		
		Icon startIcon = new ImageIcon("src/images/staticstart.png");
		Icon hoverStart = new ImageIcon("src/images/startgif.gif");
	
		JButton continueButton = new JButton(startIcon);
		
		continueButton.setBounds((buttonBack.getX()+(buttonBack.getWidth())), buttonBack.getY(), continueIcon.getIconWidth(), continueIcon.getIconHeight());;
		//continueButton.setFont(customFont);
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Game.playSound(pop, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				SetupSummaryPanel.this.buttonNext_click();
			}
		});
		//continueButton.setBounds(99, 213, 95, 23);
		continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				continueButton.setIcon(hoverStart);
			
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				continueButton.setIcon(startIcon);
			}
		});
		
		continueButton.setOpaque(false);
		continueButton.setContentAreaFilled(false);
		continueButton.setBorderPainted(false);
		
		
		//newGameButton.setIcon(icon);
		add(continueButton);

		buttonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SetupSummaryPanel.this.buttonBack_click();
			}
		});
	}
}
