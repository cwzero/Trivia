package trivia;

public class Game {
	private int playerCount = 0;
	private int roundCount = 0;
	private int currentRound = 0;
	private int currentLeader = 0;
	private int currentPlayer = 0;
	private String currentQuestion = "";
	private String[] playerNames = null;
	private int[] playerScore = null;
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
}
