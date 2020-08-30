package server.service;

import server.inter.AuthService;

import java.util.LinkedList;
import java.util.List;

public class AuthServiceImpl implements AuthService {

    private List<UserEntity> userList;

    public AuthServiceImpl() {
        this.userList = new LinkedList<>();
        this.userList.add(new UserEntity("nick1","login1", "passw1"));
        this.userList.add(new UserEntity("nick2","login2", "passw2"));
        this.userList.add(new UserEntity("nick3","login3", "passw3"));
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public String getNick(String login, String password) {
        for (UserEntity user : userList) {
            if(user.login.equals(login) && user.password.equals(password)) {
                return user.nick;
            }
        }
        return null;
    }

    @Override
    public void stop() {

    }

    private class UserEntity{

        private String nick;
        private String login;
        private String password;

        public UserEntity(String nick, String login, String password) {
            this.nick = nick;
            this.login = login;
            this.password = password;
        }
    }
}