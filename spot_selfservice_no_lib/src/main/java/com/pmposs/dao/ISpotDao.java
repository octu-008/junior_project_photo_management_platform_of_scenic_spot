package com.pmposs.dao;

import com.pmposs.model.Spot;

import java.util.List;

public interface ISpotDao {
    public List<Spot> findAllSpot();//查询所有景区信息
    public Spot findSpotById(String spot_id);//根据景区id查找对应景区
}
