package trivia.gui;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import trivia.Game;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




@SuppressWarnings("serial")
public class QuestionSelectFrame extends JFrame {

	private JPanel contentPane;
	private Game game;
	//--------------------------------------------------------------------//
	//I know this is messy, I just wanted to get code added and play around with it
	//---------------------------------------------------------------------//
						//where the txt file is located
	File path = new File("Questions.txt");
	
					//counts how many lines are in the .txt file//
		int readLines() throws IOException {
			FileReader file_to_read = new FileReader(path);
			BufferedReader bf = new BufferedReader(file_to_read);
			
			String aLine;
			int numberOfLines = 0;
			while ((aLine =bf.readLine()) != null){
				numberOfLines++;
			}
			bf.close();
			return numberOfLines;
		}
		
						//open file and return a string array of the questions
		public String[] OpenFile()throws IOException{
			FileReader fr = new FileReader(path);
			BufferedReader textReader = new BufferedReader(fr);
			
			int numberOfLines = readLines();
			String[]textData = new String[numberOfLines];
			
			int i;
			
			for(i=0;i<numberOfLines; i++){
				textData[i] = textReader.readLine();
			}
			textReader.close( );
			return textData;
		}
	
		//randomly select leader
		public String chooseLeader(){
			String leader;
			String[]Players=game.getPlayerNames();
			Random rand = new Random();
			int Low = 0;
			int High = game.getPlayerCount();
			
			int randomNum = rand.nextInt(High-Low)+Low;
			leader = Players[randomNum];
			return leader;
		}
		//---------------------------------------------------------------------//
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public QuestionSelectFrame(Game game) throws IOException {
		this.game = game;
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//called the new method openfile 
		//returns an array into questions
		String[]questions = OpenFile();
		//switched button labels to items in the questions array
		
		
		JButton btnQuestion1 = new JButton(questions[0]);
		btnQuestion1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/* the following code will eventually need to pass the question being chosen
				 * the leader will also have to be taken into consideration for whos turn it is
				 * to answer the question
				*/
				//added for ruth
				//--------------------------------------------------------------------------//
				game.setCurrentPlayer(0); //just before
				new AnswerEntryFrame(game).setVisible(true);
				QuestionSelectFrame.this.dispose();
				//--------------------------------------------------------------------------//
			}
		});
		
		
		btnQuestion1.setBounds(86, 92, 369, 31);
		contentPane.add(btnQuestion1);
		
		JButton btnQuestion2 = new JButton(questions[1]);
		btnQuestion2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/* the following code will eventually need to pass the question being chosen
				 * the leader will also have to be taken into consideration for whos turn it is
				 * to answer the question
				*/
				//added for ruth
				//--------------------------------------------------------------------------//
				game.setCurrentPlayer(0); //just before
				new AnswerEntryFrame(game).setVisible(true);
				QuestionSelectFrame.this.dispose();
				//--------------------------------------------------------------------------//
			}
		});
		btnQuestion2.setBounds(86, 142, 369, 31);
		contentPane.add(btnQuestion2);
		
		JButton btnQuestion3 = new JButton(questions[2]);
		btnQuestion3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* the following code will eventually need to pass the question being chosen
				 * the leader will also have to be taken into consideration for whos turn it is
				 * to answer the question
				*/
				//added for ruth
				//--------------------------------------------------------------------------//
				game.setCurrentPlayer(0); //just before
				new AnswerEntryFrame(game).setVisible(true);
				QuestionSelectFrame.this.dispose();
				//--------------------------------------------------------------------------//
			}
		});
		btnQuestion3.setBounds(86, 192, 369, 31);
		contentPane.add(btnQuestion3);
		
		JLabel lblPlayerPleaseSelect = new JLabel(chooseLeader()+", please select question:");
		lblPlayerPleaseSelect.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPlayerPleaseSelect.setBounds(17, 24, 344, 49);
		contentPane.add(lblPlayerPleaseSelect);
	}
}
