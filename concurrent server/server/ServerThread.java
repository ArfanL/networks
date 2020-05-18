import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ServerThread extends Thread {
	private Socket client;
	DataInputStream clientInput;
	DataOutputStream clientOutput;
	BufferedReader in;
	String hostChoice;

	public ServerThread(Socket socket) {
		client = socket;
	}

	public void begin() {

		try {
			clientInput = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			clientOutput = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		in = new BufferedReader(new InputStreamReader(clientInput));
		try {
			hostChoice = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(hostChoice);
		selection(hostChoice.trim());
		try {
			clientOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			clientInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (hostChoice.equals("Input 7")) {
			System.exit(0);
		}
	}

	public void selection(String menuSelect) {
		Process process;
		String out;
		BufferedReader in;
		try {
			switch (menuSelect) {

			case "Input 1":
				process = Runtime.getRuntime().exec("date");
				in = new BufferedReader(new InputStreamReader(process.getInputStream()));
				while ((out = in.readLine()) != null)
					clientOutput.writeChars(out);
				break;
			case "Input 2":
				process = Runtime.getRuntime().exec("uptime");
				in = new BufferedReader(new InputStreamReader(process.getInputStream()));
				while ((out = in.readLine()) != null)
					clientOutput.writeChars(out);
				break;
			case "Input 3":
				process = Runtime.getRuntime().exec("free");
				in = new BufferedReader(new InputStreamReader(process.getInputStream()));

				while ((out = in.readLine()) != null)
					clientOutput.writeChars(out);
				break;
			case "Input 4":
				process = Runtime.getRuntime().exec("netstat");
				in = new BufferedReader(new InputStreamReader(process.getInputStream()));

				while ((out = in.readLine()) != null)
					clientOutput.writeChars(out);
				break;
			case "Input 5":
				process = Runtime.getRuntime().exec("who");
				in = new BufferedReader(new InputStreamReader(process.getInputStream()));

				while ((out = in.readLine()) != null)
					clientOutput.writeChars(out);
				break;
			case "Input 6":
				process = Runtime.getRuntime().exec("ps -e");
				in = new BufferedReader(new InputStreamReader(process.getInputStream()));

				while ((out = in.readLine()) != null)
					clientOutput.writeChars(out);
				break;
			default:
				System.out.println("Invalid input.");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
