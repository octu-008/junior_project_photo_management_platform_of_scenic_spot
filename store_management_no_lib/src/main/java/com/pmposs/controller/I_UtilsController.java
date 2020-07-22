package com.pmposs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class I_UtilsController {
    //跳转至搜索导游队伍页面
    @RequestMapping(path = "/admin/photoPrint/leaderSearchStart")
    public String toLeaderSearch()
    {
        return "admin/photoPrint/leaderSearch";
    }
    //跳转至管理页面
    @RequestMapping(path = "/admin/management/main")
    public String toManagementMainPage()
    {
        return "admin/management/main";
    }
}
