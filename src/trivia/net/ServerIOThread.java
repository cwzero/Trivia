package trivia.net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerIOThread extends Thread {
	private boolean running = false;
	private Scanner input;
	private PrintStream output;
	
	public ServerIOThread(Socket clientSocket) {
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
		running = true;
		while (running) {
			output.println("SERVER TRIVIA VERSION 0");
		}
		
		input.close();
		output.close();
	}
	
	public void requestStop() {
		this.running = false;
	}
}
