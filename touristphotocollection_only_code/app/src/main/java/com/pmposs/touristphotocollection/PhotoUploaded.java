package com.pmposs.touristphotocollection;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.pmposs.model.Photo;
import com.pmposs.model.Team;
import com.pmposs.utils.GsonService;
import com.pmposs.utils.PhotoAdapter;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoUploaded extends AppCompatActivity {
    RecyclerView recyclerViewUploaded;
    List<Photo> photoList;
    List<Bitmap> bitmapList;
    GsonService service;
    TextView uploadedShow;
    String user_account;
    Button returnToMain;
    int user_type;
    private static final int CHECKING=1;
    private static final int DOWN_SUCCESS=2;
    private int count=0;
    private static final String head="http://192.168.1.105:8080/store_management/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_uploaded);
        initView();
    }
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case CHECKING:
                    uploadedShow.setText("*正在获取..*");
                    break;
                case DOWN_SUCCESS:
                    uploadedShow.setText("*获取成功！正在生成预览..*");
                    initRecycleView();
                    uploadedShow.setText("*生成成功*");
                    uploadedShow.setText("");
                    break;
                default:
                    break;
            }
        }
    };
    public void initView()
    {
        setAccountAndType();
        service=new GsonService();
        bitmapList=new ArrayList<Bitmap>();
        uploadedShow=findViewById(R.id.uploadedShow);
        returnToMain=findViewById(R.id.returnToMain);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
        Message message=new Message();
        message.what=CHECKING;
        handler.sendMessage(message);
        initPhotoUploaded();
    }
    public void backToMain()
    {
        Intent intent=new Intent();
        intent.putExtra("return_u","uploaded->main");
        setResult(RESULT_OK,intent);
        finish();
    }
    public void initRecycleView()
    {
        if(photoList !=null && bitmapList != null )
        {
            recyclerViewUploaded=findViewById(R.id.recyclerViewUploaded);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            recyclerViewUploaded.setLayoutManager(layoutManager);
            PhotoAdapter adapter=new PhotoAdapter(photoList,bitmapList);
            recyclerViewUploaded.setAdapter(adapter);
        }
    }
    public void setAccountAndType()
    {
        Intent intent=getIntent();
        user_account=intent.getStringExtra("user_account");
        user_type=intent.getIntExtra("user_type",1);
    }
    public void initPhotoUploaded() {
        if (user_type ==1)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        setPhotoListVisitor(user_account);
                        while(true)
                        {
                            if(photoList.size() == bitmapList.size())
                            {
                                Message message=new Message();
                                message.what=DOWN_SUCCESS;
                                handler.sendMessage(message);
                                break;
                            }
                            else
                            {
                                continue;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        else
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        setPhotoListLeader(user_account);
                        while(true)
                        {
                            if(photoList.size() == bitmapList.size())
                            {
                                Message message=new Message();
                                message.what=DOWN_SUCCESS;
                                handler.sendMessage(message);
                                break;
                            }
                            else
                            {
                                continue;
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public void setPhotoListLeader(String user_account) throws IOException
    {
        OkHttpClient client1=new OkHttpClient();
        String address=head+"leaderGetTeam/"+user_account;
        Request request=new Request.Builder().url(address).build();
        Response response=client1.newCall(request).execute();
        String responseData=response.body().string();
        GsonService gsonService=new GsonService();
        Team teamGet=gsonService.parseTeamJSON(responseData);
        if(teamGet!=null)
        {
            OkHttpClient client=new OkHttpClient();
            String address1=head+"leaderGetTeamPhoto";
            Gson gson=new Gson();
            String json=gson.toJson(teamGet);
            RequestBody requestBody=FormBody.create(MediaType.parse("application/json; charset=utf-8"),json);
            Request request1=new Request.Builder()
                    .url(address1)
                    .post(requestBody)
                    .build();
            Response response1=client.newCall(request1).execute();
            String responseData1=response1.body().string();
            List<Photo> photos=gsonService.parsePhotoJSON(responseData1);
            if(photos != null)
            {
                int bitcount=bitmapList.size();
                photoList=photos;
                for(Photo photo:photoList)
                {
                    setBitmapList(photo.getPho_path());
                    while(true)
                    {
                        if(bitcount == bitmapList.size())
                        {
                            continue;
                        }
                        else
                        {
                            bitcount=bitmapList.size();
                            break;
                        }
                    }
                }
            }
            else
            {
                return ;
            }
        }
    }
    public void setPhotoListVisitor(String user_account) throws IOException {
        OkHttpClient client=new OkHttpClient();
        String address=head+"getPhoto/"+user_account;
        Request request=new Request.Builder().url(address).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        List<Photo> photos=service.parsePhotoJSON(responseData);
        if(photos != null)
        {
            int bitcount=bitmapList.size();
            photoList=photos;
            for(Photo photo:photoList)
            {
                setBitmapList(photo.getPho_path());
                while(true)
                {
                    if(bitcount == bitmapList.size())
                    {
                        continue;
                    }
                    else
                    {
                        bitcount=bitmapList.size();
                        break;
                    }
                }
            }
        }
        else
        {
            return ;
        }
    }
    public void setBitmapList(String pho_path)
    {
        OkHttpClient client=new OkHttpClient();
        String address="http://192.168.1.105:8080/"+pho_path;
        Log.d("TAG:setBitmapList", "onResponse: "+address);
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                Log.d("TAG:onFailure", "onFailure: FAIL");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                byte[] result=(byte[])response.body().bytes();
                Bitmap bitmap= BitmapFactory.decodeByteArray(result,0,result.length);
                bitmapList.add(bitmap);
            }
        });
    }
}
