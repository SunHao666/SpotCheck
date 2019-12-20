package com.app.spotcheck.moudle.scancheck;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.ScanCheckBean;

import java.util.List;

public class ScanCheckAdapter extends RecyclerView.Adapter<ScanCheckAdapter.ViewHolder> {
    Context context;
    List<ScanCheckBean.SearchListBean> data;
    public ScanCheckAdapter(Context context, List<ScanCheckBean.SearchListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_check, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position%2 == 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#EAF2F8"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    CheckItemClickListener listener;
    public void setOnItemClickListener(CheckItemClickListener listener){
        this.listener = listener;
    }

    public interface CheckItemClickListener{
        void onClick(int position);
    }
}
