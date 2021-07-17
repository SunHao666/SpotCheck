package com.app.spotcheck.moudle.repair.detail2.adddevice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.AddDeviceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDeviceAdapter extends RecyclerView.Adapter<AddDeviceAdapter.AddDeviceVH> {
    private List<AddDeviceBean.SearchListBean> data = new ArrayList<>();
    @NonNull
    @Override
    public AddDeviceVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iten_adddevice,parent,false);
        return new AddDeviceVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddDeviceVH holder, int position) {
        AddDeviceBean.SearchListBean bean = data.get(position);
        holder.mDeviceNameTv.setText(bean.getSPARE_NAME());
        holder.typeTv.setText(bean.getKIND_NAME());
        holder.typeNumTv.setText(bean.getKIND_NO());
        holder.companyTv.setText(bean.getSPARE_MANUFACTOR());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<AddDeviceBean.SearchListBean> searchList){
        data.clear();
        data.addAll(searchList);
        notifyDataSetChanged();
    }

    public interface OnDeviceCLickListener {
         void onClick(int position);
    }

    public OnDeviceCLickListener listener;

    public void setOnClickListener(OnDeviceCLickListener listener){
        this.listener = listener;
    }


    public class AddDeviceVH extends RecyclerView.ViewHolder {
        @BindView(R.id.typeTv)
        TextView typeTv;
        @BindView(R.id.typeNumTv)
        TextView typeNumTv;
        @BindView(R.id.companyTv)
        TextView companyTv;
        @BindView(R.id.mDeviceNameTv)
        TextView mDeviceNameTv;

        public AddDeviceVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
