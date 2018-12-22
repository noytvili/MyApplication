package com.example.doriyaspielman.myapplication;

public class User {

    private String user_name;
    private String email;
    private String password;
    private boolean maneger;

    public User(){

    }

    public User(String user_name, String email, String password, boolean maneger) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.maneger = maneger;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManeger() {
        return maneger;
    }

    public void setManeger(boolean maneger) {
        this.maneger = maneger;
    }
}
