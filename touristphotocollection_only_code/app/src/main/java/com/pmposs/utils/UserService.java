package com.pmposs.utils;


import com.pmposs.model.User;
import okhttp3.*;

import java.io.IOException;

public class UserService {
    private User user_online;
    private static final String head="http://192.168.1.105:8080/store_management/";
    public boolean loginCheck(String address,String user_account,String user_password,
                              String user_type)throws IOException
    {
        boolean check=true;
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()
                .add("user_account",user_account)
                .add("user_password",user_password)
                .add("user_type",user_type).build();
        Request request=new Request.Builder().url(head+address).post(requestBody).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        GsonService service=new GsonService();
        user_online=service.parseUserJSON(responseData);
        if(user_online !=null)
        {
            return check;
        }
        else
        {
            check=false;
            return check;
        }
    }
    public String registerCheck(String address,String user_account,
                                String user_password,String user_type) throws IOException {
        String msg="**";
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()
                .add("user_account",user_account)
                .add("user_password",user_password)
                .add("user_type",user_type).build();
        Request request=new Request.Builder().url(head+address).post(requestBody).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        msg=responseData;
        return msg;
    }
    public String getPassword(String address,String user_account) throws IOException {
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()
                .add("user_account",user_account).build();
        Request request=new Request.Builder().url(head+address).post(requestBody).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        if (responseData == null || responseData.equals(" ") || responseData.equals(""))
        {
           return null;
        }
        else
        {
            return responseData;
        }
    }
    public User getUser_online() {
        return user_online;
    }

    public void setUser_online(User user_online) {
        this.user_online = user_online;
    }
}
