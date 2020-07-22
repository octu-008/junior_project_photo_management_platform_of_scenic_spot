package com.pmposs.service.impl;
import com.pmposs.dao.IPhotoDao;
import com.pmposs.model.Photo;
import com.pmposs.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private IPhotoDao iPhotoDao;
    @Override
    public List<Photo> findPhotos(String pho_user) {
        return iPhotoDao.findPhotoByUser_account(pho_user);
    }

    @Override
    public void printSouvenirTicket(String pho_user) {
        iPhotoDao.printSouvenirTicketByUser_accounts(pho_user);
    }
}
