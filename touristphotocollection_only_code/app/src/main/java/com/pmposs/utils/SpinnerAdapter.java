package com.pmposs.utils;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {
    Context context;
    List<String> objects;
    public SpinnerAdapter(Context context,int textViewResourceId,List<String> items)
    {
        super(context,textViewResourceId,items);
        this.context=context;
        this.objects=items;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(android.R.layout.simple_spinner_dropdown_item,parent,false);
        }
        TextView tv=(TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(objects.get(position));
        tv.setTextSize(20);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(android.R.layout.simple_spinner_item,parent,false);
        }
        TextView tv=(TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(objects.get(position));
        tv.setTextSize(20);
        return convertView;
    }
}
