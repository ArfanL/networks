import java.io.DataOutputStream;
import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Client {
	static String host;
	static int hostChoice, portNo;
	static DataOutputStream clientOutput;
	public static void main(String[] args) throws InterruptedException {
		portNo = Integer.parseInt(args[1]);
		host = args[0];

		for(;;) {
			requests();
			System.out.println("How many requests would you like to generate?");

			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			int input = scan.nextInt();
			Thread[] threads = new Thread[input];
			threads(input, threads);
			int i = 0;
			while(i < input){
				threads[i].join();
						i++;
			}
		}
	}

	
	public static void requests() {
		System.out.println("1. Date and Time\n" + "2. Uptime\n" + "3. Memory Usage\n" + "4. Netstat\n"
				+ "5. Current Users\n" + "6. Running Processes\n");
		System.out.println("Choose a function (1-6):\nOr press ctrl+c to exit");
		@SuppressWarnings("resource")
		Scanner choice = new Scanner(System.in);
		while(!choice.hasNextInt())
		{
			System.out.println("Choice must be an integer between 1-6.");
		        choice.next();
		}
		hostChoice = choice.nextInt();
		
		

	}

	public static void threads(int ticks, Thread[] threads) {
		int i = 0;
				while(i < threads.length){
					threads[i] = new ClientFunctions(host, portNo, hostChoice);
					i++;
		}
		int j = 0;
				while(j < threads.length){
					threads[j].start();
					j++;
				}
	}

}
