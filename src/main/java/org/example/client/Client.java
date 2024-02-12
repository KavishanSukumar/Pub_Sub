package org.example.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter IP Address: ");
        String serverIP = scanner.nextLine();
        System.out.print("Enter Port: ");
        int serverPort = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter User Type: ");
        String userType = scanner.nextLine();
        System.out.print("Enter topic/subject: ");
        String topic = scanner.nextLine();

        if (userType.equalsIgnoreCase("PUBLISHER")) {
            System.out.println("You are in Publisher mode. Type messages to be broadcasted to subscribers.");
        } else if (userType.equalsIgnoreCase("SUBSCRIBER")) {
            System.out.println("You are in Subscriber mode. You will receive messages from the server.");
        } else {
            System.out.println("Invalid client type. Please use 'PUBLISHER' or 'SUBSCRIBER'.");
            return;
        }

        try (Socket socket = new Socket(serverIP, serverPort);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                System.out.println("Connected to the server. Type 'terminate' to exit.");
                String userInputLine;
                writer.println(userType);
                writer.println(topic);


                if (userType.equalsIgnoreCase("PUBLISHER")){
                    System.out.print("Message:");
                    while ((userInputLine = userInput.readLine()) != null) {
                        System.out.print("Message:");
                        writer.println(userInputLine);
                        if (userInputLine.equals("terminate")) {
                            System.out.println("Disconnected from the server.");
                            break;
                        }
                    }
                }

                if (userType.equalsIgnoreCase("SUBSCRIBER")){
                    BufferedReader reader = null;
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = null;
                    while((line = reader.readLine()) != null){
                        System.out.println("Received from server: " + line);
                    }
                    reader.close();
                }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
