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
}
