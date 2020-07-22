package com.pmposs.service;

import com.pmposs.model.Team;

import java.util.List;

public interface TeamService {
    public List<Team> findAllTeam();
    public Team findTeamByLeaderAccount(String team_leader);
    public void teamUpdatePrinted(String team_Leader);
    public void teamDelete(String team_id);
    public void newTeam(Team team);
    public void addTeamMember(String update,String target);
}
