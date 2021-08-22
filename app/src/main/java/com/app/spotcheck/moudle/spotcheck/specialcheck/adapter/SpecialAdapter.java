package com.app.spotcheck.moudle.spotcheck.specialcheck.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.RepairItemBean;
import com.app.spotcheck.moudle.bean.SpecialItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private static final int EMPTTY = 1;
    private List<SpecialItemBean.SearchListBean> datas = new ArrayList<>();
    private int tab;

    public SpecialAdapter(Context context, List<SpecialItemBean.SearchListBean> datas) {
        this.context = context;
        this.datas = datas;
        this.tab = tab;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == EMPTTY){
            View inflate = LayoutInflater.from(context).inflate(R.layout.empty, parent, false);
            return new EmptyViewHolder(inflate);
        }else{
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_special, parent, false);
            return new ConViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof EmptyViewHolder){

        }else if(holder instanceof ConViewHolder) {
            ConViewHolder holder1 = (ConViewHolder) holder;
            SpecialItemBean.SearchListBean bean = datas.get(position);
            holder1.mDeviceNameTv.setText(bean.getMAINNAME());
            holder1.timeTv.setText(bean.getCHECKTIME());
            holder1.topCheckTv.setText(bean.getCHECKITEM());
            if(bean.getCHECKRESULT().equals("异常")){
                holder1.exceptionTv.setVisibility(View.VISIBLE);
                holder1.noExceptionTv.setVisibility(View.GONE);
            }else{
                holder1.exceptionTv.setVisibility(View.GONE);
                holder1.noExceptionTv.setVisibility(View.VISIBLE);
            }
            holder1.partTv.setText(bean.getCHECKRECORDS());
//            holder1.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    onCheckItemClickListener.onClick(position,tab);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        if(datas.size() == 0){
            return 1;
        }else{
            return datas.size();
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(datas.size() == 0){
            return EMPTTY;
        }else{
            return super.getItemViewType(position);
        }
    }

    public OnCheckItemClickListener onCheckItemClickListener;

    public void setOnCheckItemClickListener(OnCheckItemClickListener onCheckItemClickListener){
        this.onCheckItemClickListener = onCheckItemClickListener;
    }
    public interface OnCheckItemClickListener{
        void onClick(int position,int tab);
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ConViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mDeviceNameTv)
        TextView mDeviceNameTv;
        @BindView(R.id.timeTv)
        TextView timeTv;
        @BindView(R.id.partTv)
        TextView partTv;
        @BindView(R.id.topCheckTv)
        TextView topCheckTv;
        @BindView(R.id.noExceptionTv)
        TextView noExceptionTv;
        @BindView(R.id.exceptionTv)
        TextView exceptionTv;

        public ConViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
