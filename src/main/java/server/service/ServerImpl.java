package server.service;

import server.handler.ClientHandler;
import server.inter.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ServerImpl implements Server {

    private List<ClientHandler> clients;
    private AuthServiceImpl authService;

    public ServerImpl() throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            authService = new AuthServiceImpl();
            authService.start();
            clients = new LinkedList<>();
            while (true) {
                System.out.println("Ожидание подключения..");
                Socket socket = serverSocket.accept();
                System.out.println("Клиент подлючился!");
                new ClientHandler(this, socket);

            }
        } catch (IOException e) {
            System.out.println("Проблемы на сервере");
        } finally {
            if (authService!=null) {
                authService.stop();
            }
        }
    }

    @Override
    public boolean isNickBusy(String nick) {
        for (ClientHandler c : clients) {
            if (c.getNick()!=null && (c.getNick().equals(nick))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void broadcastClientList() {
        StringBuilder builder = new StringBuilder();
        for (ClientHandler c:clients) {
            builder.append(c.getNick() + " ");
        }
        broadcastMsg(builder.toString());
    }

    @Override
    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }

    }

    @Override
    public synchronized void subcribe(ClientHandler client) {
        clients.add(client);
    }

    @Override
    public synchronized void unsubcribe(ClientHandler client) {
        clients.remove(client);
    }

    @Override
    public synchronized void sndMsgToClient(ClientHandler from, String to, String msg) {
        for (ClientHandler c : clients) {
            if (c.getNick().equals(to)) {
                c.sendMsg("from " + from.getNick()+ ":" + msg);
                from.sendMsg("client " + to + ": " + msg);
            }
        }
    }

    @Override
    public AuthServiceImpl getAuthService() {
        return authService;
    }
}