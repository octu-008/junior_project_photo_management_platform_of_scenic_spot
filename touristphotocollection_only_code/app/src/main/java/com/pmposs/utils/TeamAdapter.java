package com.pmposs.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pmposs.touristphotocollection.R;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    List<String> members;
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView member;
        public ViewHolder (View view)
        {
            super(view);
            member=view.findViewById(R.id.member);
        }
    }
    public TeamAdapter(List<String> members)
    {
        this.members=members;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_team_member,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String member=members.get(position);
        holder.member.setText(member);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }
}
