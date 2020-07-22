package com.pmposs.dao;

import com.pmposs.model.Print_style;

import java.util.List;

public interface IPrint_styleDao {
    public List<Print_style> findAllAtLocalSpot(String style_spot);//查询当前景区的所有打印风格信息
}
