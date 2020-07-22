package com.pmposs.dao;


import com.pmposs.model.Photo;

import java.util.List;

public interface IPhotoDao {
    public List<Photo> findPhotoByUser_account(String pho_user);//按照用户账号，查询该用户的所有照片
    public void printSouvenirTicketByUser_accounts(String pho_user);//根据用户账号，将用户照片设置为已打印
}
