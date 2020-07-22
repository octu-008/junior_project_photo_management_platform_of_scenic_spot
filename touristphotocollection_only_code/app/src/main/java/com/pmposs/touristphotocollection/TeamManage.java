package com.pmposs.touristphotocollection;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pmposs.model.Team;
import com.pmposs.utils.GsonService;
import com.pmposs.utils.TeamAdapter;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamManage extends AppCompatActivity {
    TextView teamShow;
    RecyclerView recyclerViewTeam;
    EditText memberAccount;
    Button newMember,refreshTeam,returnToMainTeam,clearTeam;
    Team teamOnline;
    String user_account;
    private static final int GET_TEAM_SUCCESS=1;
    private static final int ADD_MEMBER_SUCCESS=2;
    private static final int ADD_MEMBER_WRONG=3;
    private static final int ADD_MEMBER_FAIL=4;
    private static final int DELETE_TEAM_SUCCESS=5;
    private static final int DELETE_TEAM_FAIL=6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_manage);
        initView();
    }
    private final Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case GET_TEAM_SUCCESS:
                    setRecyclerView();
                    break;
                case ADD_MEMBER_SUCCESS:
                    setRefreshTeam();
                    teamShow.setText("*添加成功*");
                    break;
                case ADD_MEMBER_FAIL:
                    teamShow.setText("*添加失败，请检查输入的用户账号*");
                    break;
                case ADD_MEMBER_WRONG:
                    teamShow.setText("*输入账号不能为空或不等于11位*");
                    break;
                case DELETE_TEAM_SUCCESS:
                    teamShow.setText("*删除成功*");
                    teamOnline=null;
                    backToMain();
                    break;
                case DELETE_TEAM_FAIL:
                     teamShow.setText("*删除失败*");
                    break;
                default:
                    break;
            }
        }
    };
    public void initView()
    {
        getUser_account();
        teamShow=findViewById(R.id.teamShow);
        memberAccount=findViewById(R.id.memberAccount);
        newMember=findViewById(R.id.newMember);
        refreshTeam=findViewById(R.id.refreshTeam);
        clearTeam=findViewById(R.id.clearTeam);
        returnToMainTeam=findViewById(R.id.returnToMainTeam);
        newMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newAccount=memberAccount.getText().toString();
                if(newAccount == null || newAccount.equals(""))
                {
                    Message message=new Message();
                    message.what=ADD_MEMBER_WRONG;
                    handler.sendMessage(message);
                }
                else
                {
                    if (newAccount.length()!=11)
                    {
                        Message message=new Message();
                        message.what=ADD_MEMBER_WRONG;
                        handler.sendMessage(message);
                    }
                    else
                    {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    setNewMember(user_account,newAccount);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
            }
        });
        clearTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            setDeleteTeam();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        refreshTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRefreshTeam();
            }
        });
        returnToMainTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getTeam(user_account);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while(true)
                {
                    if(teamOnline ==null)
                    {
                        continue;
                    }
                    else
                    {
                        Message message=new Message();
                        message.what=GET_TEAM_SUCCESS;
                        handler.sendMessage(message);
                        break;
                    }
                }
            }
        }).start();
    }
    public void getUser_account()
    {
        Intent intent=getIntent();
        user_account=intent.getStringExtra("user_account");
    }
    public void setRecyclerView()
    {
        if(teamOnline !=null)
        {
            List<String> teamList=splitTeamMember(teamOnline.getTeam_member());
            recyclerViewTeam=findViewById(R.id.recyclerViewTeam);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            recyclerViewTeam.setLayoutManager(layoutManager);
            TeamAdapter adapter=new TeamAdapter(teamList);
            recyclerViewTeam.setAdapter(adapter);
        }
    }
    public void getTeam(String user_account) throws IOException {
        OkHttpClient client=new OkHttpClient();
        String address="http://192.168.1.105:8080/store_management/leaderGetTeam/"+user_account;
        Request request=new Request.Builder().url(address).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        GsonService service=new GsonService();
        Team team=service.parseTeamJSON(responseData);
        if(team != null)
        {
            teamOnline=team;
            System.out.println(team);
        }
    }
    public void setNewMember(String leader,String user_account) throws IOException {
        OkHttpClient client=new OkHttpClient();
        String address="http://192.168.1.105:8080/store_management/addTeamMemberCheck";
        RequestBody requestBody=new FormBody.Builder()
                .add("leader",leader)
                .add("user_account",user_account)
                .build();
        Request request=new Request.Builder().url(address).post(requestBody).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        if(responseData.equals("Success"))
        {
            Message message=new Message();
            message.what=ADD_MEMBER_SUCCESS;
            handler.sendMessage(message);
        }
        else
        {
            Message message=new Message();
            message.what=ADD_MEMBER_FAIL;
            handler.sendMessage(message);
        }
    }
    public void setDeleteTeam() throws IOException {
        OkHttpClient client=new OkHttpClient();
        String address="http://192.168.1.105:8080/store_management/deleteTeam/"+user_account;
        Request request=new Request.Builder().url(address).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        if(responseData.equals("Success"))
        {
            Message message=new Message();
            message.what=DELETE_TEAM_SUCCESS;
            handler.sendMessage(message);
        }
        else
        {
            Message message=new Message();
            message.what=DELETE_TEAM_FAIL;
            handler.sendMessage(message);
        }
    }
    public void setRefreshTeam()
    {
        if(teamOnline != null)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        teamOnline=null;
                        getTeam(user_account);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    while(true)
                    {
                        if(teamOnline ==null)
                        {
                            continue;
                        }
                        else
                        {
                            Message message=new Message();
                            message.what=GET_TEAM_SUCCESS;
                            handler.sendMessage(message);
                            break;
                        }
                    }
                }
            }).start();
        }
    }
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
    public void backToMain()
    {
        finish();
    }
}
