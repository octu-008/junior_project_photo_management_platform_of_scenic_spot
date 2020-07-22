package com.pmposs.model;

import java.io.Serializable;


public class Team implements Serializable {
    private String team_id;
    private String team_leader;
    private String team_member;
    private int team_mark;

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_leader() {
        return team_leader;
    }

    public void setTeam_leader(String team_leader) {
        this.team_leader = team_leader;
    }

    public String getTeam_member() {
        return team_member;
    }

    public void setTeam_member(String team_member) {
        this.team_member = team_member;
    }

    public int getTeam_mark() {
        return team_mark;
    }

    public void setTeam_mark(int team_mark) {
        this.team_mark = team_mark;
    }

    @Override
    public String toString() {
        return "Team{" +
                "team_id='" + team_id + '\'' +
                ", team_leader='" + team_leader + '\'' +
                ", team_member='" + team_member + '\'' +
                ", team_mark=" + team_mark +
                '}';
    }
}
