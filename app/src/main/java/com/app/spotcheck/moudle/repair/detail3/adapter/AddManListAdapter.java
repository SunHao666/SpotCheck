package com.app.spotcheck.moudle.repair.detail3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.RepairDeviceListBean;
import com.app.spotcheck.moudle.bean.RepairManListBean;

import java.util.ArrayList;
import java.util.List;

public class AddManListAdapter extends RecyclerView.Adapter<AddManListVH> {

    private List<RepairManListBean.SearchListBean> data = new ArrayList<>();
    @NonNull
    @Override
    public AddManListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_man_add,parent,false);
        return new AddManListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddManListVH holder, int position) {
        RepairManListBean.SearchListBean bean = data.get(position);
        if(bean == null){
            return;
        }
        holder.mFixedTv.setText(bean.getWORKHOUR_D());
        holder.mTendsTv.setText(bean.getWORKHOUR_F());
        holder.mManName.setText(bean.getWORKER_NAME());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(longClickListener != null){
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

    public void setData(List<RepairManListBean.SearchListBean> bean){
        data.clear();
        data.addAll(bean);
        notifyDataSetChanged();
    }

    public interface OnManLongClickListener{
        void onLongClick(RepairManListBean.SearchListBean bean);
    }
    AddManListAdapter.OnManLongClickListener longClickListener;
    public void setOnManLongClickListener(AddManListAdapter.OnManLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }
}
