package com.pmposs.service;

import com.pmposs.model.Spot;

import java.util.List;

public interface SpotService {
    /**
     * 根据景区的编号查询景区信息
     * @param spot_id 景区编号
     * @return
     */
    public Spot findSpotById(String spot_id);

    /**
     * 查询所有景区信息
     * @return
     */
    public List<Spot> findAllSpot();
}
