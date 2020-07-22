package com.pmposs.dao;

import com.pmposs.model.Print_style;

import java.util.List;

public interface IPrint_styleDao {
    public List<Print_style> findAllAtLocalSpot(String style_spot);//查询当前景区的所有打印风格信息
    public void insertPrint_style(Print_style print_style);//新增打印信息
    public void deletePrint_style(String style_id,String style_spot);//删除打印信息
    public void updatePrint_style(String type, String update,String target1,String target2);//更新打印格式
}
