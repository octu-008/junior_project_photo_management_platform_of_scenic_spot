package com.pmposs.model;


import java.io.Serializable;

public class Spot implements Serializable {
    private String spot_id;
    private String spot_name;
    private String spot_state;

    public String getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(String spot_id) {
        this.spot_id = spot_id;
    }

    public String getSpot_name() {
        return spot_name;
    }

    public void setSpot_name(String spot_name) {
        this.spot_name = spot_name;
    }

    public String getSpot_state() {
        return spot_state;
    }

    public void setSpot_state(String spot_state) {
        this.spot_state = spot_state;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "spot_id='" + spot_id + '\'' +
                ", spot_name='" + spot_name + '\'' +
                ", spot_state='" + spot_state + '\'' +
                '}';
    }
}
