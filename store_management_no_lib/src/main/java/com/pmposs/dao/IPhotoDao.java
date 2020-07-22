package com.pmposs.dao;


import com.pmposs.model.Photo;

import java.util.List;

public interface IPhotoDao {
    public List<Photo> findAll();//查询所有用户照片
    public List<Photo> findById(String pho_user);//按照用户账号，查询该用户的所有照片
    public void printSouvenirTicketByUser_accounts(String pho_user);//根据用户账号，将用户照片设置为已打印
    public void deletePhoto(String pho_name);//根据照片的pho_name照片名称删除照片
    public void insertPhoto(Photo photo);//新增图片信息
    public List<Photo> findByIdWithLe(String pho_user);
    /*   public void updatePhoto(String pho_style, String pho_name);*/
}
