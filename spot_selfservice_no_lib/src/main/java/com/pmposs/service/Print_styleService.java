package com.pmposs.service;

import com.pmposs.model.Print_style;

import java.util.List;

public interface Print_styleService {
    /**
     * 根据景区编号查询景区对应的打印风格信息
     * @param style_spot 景区编号
     * @return
     */
    public List<Print_style> findAllAtLocalSpot(String style_spot);
}
