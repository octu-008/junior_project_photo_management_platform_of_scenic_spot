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
import com.pmposs.utils.VisitorService;
import okhttp3.*;

import java.io.IOException;

public class Recharge extends AppCompatActivity {
    EditText money;
    TextView rechargeTextShow;
    Button recharge,rechargeBackToMain;
    VisitorService visitorService;
    String user_account;
    Double user_rest;
    private final static int RECHARGE_SUCCESS=1;
    private final static int RECHARGE_FAIL=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initView();
    }
    public void initView()
    {
        money=findViewById(R.id.money);
        rechargeTextShow=findViewById(R.id.rechargeTextShow);
        recharge=findViewById(R.id.recharge);
        rechargeBackToMain=findViewById(R.id.rechargeBackToMain);
        visitorService=new VisitorService();
        getUserAccount();
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recharge();
            }
        });
        rechargeBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }
    private final Handler handler=new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case RECHARGE_SUCCESS:
                    rechargeTextShow.setText("*充值成功，请返回主页刷新查看*");
                    break;
                case RECHARGE_FAIL:
                    rechargeTextShow.setText("*充值失败，请重试*");
                    break;
                default:
                    break;
            }
        }
    };
    public void recharge()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String rechargeMoney=money.getText().toString();
                try {
                    String msg=rechargeVisitor(rechargeMoney);
                    if(msg.equals("RechargeSuccess"))
                    {
                        Message message=new Message();
                        message.what=RECHARGE_SUCCESS;
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void getUserAccount()
    {
        Intent intent=getIntent();
        user_account=intent.getStringExtra("user_account");
        user_rest=intent.getDoubleExtra("user_rest",0);
    }
    public void backToMain()
    {
        Intent intent=new Intent();
        intent.putExtra("return_v","recharge->login");
        setResult(RESULT_OK,intent);
        finish();
    }
    public String rechargeVisitor(String recharge) throws IOException {
        OkHttpClient client=new OkHttpClient();
        String address="http://192.168.1.105:8080/store_management/user/recharge";
        RequestBody requestBody=new FormBody.Builder()
                .add("user_account",user_account)
                .add("rest",String.valueOf(user_rest))
                .add("recharge",recharge).build();
        Request request=new Request.Builder().url(address).post(requestBody).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        return responseData;
    }
}
