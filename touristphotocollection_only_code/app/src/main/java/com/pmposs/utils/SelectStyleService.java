package com.pmposs.utils;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pmposs.model.Photo;
import com.pmposs.model.Print_style;
import com.pmposs.model.Spot;
import okhttp3.*;

import java.io.IOException;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;



public class SelectStyleService {
    private static final String head="http://192.168.1.105:8080/store_management/";
    private List<Spot> spotList;
    private List<Print_style> print_styleList;
    private Print_style selectedPrint_style;
    private GsonService service=new GsonService();
    public String getSPotIdBySPotName(String spot_name)
    {
        String spot_id="";
        if(spotList!=null)
        {
            for(Spot spot:spotList)
            {
                if(spot_name.equals(spot.getSpot_name()))
                {
                    spot_id=spot.getSpot_id();
                    break;
                }
            }
        }
        return spot_id;
    }
    public String upLoadPhoto(String picTruePath,String user_account,String spot_id,String uploadType) throws IOException {
        Photo photo=new Photo();
        String pho_name=getPhotoName(picTruePath);
        photo.setPho_name(pho_name);
        photo.setPho_path("upload/photo/"+pho_name);
        Date now=new Date();
        photo.setPho_date(now);
        photo.setPho_user(user_account);
        photo.setPho_spot(spot_id);
        photo.setPho_style(selectedPrint_style.getStyle_id());
        if (uploadType.equals("leader"))
        {
            photo.setPho_store("le");
        }
        else
        {
            photo.setPho_store("at");
        }
        photo.setPho_mark(0);
        String infoCheck=uploadPhotoInfo(photo);
        if(infoCheck.equals("UploadSuccess"))
        {
            return "Success";
        }
        else
        {
            return "Fail";
        }
    }
    public String uploadPhotoInfo(Photo photo) throws IOException {
        OkHttpClient client=new OkHttpClient();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String json=gson.toJson(photo);
        RequestBody requestBody=FormBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Request request=new Request.Builder()
                .url(head+"addPhotoInfo")
                .post(requestBody)
                .build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        Log.d("TAG", "uploadPhotoInfo: "+response);
        return responseData;
    }
    public String getPhotoName(String pitTruePath)
    {
        int div=pitTruePath.lastIndexOf("/");
        String pho_name=pitTruePath.substring(div+1,pitTruePath.length());
        return pho_name;
    }
    public void refreshSpotList() throws IOException {
        String address=head+"user/getAllSpot";
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        spotList=service.parseSpotsJSON(responseData);
    }
    public List<String> getSpotNameList() throws IOException {
        refreshSpotList();
        List<String> nameList=new ArrayList<String>();
        if(spotList !=null)
        {
            for (Spot spot:spotList)
            {
                nameList.add(spot.getSpot_name());
            }
        }
        return nameList;
    }
    public void getPrintStyleWithSpot(String spot_id) throws IOException {
        String address=head+"user/getAllPrintStyle";
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()
                .add("spot_id",spot_id).build();
        Request request=new Request.Builder().url(address).post(requestBody).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();
        print_styleList=service.parsePrintStyleJSON(responseData);
    }
    public  List<String> getPrintStyleNameList(String spot_id) throws IOException {
        getPrintStyleWithSpot(spot_id);
        List<String> nameList=new ArrayList<String>();
        if( print_styleList !=null)
        {
            for (Print_style print_style:print_styleList)
            {
                nameList.add(print_style.getStyle_name());
            }
        }
        return nameList;
    }
    public void setSelectedPrint_style(String style_name)
    {
        if(print_styleList !=null )
        {
            for(Print_style print_style:print_styleList)
            {
                if (print_style.getStyle_name().equals(style_name))
                {
                    selectedPrint_style=print_style;
                    break;
                }
            }
        }
    }

    public List<Spot> getSpotList() {
        return spotList;
    }

    public void setSpotList(List<Spot> spotList) {
        this.spotList = spotList;
    }

    public List<Print_style> getPrint_styleList() {
        return print_styleList;
    }

    public void setPrint_styleList(List<Print_style> print_styleList) {
        this.print_styleList = print_styleList;
    }

    public Print_style getSelectedPrint_style() {
        return selectedPrint_style;
    }
}
