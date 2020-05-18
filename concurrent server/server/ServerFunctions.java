import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerFunctions {
	static DataInputStream clientInput;
	static DataOutputStream clientOutput;

	public static void main(String[] args) throws IOException {
		ServerSocket server;
		Socket client;

		System.out.println("Please input port number");
		Scanner portno = new Scanner(System.in);
		int portNo = portno.nextInt();

		try {
			for(;;) {
				server = new ServerSocket(portNo);

				System.out.print("Connecting...");
				client = server.accept();
				ServerThread host = new ServerThread(client);
				host.begin();
				server.close();
			}

		} catch (Exception e) {
				e.printStackTrace();
			
		}
	}
}
