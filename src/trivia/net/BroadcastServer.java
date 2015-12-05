package trivia.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class BroadcastServer extends Thread {
	private DatagramSocket serverSocket;
	private boolean running = false;

	@Override
	public void run() {
		try {
			serverSocket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
			serverSocket.setBroadcast(true);

			running = true;
			while (running) {
				byte[] buffer = new byte[2048];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				try {
					serverSocket.receive(packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String message = new String(packet.getData()).trim();

				if (message.equals("CONNECT TRIVIA VERSION 0")) {
					byte[] response = "SERVER TRIVIA VERSION 0".getBytes();
					DatagramPacket responsePacket = new DatagramPacket(response, response.length, packet.getAddress(),
							packet.getPort());
					try {
						serverSocket.send(responsePacket);
						// Now the client will connect to us using normal
						// networking
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void requestStop() {
		running = false;
	}
}
