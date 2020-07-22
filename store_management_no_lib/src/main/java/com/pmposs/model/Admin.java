package com.pmposs.model;

import java.io.Serializable;

public class Admin implements Serializable {
    private String admin_account;
    private String admin_pwd;
    private String spot_id;
    private String store_id;

    public String getAdmin_account() {
        return admin_account;
    }

    public void setAdmin_account(String admin_account) {
        this.admin_account = admin_account;
    }

    public String getAdmin_pwd() {
        return admin_pwd;
    }

    public void setAdmin_pwd(String admin_pwd) {
        this.admin_pwd = admin_pwd;
    }

    public String getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(String spot_id) {
        this.spot_id = spot_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_account='" + admin_account + '\'' +
                ", admin_pwd='" + admin_pwd + '\'' +
                ", spot_id='" + spot_id + '\'' +
                ", store_id='" + store_id + '\'' +
                '}';
    }
}
