package com.pmposs.service.impl;

import com.pmposs.dao.IUserDao;
import com.pmposs.model.User;
import com.pmposs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserDao iUserDao;
    @Override
    public User findLeader(String user_account) {
        return iUserDao.findLeader(user_account);
    }

    @Override
    public List<User> findAllUser() {
        return iUserDao.findAll();
    }

    @Override
    public void userUpdate(String type, String update, String target) {
        iUserDao.updateUser(type,update,target);
    }

    @Override
    public void userDelete(String user_account) {
        iUserDao.deleteUser(user_account);
    }

    @Override
    public void userNew(User user) {
        iUserDao.insertUser(user);
    }

    @Override
    public User userLoginCheck(String user_account, String user_pwd, String user_type) {
        User user=iUserDao.findUser(user_account);
        int user_type_c=0;//默认type为导游
        if(user_type.equals("游客"))
        {
            user_type_c=1;
        }
        if (user == null)
        {
            return null;
        }
        else
        {
            if (user.getUser_pwd().equals(user_pwd))
            {
                if (user.getUser_type() == user_type_c)
                {
                    return user;
                }
                else
                {
                    return null;
                }
            }
            else
            {
                return null;
            }
        }
    }

    @Override
    public String registerCheck(String user_account, String user_pwd, String user_type) {
        boolean check=true;
        List<User> users=iUserDao.findAll();
        for(User user:users)
        {
            if(user.getUser_account().equals(user_account))
            {
                check=false;
                break;
            }
        }
        if(check)
        {
            User newUser=new User();
            newUser.setUser_account(user_account);
            newUser.setUser_pwd(user_pwd);
            if(user_type.equals("导游"))
            {
                newUser.setUser_type(0);
            }
            else
            {
                newUser.setUser_type(1);
            }
            newUser.setUser_rest(0.00);
            iUserDao.insertUser(newUser);
            return "success";
        }
        else
        {
            return "fail";
        }
    }

    @Override
    public String userForgotPassword(String user_account) {
        User user=iUserDao.findUser(user_account);
        if(user != null )
        {
            return user.getUser_pwd();
        }
        else
        {
            return null;
        }
    }

    @Override
    public void recharge(String user_account, String money) {
        iUserDao.updateUser("user_rest",money,user_account);
    }

    @Override
    public boolean searchUserResult(String user_account) {
        boolean check=false;
        User user=iUserDao.findUser(user_account);
        if(user != null)
        {
            check=true;
        }
        return check;
    }

    @Override
    public User getRefreshUser(String user_account) {
        return iUserDao.findUser(user_account);
    }
}
