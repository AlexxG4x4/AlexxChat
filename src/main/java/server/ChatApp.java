package server;

import server.service.ServerImpl;
import java.io.IOException;

public class ChatApp {

    public static void main(String[] args) throws IOException {
        new ServerImpl();
    }
}