package com.pmposs.touristphotocollection;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.pmposs.utils.SelectStyleService;
import com.pmposs.utils.SpinnerAdapter;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class VisitorSelect extends AppCompatActivity {
    Button getSpotList,uploadToLeader,uploadToSelfService,backToSelectPhoto;
    TextView stateOfStyle,styleName,styleState,styleSpot,styleCost,uploadShow;
    Spinner spotList,selectStyleList;
    private SelectStyleService selectStyleService;
    private String spotLocal="";
    private String user_account;
    private String picTruePath;
    private static final int GET_SPOT_LIST_SUCCESS=1;
    private static final int GET_SPOT_LIST_FAIL=2;
    private static final int GET_PS_LIST_SUCCESS=3;
    private static final int GET_PS_LIST_FAIL=4;
    private static final int SELECT_SPOT=5;
    private static final int SELECT_PRINT_STYLE=6;
    private static final int UPLOAD_SUCCESS=7;
    private static final int UPLOAD_FAIL=8;
    SpinnerAdapter spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_select);
        initView();
    }
    public void initView()
    {
        //初始化界面
        getSpotList=findViewById(R.id.getSpotList);
        uploadToLeader=findViewById(R.id.uploadToLeader);
        uploadToSelfService=findViewById(R.id.uploadToSelfService);
        backToSelectPhoto=findViewById(R.id.backToSelectPhoto);
        stateOfStyle=findViewById(R.id.stateOfStyle);
        styleName=findViewById(R.id.styleName);
        styleState=findViewById(R.id.styleState);
        styleSpot=findViewById(R.id.styleSpot);
        styleCost=findViewById(R.id.styleCost);
        uploadShow=findViewById(R.id.uploadShow);
        spotList=findViewById(R.id.spotList);
        selectStyleList=findViewById(R.id.selectStyleList);
        selectStyleService=new SelectStyleService();
        setAccountAndPicTruePath();
        //设置点击事件
        getSpotList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                setSpotSpinner();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
            }
        });
        uploadToLeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            uploadPhotoToServer("leader");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        uploadToSelfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            uploadPhotoToServer("auto");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        backToSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToSelectPhoto();
            }
        });
    }
    public void setAccountAndPicTruePath()
    {
        Intent intent=getIntent();
        user_account=intent.getStringExtra("user_account");
        picTruePath=intent.getStringExtra("picTruePath");
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case GET_SPOT_LIST_SUCCESS:
                    spotList.setAdapter(spinnerAdapter);
                    setSpotSpinnerClickEvent();
                    break;
                case SELECT_SPOT:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                setPrintStyleSpinner(spotLocal);
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    break;
                case GET_PS_LIST_SUCCESS:
                    selectStyleList.setAdapter(spinnerAdapter);
                    setPrintStyleClickEvent();
                    break;
                case SELECT_PRINT_STYLE:
                    stateOfStyle.setText("打印风格说明");
                    styleName.setText("风格名称:"+selectStyleService.getSelectedPrint_style().getStyle_name());
                    styleState.setText("风格说明:"+selectStyleService.getSelectedPrint_style().getStyle_state());
                    styleSpot.setText("风格所属景区代号:"+selectStyleService.getSelectedPrint_style().getStyle_spot());
                    styleCost.setText("花费（元/每张）:"+selectStyleService.getSelectedPrint_style().getStyle_cost());
                    break;
                case UPLOAD_SUCCESS:
                    uploadPhotoFile(picTruePath);
                    uploadShow.setText("*上传成功，请返回主页查看*");
                    break;
                case UPLOAD_FAIL:
                    uploadShow.setText("*上传失败，请返回重新尝试*");
                    break;
                default:
                    break;
            }
        }
    };
    public void backToSelectPhoto()
    {
        Intent intent=new Intent();
        intent.putExtra("return_s","select>upload");
        setResult(RESULT_OK,intent);
        finish();
    }
    public void uploadPhotoToServer(String type) throws IOException {
        if(spotList.getSelectedItem().toString()!=null && selectStyleList.getSelectedItem().toString() !=null)
        {
            String spot_id=selectStyleService.getSPotIdBySPotName(spotList.getSelectedItem().toString());
            String uploadCheck=selectStyleService.upLoadPhoto(picTruePath,user_account,spot_id,type);
            if(uploadCheck.equals("Success"))
            {
                Message message=new Message();
                message.what=UPLOAD_SUCCESS;
                handler.sendMessage(message);
            }
            else
            {
                Message message=new Message();
                message.what=UPLOAD_FAIL;
                handler.sendMessage(message);
            }
        }
        else
        {
            Toast.makeText(this,"请您先选择所在景区和打印风格",Toast.LENGTH_LONG).show();
        }
    }
    public void setSpotSpinner() throws IOException {
        List<String> spotNameList=selectStyleService.getSpotNameList();
        if(spotNameList !=null)
        {
            spinnerAdapter=new SpinnerAdapter(this,android.R.layout.simple_spinner_item,spotNameList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Message message=new Message();
            message.what=GET_SPOT_LIST_SUCCESS;
            handler.sendMessage(message);
        }
    }
    public void setPrintStyleSpinner(String spot_id)throws IOException
    {
        List<String> printStyleList=selectStyleService.getPrintStyleNameList(spot_id);
        if(printStyleList !=null)
        {
            spinnerAdapter=new SpinnerAdapter(this,android.R.layout.simple_spinner_item,printStyleList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Message message=new Message();
            message.what=GET_PS_LIST_SUCCESS;
            handler.sendMessage(message);
        }
    }
    public void setSpotSpinnerClickEvent()
        {
            spotList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String data=(String)spotList.getItemAtPosition(position);
                    String spot_id=selectStyleService.getSPotIdBySPotName(data);
                    if(spot_id !=null)
                    {
                        spotLocal=spot_id;
                        Message message=new Message();
                        message.what=SELECT_SPOT;
                        handler.sendMessage(message);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    public void setPrintStyleClickEvent()
    {
        selectStyleList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data=(String)selectStyleList.getItemAtPosition(position);
                selectStyleService.setSelectedPrint_style(data);
                Message message=new Message();
                message.what=SELECT_PRINT_STYLE;
                handler.sendMessage(message);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void uploadPhotoFile(String picTruePath)
    {
        Log.d("TAG" ,"uploadPhotoFile: "+picTruePath);
        OkHttpClient client=new OkHttpClient();
        if(picTruePath !=null)
        {
            File file=new File(picTruePath);
            MediaType mediaType=MediaType.parse("image/jpeg");
            RequestBody fileBody=RequestBody.Companion.create(file,mediaType);
            RequestBody body=new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("photo",file.getName(),fileBody)
                    .build();
            Request request=new Request.Builder()
                    .url("http://192.168.1.105:8080/store_management/uploadPhoto")
                    .post(body)
                    .build();
            Call call=client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d("TAG", "onFailure: "+"FAILFAIL");
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(response.isSuccessful())
                    {
                        Log.d("TAG", "onResponse: "+"success!!");
                    }
                    else
                    {
                        Log.d("TAG", "onResponse: "+"fail!!");
                    }
                }
            });
        }
    }
}
