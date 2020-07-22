package com.pmposs.utils;

import com.pmposs.model.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class LeaderService {
    private User leader_online;
    private static final String head="http://192.168.1.105:8080/store_management/";
    public void refreshVisitor() throws IOException {
        OkHttpClient client=new OkHttpClient();
        String address=head+"userRefresh/"+leader_online.getUser_account();
        Request request=new Request.Builder().url(address).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        GsonService gsonService=new GsonService();
        User user=gsonService.parseUserJSON(responseData);
        setLeader_online(user);
    }
    public User getLeader_online() {
        return leader_online;
    }

    public void setLeader_online(User leader_online) {
        this.leader_online = leader_online;
    }
}
