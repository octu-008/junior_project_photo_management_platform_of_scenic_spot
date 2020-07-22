package com.pmposs.touristphotocollection;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.pmposs.utils.UserService;

public class register extends AppCompatActivity {
    EditText register_user_account,register_user_pwd,register_user_pwd_again;
    Spinner register_user_type;
    Button register_check,register_backToLogin;
    TextView register_text_show;
    UserService userService;
    private final static int REGISTER_SUCCESS=1;
    private final static int REGISTER_FAIL=2;
    private final static int REGISTERING=3;
    private final static int NORMAL=4;
    private final static int WRONG_ACCOUNT=5;
    private final static int WRONG_PASSWORD=6;
    private final static int NULL_PASSWORD=7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
    }
    public void initView()
    {
        //初始化
        register_user_account=findViewById(R.id.register_user_account);
        register_user_pwd=findViewById(R.id.register_user_pwd);
        register_user_pwd_again=findViewById(R.id.register_user_pwd_again);
        register_user_type=findViewById(R.id.register_user_type);
        register_check=findViewById(R.id.register_check);
        register_backToLogin=findViewById(R.id.register_backToLogin);
        register_text_show=findViewById(R.id.register_text_show);
        userService=new UserService();
        //设置点击事件
        register_backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });
        register_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=register_user_account.getText().toString();
                String pwd=register_user_pwd.getText().toString();
                String pwdr=register_user_pwd_again.getText().toString();
                if(account.length() == 11)
                {
                    if (pwd != null && pwdr !=null )
                    {
                        if(pwd.equals(pwdr))
                        {
                            sendRegisterRequest();
                        }
                        else
                        {
                            Message message=new Message();
                            message.what=WRONG_PASSWORD;
                            handler.sendMessage(message);
                        }
                    }
                    else
                    {
                        Message message=new Message();
                        message.what=NULL_PASSWORD;
                        handler.sendMessage(message);
                    }
                }
                else {
                    Message message=new Message();
                    message.what=WRONG_ACCOUNT;
                    handler.sendMessage(message);
                }
            }
        });
    }
    private final Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case REGISTERING:
                    register_text_show.setText("*注册中*");
                    break;
                case REGISTER_FAIL:
                    register_text_show.setText("*注册失败,请检查输入信息后重试*");
                    break;
                case REGISTER_SUCCESS:
                    register_text_show.setText("*注册成功，请返回登录*");
                    break;
                case WRONG_ACCOUNT:
                    register_text_show.setText("*注册账号需要为11位*");
                    break;
                case WRONG_PASSWORD:
                    register_text_show.setText("*两次密码输入不匹配*");
                    break;
                case NULL_PASSWORD:
                    register_text_show.setText("*输入注册密码不能为空*");
                    break;
                default:
                    break;
            }
        }
    };
    public void sendRegisterRequest()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Message preMessage=new Message();
                    preMessage.what=REGISTERING;
                    handler.sendMessage(preMessage);
                    String msg=userService.registerCheck("user/register",register_user_account.getText().toString()
                    ,register_user_pwd.getText().toString(),register_user_type.getSelectedItem().toString());
                    if (msg.equals("success"))
                    {
                        Message message=new Message();
                        message.what=REGISTER_SUCCESS;
                        handler.sendMessage(message);
                    }
                    else
                    {
                        Message message=new Message();
                        message.what=REGISTER_FAIL;
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
    public void backToLogin()
    {
        Intent intent=new Intent();
        intent.putExtra("return_r","register->login");
        setResult(RESULT_OK,intent);
        finish();
    }
}
