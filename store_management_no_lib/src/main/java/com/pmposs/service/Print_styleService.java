package com.pmposs.service;

import com.pmposs.model.Print_style;

import java.util.List;

public interface Print_styleService {
    /**
     * 查询所有打印款式信息
     * @return
     */
    public List<Print_style> findAllAtLocalSpot(String style_spot);

    /**
     * 新增打印款式信息
     * @param print_style 需要从controller获取新增的print_style对象
     */
    public void print_styleNew(Print_style print_style);

    /**
     * 更新打印款式信息
     * @param type 更新类型
     * @param update 更新内容
     * @param target1 更新目标，需要指定打印款式的style_id
     * @param target2 更新目标，需要指定打印款式的style_spot
     */
    public void print_styleUpdate(String type, String update,String target1,String target2);

    /**
     * 删除打印款式信息
     * @param style_id 删除目标，需要指定打印款式的style_id
     * @param style_spot 删除目标，需要指定打印款式的style_spot
     */
    public void print_styleDelete(String style_id,String style_spot);
}
