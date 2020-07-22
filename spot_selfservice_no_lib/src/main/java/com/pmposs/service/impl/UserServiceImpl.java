package com.pmposs.service.impl;

import com.pmposs.dao.IUserDao;
import com.pmposs.model.User;
import com.pmposs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserDao iUserDao;

    @Override
    public String userLoginCheck(String user_account, String user_pwd) {
        User userCheck=iUserDao.findUserByUser_Account(user_account);
        String checkMsg="未知错误";
        if (userCheck != null)
        {
            if (userCheck.getUser_pwd().equals(user_pwd))
            {
                checkMsg="登录通过";
            }
            else
            {
                checkMsg="输入密码错误";
            }
        }
        else {
            checkMsg="未找到输入的用户账号。";
        }
        return checkMsg;
    }

    @Override
    public User findUserByAccount(String user_account) {
        return iUserDao.findUserByUser_Account(user_account);
    }

    @Override
    public String userUpdate(String type, String update, String target) {
        iUserDao.updateUser(type,update,target);
        return null;
    }
}
