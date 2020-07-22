package com.pmposs.service;

import com.pmposs.model.Photo;

import java.util.List;

public interface PhotoService {
    /**
     * 维护运营数据：寻找所有照片信息
     * @return 所有照片
     */
    public List<Photo> findAllPhoto();

    /**
     * 根据导游队伍中的游客账号查询所属的照片
     * @param user_accounts 导游队伍中的游客账号
     * @return 对应游客的照片
     */
    public List<Photo> findPhotoByUser_accountList(List<String> user_accounts);

    /**
     * 打印纪念券，同时根据提供的游客账号将已经完成打印的照片进行标记
     * @param user_accounts 游客照片
     */
    public void printSouvenirTicket(List<String> user_accounts);

    /**
     * 维护运营数据：根据照片名称删除指定照片
     * @param pho_name
     */
    public void photoDelete(String pho_name);
    public void newPhotoInfo(Photo photo);
    public List<Photo> findPhotoByUserAccount(String user_account);
}
