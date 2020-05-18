import java.io.*;
import java.net.*;

public class ClientFunctions extends Thread {
	public String host;
	public int hostChoice;
	public int portNo;
	
	public ClientFunctions(String hostID, int port, int hostReq) {
		host = hostID;
		hostChoice = hostReq;
		portNo = port;
	}

	public void start() {
		double turnaroundTime;
		double startTime;
		double endTime;
		Socket clientSocket;

		try {
			startTime = System.currentTimeMillis();
			clientSocket = new Socket(host, portNo);
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


			if(hostChoice == 1){
				System.out.println("Date and Time:");
				out.println("Input 1");
			}
			else if(hostChoice == 2){
				System.out.println("Uptime:");
				out.println("Input 2");
			}
			else if(hostChoice == 3){
				System.out.println("Memory Usage:\n");
				out.println("Input 3");
			}
			else if(hostChoice == 4){
				System.out.println("Netstat:");
				out.println("Input 4");
			}
			else if(hostChoice == 5){
				System.out.println("Current Users:");
				out.println("Input 5");
			}
			else if(hostChoice == 6){
				System.out.println("Running Processes:");
				out.println("Input 6");
			}
			else {
				System.out.println("Invalid input, please input a number between 1 to 6");
			}

			String result;
			String i = "";

			while ((result = input.readLine()) != null && !result.equals("ServerDone")) {
				i = i + result;
			}

			clientSocket.close();
			endTime = System.currentTimeMillis();
			turnaroundTime = endTime - startTime;
			System.out.println(i);
			System.out.println("Client Turnaround Time: " + turnaroundTime);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
