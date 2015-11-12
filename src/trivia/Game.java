<<<<<<< Upstream, based on branch 'audio' of https://github.com/cwzero/Trivia.git
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
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

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

	// The currently selected question //
	private String currentQuestion = "";

	// The possible questions //
	private List<String> questionPool = null;

	// The names of the players //
	private String[] playerNames = null;

	// Players' scores //
	private int[] playerScore = null;

	// The answers entered by the players this round //
	private String[] playerAnswers = null;

	// The winner of the round //
	private int roundWinner = -1;

	public Game() {
		this(0, 0);
	}

	public Game(int roundCount, int playerCount) {
		this.roundCount = roundCount;
		this.playerCount = playerCount;
	}
	
	public void resetNames() {
		playerNames = new String[playerCount];
	}

	public boolean isOver() {
		return currentRound >= roundCount - 1;
	}

	public void nextRound() {
		currentRound++;
		currentQuestion = "";
		playerAnswers = new String[playerCount];
		chooseLeader();
		currentPlayer = 0;
	}

	public void start() {
		playerScore = new int[playerCount];
		playerAnswers = new String[playerCount];
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
		questionPool = new ArrayList<String>();
		while (input.hasNext())
			questionPool.add(input.nextLine());
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
		playerNames[player] = name;
	}

	public void setPlayerAnswer(int player, String answer) {
		playerAnswers[player] = answer;
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
		playerNames = new String[playerCount];
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

	public void setCurrentLeader(int currentLeader) {
		this.currentLeader = currentLeader;
	}

	public String getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(String currentQuestion) {
		this.currentQuestion = currentQuestion;
		questionPool.remove(currentQuestion);
		if (questionPool.isEmpty()) {
			loadQuestions();
		}
	}

	public String[] getPlayerNames() {
		return playerNames;
	}

	public void setPlayerNames(String[] playerNames) {
		this.playerNames = playerNames;
	}

	public int[] getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int index, int playerScore) {
		this.playerScore[index] = playerScore;
	}

	public String[] getPlayerAnswers() {
		return playerAnswers;
	}

	public void setPlayerAnswers(String[] playerAnswers) {
		this.playerAnswers = playerAnswers;
	}

	public String[] getQuestionPool(int count) {
		String[] questions = new String[count];
		for (int index = 0; index < count; index++) {
			questions[index] = getRandomQuestion(questions);
		}
		return questions;
	}

	private String getRandomQuestion(String[] currentQuestions) {
		String question = questionPool.get(new Random().nextInt(questionPool.size()));
		for (String prev : currentQuestions) {
			if (question.equals(prev)) {
				return getRandomQuestion(currentQuestions);
			}
		}
		return question;
	}

	public String[] getQuestionPool() {
		return questionPool.toArray(new String[questionPool.size()]);
	}

	public void setQuestionPool(String[] questionPool) {
		this.questionPool = new ArrayList<String>(Arrays.asList(questionPool));
	}

	public String getQuestion(int questionIndex) {
		return this.questionPool.get(questionIndex);
	}

	public int getRoundWinner() {
		return roundWinner;
	}

	public void setRoundWinner(int roundWinner) {
		this.roundWinner = roundWinner;
	}

	public int getGameWinner() {
		int maxScore = -1;
		int winner = -1;
		for (int player = 0; player < playerCount; player++) {
			if (playerScore[player] > maxScore) {
				maxScore = playerScore[player];
				winner = player;
			}
		}
		return winner;
	}

	public static void playSound(File soundFile, long length) throws IOException {
		InputStream is = new FileInputStream(soundFile);
		AudioStream as = new AudioStream(is);
		AudioData data = as.getData();
		ContinuousAudioDataStream cas = new ContinuousAudioDataStream(data);
		Thread audioThread = new Thread() {
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
	}
}
=======
package trivia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import trivia.db.TriviaDatabase;

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
		TriviaDatabase.init();
		questionPool = new ArrayList<Question>(TriviaDatabase.getQuestions());
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
		for (Player player : players) {
			if (player.getScore() > maxScore && !player.equals(winner)) {
				maxScore = player.getScore();
				winner = player;
			}
		}
		return winner;
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
>>>>>>> 1bc6258 Added count down timer to the AnswerEntryPanel. 
