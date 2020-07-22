package com.pmposs.controller;

import com.pmposs.model.User;
import com.pmposs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    //有关User表的控制器
    //注入UserService
    @Autowired
    private UserService userService;

    @RequestMapping(path="/user/login")
    public String userLoginCheck(HttpServletRequest request, HttpSession session)
    {
        if(session.getAttribute("user_online")!=null)
        {
            return "user/main";
        }
        else
        {
            String check=userService.userLoginCheck(request.getParameter("user_account"),request.getParameter("user_pwd"));
            String path="user/failLogin";
            if(check.equals("登录通过"))
            {
                path="redirect:../user/"+request.getParameter("user_account");
                User userChecked=userService.findUserByAccount(request.getParameter("user_account"));
                session.setAttribute("user_online",userChecked);
                return path;
            }
            else
            {
                session.setAttribute("failMsg",check);
                return path;
            }
        }
    }
}
