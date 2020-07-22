package com.pmposs.touristphotocollection;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.pmposs.model.User;
import com.pmposs.utils.LeaderService;

import java.io.IOException;

public class LeaderMain extends AppCompatActivity {
    TextView leaderOnlineAccount,leaderRest;
    Button leaderRefresh,uploadVisitorPhoto,leaderRecharge,teamCheckout,leaderLogOut;
    LeaderService leaderService;
    private static final int TO_PHOTOUPLOAD=1;
    private static final int REFRESH_VISITOR=2;
    private static final int TO_RECHARGE=3;
    private static final int TO_TEAMMANAGE=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_main);
        initView();
    }
    private final Handler handler=new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case REFRESH_VISITOR:
                    leaderRest.setText(String.valueOf(leaderService.getLeader_online().getUser_rest()));
                default:
                    break;
            }
        }
    };
    public void initView()
    {
        //初始化页面
        leaderOnlineAccount=findViewById(R.id.leaderOnlineAccount);
        leaderRest=findViewById(R.id.leaderRest);
        leaderRefresh=findViewById(R.id.leaderRefresh);
        uploadVisitorPhoto=findViewById(R.id.uploadVisitorPhoto);
        leaderRecharge=findViewById(R.id.leaderRecharge);
        teamCheckout=findViewById(R.id.teamCheckout);
        leaderLogOut=findViewById(R.id.leaderLogOut);
        initViewLoginInfo();
        //设置导游信息
        leaderOnlineAccount.setText(leaderService.getLeader_online().getUser_account());
        leaderRest.setText(String.valueOf(leaderService.getLeader_online().getUser_rest()));
        //设置点击事件
        leaderLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });
        uploadVisitorPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUploadVisitorPhoto();
            }
        });
        leaderRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            leaderService.refreshVisitor();
                            Message message=new Message();
                            message.what=REFRESH_VISITOR;
                            handler.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        leaderRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toReCharge();
            }
        });
        teamCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTeamManage();
            }
        });
    }
    public void initViewLoginInfo()
    {
        //取得登录信息
        Intent intent=getIntent();
        User user=new User();
        leaderService=new LeaderService();
        user.setUser_account(intent.getStringExtra("user_account"));
        user.setUser_pwd(intent.getStringExtra("user_pwd"));
        user.setUser_type(intent.getIntExtra("user_type",1));
        user.setUser_rest(intent.getDoubleExtra("user_rest",0.00));
        leaderService.setLeader_online(user);
    }
    public void backToLogin()
    {
        Intent intent=new Intent();
        intent.putExtra("return_l","leader->login");
        setResult(RESULT_OK,intent);
        finish();
    }
    public void toUploadVisitorPhoto()
    {
        Intent intent=new Intent("com.pmposs.toursitphotocollection.PHOTOUPLOAD_START");
        intent.putExtra("user_account",leaderService.getLeader_online().getUser_account());
        intent.putExtra("user_type",leaderService.getLeader_online().getUser_type());
        startActivityForResult(intent,TO_PHOTOUPLOAD);
    }
    public void toReCharge()
    {
        Intent intent=new Intent("com.pmposs.toursitphotocollection.RECHARGE_START");
        intent.putExtra("user_account",leaderService.getLeader_online().getUser_account());
        intent.putExtra("user_rest",leaderService.getLeader_online().getUser_rest());
        startActivityForResult(intent,TO_RECHARGE);
    }
    public void toTeamManage()
    {
        Intent intent=new Intent("com.pmposs.toursitphotocollection.TEAMMANAGE_START");
        intent.putExtra("user_account",leaderService.getLeader_online().getUser_account());
        startActivityForResult(intent,TO_TEAMMANAGE);
    }
}
