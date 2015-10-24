package trivia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

	public Game(int roundCount, int playerCount) {
		this.roundCount = roundCount;
		this.playerCount = playerCount;
		playerNames = new String[playerCount];
		playerAnswers = new String[playerCount];
		loadQuestions();
		chooseLeader();
	}

	public boolean isOver() {
		return currentRound < roundCount;
	}

	public void nextRound() {
		currentRound++;
		currentQuestion = "";
		playerAnswers = new String[playerCount];
		chooseLeader();
		currentPlayer = 0;
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

	public void setPlayerScore(int[] playerScore) {
		this.playerScore = playerScore;
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
		String question = questionPool
				.get(new Random().nextInt(questionPool.size()));
		for (String prev: currentQuestions) {
			if (question.equals(prev)) {
				return getRandomQuestion(currentQuestions);
			}
		}
		return question;
	}

	private String getRandomQuestion(String[] currentQuestions) {
		String question = questionPool
				.get(new Random().nextInt(questionPool.size()));
		for (String prev: currentQuestions) {
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
}
