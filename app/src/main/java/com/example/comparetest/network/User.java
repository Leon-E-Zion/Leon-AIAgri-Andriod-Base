package com.example.comparetest.network;

public class User {
    public class Data{
       public String username;
       public String password;
       public String power;
    }
    public String code;
    public String username;
    public String password;
    public String msg;
    public Data data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
