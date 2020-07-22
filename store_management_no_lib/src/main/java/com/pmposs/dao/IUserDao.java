package com.pmposs.dao;


import com.pmposs.model.User;

import java.util.List;

public interface IUserDao {

    public List<User> findAll();//查询所有用户
    public User findLeader(String user_account);//查询导游
    public User findUser(String user_account);//通过用户账号查找用户
    public void insertUser(User user);//新增用户
    public void deleteUser(String user_account);//根据用户账号删除用户
    public void updateUser(String type, String update,String target);//更新用户信息
}
