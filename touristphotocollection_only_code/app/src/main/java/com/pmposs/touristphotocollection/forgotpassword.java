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
import com.pmposs.utils.UserService;

public class forgotpassword extends AppCompatActivity {
    EditText user_account_forgot;
    Button getForgotPassword,forgot_backToLogin;
    TextView text_show_forgot;
    UserService userService;
    String successMessage;
    static private final int GET_PASSWORD_SUCCESS=1;
    static private final int GET_PASSWORD_FAIL=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        initView();
    }
    private final Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case GET_PASSWORD_SUCCESS:
                    text_show_forgot.setText("您的密码是："+successMessage);
                    break;
                case GET_PASSWORD_FAIL:
                    text_show_forgot.setText("未找到该账号的密码");
                    break;
                default:
                    break;
            }
        }
    };
    public void initView()
    {
        //初始化
        user_account_forgot=findViewById(R.id.user_account_forgot);
        getForgotPassword=findViewById(R.id.getForgotPassword);
        forgot_backToLogin=findViewById(R.id.forgot_backToLogin);
        text_show_forgot=findViewById(R.id.text_show_forgot);
        userService=new UserService();
        //设置点击事件
        forgot_backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });
        getForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendForgotPasswordRequest();
            }
        });
    }
    public void sendForgotPasswordRequest()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String backMessage=userService.getPassword("user/forgotPassword"
                            ,user_account_forgot.getText().toString());
                    if(backMessage == null)
                    {
                        Message message=new Message();
                        message.what=GET_PASSWORD_FAIL;
                        handler.sendMessage(message);
                    }
                    else
                    {
                        successMessage=backMessage;
                        Message message=new Message();
                        message.what=GET_PASSWORD_SUCCESS;
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
        intent.putExtra("return_f","forgotPassword->login");
        setResult(RESULT_OK,intent);
        finish();
    }
}
