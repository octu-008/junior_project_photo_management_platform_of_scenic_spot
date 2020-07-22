package com.pmposs.utils;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pmposs.model.Photo;
import com.pmposs.touristphotocollection.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{
    private List<Photo> photoList;
    private List<Bitmap> bitmapsList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView photoUploaded;
        TextView uploadedSpotId,uploadedStylID,uploadedType,uploadedPrinted;
        public ViewHolder(View view)
        {
            super(view);
            photoUploaded=view.findViewById(R.id.photoUploaded);
            uploadedSpotId=view.findViewById(R.id.uploadedSpotId);
            uploadedStylID=view.findViewById(R.id.uploadedStylID);
            uploadedType=view.findViewById(R.id.uploadedType);
            uploadedPrinted=view.findViewById(R.id.uploadedPrinted);
        }
    }
    public PhotoAdapter(List<Photo> photoList,List<Bitmap> bitmapsList)
    {
        this.photoList=photoList;
        this.bitmapsList=bitmapsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_photo_uploaded,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo=photoList.get(position);
        holder.photoUploaded.setImageBitmap(bitmapsList.get(position));
        holder.uploadedSpotId.setText("上传景区编号："+photo.getPho_spot());
        holder.uploadedStylID.setText("打印风格编号："+photo.getPho_style());
        if(photo.getPho_store().equals("at"))
        {
            holder.uploadedType.setText("上传方式：上传至服务器");
        }
        else
        {
            holder.uploadedType.setText("上传方式：上传至导游");
        }
        if(photo.getPho_mark() == 1)
        {
            holder.uploadedPrinted.setText("状态：已打印");
        }
        else{
            holder.uploadedPrinted.setText("状态：未打印");
        }
    }
    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
