package com.pmposs.controller;

import com.pmposs.model.Admin;
import com.pmposs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

//添加此注释后
@Controller
@SessionAttributes(value = "admin_online")
public class AdminController {
    //有关Admin表的控制器
    //注入AdminService
    @Autowired
    private AdminService adminService;
    //AdminController中管理员登录时的登录验证请求方法
    //设置请求路径
    @RequestMapping(path = "/admin/main")
    public String loginAdmin(Admin admin, Model model)
    {    //登录验证，Admin参数为表单提交时Spring对表达数据的封装
         //model用于获取是否已有登入的管理员账号
        if (model.getAttribute("admin_online")!=null)
        {
            //已有登录的管理员，返回到管理员主页
            return "admin/main";
        }
        else
        {
            //未有登录的管理员，以下开始登录验证
            //调用自动注入的AdminService接口中的登录验证方法
            int check=adminService.adminLoginCheck(admin);
            String path="admin/failLogin";
            if(check==4)
            {
                path="admin/main";
                model.addAttribute("admin_online",admin);//登录成功将登入的admin存放于session
                return path;//转入登录页面
            }
            //登录信息不匹配时，进入失败信息提示页面
            else if(check==3)
            {
                model.addAttribute("fail_msg","门店编号不匹配");
                return path;
            }
            else if (check==2)
            {
                model.addAttribute("fail_msg","景区编号不匹配");
                return path;
            }
            else if(check==1)
            {
                model.addAttribute("fail_msg","密码不匹配");
                return path;
            }
            else
            {
                model.addAttribute("fail_msg","无效的管理员账号");
                return path;
            }
        }
    }
    @RequestMapping(path="/admin/management/admin")
    public String findAllAdmin( HttpSession session)//查询全部管理员
    {
        session.removeAttribute("adminUpdate");//将待修改管理员先去除，展示管理员列表
        List<Admin> admins=adminService.findAllAdmin();
        session.setAttribute("admins",admins);
        return "admin/management/admin";
    }
    @RequestMapping(path = "/admin/management/admin/{admin_account}",method = RequestMethod.GET)
    public String preUpdateAdmin(@PathVariable String admin_account, HttpSession session)
    {   //将选中的一个管理员提取出来，作为待修改管理员 adminupdate 放入session并刷新页面转换至管理员修改页面
        List<Admin> admins=(List<Admin>)session.getAttribute("admins");
        Admin adminUpdate=new Admin();
        for(Admin admin:admins)
        {
            if (admin.getAdmin_account().equals(admin_account))
            {
                adminUpdate=admin;
                break;
            }
        }
        session.removeAttribute("admins");//将全部管理员列表先去除，展示带修改管理员页面
        session.setAttribute("adminUpdate",adminUpdate);
        return "admin/management/admin";
    }
    @RequestMapping(path = "/admin/management/admin/update")
    public String updateAdmin(Admin admin,HttpSession session)
    {
        //逐个检测表单提交信息，完成对应的修改
        Admin adminUpdate=(Admin) session.getAttribute("adminUpdate");
        if (admin.getStore_id().equals(adminUpdate.getStore_id())){}
        else
        {
            adminService.adminUpdate("store_id",admin.getStore_id(),adminUpdate.getAdmin_account());
        }
        if (admin.getAdmin_pwd().equals(adminUpdate.getAdmin_pwd())){}
        else
        {
            adminService.adminUpdate("admin_pwd",admin.getAdmin_pwd(),adminUpdate.getAdmin_account());
        }
        if(admin.getAdmin_account().equals(adminUpdate)){}
        else
        {
            adminService.adminUpdate("admin_account",admin.getAdmin_account(),adminUpdate.getAdmin_account());
        }
        return "redirect:../admin";
    }
    @RequestMapping(path ="/admin/management/admin/preNew")
    public String preNewAdmin(HttpSession session)
    {
        session.removeAttribute("admins");//将待修改管理员先去除，展示新增管理员页面
        return "admin/management/admin";
    }
    @RequestMapping(path = "/admin/management/admin/new")
    public String newAdmin(Admin admin,HttpSession session)
    {
        //按照表单提交信息，正式新增管理员
        adminService.adminNew(admin);
        return "redirect:../admin";
    }
    @RequestMapping(path="/admin/management/delete/{admin_account}")
    public String deleteAdmin(@PathVariable String admin_account)
    {
        //按照管理员账号删除管理员
        adminService.adminDelete(admin_account);
        return "redirect:../admin";
    }
}
