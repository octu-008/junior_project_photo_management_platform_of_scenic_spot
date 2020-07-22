package com.pmposs.controller;

import com.pmposs.model.Spot;
import com.pmposs.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SpotController {
    //关于Spot表的控制器
    //自动注入spotService
    @Autowired
    private SpotService spotService;

    @RequestMapping(path="/spot/{spot_id}")
    public String setSpotInfo(@PathVariable String spot_id, HttpSession session)//根据选择的景区查询景区信息
    {
        Spot spot_local=spotService.findSpotById(spot_id);
        if (spot_local!=null)
        {
            session.setAttribute("spot_local",spot_local);
            return "redirect:../loginMain";
        }
        else
        {
            return "spotSelect";
        }
    }
    @RequestMapping(path="/spotSelect")
    public String selectSpot(HttpSession session)//查询所有景区信息供用户选择
    {
        session.removeAttribute("spot_local");
        List<Spot> spots=spotService.findAllSpot();
        session.setAttribute("spots",spots);
        return "spotSelect";
    }
}
