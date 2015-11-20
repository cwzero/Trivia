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

	private static String host = "bitweb3.nwtc.edu";
	private static int port = 1433;
	private static String db = "dbdev128";

	private static String user = "dbdev128";
	private static String pass = "123456";

	private static List<Question> questions;

	public static void init() {
		if (!initialized) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Statement statement = null;
			ResultSet resultSet = null;
			questions = new ArrayList<Question>();

			try {
				connection = DriverManager.getConnection("jdbc:sqlserver://" + host + ":" + port + ";database=" + db + ";user="
						+ user + ";password=" + pass + ";");
				statement = connection.createStatement();
				resultSet = statement.executeQuery("SELECT question_text FROM question;");
				while (resultSet.next()) {
					questions.add(new Question(resultSet.getString("question_text")));
				}
				statement.close();
				connection.close();
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

	public static void addQuestions() {

	}
}
