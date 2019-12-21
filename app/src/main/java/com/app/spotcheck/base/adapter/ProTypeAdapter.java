package com.app.spotcheck.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ProTypeAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 15:13
 */
public class ProTypeAdapter extends RecyclerView.Adapter<ProTypeAdapter.ViewHolder> {

    Context context;
    List<CheckExceptionBean.PROBLEMKINDBean> data;

    public ProTypeAdapter(Context context, List<CheckExceptionBean.PROBLEMKINDBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_protype, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvContent.setText(data.get(position).getName());
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_content)
        TextView tvContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    onProClickListener listener;
    public void setonProClickListener(onProClickListener listener){
        this.listener = listener;
    }
    public interface onProClickListener{
        void onClick(int position);
    }
}
