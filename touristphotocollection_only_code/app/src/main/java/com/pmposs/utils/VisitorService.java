package com.pmposs.utils;

import com.pmposs.model.User;
import okhttp3.*;

import java.io.IOException;


public class VisitorService  {
    private User visitor_online;
    private static final String head="http://192.168.1.105:8080/store_management/";
    public void refreshVisitor() throws IOException {
        OkHttpClient client=new OkHttpClient();
        String address=head+"userRefresh/"+visitor_online.getUser_account();
        Request request=new Request.Builder().url(address).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        GsonService gsonService=new GsonService();
        User user=gsonService.parseUserJSON(responseData);
        setVisitor_online(user);
    }
    public User getVisitor_online() {
        return visitor_online;
    }

    public void setVisitor_online(User visitor_online) {
        this.visitor_online = visitor_online;
    }

}
