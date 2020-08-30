package client;

import client.service.ClientService;

import java.io.IOException;

public class ClientChatApp {

    public static void main(String[] args) throws IOException {
        ClientService clientService = new ClientService();
        clientService.startGraphic();
    }
}