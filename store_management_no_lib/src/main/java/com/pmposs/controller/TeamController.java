package com.pmposs.controller;

import com.pmposs.model.Team;
import com.pmposs.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class TeamController {
    //有关Team表的控制器
    //注入teamService
    @Autowired
    private TeamService teamService;

    @RequestMapping(path="/admin/photoPrint/leaderSearch")
    public String leaderSearch(String team_leader, HttpSession session)//根据导游的账号，搜索导游的队伍
    {
        Team teamResult=teamService.findTeamByLeaderAccount(team_leader);
        if (teamResult!=null)
        {
            session.setAttribute("teamResult",teamResult);
            return "admin/photoPrint/leaderSearch";
        }
        else
        {
            return "admin/photoPrint/leaderSearch";
        }
    }
    @RequestMapping(path = "/admin/management/team")
    public String findAllTeam(HttpSession session)//查询所有队伍
    {
        List<Team> teams=teamService.findAllTeam();
        session.setAttribute("teams",teams);
        return "admin/management/team";
    }
    @RequestMapping(path = "/admin/management/teamDelete/{team_id}")//根据队伍编号删除指定队伍
    public String deleteTeam(@PathVariable String team_id)
    {
        teamService.teamDelete(team_id);
        return "redirect:../team";
    }
    //==========================================以下为Android端使用到的控制器=========================================
    @RequestMapping(path = "/addTeamMemberCheck")
    public String addTeamMemberCheckFirst(HttpServletRequest request)
    {
        String user_account=request.getParameter("user_account");
        String leader=request.getParameter("leader");
        String path=leader+"/"+user_account;
        return "redirect:./userAddCheck/"+path;
    }
    @RequestMapping(path= "/deleteTeam/{leader_account}")
    @ResponseBody
    public String delelteTeam(@PathVariable String leader_account)
    {
        Team team=teamService.findTeamByLeaderAccount(leader_account);
        if (team !=null)
        {
            teamService.teamDelete(team.getTeam_id());
            return "Success";
        }
        else {
            return "Fail";
        }
    }
    @RequestMapping(path ="/userAddCheck/addTeamMember/{leader}/{user_account}")
    @ResponseBody
    public String addTeamMember(@PathVariable String leader,@PathVariable String user_account)
    {
        Team team=teamService.findTeamByLeaderAccount(leader);
        if (team == null)
        {
            Team newTeam=new Team();
            newTeam.setTeam_leader(leader);
            newTeam.setTeam_member(user_account);
            newTeam.setTeam_mark(0);
            Random random=new Random();
            String randomInt=String.valueOf(random.nextInt(10));
            newTeam.setTeam_id(randomInt+"_"+leader);
            teamService.newTeam(newTeam);
        }
        else
        {
            String newTeamMembers=team.getTeam_member()+","+user_account;
            teamService.addTeamMember(newTeamMembers,leader);
        }
        return "Success";
    }
    @RequestMapping(path ="/userAddCheck/addTeamMemberFail")
    @ResponseBody
    public String addTeamMemberFail()
    {
        return "Fail";
    }
    @RequestMapping(path ="/leaderGetTeam/{leader_account}")
    @ResponseBody
    public Team leaderGetTeam(@PathVariable String leader_account)
    {
        Team team=teamService.findTeamByLeaderAccount(leader_account);
        if(team !=null)
        {
            return team;
        }
        else
        {
            return null;
        }
    }
}
