package com.pmposs.controller;

import com.pmposs.model.User;
import com.pmposs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    //有关User表的控制器
    //注入UserService
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/admin/management/user")
    public String findAllUser(HttpSession session)//查询所有用户
    {
        session.removeAttribute("userUpdate");
        List<User> users=userService.findAllUser();
        session.setAttribute("users",users);
        return "admin/management/user";
    }
    @RequestMapping(path = "/admin/management/user/{user_account}")
    public String preUpdateUser(@PathVariable String user_account,HttpSession session)//根据用户的用户账号提取并转换到用户信息修改页面
    {
        List<User> usersSession=(List<User>)session.getAttribute("users");
        User userUpdate=new User();
        for(User user:usersSession)
        {
            if (user.getUser_account().equals(user_account))
            {
                userUpdate=user;
                break;
            }
        }
        session.removeAttribute("users");
        session.setAttribute("userUpdate",userUpdate);
        return "admin/management/user";
    }
    @RequestMapping(path = "/admin/management/user/update")
    public String updateUser(User user,HttpSession session)//根据表单内容正式修改用户信息
    {
        User userUpdate=(User) session.getAttribute("userUpdate");
        if(user.getUser_pwd().equals(userUpdate.getUser_pwd())){}
        else
        {
            userService.userUpdate("user_pwd",user.getUser_pwd(),userUpdate.getUser_account());
        }
        if(user.getUser_type() == userUpdate.getUser_type()){}
        else
        {
            userService.userUpdate("user_type",String.valueOf(user.getUser_type()),userUpdate.getUser_account());
        }
        if (user.getUser_rest() == userUpdate.getUser_rest()){}
        else
        {
            userService.userUpdate("user_rest",String.valueOf(user.getUser_rest()),userUpdate.getUser_account());
        }
        if(user.getUser_account().equals(userUpdate.getUser_account())){}
        else
        {
            userService.userUpdate("user_account",user.getUser_account(),userUpdate.getUser_account());
        }
        return "redirect:../user";
    }
    @RequestMapping(path ="/admin/management/user/preNew")
    public String preNewUser(HttpSession session)//展示新增用户页面
    {
        session.removeAttribute("users");
        return "admin/management/user";
    }
    @RequestMapping(path = "/admin/management/user/new")
    public String newUser(User user)//根据表单内容正式新增用户
    {
        userService.userNew(user);
        return "redirect:../user";
    }
    @RequestMapping(path = "/admin/management/userDelete/{user_account}")
    public String deleteUser(@PathVariable String user_account)//根据用户账号删除指定用户
    {
        userService.userDelete(user_account);
        return "redirect:../user";
    }
//==========================================以下为Android端使用到的控制器=========================================
    @RequestMapping(path="/user/login")
    @ResponseBody
    public User userLoginCheck(HttpServletRequest request)
    {
        String user_account=request.getParameter("user_account");
        String user_password=request.getParameter("user_password");
        String user_type=request.getParameter("user_type");
        User user=userService.userLoginCheck(user_account,user_password,user_type);
        return user;
    }
    @RequestMapping(path="/user/register")
    @ResponseBody
    public String userRegister(HttpServletRequest request)
    {
        String user_account=request.getParameter("user_account");
        String user_password=request.getParameter("user_password");
        String user_type=request.getParameter("user_type");
        String checkMsg=userService.registerCheck(user_account,user_password,user_type);
        return checkMsg;
    }
    @RequestMapping(path ="/userRefresh/{user_account}")
    @ResponseBody
    public User userRefresh(@PathVariable String user_account)
    {
        return userService.getRefreshUser(user_account);
    }
    @RequestMapping(path = "/user/forgotPassword")
    @ResponseBody
    public String userForgotPassword(HttpServletRequest request)
    {
        String user_account=request.getParameter("user_account");
        String password=userService.userForgotPassword(user_account);
        if(password != null)
        {
            return password;
        }
        else{
            return null;
        }
    }
    @RequestMapping(path= "/user/recharge")
    @ResponseBody
    public String userRecharge(HttpServletRequest request)
    {
        String user_account=request.getParameter("user_account");
        String rest=request.getParameter("rest");
        String recharge=request.getParameter("recharge");
        Double restD=Double.valueOf(rest);
        Double rechargeD=Double.valueOf(recharge);
        String result=String.valueOf(restD+rechargeD);
        userService.recharge(user_account,result);
        return "RechargeSuccess";
    }
    @RequestMapping(path ="/userAddCheck/{leader}/{user_account}")
    public String userCheckWithAccount(@PathVariable String leader,@PathVariable String user_account)
    {
        boolean check=userService.searchUserResult(user_account);
        if (check)
        {
            String path=leader+"/"+user_account;
            return "redirect:../addTeamMember/"+path;
        }
        else {
            return "redirect:../addTeamMemberFail";
        }
    }
}
