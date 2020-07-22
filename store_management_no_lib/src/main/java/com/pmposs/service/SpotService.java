package com.pmposs.service;

import com.pmposs.model.Spot;

import java.util.List;

public interface SpotService {
    /**
     * 根据景区的id即spot_id查询景区信息
     * @param spot_id
     * @return
     */
    public Spot findSpotById(String spot_id);

    /**
     * 查询所有景区信息
     * @return
     */
    public List<Spot> findAllSpot();

    /**
     * 更新景区信息
     * @param type 更新类型
     * @param update 更新内容
     * @param target 更新目标，需要指定打印款式的spot_id
     */
    public void spotUpdate(String type, String update,String target);

    /**
     * 新建景区信息
     * @param spot 从controller获取的景区对象
     */
    public void spotNew(Spot spot);
}
