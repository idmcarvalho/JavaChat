package network;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(90000);
		System.out.println("Door 9000 is open, waiting for client connection...");

		Socket client = server.accept();
		System.out.println("Client connection" + client.getInetAddress().getHostAddress());

		Scanner s = new Scanner(client.getInputStream());
		while(s.hasNextLine()) {
			System.out.println(s.nextLine());
		}
		
		s.close();
		client.close();
		server.close();

	}
}


