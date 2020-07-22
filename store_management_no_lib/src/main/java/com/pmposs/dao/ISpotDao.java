package com.pmposs.dao;

import com.pmposs.model.Spot;

import java.util.List;

public interface ISpotDao {
    public List<Spot> findAll();//查询所有景区
    public Spot findSpotById(String spot_id);//根据景区id查找对应景区
    public void insertSpot(Spot spot);//新增景区
    public void updateSpot(String type, String update, String target);//更新景区信息
/*    public void deleteSpot(String spot_id);*/
}
