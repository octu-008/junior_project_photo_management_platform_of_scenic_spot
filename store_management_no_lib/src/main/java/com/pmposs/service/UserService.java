package com.pmposs.service;

import com.pmposs.model.User;

import java.util.List;

public interface UserService {
    /**
     *根据导游账号寻找导游信息
     * @param user_account 传入导游账号user_account
     * @return
     */
    public User findLeader(String user_account);

    /**
     * 维护运营数据：寻找所有用户信息
     * @return
     */
    public List<User> findAllUser();

    /**
     * 维护运营数据：更新用户信息
     * @param type 更新类型
     * @param update 更新内容
     * @param target 更新目标，需要传入目标的user_account即用户账号
     */
    public void userUpdate(String type, String update,String target);

    /**
     * 维护运营数据：删除用户信息
     * @param user_account 传入目标的user_account即用户账号
     */
    public void userDelete(String user_account);

    /**
     * 维护运营数据：新增用户信息
     * @param user 从controller中获取新建的Admin对象
     */
    public void userNew(User user);
    //==========================================以下为Android端使用的服务==========================================

    /**
     * 用户登录信息检查用户并检验是否登录成功
     * @param user_account 用户的账号
     * @param user_pwd 用户的密码
     * @param user_type 用户的类型
     * @return 返回找到的用户及其信息
     */
    public User userLoginCheck(String user_account,String user_pwd,String user_type);

    /**
     * 用户注册账号
     * @param user_account 注册用的用户账号
     * @param user_pwd 注册用户的密码
     * @param user_type 注册的用户类型
     * @return 注册的结果信息
     */
    public String registerCheck(String user_account,String user_pwd,String user_type);

    /**
     *找回密码
     * @param user_account 用户的账号
     * @return 用户的密码
     */
    public String userForgotPassword(String user_account);

    /**
     * 充值
     * @param user_account 用户的账号
     * @param money 充值后的金额
     */
    public void recharge(String user_account,String money);
    public boolean searchUserResult(String user_account);
    public User getRefreshUser(String user_account);
}
