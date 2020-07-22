package com.pmposs.service;

import com.pmposs.model.User;

public interface UserService {
    /**
     * 根据输入的账号与密码检查登录信息
     * @param user_account 用户账号
     * @param user_pwd 账号密码
     * @return
     */
    public String userLoginCheck(String user_account,String user_pwd);

    /**
     * 根据账号查询用户
     * @param user_account 用户账号
     * @return
     */
    public User findUserByAccount(String user_account);

    /**
     * 根据提供内容更新用户信息
     * @param type 更新的信息类型
     * @param update 更新的信息内容
     * @param target 更新的目标，用户账号
     * @return
     */
    public String  userUpdate(String type,String update,String target);
}
