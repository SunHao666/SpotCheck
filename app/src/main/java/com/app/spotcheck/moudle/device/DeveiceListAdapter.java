package com.app.spotcheck.moudle.device;

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
import com.app.spotcheck.moudle.bean.DeviceListBean;
import com.app.spotcheck.moudle.spotcheck.MCheckAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeveiceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context context;
    public List<DeviceListBean.DataBean> data;
    private static final int EMPTTY = 1;


    public DeveiceListAdapter(Context context, List<DeviceListBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == EMPTTY) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.empty, parent, false);
            return new EmptyViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_device, parent, false);
            return new ViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EmptyViewHolder) {

        } else if (holder instanceof ViewHolder) {
            ViewHolder holder1 = (ViewHolder) holder;
            holder1.tvNo.setText("NO." + (position + 1));
            holder1.tvTitle.setText(data.get(position).getMAINNAME());
            holder1.tvWaitRepair.setText(data.get(position).getREPCOUNT());
            holder1.tvDeviceId.setText(data.get(position).getMAINID());
            holder1.tvDeviceStopTime.setText(data.get(position).getSTOPDATE());
            holder1.tvDeviceRespectTime.setText(data.get(position).getRESTARTDATE());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckItemClickListener.onClick(position);
                }
            });
        }
    }

    public OnCheckItemClickListener onCheckItemClickListener;

    public void setOnCheckItemClickListener(OnCheckItemClickListener onCheckItemClickListener){
        this.onCheckItemClickListener = onCheckItemClickListener;
    }
    public interface OnCheckItemClickListener{
        void onClick(int position);
    }
    

    @Override
    public int getItemCount() {
        if (data.size() == 0) {
            return 1;
        } else {
            return data.size();
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (data.size() == 0) {
            return EMPTTY;
        } else {
            return super.getItemViewType(position);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View inflate) {
            super(inflate);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_no)
        TextView tvNo;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.lay_uncheck_num)
        LinearLayout layUncheckNum;
        @BindView(R.id.lay_device)
        LinearLayout layDevice;
        @BindView(R.id.tv_device_id)
        TextView tvDeviceId;
        @BindView(R.id.tv_device_stop_time)
        TextView tvDeviceStopTime;
        @BindView(R.id.tv_device_respect_time)
        TextView tvDeviceRespectTime;
        @BindView(R.id.lay_device_id)
        LinearLayout layDeviceId;
        @BindView(R.id.lay_check_card)
        RelativeLayout layCheckCard;
        @BindView(R.id.tv_wait_repair)
        TextView tvWaitRepair;
        public ViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, itemView);
        }
    }
}
