package com.app.spotcheck.moudle.spotcheck.specialcheck.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.SpecialSearchBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 专检adapter
 */
public class DeviceSeachAdapter extends RecyclerView.Adapter<DeviceSeachAdapter.InnerViewHolder> {
    private List<SpecialSearchBean.Device> data = new ArrayList<>();
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_layout,parent,false);
        return new InnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InnerViewHolder holder, int position) {
        holder.group_title.setText(data.get(position).MAINNAME);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<SpecialSearchBean.Device> bean){
        this.data.clear();
        data.addAll(bean);
        notifyDataSetChanged();
    }
    public class InnerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.group_title)
        TextView group_title;
        public InnerViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnDeviceOnClickListener{
        void onClick(int position);
    }

    OnDeviceOnClickListener listener;

    public void setOnDeviceOnClickListener( OnDeviceOnClickListener listener){
        this.listener = listener;
    }
}
