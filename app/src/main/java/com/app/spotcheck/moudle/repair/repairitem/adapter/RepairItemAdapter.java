package com.app.spotcheck.moudle.repair.repairitem.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.moudle.bean.RepairItemBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.spotcheck.MCheckAdapter;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepairItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private static final int EMPTTY = 1;
    private List<RepairItemBean.SearchListBean> datas = new ArrayList<>();
    private int tab;

    public RepairItemAdapter(Context context, List<RepairItemBean.SearchListBean> datas, int tab) {
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
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_repair, parent, false);
            return new ConViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof EmptyViewHolder){

        }else if(holder instanceof ConViewHolder) {
            ConViewHolder holder1 = (ConViewHolder) holder;
            RepairItemBean.SearchListBean bean = datas.get(position);

            String lable = bean.getMAINNAME()+"/"+bean.getPARTNAME();
            int preIndex = lable.indexOf("/");
            SpannableString styledText = new SpannableString(lable);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.exception_part_style1), 0, preIndex, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            styledText.setSpan(new TextAppearanceSpan(context, R.style.exception_part_style2), preIndex, lable.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            holder1.mDeviceNameTv.setText(styledText);

            holder1.mStartTimeTv.setText(bean.getAPPLYTIME());
            if (!bean.getFINISHCHECKTIME().isEmpty()){
                holder1.mEndTimeTv.setText(bean.getFINISHCHECKTIME());
                holder1.mEndTime.setVisibility(View.VISIBLE);
            }else {
                holder1.mEndTime.setVisibility(View.GONE);
            }

            holder1.mRepairContentTv.setText(bean.getPROBLEM());
            getState(bean.getREPSTATE_VALUE(),bean.getREPSTATE_CODE(),holder1);
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckItemClickListener.onClick(position,tab);
                }
            });
        }
    }

    private void getState(String repstate,String code,ConViewHolder holder) {
        if(null == repstate){
            return;
        }
        if(code.equals("1")){
            holder.mRepairStateTv.setBackgroundResource(R.drawable.shape_repair_list_state_1);
        }else if(code.equals("2")){
            holder.mRepairStateTv.setBackgroundResource(R.drawable.shape_repair_list_state_2);
        }else if(code.equals("3")){
            holder.mRepairStateTv.setBackgroundResource(R.drawable.shape_repair_list_state_3);
        }else if(code.equals("4")){
            holder.mRepairStateTv.setBackgroundResource(R.drawable.shape_repair_list_state_4);
        }else if(code.equals("5")){
            holder.mRepairStateTv.setBackgroundResource(R.drawable.shape_repair_list_state_5);
        }

        holder.mRepairStateTv.setText(repstate);
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
        @BindView(R.id.mRepairStateTv)
        TextView mRepairStateTv;
        @BindView(R.id.mStartTimeTv)
        TextView mStartTimeTv;
        @BindView(R.id.mEndTimeTv)
        TextView mEndTimeTv;
        @BindView(R.id.mEndTime)
        TextView mEndTime;
        @BindView(R.id.mRepairContentTv)
        TextView mRepairContentTv;

        public ConViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
