package com.pmposs.service.impl;

import com.pmposs.dao.IPrint_styleDao;
import com.pmposs.model.Print_style;
import com.pmposs.service.Print_styleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("print_styleService")
public class Print_styleServiceImpl implements Print_styleService {
    @Autowired
    private IPrint_styleDao iPrint_styleDao;

    @Override
    public List<Print_style> findAllAtLocalSpot(String style_spot) {
        return iPrint_styleDao.findAllAtLocalSpot(style_spot);
    }

    @Override
    public void print_styleNew(Print_style print_style) {
        iPrint_styleDao.insertPrint_style(print_style);
    }

    @Override
    public void print_styleUpdate(String type, String update, String target1,String target2) {
        iPrint_styleDao.updatePrint_style(type,update,target1,target2);
    }

    @Override
    public void print_styleDelete(String style_id,String style_spot) {
        iPrint_styleDao.deletePrint_style(style_id,style_spot);
    }
}
