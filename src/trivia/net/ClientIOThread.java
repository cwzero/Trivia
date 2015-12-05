package trivia.net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientIOThread extends Thread {
	private boolean running = false;
	private Scanner input;
	private PrintStream output;

	public ClientIOThread(Socket clientSocket) {
		try {
			input = new Scanner(clientSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			output = new PrintStream(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String serverHello = input.nextLine().trim();
		
		if (serverHello.equals("SERVER TRIVIA VERSION 0")) {
			System.out.println("Connected to server.");
		}
	}
}
