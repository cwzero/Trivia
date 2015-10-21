package trivia.gui;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile {
	// ---------------------------------------------------------------------//
	// Eric, I wasn't sure if a new class was needed or not, I will talk to you
	// about it when we meet next
	// ---------------------------------------------------------------------//

	private String path;

	public ReadFile(String file_path) {
		path = file_path;

	}

	// counts how many lines are in the .txt file//
	int readLines() throws IOException {
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);

		String aLine;
		int numberOfLines = 0;
		while ((aLine = bf.readLine()) != null) {
			numberOfLines++;
		}
		bf.close();
		return numberOfLines;
	}

	// open file and return a string array of the questions
	public String[] OpenFile() throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];

		int i;

		for (i = 0; i < numberOfLines; i++) {
			textData[i] = textReader.readLine();
		}
		textReader.close();
		return textData;
	}
}
