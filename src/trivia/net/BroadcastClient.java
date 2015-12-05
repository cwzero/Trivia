package trivia.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class BroadcastClient extends Thread {
	private DatagramSocket clientSocket;
	
	@Override
	public void run() {
		try {
			clientSocket = new DatagramSocket();
			clientSocket.setBroadcast(true);

			byte[] message = "CONNECT TRIVIA VERSION 0".getBytes();

			try {
				DatagramPacket sendPacket = new DatagramPacket(message, message.length,
						InetAddress.getByName("255.255.255.255"), 8888);
				try {
					clientSocket.send(sendPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			byte[] reply = new byte[2048];

			DatagramPacket packet = new DatagramPacket(reply, reply.length);
			try {
				clientSocket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String response = new String(packet.getData()).trim();
			if (response.equals("SERVER TRIVIA VERSION 0")) {
				try {
					Socket clientSocket = new Socket(packet.getAddress(), packet.getPort());
					new ClientIOThread(clientSocket).start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
