package com.pmposs.controller;

import com.pmposs.model.Photo;
import com.pmposs.model.Team;
import com.pmposs.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes(value="photos")
public class PhotoController {
    //有关Photo表的控制器
    //注入PhotoService进行业务处理
    @Autowired
    private PhotoService photoService;
    //请求路径
    @RequestMapping(path="/admin/photoPrint/prepareToPrint")
    public String prepareToPrint(Model model, HttpSession session)
    {   //将需要打印的照片展示到页面内
        //首先在Session中获取查询到的Team对象
            Team teamResult=(Team) session.getAttribute("teamResult");
            //将Team中TeamMember（队伍成员的用户账号集）分离为一个List
            List<String> splitResult = splitTeamMember(teamResult.getTeam_member());
            //使用PhotoService中的方法，将List中每一个用户账号对应的用户照片信息存入photos存入PhotoList中
            List<Photo> photos = photoService.findPhotoByUser_accountList(splitResult);
            //将获取到的PhotoList存入到Session中
            model.addAttribute("photos", photos);
            //跳转到准备打印页面
            return "admin/photoPrint/prepareToPrint";
    }
    @RequestMapping(path="/admin/photoPrint/printResult")
    public String printSouvenirTicket(Model model,HttpSession session)
    {   //打印纪念券，将照片标记设置为已打印
        Team teamResult=(Team) session.getAttribute("teamResult");
        List<String> splitResult = splitTeamMember(teamResult.getTeam_member());
        String printResult=printTicket(splitResult);
        model.addAttribute("resultMessage",printResult);
        session.removeAttribute("photos");
        session.removeAttribute("teamResult");
        return "admin/photoPrint/printResult";
    }
    @RequestMapping(path ="/admin/photoPrint/cancelPrint")
    public String cancelPrintSouvenirTicket(HttpSession session)
    {
        session.removeAttribute("teamResult");
        session.removeAttribute("photos");
        return "redirect:../main";
    }
    @RequestMapping(path="/admin/management/photo")
    public String photoManagement(HttpSession session)
    {
        //在照片管理页面查询所有照片
        List<Photo> photos=photoService.findAllPhoto();
        session.setAttribute("photosManagement",photos);
        return "admin/management/photo";
    }
    @RequestMapping(path = "/admin/management/photoDelete/{pho_name}")
    public String deletePhoto(@PathVariable String pho_name)
    {
        //根据照片名称删除指定照片
        photoService.photoDelete(pho_name);
        return "redirect:../photo";
    }
    //获得队伍中的游客
    public List<String> splitTeamMember(String team_member)
    {
        List<String> members=new ArrayList<String>();
        String [] arrayMembers=team_member.split(",");
        for (String member:arrayMembers)
        {
            members.add(member);
        }
        return members;
    }
    //模拟打印机打打印
    public String printTicket(List<String> teamMember)
    {
        String resultMessage="打印成功";
        photoService.printSouvenirTicket(teamMember);
        return resultMessage;
    }
    //==========================================以下为Android端使用的服务==========================================
    @RequestMapping(path = "/uploadPhoto")
    @ResponseBody
    public String uploadPhoto(HttpServletRequest request) throws IOException //将上传的图片保存到本地
    {
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
            MultipartFile file = multipartRequest.getFile("photo");
            String filename = file.getOriginalFilename();
            File uploadDir = new File("D:/upload/photo");
            File uploadFile = new File(uploadDir.getAbsolutePath() + "/" + filename);
            file.transferTo(uploadFile);
            return "UploadSuccess";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            return "UploadSuccess";
        }
    }
    @RequestMapping(path = "/addPhotoInfo")
    @ResponseBody
    public String addPhotoInfo(@RequestBody Photo photo)//添加上传图片的信息到数据库
    {
        photoService.newPhotoInfo(photo);
        return "UploadSuccess";
    }
    @RequestMapping(path = "/getPhoto/{user_account}")
    @ResponseBody
    public List<Photo> getPhotoList(@PathVariable String user_account)//根据用户账号获得该用户上传的照片
    {
        List<Photo> photos=photoService.findPhotoByUserAccount(user_account);
        return photos;
    }
    @RequestMapping(path ="/leaderGetTeamPhoto")
    @ResponseBody
    public List<Photo> getLeaderGetTeamPhoto(@RequestBody Team team)
    {
        Team teamGet=team;
        if(teamGet !=null )
        {
            String teamMember=teamGet.getTeam_member();
            List<String> teamSplit= splitTeamMember(teamMember);
            List<Photo> photos=photoService.findPhotoByUser_accountList(teamSplit);
            return photos;
        }
        else
        {
            return null;
        }
    }
}
