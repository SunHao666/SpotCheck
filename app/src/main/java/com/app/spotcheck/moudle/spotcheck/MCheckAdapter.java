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
public class MCheckAdapter extends RecyclerView.Adapter<MCheckAdapter.ViewHolder> {

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_check, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(datas.get(position).getPARTNAME());
        holder.tvCheckedNum.setText(datas.get(position).getCHECKNUM()+"");
        holder.tvUncheckNum.setText(datas.get(position).getUNCHECKNUM()+"");
        holder.tvCheckTime.setText(datas.get(position).getEXECSTARTTIME()+"~"+datas.get(position).getEXECENDTIME());
        holder.tvCheckPlace.setText(datas.get(position).getMAINNAME());
        if(tab == 0){
            holder.tvUncheckNum.setVisibility(View.VISIBLE);
            holder.tvCheckedNum.setVisibility(View.VISIBLE);

        }else if(tab == 1){
            holder.tvUncheckNum.setVisibility(View.VISIBLE);
            holder.tvCheckedNum.setVisibility(View.GONE);
        }else if(tab == 2){
            holder.tvUncheckNum.setVisibility(View.GONE);
            holder.tvCheckedNum.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
