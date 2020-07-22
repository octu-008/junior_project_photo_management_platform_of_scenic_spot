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
import com.pmposs.utils.VisitorService;

import java.io.IOException;

public class VisitorMain extends AppCompatActivity {
    TextView visitorOnlineAccount,visitorRest;
    Button visitorRefresh,upload,visitorCheckPhoto,visitorRecharge,visitorLogOut;
    VisitorService visitorService;
    private static final int TO_VISITORUPLOADPHOTO=1;
    private static final int TO_PHOTOUPLOAD=2;
    private static final int TO_RECHARGE=3;
    private static final int REFRESH_VISITOR=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_main);
        initView();
    }
    public void initView()
    {
        //初始化界面
        visitorOnlineAccount=findViewById(R.id.visitorOnlineAccount);
        visitorRest=findViewById(R.id.visitorRest);
        visitorRefresh=findViewById(R.id.visitorRefresh);
        upload=findViewById(R.id.upload);
        visitorCheckPhoto=findViewById(R.id.visitorCheckPhoto);
        visitorRecharge=findViewById(R.id.visitorRecharge);
        visitorLogOut=findViewById(R.id.visitorLogOut);
        initViewLoginInfo();
        //设置游客信息
        visitorOnlineAccount.setText(visitorService.getVisitor_online().getUser_account());
        visitorRest.setText(String.valueOf(visitorService.getVisitor_online().getUser_rest()));
        //设置点击事件
        visitorLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUploadPhoto();
            }
        });
        visitorCheckPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCheckOutPhoto();
            }
        });
        visitorRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toReCharge();
            }
        });
        visitorRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            visitorService.refreshVisitor();
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
    }
    private final Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case REFRESH_VISITOR:
                    visitorRest.setText(String.valueOf(visitorService.getVisitor_online().getUser_rest()));
                default:
                    break;
            }
        }
    };
    public void initViewLoginInfo()
    {
        //取得登录信息
        Intent intent=getIntent();
        User user=new User();
        visitorService=new VisitorService();
        user.setUser_account(intent.getStringExtra("user_account"));
        user.setUser_pwd(intent.getStringExtra("user_pwd"));
        user.setUser_type(intent.getIntExtra("user_type",1));
        user.setUser_rest(intent.getDoubleExtra("user_rest",0.00));
        visitorService.setVisitor_online(user);
    }
    public void toCheckOutPhoto()
    {
        Intent intent=new Intent("com.pmposs.toursitphotocollection.PHOTOUPLOAD_START");
        intent.putExtra("user_account",visitorService.getVisitor_online().getUser_account());
        intent.putExtra("user_type",visitorService.getVisitor_online().getUser_type());
        startActivityForResult(intent,TO_PHOTOUPLOAD);
    }
    public void toReCharge()
    {
        Intent intent=new Intent("com.pmposs.toursitphotocollection.RECHARGE_START");
        intent.putExtra("user_account",visitorService.getVisitor_online().getUser_account());
        intent.putExtra("user_rest",visitorService.getVisitor_online().getUser_rest());
        startActivityForResult(intent,TO_RECHARGE);
    }
    public void backToLogin()
    {
        Intent intent=new Intent();
        intent.putExtra("return_v","visitor->login");
        setResult(RESULT_OK,intent);
        finish();
    }
    public void toUploadPhoto()
    {
        Intent intent=new Intent("com.pmposs.touristphotocollection.VISITORUPLOAD_START");
        intent.putExtra("user_account",visitorService.getVisitor_online().getUser_account());
        startActivityForResult(intent,TO_VISITORUPLOADPHOTO);
    }
}
