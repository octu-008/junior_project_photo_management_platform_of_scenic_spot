package com.pmposs.service;

import com.pmposs.model.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 管理员登录验证
     * @param admin 从controller中传入Admin对象
     * @return 返回的int值由controller判断是否登录成功
     */
    public int adminLoginCheck(Admin admin);
    public List<Admin> findAllAdmin();
    /**
     * 管理员对管理员账号进行更新
     * @param type 更新类型
     * @param update 更新内容
     * @param target 更新目标，需要传入目标的admin_account即进行更新的管理员账号
     */
    public void adminUpdate(String type, String update,String target);

    /**
     * 管理员新建管理员账号
     * @param admin 从controller中获取新建的Admin对象
     */
    public void adminNew(Admin admin);

    /**
     * 管理员删除管理员账号
     * @param admin_account 从controller中获取进行删除的管理员账号
     */
    public void adminDelete(String admin_account);
}
