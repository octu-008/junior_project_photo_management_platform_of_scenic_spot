package com.pmposs.model;


import java.io.Serializable;

public class Print_style implements Serializable {
    private String style_id;
    private String style_name;
    private String style_state;
    private String style_spot;
    private Double style_cost;

    public String getStyle_id() {
        return style_id;
    }

    public void setStyle_id(String style_id) {
        this.style_id = style_id;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public String getStyle_state() {
        return style_state;
    }

    public void setStyle_state(String style_state) {
        this.style_state = style_state;
    }

    public String getStyle_spot() {
        return style_spot;
    }

    public void setStyle_spot(String style_spot) {
        this.style_spot = style_spot;
    }

    public Double getStyle_cost() {
        return style_cost;
    }

    public void setStyle_cost(Double style_cost) {
        this.style_cost = style_cost;
    }

    @Override
    public String toString() {
        return "Print_style{" +
                "style_id='" + style_id + '\'' +
                ", style_name='" + style_name + '\'' +
                ", style_state='" + style_state + '\'' +
                ", style_spot='" + style_spot + '\'' +
                ", style_cost=" + style_cost +
                '}';
    }
}
