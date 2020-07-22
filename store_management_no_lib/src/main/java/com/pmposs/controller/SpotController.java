package com.pmposs.controller;

import com.pmposs.model.Admin;
import com.pmposs.model.Spot;
import com.pmposs.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SpotController {
    //有关Spot表的控制器
    //注入spotService
    @Autowired
    private SpotService spotService;
    @RequestMapping(path = "/admin/management/spot")
    public String findSpot(HttpSession session)//根据管理员所在的景区查询对应的景区
    {
        Admin admin_online=(Admin)session.getAttribute("admin_online");
        Spot localSpot=spotService.findSpotById(admin_online.getSpot_id());
        session.setAttribute("spot_local",localSpot);
        return "admin/management/spot";
    }
    @RequestMapping(path = "/admin/management/spot/update")
    public String updateSpot(Spot spot,HttpSession session)//根据表单内容，修改对应的景区信息
    {
        Spot spot_local=(Spot)session.getAttribute("spot_local");
        if (spot.getSpot_name().equals(spot_local.getSpot_name())){}
        else
        {
            spotService.spotUpdate("spot_name",spot.getSpot_name(),spot_local.getSpot_id());
        }
        if(spot.getSpot_state().equals(spot_local.getSpot_state())){}
        else
        {
            spotService.spotUpdate("spot_state",spot.getSpot_state(),spot_local.getSpot_id());
        }
        if(spot.getSpot_id().equals(spot_local.getSpot_id())){}
        else
        {
            spotService.spotUpdate("spot_id",spot.getSpot_id(),spot_local.getSpot_id());
        }
        return "redirect:../spot";
    }
    @RequestMapping(path = "/admin/management/spot/preNew")
    public String preNewSpot(HttpSession session)//展示新增景区信息的页面
    {
        session.removeAttribute("spot_local");
        return "admin/management/spot";
    }
    @RequestMapping(path = "/admin/management/spot/new")
    public String newSpot(Spot spot,HttpSession session)//根据表单内容，正式新增景区信息
    {
        spotService.spotNew(spot);
        return "redirect:../spot";
    }
    //==========================================以下为Android端使用到的控制器=========================================
    @RequestMapping(path = "/user/getAllSpot")
    @ResponseBody
    public List<Spot> userGetAllSpot()
    {
        return spotService.findAllSpot();
    }
}
