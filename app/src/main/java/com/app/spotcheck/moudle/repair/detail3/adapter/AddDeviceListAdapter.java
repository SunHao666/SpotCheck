package com.app.spotcheck.moudle.repair.detail3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.RepairDeviceListBean;

import java.util.ArrayList;
import java.util.List;

public class AddDeviceListAdapter extends RecyclerView.Adapter<AddDeviceListVH> {

    private List<RepairDeviceListBean.SearchListBean> data = new ArrayList<>();
    @NonNull
    @Override
    public AddDeviceListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_add,parent,false);
        return new AddDeviceListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddDeviceListVH holder, int position) {
        RepairDeviceListBean.SearchListBean bean = data.get(position);
        if(bean != null){
            holder.mDeviceNameTv.setText(bean.getSPARE_NAME());
            holder.mTypeNumTv.setText(bean.getSPARE_NO());
            holder.mNumDeviceTv.setText(bean.getUSEQUANTITY()+"");
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(longClickListener!= null){
                    longClickListener.onLongClick(bean);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<RepairDeviceListBean.SearchListBean> bean){
        data.clear();
        data.addAll(bean);
        notifyDataSetChanged();
    }

    public interface OnDeviceLongClickListener{
        void onLongClick(RepairDeviceListBean.SearchListBean bean);
    }
    OnDeviceLongClickListener longClickListener;
    public void setOnDeviceLongClickListener(OnDeviceLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }
}
