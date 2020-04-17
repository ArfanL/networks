import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class IterativeServer {
    static DataInputStream clientInput;
    static DataOutputStream clientOutput;

    public static void main(String[] args) throws Exception {
        ServerSocket server;
        Socket client;
        String hostChoice;
        System.out.println("Please input port number");
        Scanner portno = new Scanner(System.in);
        int portNo = portno.nextInt();

        BufferedReader input;

        try {
            for(;;) {
                server = new ServerSocket(portNo);
                System.out.println("Connecting...");
                client = server.accept();
                System.out.println("Connected!");
                clientInput = new DataInputStream(client.getInputStream());
                clientOutput = new DataOutputStream(client.getOutputStream());


                input = new BufferedReader(new InputStreamReader(clientInput));
                hostChoice = input.readLine();
                choice(hostChoice.trim());
                clientOutput.close();
                clientInput.close();
                server.close();
                client.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void choice(String choice) {
        Process process;
        String out;
        BufferedReader in;

        try {
            switch (choice) {

                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
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

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
