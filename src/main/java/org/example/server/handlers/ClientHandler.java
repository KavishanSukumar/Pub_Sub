package org.example.server.handlers;

import org.example.server.bean.ClientBean;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private static final ArrayList<ClientBean> subscribers = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String type = reader.readLine();
            String topic = reader.readLine();
            ClientBean clientBean=new ClientBean(this,type,topic);
            subscribers.add(clientBean);
            System.out.println(subscribers);

            String line =null;
            while ((line = reader.readLine()) != null){
                System.out.println("Client: " + line);
                if (line.equals("terminate")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                broadcast(line,topic);
            };
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Broadcast a message to all subscribers
    private void broadcast(String message, String topic) throws IOException {
        for (ClientBean subscriber : subscribers) {
            if (subscriber.getClientHandler() != this && subscriber.getType().equalsIgnoreCase("SUBSCRIBER") && subscriber.getTopic().equalsIgnoreCase(topic)) {
                subscriber.getClientHandler().sendMessage(message);
            }
        }
    }

    // Send a message to this client
    private void sendMessage(String message) throws IOException {
        System.out.println("this is send message");
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        writer.println(message);
    }
}
