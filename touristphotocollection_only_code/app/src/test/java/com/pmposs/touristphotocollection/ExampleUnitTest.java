package com.pmposs.touristphotocollection;

import com.google.gson.Gson;
import com.pmposs.model.Photo;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test001()
    {
        Photo photo1=new Photo();
        Date date=new Date();
        Photo photo2=new Photo();
        java.sql.Date date1=new java.sql.Date(date.getTime());
        Gson gson=new Gson();
        photo1.setPho_date(date);
        System.out.println(gson.toJson(photo1));
        System.out.println(gson.toJson(photo2));
    }
}