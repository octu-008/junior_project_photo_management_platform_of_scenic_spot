package com.pmposs.controller;

import com.pmposs.model.Admin;
import com.pmposs.model.Print_style;
import com.pmposs.service.Print_styleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Print_styleController {
    //有关Print_style表的控制器
    //注入Print_styleService
    @Autowired
    private Print_styleService print_styleService;
    @RequestMapping(path = "/admin/management/print_style")
    public String findAllPrint_Style(HttpSession session)//根据已登录管理员的景区编号查询相应景区中的打印格式信息
    {
        Admin admin_online=(Admin)session.getAttribute("admin_online");
        session.removeAttribute("print_styleUpdate");
        List<Print_style> print_styles=print_styleService.findAllAtLocalSpot(admin_online.getSpot_id());
        session.setAttribute("print_styles",print_styles);
        return "admin/management/print_style";
    }
    @RequestMapping(path = "/admin/management/print_style/{style_id}")
    public String preUpdatePrint_Style(@PathVariable String style_id,HttpSession session)//将需要修改的打印格式按照style_id提取出来展示在修改页面中
    {
        List<Print_style> print_styles=(List<Print_style>)session.getAttribute("print_styles");
        Print_style print_styleUpdate=new Print_style();
        for(Print_style print_style:print_styles)
        {
            if (print_style.getStyle_spot().equals(style_id))
            {
                print_styleUpdate=print_style;
                break;
            }
        }
        session.removeAttribute("print_styles");
        session.setAttribute("print_styleUpdate",print_styleUpdate);
        return "admin/management/print_style";
    }
    @RequestMapping(path = "/admin/management/print_style/update")
    public String updatePrint_Style(Print_style print_style,HttpSession session)//按照表单提交内容，正式修改打印风格
    {
        Print_style print_styleUpdate=(Print_style)session.getAttribute("print_styleUpdate");
        if (print_style.getStyle_name().equals(print_styleUpdate.getStyle_name())){}
        else
        {
            print_styleService.print_styleUpdate("style_name",print_style.getStyle_name(),
                    print_styleUpdate.getStyle_id(),print_styleUpdate.getStyle_spot());
        }
        if(print_style.getStyle_state().equals(print_styleUpdate.getStyle_state())){}
        else
        {
            print_styleService.print_styleUpdate("style_state",print_style.getStyle_state(),
                    print_styleUpdate.getStyle_id(),print_styleUpdate.getStyle_spot());
        }
        if(print_style.getStyle_cost() == print_styleUpdate.getStyle_cost()){}
        else
        {
            print_styleService.print_styleUpdate("style_cost",print_style.getStyle_cost().toString(),
                    print_styleUpdate.getStyle_id(),print_styleUpdate.getStyle_spot());
        }
        if(print_style.getStyle_id().equals(print_styleUpdate.getStyle_id())){}
        else
        {
            print_styleService.print_styleUpdate("style_id",print_style.getStyle_id(),
                    print_styleUpdate.getStyle_id(),print_styleUpdate.getStyle_spot());
        }
        return "redirect:../print_style";
    }
    @RequestMapping(path= "/admin/management/print_style/preNew")
    public String preNewPrint_Style(HttpSession session)//展示新增打印风格页面
    {
        session.removeAttribute("print_styles");
        return "admin/management/print_style";
    }
    @RequestMapping(path ="/admin/management/print_style/new")
    public String newPrint_Style(Print_style print_style)//按照表单内容，正式新增打印风格
    {
        print_styleService.print_styleNew(print_style);
        return "redirect:../print_style";
    }
    @RequestMapping(path = "/admin/management/print_styleDelete/{style_id}")
    public String deletePrint_style(@PathVariable String style_id,HttpSession session)//按照style_id删除指定的打印格式
    {
        Admin admin_online=(Admin) session.getAttribute("admin_online");
        print_styleService.print_styleDelete(style_id,admin_online.getSpot_id());
        return "redirect:../print_style";
    }
    //==========================================以下为Android端使用到的控制器=========================================
    @RequestMapping(path ="/user/getAllPrintStyle")
    @ResponseBody
    public List<Print_style> getAllPrintStyle(HttpServletRequest request)
    {
        String spot_id=request.getParameter("spot_id");
        return print_styleService.findAllAtLocalSpot(spot_id);
    }
}
