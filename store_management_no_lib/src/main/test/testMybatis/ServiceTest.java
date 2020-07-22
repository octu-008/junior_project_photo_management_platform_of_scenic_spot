package testMybatis;


import com.pmposs.model.*;
import com.pmposs.service.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ServiceTest {

/*    @Test
    public void testServiceAdmin_0001 ()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AdminService as=(AdminService)ac.getBean("adminService");
        as.adminLoginCheck();
    }*/
/*    @Test
    public void testDes_0002() throws Exception {
        Des de1 = new Des();
        String msg ="abc";
        byte[] encontent = de1.Encrytor(msg);
        byte[] decontent = de1.Decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + (new String(encontent,"UTF-8")));
        System.out.println("解密后:" + (new String(decontent)));
    }*/

//测试Admin登录功能
      @Test
    public void testServiceAdmin_0003()
      {
          Admin admin4=new Admin();
          admin4.setAdmin_account("boss");
          admin4.setAdmin_pwd("114514");
          admin4.setSpot_id("GC");
          admin4.setStore_id("01");
          System.out.println(admin4);
          Admin admin3=new Admin();
          admin3.setAdmin_account("dana");
          admin3.setAdmin_pwd("114514");
          admin3.setSpot_id("GC");
          admin3.setStore_id("02");
          System.out.println(admin3);
          Admin admin2=new Admin();
          admin2.setAdmin_account("dana");
          admin2.setAdmin_pwd("114514");
          admin2.setSpot_id("GG");
          admin2.setStore_id("01");
          System.out.println(admin2);
          Admin admin1=new Admin();
          admin1.setAdmin_account("dana");
          admin1.setAdmin_pwd("1145141");
          admin1.setSpot_id("GC");
          admin1.setStore_id("01");
          System.out.println(admin1);
          Admin admin0=new Admin();
          admin0.setAdmin_account("dana3");
          admin0.setAdmin_pwd("114514");
          admin0.setSpot_id("GC");
          admin0.setStore_id("01");
          System.out.println(admin0);
          ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
          AdminService as=(AdminService)ac.getBean("adminService");
          System.out.println(as.adminLoginCheck(admin4));
          System.out.println(as.adminLoginCheck(admin3));
          System.out.println(as.adminLoginCheck(admin2));
          System.out.println(as.adminLoginCheck(admin1));
          System.out.println(as.adminLoginCheck(admin0));
      }
      //测试Admin表更新
      @Test
      public void testServiceAdmin_0004()
      {
          ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
          AdminService as=(AdminService)ac.getBean("adminService");
          as.adminUpdate("store_id","01","anc");
      }
      //测试Admin表新增
      @Test
    public void testServiceAdmin_0005()
      {
          ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
          AdminService as=(AdminService)ac.getBean("adminService");
          Admin adminex=new Admin();
          adminex.setAdmin_account("fn94");
          adminex.setAdmin_pwd("1919");
          adminex.setSpot_id("GC");
          adminex.setStore_id("01");
          as.adminNew(adminex);
      }
      //测试Admin表删除
    @Test
    public void testServiceAdmin_0006()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AdminService as=(AdminService)ac.getBean("adminService");
        as.adminDelete("fn94");
    }
    //测试User表寻找所有用户
    @Test
    public void testServiceUser_0007()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService userService=(UserService)ac.getBean("userService");
        List<User> users= userService.findAllUser();
        for (User user:users)
        {
            System.out.println(user);
        }
        System.out.println("done");
    }
    //测试User表寻找导游
    @Test
    public void testServiceUser_0008()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService userService=(UserService)ac.getBean("userService");
        User leader=userService.findLeader("13543026232");
        System.out.println(leader);
        System.out.println("done");
    }
    //测试User表新增User
    @Test
    public void testServiceUser_0009()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService userService=(UserService)ac.getBean("userService");
        User user=new User();
        user.setUser_account("13543026233");
        user.setUser_pwd("114514");
        user.setUser_type(1);
        user.setUser_rest(99.0);
        userService.userNew(user);
        System.out.println("done");
    }
    //测试User表修改
    @Test
    public void testServiceUser_0010()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService userService=(UserService)ac.getBean("userService");
        userService.userUpdate("user_rest","99","13543026233");
        userService.userUpdate("user_type","0","13543026233");
        userService.userUpdate("user_pwd","514514","13543026233");
        userService.userUpdate("user_account","13543026234","13543026233");
        System.out.println("done");
    }
    //测试User表删除
    @Test
    public void testServiceUser_0011()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService userService=(UserService)ac.getBean("userService");
        userService.userDelete("13543026234");
        System.out.println("done");
    }
    //测试Photo表查询所有Photo
    @Test
    public void testServicePhoto_0012()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        PhotoService photoService=(PhotoService)ac.getBean("photoService");
        List<Photo> photos=photoService.findAllPhoto();
        for (Photo photo:photos)
        {
            System.out.println(photo);
        }
        System.out.println("done");
    }
    //测试Photo表按照用户账号查询
    @Test
    public void testServicePhoto_0013()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        PhotoService photoService=(PhotoService)ac.getBean("photoService");
        List<String> strings = new ArrayList<String>();
        strings.add("13543026232");
        strings.add("13543026231");
        List<Photo> photos=photoService.findPhotoByUser_accountList(strings);
        for (Photo photo:photos)
        {
            System.out.println(photo);
        }
        System.out.println("done");
    }
    //测试Photo表设置照片为已打印状态
    @Test
    public void testServicePhoto_0014()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        PhotoService photoService=(PhotoService)ac.getBean("photoService");
        List<String> strings = new ArrayList<String>();
        strings.add("13543026232");
        strings.add("13543026231");
        photoService.printSouvenirTicket(strings);
        System.out.println("done");
    }
    //测试Photo表删除指定照片
    @Test
    public void testServicePhoto_0015()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        PhotoService photoService=(PhotoService)ac.getBean("photoService");
        photoService.photoDelete("IMG_0004");
    }
    //测试Print_style表新增
    @Test
    public void testServicePrint_style_0016()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Print_styleService print_styleService=(Print_styleService)ac.getBean("print_styleService");
        Print_style print_style=new Print_style();
        print_style.setStyle_id("02");
        print_style.setStyle_name("标准");
        print_style.setStyle_state("测试用的标准打印款式");
        print_style.setStyle_spot("GC");
        print_style.setStyle_cost(10.0);
        print_styleService.print_styleNew(print_style);
        System.out.println("done");
    }
    //测试Print_style表查询所有
    @Test
    public void testServicePrint_style_0017()
    {
/*        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Print_styleService print_styleService=(Print_styleService)ac.getBean("print_styleService");
        List<Print_style> print_styles=print_styleService.findAll();
        for (Print_style print_style:print_styles)
        {
            System.out.println(print_style);
        }
        System.out.println("done");*/
    }
    //测试Print_style表更新
    @Test
    public void testServicePrint_style_0018()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Print_styleService print_styleService=(Print_styleService)ac.getBean("print_styleService");
