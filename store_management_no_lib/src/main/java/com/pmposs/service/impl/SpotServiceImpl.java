package com.pmposs.service.impl;

import com.pmposs.dao.ISpotDao;
import com.pmposs.model.Spot;
import com.pmposs.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("spotService")
public class SpotServiceImpl implements SpotService {
    @Autowired
    private ISpotDao iSpotDao;

    @Override
    public Spot findSpotById(String spot_id) {
        return iSpotDao.findSpotById(spot_id);
    }

    @Override
    public List<Spot> findAllSpot() {
        return iSpotDao.findAll();
    }

    @Override
    public void spotUpdate(String type, String update, String target) {
        iSpotDao.updateSpot(type,update,target);
    }

    @Override
    public void spotNew(Spot spot) {
        iSpotDao.insertSpot(spot);
    }
}
