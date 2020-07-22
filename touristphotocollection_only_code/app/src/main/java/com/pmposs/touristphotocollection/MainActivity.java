package com.pmposs.touristphotocollection;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.pmposs.utils.UserService;

public class MainActivity extends AppCompatActivity {
    EditText user_account,user_pwd;
    Spinner user_type;
    Button login,register,forgotPassword;
    TextView text_show;
    UserService userService;
    private final static int TO_REGISTER=1;
    private final static int TO_FORGOT_PASSWORD=2;
    private final static int SUCCESS_LOGIN=3;
    private final static int FAIL_LOGIN=4;
    private final static int NORMAL=5;
    private final static int CHECK_LOGIN=6;
    private final static int VISITOR_MAIN=7;
    private final static int LEADER_MAIN=8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    public void initView()
    {
        //初始化
        user_account=findViewById(R.id.user_account);
        user_pwd=findViewById(R.id.user_pwd);
        user_type=findViewById(R.id.user_type);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        forgotPassword=findViewById(R.id.forgotPassword);
        text_show=findViewById(R.id.text_show_login);
        userService=new UserService();
        //设置点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=user_account.getText().toString();
                String pwd=user_pwd.getText().toString();
                if(account == null || account.equals("") )
                {
                    Message message=new Message();
                    message.what=FAIL_LOGIN;
                    handler.sendMessage(message);
                    return ;
                }
                if (pwd == null || pwd.equals(""))
                {
                    Message message=new Message();
                    message.what=FAIL_LOGIN;
                    handler.sendMessage(message);
                    return ;
                }
                sendLoginRequest();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister();
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toForgotP();
            }
        });
    }
    private final Handler handler=new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what)
            {
                case SUCCESS_LOGIN:
                    text_show.setText("*登录成功*");
                    break;
                case FAIL_LOGIN:
                    text_show.setText("*登录失败，请检查登录信息*");
                    break;
                case CHECK_LOGIN:
                    text_show.setText("*登录中*");
                    break;
                case NORMAL:
                    text_show.setText("");
                    user_account.setText("");
                    user_pwd.setText("");
                    break;
                default:
                    break;
            }
        }
    };
    public void toRegister()
    {
        Intent intent=new Intent("com.pmposs.touristphotocollection.REGISTER_START");
        startActivityForResult(intent,TO_REGISTER);
    }
    public void toForgotP()
    {
        Intent intent=new Intent("com.pmposs.touristphotocollection.FORGOTP_START");
        startActivityForResult(intent,TO_FORGOT_PASSWORD);
    }
    public void sendLoginRequest()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean loginCheck=userService.loginCheck("user/login",
                            user_account.getText().toString(),user_pwd.getText().toString(),user_type.getSelectedItem().toString());
                    if(loginCheck)
                    {
                        Message message=new Message();
                        message.what=SUCCESS_LOGIN;
                        handler.sendMessage(message);
                        if(userService.getUser_online().getUser_type() == 0)
                        {
                            toLeaderMain();
                        }
                        else
                        {
                            toVisitorMain();
                        }
                    }
                    else
                    {
                        Message message=new Message();
                        message.what=FAIL_LOGIN;
                        handler.sendMessage(message);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void toVisitorMain()
    {
        Intent intent=new Intent("com.pmposs.touristphotocollection.VISITOR_START");
        intent.putExtra("user_account",userService.getUser_online().getUser_account());
        intent.putExtra("user_pwd",userService.getUser_online().getUser_pwd());
        intent.putExtra("user_type",userService.getUser_online().getUser_type());
        intent.putExtra("user_rest",userService.getUser_online().getUser_rest());
        startActivityForResult(intent,VISITOR_MAIN);
    }
    public void toLeaderMain()
    {
     Intent intent=new Intent("com.pmposs.touristphotocollection.LEADER_MAIN");
        intent.putExtra("user_account",userService.getUser_online().getUser_account());
        intent.putExtra("user_pwd",userService.getUser_online().getUser_pwd());
        intent.putExtra("user_type",userService.getUser_online().getUser_type());
        intent.putExtra("user_rest",userService.getUser_online().getUser_rest());
        startActivityForResult(intent,LEADER_MAIN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode)
        {
            case TO_REGISTER:
            case TO_FORGOT_PASSWORD:
            case LEADER_MAIN:
            case VISITOR_MAIN:
                if(resultCode == RESULT_OK)
                {
                    Message message1=new Message();
                    message1.what=NORMAL;
                    handler.sendMessage(message1);
                }
                break;
            default:
                break;
        }
    }
}
