package trivia.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import trivia.Question;

public class TriviaDatabase {
	private static boolean initialized = false;
	private static Connection connection = null;
	
	private static String host = "trivia.c8jug9wmu3by.us-east-1.rds.amazonaws.com";
	private static int port = 3306;
	private static String db = "Trivia";
	
	private static String user = "trivia";
	private static String pass = "trivia";
	
	private static List<Question> questions;

	public static void init() {
		if (!initialized) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			Statement statement = null;
			ResultSet resultSet = null;
			questions = new ArrayList<Question>();

			try {
				connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pass);
				statement = connection.createStatement();
				resultSet = statement.executeQuery("SELECT Question_Text FROM Question;");
				while (resultSet.next()) {
					questions.add(new Question(resultSet.getString("Question_Text")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			initialized = true;
		}
	}

	public static List<Question> getQuestions() {
		return questions;
	}
}
