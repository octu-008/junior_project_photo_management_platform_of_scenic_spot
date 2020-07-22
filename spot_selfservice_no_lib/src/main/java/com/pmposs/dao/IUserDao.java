package com.pmposs.dao;


import com.pmposs.model.User;

public interface IUserDao {
    public User findUserByUser_Account(String user_account);//根据用户账号查询游客
    public void updateUser(String type, String update, String target);//更新用户信息
}
