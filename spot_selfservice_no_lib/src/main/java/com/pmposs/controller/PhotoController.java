package com.pmposs.controller;

import com.pmposs.model.Photo;
import com.pmposs.model.User;
import com.pmposs.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PhotoController {
    //@Controller注释，可以被扫描后自动创建bean
    //有关Photo表的控制器
    //自动注入photoService
    @Autowired
    private PhotoService photoService;
    //根据用户账号查找用户上传的照片,使用@PathVariable注释，方便缓存
    @RequestMapping(path = "/user/{user_account}")
    private String toPhotoMainPage(@PathVariable String user_account, HttpSession session)
    {
        //调用photoService中的根据用户账号查询该用户上传的照片方法
        //获得一个List<Photo>存放获取到的用户照片数据
        List<Photo> photos=photoService.findPhotos(user_account);
        //将获取的照片信息存入Session中，供前端jsp调用
        session.setAttribute("photos",photos);
        //跳转至用户照片展示主页
        return "user/main";
    }
    //将用户的照片进行打印
    @RequestMapping(path ="/user/print")
    private String printSouvenirTicket(HttpSession session)
    {
        //从Session中获取用户上传照片的信息，若为空则展示打印失败信息。
        if (session.getAttribute("photos") != null)
        {
            //从Session获得登录用户的信息
            User user=(User)session.getAttribute("user_online");
            //调用photoService的将照片标记为打印服务
            photoService.printSouvenirTicket(user.getUser_account());
            //将打印结果信息存入到Session供前端jsp展示
            session.setAttribute("resultMsg","打印成功");
            //跳转到打印结果页面
            return "user/result";
        }
        else
        {
            //将打印结果信息存入到Session供前端jsp展示
            session.setAttribute("resultMsg","打印失败");
            //跳转到打印结果页面
            return "user/result";
        }
    }
}