/*        print_styleService.print_styleUpdate("style_name","标准2","02");
        print_styleService.print_styleUpdate("style_state","测试用的标准打印格式2","02");
        print_styleService.print_styleUpdate("style_cost","12","02");
        print_styleService.print_styleUpdate("style_id","03","02");*/
        System.out.println("done");
    }
    //测试print_style表删除
    @Test
    public void testServicePrint_style_0019()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Print_styleService print_styleService=(Print_styleService)ac.getBean("print_styleService");
/*        print_styleService.print_styleDelete("02");*/
        System.out.println("done");
    }
    //测试spot表新增
    @Test
    public void testServiceSpot_0020()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SpotService spotService=(SpotService)ac.getBean("spotService");
        Spot spot=new Spot();
        spot.setSpot_id("GD");
        spot.setSpot_name("gdivision");
        spot.setSpot_state("gDivisionState");
        spotService.spotNew(spot);
        System.out.println("done");
    }
    //测试spot表更新
    @Test
    public void testServiceSpot_0021()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SpotService spotService=(SpotService)ac.getBean("spotService");
        spotService.spotUpdate("spot_name","gDive","GD");
        spotService.spotUpdate("spot_state","gDiveState2","GD");
        spotService.spotUpdate("spot_id","GD2","GD");
        System.out.println("done");
    }
    //测试Spot表查询所有
    @Test
    public void testServiceSpot_0022()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SpotService spotService=(SpotService)ac.getBean("spotService");
        List<Spot> spots=spotService.findAllSpot();
        for (Spot spot:spots)
        {
            System.out.println(spot);
        }
        System.out.println("done");
    }
    //测试Spot表根据ID查询
    @Test
    public void testServiceSpot_0023()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SpotService spotService=(SpotService)ac.getBean("spotService");
        Spot spot=spotService.findSpotById("GC");
        System.out.println(spot);
        System.out.println("done");
    }
    //测试Team表按照导游Account查询
    @Test
    public void testServiceTeam_0024()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TeamService teamService=(TeamService)ac.getBean("teamService");
        Team team=teamService.findTeamByLeaderAccount("13543026231");
        System.out.println(team);
        System.out.println("done");
    }
    //测试Team表删除
    @Test
    public void testServiceTeam_0025()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TeamService teamService=(TeamService)ac.getBean("teamService");
        teamService.teamDelete("13543026231_02");
        System.out.println("done");
    }
    //测试Team表标记已打印
    @Test
    public void testServiceTeam_0026()
    {
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TeamService teamService=(TeamService)ac.getBean("teamService");
        teamService.teamUpdatePrinted("13543026231");
        System.out.println("done");
    }
    //测试拆分字符串功能
    @Test
    public void testSplit_0027()
    {
        String test="13543026231,13543026231,13543026231,13543026231";
        String[] testsplit=test.split(",");
        for (String testt:testsplit)
        {
            System.out.println(testt);
        }
        System.out.println("done");
    }
    @Test
    public void testSplit_0028()
    {
        String test="/111/333/22/99.jpg";
        int div=test.lastIndexOf("/");
        String result=test.substring(div+1,test.length());
        System.out.println(result);
    }
    @Test
    public void test_0029() throws ParseException {
        Date date=new Date();
        System.out.println(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateS=sdf.format(date);
        System.out.println(dateS);
        Date changeDate=sdf.parse(dateS);
        System.out.println(changeDate);
    }
}
