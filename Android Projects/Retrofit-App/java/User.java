package com.example.retrofit_open_source;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
