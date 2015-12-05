package trivia.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {
	private ServerSocket serverSocket;
	private boolean running = false;
	private List<ServerIOThread> clients;
	
	public ServerThread() {
		try {
			serverSocket = new ServerSocket(8190);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clients = new ArrayList<ServerIOThread>();
	}
	
	@Override
	public void run() {
		running = true;
		while(running) {
			try {
				Socket clientSocket = serverSocket.accept();
				ServerIOThread clientThread = new ServerIOThread(clientSocket);
				clientThread.start();
				clients.add(clientThread);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void requestStop() {
		for (ServerIOThread thread: clients) {
			thread.requestStop();
		}
		running = false;
	}
}
