package com.example.demo.models;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private String name;
    private String login;
    private String password;
    private String salt = BCrypt.gensalt();

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password= BCrypt.hashpw(password,salt);
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password,salt);
    }
}
