package com.pmposs.model;


import java.io.Serializable;
import java.util.Date;

public class Photo implements Serializable {
    private String pho_name;
    private String pho_path;
    private Date pho_date;
    private String pho_user;
    private String pho_spot;
    private String pho_style;
    private String pho_store;
    private int pho_mark;

    public String getPho_name() {
        return pho_name;
    }

    public void setPho_name(String pho_name) {
        this.pho_name = pho_name;
    }

    public String getPho_path() {
        return pho_path;
    }

    public void setPho_path(String pho_path) {
        this.pho_path = pho_path;
    }

    public Date getPho_date() {
        return pho_date;
    }

    public void setPho_date(Date pho_date) {
        this.pho_date = pho_date;
    }

    public String getPho_user() {
        return pho_user;
    }

    public void setPho_user(String pho_user) {
        this.pho_user = pho_user;
    }

    public String getPho_spot() {
        return pho_spot;
    }

    public void setPho_spot(String pho_spot) {
        this.pho_spot = pho_spot;
    }

    public String getPho_style() {
        return pho_style;
    }

    public void setPho_style(String pho_style) {
        this.pho_style = pho_style;
    }

    public String getPho_store() {
        return pho_store;
    }

    public void setPho_store(String pho_store) {
        this.pho_store = pho_store;
    }

    public int getPho_mark() {
        return pho_mark;
    }

    public void setPho_mark(int pho_mark) {
        this.pho_mark = pho_mark;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "pho_name='" + pho_name + '\'' +
                ", pho_path='" + pho_path + '\'' +
                ", pho_date=" + pho_date +
                ", pho_user='" + pho_user + '\'' +
                ", pho_spot='" + pho_spot + '\'' +
                ", pho_style='" + pho_style + '\'' +
                ", pho_store='" + pho_store + '\'' +
                ", pho_mark=" + pho_mark +
                '}';
    }
}
