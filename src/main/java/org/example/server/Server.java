package org.example.server;


import org.example.server.handlers.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Port: ");
        int port = Integer.parseInt(scanner.nextLine());
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
