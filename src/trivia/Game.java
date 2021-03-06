package trivia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
//import trivia.db.TriviaDatabase;

public class Game {
	// The number of players in the game //
	private int playerCount = 0;

	// The number of rounds the game will run for //
	private int roundCount = 0;

	// The current round number //
	private int currentRound = 0;

	// The current leader - index into the playerNames array //
	private int currentLeader = 0;

	// The player currently taking their turn - index into playerNames //
	private int currentPlayer = 0;

	// The time allowed for the player to enter his answer
	private int answerTime = 0;

	// The currently selected question //
	private Question currentQuestion = null;

	// The possible questions //
	private List<Question> questionPool = null;

	// The game's players //
	private List<Player> players = null;
	
	private static List<InputStream> audio = new ArrayList<InputStream>();

	// The winner of the round //
	private int roundWinner = -1;

	public Game() {
		this(0, 0);
	}

	public Game(int roundCount, int playerCount) {
		this.roundCount = roundCount;
		this.playerCount = playerCount;
		reset();
	}

	public void reset() {
		currentPlayer = 0;
		players = new ArrayList<Player>();
	}

	public boolean isOver() {
		return currentRound >= roundCount - 1;
	}

	public void nextRound() {
		currentRound++;
		currentQuestion = null;
		for (Player player : players) {
			player.setAnswer(null);
		}
		chooseLeader();
		currentPlayer = 0;
	}

	public void start() {
		for (Player player : players) {
			player.setScore(0);
			player.setAnswer(null);
		}
		loadQuestions();
		chooseLeader();
	}

	private void loadQuestions() {
		Scanner input = null;
		try {
			input = new Scanner(new File("Questions.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TriviaDatabase.init();
		questionPool = new ArrayList<Question>(/*TriviaDatabase.getQuestions()*/);
		while (input.hasNext()) {
			Question q = new Question(input.nextLine());
			if (!questionPool.contains(q)) {
				questionPool.add(q);
			}
		}
		input.close();
	}

	private void chooseLeader() {
		int prevLeader = currentLeader;
		Random rand = new Random();
		int nextLeader = rand.nextInt(playerCount);
		while (nextLeader == prevLeader) {
			nextLeader = rand.nextInt(playerCount);
		}
		currentLeader = nextLeader;
	}

	public void setPlayerName(int player, String name) {
		players.get(player).setName(name);
	}

	public void setPlayerAnswer(int player, String answer) {
		players.get(player).setAnswer(answer);
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
		players = new ArrayList<Player>(playerCount);
	}

	public int getRoundCount() {
		return roundCount;
	}

	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public int getCurrentLeader() {
		return currentLeader;
	}

	public void setAnswerTime(int time) {
		answerTime = time;
	}

	public int getTimeAnswer() {
		return answerTime;
	}

	public void setCurrentLeader(int currentLeader) {
		this.currentLeader = currentLeader;
	}

	public Question getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(Question question) {
		this.currentQuestion = question;
		questionPool.remove(question);
		if (questionPool.isEmpty()) {
			loadQuestions();
		}
	}

	public void setPlayerScore(int index, int playerScore) {
		players.get(index).setScore(playerScore);
	}

	public Question[] getQuestionPool(int count) {
		Question[] questions = new Question[count];
		for (int index = 0; index < count; index++) {
			questions[index] = getRandomQuestion(questions);
		}
		return questions;
	}

	private Question getRandomQuestion(Question[] currentQuestions) {
		Question question = questionPool.get(new Random().nextInt(questionPool.size()));
		for (Question prev : currentQuestions) {
			if (question.equals(prev)) {
				return getRandomQuestion(currentQuestions);
			}
		}
		return question;
	}

	public String[] getQuestionPool() {
		return questionPool.toArray(new String[questionPool.size()]);
	}

	public void setQuestionPool(Question[] questionPool) {
		this.questionPool = new ArrayList<Question>(Arrays.asList(questionPool));
	}

	public Question getQuestion(int questionIndex) {
		return this.questionPool.get(questionIndex);
	}

	public Player getRoundWinner() {
		for (Player player : players) {
			if (player.isWinner())
				return player;
		}
		return players.get(roundWinner);
	}

	public void setRoundWinner(int roundWinner) {
		this.roundWinner = roundWinner;
	}

	public Player getGameWinner() {
		int maxScore = -1;
		Player winner = null;
		boolean tie = true;
		for (Player player : players) {
			if (player.getScore() > maxScore && !player.equals(winner)) {
				maxScore = player.getScore();
				winner = player;
				tie = false;
			} else if (player.getScore() == maxScore) {
				tie = true;
			}
		}
		if (!tie)
			return winner;
		else
			return null;
	}

	public static ContinuousAudioDataStream playSoundLoop(File soundFile, long length) throws IOException {
		InputStream is = new FileInputStream(soundFile);
		AudioStream as = new AudioStream(is);
		AudioData data = as.getData();
		ContinuousAudioDataStream cas = new ContinuousAudioDataStream(data);
		Thread audioThread = new Thread() {
			@Override
			public void run() {
				AudioPlayer.player.start(cas);
				try {
					Thread.sleep(length);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AudioPlayer.player.stop(cas);
				try {
					as.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		audioThread.start();
		return cas;
	}

	public static ContinuousAudioDataStream playSoundLoop(File soundFile) throws IOException {
		InputStream is = new FileInputStream(soundFile);
		AudioStream as = new AudioStream(is);
		AudioData data = as.getData();
		ContinuousAudioDataStream cas = new ContinuousAudioDataStream(data);
		Thread audioThread = new Thread() {
			@Override
			public void run() {
				AudioPlayer.player.start(cas);
			}
		};
		audioThread.start();
		audio.add(cas);
		return cas;
	}
	
	public static void stopSounds() {
		for (InputStream in: audio) {
			AudioPlayer.player.stop(in);
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public static AudioDataStream playSound(File soundFile, long length) throws IOException {
		InputStream is = new FileInputStream(soundFile);
		AudioStream as = new AudioStream(is);
		AudioData data = as.getData();
		AudioDataStream cas = new AudioDataStream(data);
		Thread audioThread = new Thread() {
			@Override
			public void run() {
				AudioPlayer.player.start(cas);
				try {
					Thread.sleep(length);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AudioPlayer.player.stop(cas);
				try {
					as.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		audioThread.start();
		return cas;
	}
	
	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	  public String choose(File f) throws FileNotFoundException
	  {
	     String result = null;
	     Random rand = new Random();
	     int n = 0;
	     for(Scanner sc = new Scanner(f); sc.hasNext(); )
	     {
	        ++n;
	        String line = sc.nextLine();
	        if(rand.nextInt(n) == 0)
	           result = line;         
	     }

	     return result;      
	  }
	
	

	public Player getPlayer(int index) {
		if (index < playerCount && index >= players.size()) {
			players.add(new Player());
		}
		return players.get(index);
	}

	public List<Player> getPlayers() {
		return players;
	}
}
