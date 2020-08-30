package server.inter;

import server.handler.ClientHandler;
import server.service.AuthServiceImpl;

public interface Server {

    int PORT = 2101;

    boolean isNickBusy(String Nick);

    void broadcastClientList();

    void broadcastMsg(String msg);

    void subcribe(ClientHandler client);

    void unsubcribe(ClientHandler client);

    void sndMsgToClient(ClientHandler from, String to, String msg);

    AuthServiceImpl getAuthService();
}