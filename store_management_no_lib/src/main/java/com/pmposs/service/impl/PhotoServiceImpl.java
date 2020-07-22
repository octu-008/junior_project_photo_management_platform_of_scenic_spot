package com.pmposs.service.impl;

import com.pmposs.dao.IPhotoDao;
import com.pmposs.model.Photo;
import com.pmposs.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private IPhotoDao iPhotoDao;
    @Override
    public List<Photo> findAllPhoto() {
        return iPhotoDao.findAll();
    }

    @Override
    public List<Photo> findPhotoByUser_accountList(List<String> user_accounts) {
        List<Photo> photos=new ArrayList<Photo>();
        List<Photo> photosex=new ArrayList<Photo>();
        for (String user_account:user_accounts)
        {
            //需要根据游客的user_account找到每一位游客上传的照片
            photosex=iPhotoDao.findByIdWithLe(user_account);
            if (photosex!=null)
            {
                for (Photo photo:photosex)
                {
                    photos.add(photo);
                }
            }
            else
            {
                continue;
            }
        }
        return photos;
    }

    @Override
    public void printSouvenirTicket(List<String> user_accounts) {
        for (String user_account:user_accounts)
        {
            iPhotoDao.printSouvenirTicketByUser_accounts(user_account);
        }
    }

    @Override
    public void photoDelete(String pho_name) {
        iPhotoDao.deletePhoto(pho_name);
    }

    @Override
    public void newPhotoInfo(Photo photo) {
        iPhotoDao.insertPhoto(photo);
    }

    @Override
    public List<Photo> findPhotoByUserAccount(String user_account) {
        return iPhotoDao.findById(user_account);
    }
}
