package com.pmposs.controller;

import com.pmposs.model.Print_style;
import com.pmposs.model.Spot;
import com.pmposs.service.Print_styleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Print_styleController {
    //有关Print_style表的控制器
    //自动注入print_styleService
    @Autowired
    private Print_styleService print_styleService;
    @RequestMapping(path ="/loginMain")
    public String showLocalSpotInfo(HttpSession session)//根据选择的景区地点查询景区中的打印格式
    {

        Spot spot=(Spot)session.getAttribute("spot_local");
        if(spot == null)
        {
            return "spotSelect";
        }
        else {
            List<Print_style> print_styles = print_styleService.findAllAtLocalSpot(spot.getSpot_id());
            session.setAttribute("print_styles", print_styles);
            return "loginMain";
        }
    }
}
