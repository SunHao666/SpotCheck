package com.app.spotcheck.moudle.spotcheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: MCheckAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/20 13:43
 */
public class MCheckAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EMPTTY = 1;
    private Context context;
    private List<SpotCheckAllBean.SearchListBean> datas;
    private int tab;

    public MCheckAdapter(Context context, List<SpotCheckAllBean.SearchListBean> datas, int tab) {
        this.context = context;
        this.datas = datas;
        this.tab = tab;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtils.error("viewType="+viewType);
        if(viewType == EMPTTY){
            View inflate = LayoutInflater.from(context).inflate(R.layout.empty, parent, false);
            return new EmptyViewHolder(inflate);
        }else{
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_check, parent, false);
            return new ConViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof EmptyViewHolder ){

        }else if(holder instanceof ConViewHolder ){
            ConViewHolder holder1 = (ConViewHolder) holder;
            holder1.tvTitle.setText(datas.get(position).getPARTNAME());
            holder1.tvCheckedNum.setText("已点检项目"+datas.get(position).getCHECKNUM()+"项");
            holder1.tvUncheckNum.setText(datas.get(position).getUNCHECKNUM()+"");
            holder1.tvCheckTime.setText(datas.get(position).getEXECSTARTTIME()+"~"+datas.get(position).getEXECENDTIME());
            holder1.tvCheckPlace.setText(datas.get(position).getMAINNAME());
            if(tab == 0){
                holder1.layUncheckNum.setVisibility(View.VISIBLE);
                holder1.tvCheckedNum.setVisibility(View.VISIBLE);
            }else if(tab == 1){
                holder1.layUncheckNum.setVisibility(View.VISIBLE);
                holder1.tvCheckedNum.setVisibility(View.GONE);
            }else if(tab == 2){
                holder1.layUncheckNum.setVisibility(View.GONE);
                holder1.tvCheckedNum.setVisibility(View.VISIBLE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckItemClickListener.onClick(position,tab);
                }
            });
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
        LogUtils.error("position="+position);
        if(datas.size() == 0){
            return EMPTTY;
        }else{
            return super.getItemViewType(position);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ConViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_checked_num)
        TextView tvCheckedNum;
        @BindView(R.id.tv_uncheck_num)
        TextView tvUncheckNum;
        @BindView(R.id.lay_uncheck_num)
        LinearLayout layUncheckNum;
        @BindView(R.id.lay_check)
        LinearLayout layCheck;
        @BindView(R.id.tv_check_place)
        TextView tvCheckPlace;
        @BindView(R.id.lay_space)
        LinearLayout laySpace;
        @BindView(R.id.tv_check_time)
        TextView tvCheckTime;
        @BindView(R.id.lay_check_plan)
        LinearLayout layCheckPlan;
        @BindView(R.id.lay_check_card)
        RelativeLayout layCheckCard;

        public ConViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public OnCheckItemClickListener onCheckItemClickListener;

    public void setOnCheckItemClickListener(OnCheckItemClickListener onCheckItemClickListener){
        this.onCheckItemClickListener = onCheckItemClickListener;
    }
    public interface OnCheckItemClickListener{
        void onClick(int position,int tab);
    }
}
