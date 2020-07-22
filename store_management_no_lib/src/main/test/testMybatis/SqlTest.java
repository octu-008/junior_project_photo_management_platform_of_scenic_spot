package testMybatis;

import com.pmposs.dao.IAdminDao;
import com.pmposs.dao.ISpotDao;
import com.pmposs.model.Admin;
import com.pmposs.model.Spot;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class SqlTest {
    private InputStream in;
    private SqlSession sqlSession;
    //执行测试前的配置
    @Before
    public void init() throws Exception
    {
        in= Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        sqlSession=factory.openSession();
    }
    //执行测试后销毁配置
    @After
    public void destroy() throws Exception
    {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    //测试查询Admin功能
    @Test
    public void test_0001()
    {
        IAdminDao iAdminDao=sqlSession.getMapper(IAdminDao.class);
        List<Admin> admins=iAdminDao.findAll();
        for (Admin admin:admins)
        {
            System.out.println(admin);
        }
    }
    @Test
    public void test_0002()
    {
        ISpotDao iSpotDao=sqlSession.getMapper(ISpotDao.class);
        List<Spot> spots=iSpotDao.findAll();
        for (Spot spot:spots)
        {
            System.out.println(spot);
        }
    }
}
