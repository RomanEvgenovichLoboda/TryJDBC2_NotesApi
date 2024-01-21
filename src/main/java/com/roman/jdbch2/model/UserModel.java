package com.roman.jdbch2.model;

public class UserModel {
    private long id;
    private String login;
    private String password;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public UserModel(long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }
    @Override
    public String toString() {
        return String.format("User[Id = %d, Login = '%s', Password = '%s']",id,login,password);
    }
}
