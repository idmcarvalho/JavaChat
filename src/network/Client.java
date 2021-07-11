package network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args)throws IOException {
		Socket client = new Socket("127.0.0.1", 90000);
		System.out.println("Client connected!");
		
		Scanner s = new Scanner(System.in);
		PrintStream out = new PrintStream(client.getOutputStream());
		
		while(s.hasNextLine()) {
			out.println(s.nextLine());
		}
		
		out.close();
		s.close();
		client.close();
		
	}

}


