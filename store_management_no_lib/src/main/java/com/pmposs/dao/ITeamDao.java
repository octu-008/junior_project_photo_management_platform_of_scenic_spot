package com.pmposs.dao;


import com.pmposs.model.Team;

import java.util.List;

public interface ITeamDao {
    public List<Team> findAll();//寻找所有队伍
    public void insertTeam(Team team);//新增队伍
    public Team findTeamByLeaderAccount(String team_leader);//根据导游用户账号寻找对应队伍
    public void deleteTeam(String team_id);//打印完成后将队伍设置为以打印状态
    public void teamUpdatePrinted(String team_leader);//删除选定队伍
    public void addTeamMember(String update,String target);//更新队伍信息
}
