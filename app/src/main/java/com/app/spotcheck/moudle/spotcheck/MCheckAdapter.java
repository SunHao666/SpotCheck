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
import com.app.spotcheck.utils.CommonUtils;

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
        if(viewType == EMPTTY){
            View inflate = LayoutInflater.from(context).inflate(R.layout.empty, parent, false);
            return new EmptyViewHolder(inflate);
        }else{
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_new_check, parent, false);
            return new ConViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof EmptyViewHolder ){

        }else if(holder instanceof ConViewHolder){
            ConViewHolder holder1 = (ConViewHolder) holder;
            SpotCheckAllBean.SearchListBean bean = datas.get(position);
            String devicePart = CommonUtils.setDevicePart(bean.getMAINNAME(), bean.getPARTNAME(), context);
            holder1.tvTitle.setText(devicePart);
            //计划点检时间
            holder1.tvCheckTime.setText(datas.get(position).getEXECSTARTTIME()+"~"+datas.get(position).getEXECENDTIME());
            //已检项目条数
            holder1.tvCheckedNum.setText("已检"+datas.get(position).getCHECKNUM()+"项");
            //待检项目条数
            holder1.tvUncheckNum.setText(datas.get(position).getUNCHECKNUM()+"");

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
        @BindView(R.id.tv_check_time)
        TextView tvCheckTime;

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
