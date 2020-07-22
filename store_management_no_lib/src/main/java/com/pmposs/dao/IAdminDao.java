package com.pmposs.dao;


import com.pmposs.model.Admin;

import java.util.List;

public interface IAdminDao {
    public List<Admin> findAll();//查询所有管理员
    public void insertAdmin(Admin admin);//新增管理员
    public void deleteAdmin(String admin_account);//删除管理员
    public void updateAdmin(String type, String update,String target);//更新管理员账号
}
