package com.pmposs.service;

import com.pmposs.model.Photo;

import java.util.List;

public interface PhotoService {
    /**
     * 根据用户的账号找到用户上传的照片
     * @param pho_user 用户账号
     * @return
     */
    public List<Photo> findPhotos(String pho_user);

    /**
     * 将用户的照片标记为已打印状态
     * @param pho_user 用户账号
     */
    public void printSouvenirTicket(String pho_user);
}
