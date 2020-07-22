package com.pmposs.service.impl;

import com.pmposs.dao.ITeamDao;
import com.pmposs.model.Team;
import com.pmposs.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teamService")
public class TeamServiceImpl implements TeamService {
    @Autowired
    private ITeamDao iTeamDao;

    @Override
    public List<Team> findAllTeam() {
        return iTeamDao.findAll();
    }

    @Override
    public Team findTeamByLeaderAccount(String team_leader) {
        return iTeamDao.findTeamByLeaderAccount(team_leader);
    }

    @Override
    public void teamUpdatePrinted(String team_Leader) {
        iTeamDao.teamUpdatePrinted(team_Leader);
    }

    @Override
    public void teamDelete(String team_id) {
        iTeamDao.deleteTeam(team_id);
    }

    @Override
    public void newTeam(Team team) {
        iTeamDao.insertTeam(team);
    }

    @Override
    public void addTeamMember(String update, String target) {
        iTeamDao.addTeamMember(update,target);
    }
}
