package org.example.server.bean;

import org.example.server.handlers.ClientHandler;

public class ClientBean {
    private ClientHandler clientHandler;
    private String type;
    private  String topic;

    public ClientBean(ClientHandler clientHandler, String type, String topic) {
        this.clientHandler = clientHandler;
        this.type = type;
        this.topic = topic;
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "ClientBean{" +
                "clientHandler=" + clientHandler +
                ", type='" + type + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
