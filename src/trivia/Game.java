package trivia;

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
	private String[] questionPool = null;
	
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
	}

	public void setPlayerName(int player, String name) {
		playerNames[player] = name;
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

	public String[] getQuestionPool() {
		return questionPool;
	}

	public void setQuestionPool(String[] questionPool) {
		this.questionPool = questionPool;
	}
	
	public String getQuestion(int questionIndex) {
		return this.questionPool[questionIndex];
	}
}
