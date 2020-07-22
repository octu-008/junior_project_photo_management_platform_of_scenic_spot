package com.pmposs.model;


import java.io.Serializable;

public class User implements Serializable {
    private String user_account;
    private String user_pwd;
    private int user_type;
    private double user_rest;

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public double getUser_rest() {
        return user_rest;
    }

    public void setUser_rest(double user_rest) {
        this.user_rest = user_rest;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_account='" + user_account + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_type=" + user_type +
                ", user_rest=" + user_rest +
                '}';
    }
}
