package com.app.spotcheck.moudle.lubrication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.LubBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: MCheckAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/20 13:43
 */
public class MLubAdapter extends RecyclerView.Adapter<MLubAdapter.ViewHolder> {

    private Context context;
    private List<LubBean.LubSearchListBean> datas;
    private int tab;

    public MLubAdapter(Context context, List<LubBean.LubSearchListBean> datas, int tab) {
        this.context = context;
        this.datas = datas;
        this.tab = tab;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_lub, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(datas.get(position).getPARTNAME());
        //...
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_plan_time)
        TextView tvPlanTime;
        @BindView(R.id.tv_lub_place)
        TextView tvLubPlace;
        @BindView(R.id.tv_lub_time)
        TextView tvLubTime;

        @BindView(R.id.tv_over)
        TextView tvOver;
        @BindView(R.id.tv_wait_lub)
        TextView tvWaitLub;
        @BindView(R.id.lay_space)
        LinearLayout laySpace;
        @BindView(R.id.lay_check_plan)
        LinearLayout layCheckPlan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
