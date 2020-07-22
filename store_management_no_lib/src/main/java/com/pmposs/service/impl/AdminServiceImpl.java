package com.pmposs.service.impl;

import com.pmposs.dao.IAdminDao;
import com.pmposs.model.Admin;
import com.pmposs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private IAdminDao iAdminDao;

    @Override
    public int adminLoginCheck(Admin admin) {
        int judge=0;//用于识别检验管理员登录信息的情况
        List<Admin> admins=iAdminDao.findAll();//查询Admin表所有Admin
        for (Admin rAdmin:admins)
        {   //判断1：判断是否存在此管理员账号，若存在进入下一步判断，否则检验下一admin对象直至所有admin被检验后到结果1
            if (rAdmin.getAdmin_account().equals(admin.getAdmin_account()))
            {//判读2：判断输入的密码是否正确，若正确，进入下一步判断；否则执行结果2
                if (adminPaswordCheck(rAdmin.getAdmin_pwd(),admin.getAdmin_pwd()))
                {//判断3：判断输入景区编号是否正确匹配，若正确，进入下一步判断；否则执行结果3
                    if (rAdmin.getSpot_id().equals(admin.getSpot_id()))
                    {//判断4：判断输入门店编号是否正确匹配，若正确，执行结果5；否则执行结果4
                        if (rAdmin.getStore_id().equals(admin.getStore_id()))
                        {
                            judge=4;//结果5：所有信息输入正确，返回judge=4.成功登录
                            break;
                        }
                        else
                        {
                            judge=3;//结果4：门店编号输入错误，返回judge=3，登录失败
                            break;
                        }
                    }
                    else
                    {
                        judge=2;//结果3：景区编号输入错误，返回judge=2，登录失败
                        break;
                    }
                }
                else
                {
                    judge=1;//结果2:密码输入错误，返回judge=1，登录失败
                    break;
                }
            }
            else
            {
                continue;
            }
        }
        //结果1：未搜索到传入的admin_account(管理员账号)，返回judge=0
        return judge;
    }

    @Override
    public List<Admin> findAllAdmin() {
        return iAdminDao.findAll();
    }

    @Override
    public void adminUpdate(String type, String update,String target) {
        iAdminDao.updateAdmin(type,update,target);
    }

    @Override
    public void adminNew(Admin admin) {
        iAdminDao.insertAdmin(admin);
    }

    @Override
    public void adminDelete(String admin_account) {
        iAdminDao.deleteAdmin(admin_account);
    }

    public boolean adminPaswordCheck(String rpwd, String pwd) {
        //将来加入密码加密
        return rpwd.equals(pwd);
    }
}
