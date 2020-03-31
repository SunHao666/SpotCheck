package com.app.spotcheck.moudle.device.deviceinfo;

import android.content.Context;
import android.view.DragAndDropPermissions;
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
import com.app.spotcheck.moudle.bean.DeviceInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeveiceInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context context;
    public List<DeviceInfoBean.DeviceInfoListBean.DeviceInfoInnerBean> data;
    private static final int EMPTTY = 1;


    public DeveiceInfoAdapter(Context context, List<DeviceInfoBean.DeviceInfoListBean.DeviceInfoInnerBean> data) {
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
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_device_info, parent, false);
            return new ViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EmptyViewHolder) {

        } else if (holder instanceof ViewHolder) {
            ViewHolder holder1 = (ViewHolder) holder;
            holder1.tvNo.setText("NO." + position + 1);
            holder1.tvTitle.setText(data.get(position).getMAINNAME());
            holder1.tvFindTime.setText(data.get(position).getFINDTIME());
            holder1.tvFindType.setText(data.get(position).getFINDTYPE());
            holder1.tvDevinfoName.setText(data.get(position).getITEMNAME());
            holder1.tvDevQuestionName.setText(data.get(position).getPROBLEM());
            holder1.tvDevQuestionName.setText(data.get(position).getPROBLEMKIND());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckItemClickListener.onClick(position);
                }
            });
        }
    }

    public OnCheckItemClickListener onCheckItemClickListener;

    public void setOnCheckItemClickListener(OnCheckItemClickListener onCheckItemClickListener) {
        this.onCheckItemClickListener = onCheckItemClickListener;
    }

    public interface OnCheckItemClickListener {
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
        LogUtils.error("position=" + position);
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
        @BindView(R.id.tv_find_time)
        TextView tvFindTime;
        @BindView(R.id.lay_uncheck_num)
        LinearLayout layUncheckNum;
        @BindView(R.id.lay_device)
        LinearLayout layDevice;
        @BindView(R.id.tv_find_type)
        TextView tvFindType;
        @BindView(R.id.tv_devinfo_name)
        TextView tvDevinfoName;
        @BindView(R.id.tv_dev_question_name)
        TextView tvDevQuestionName;
        @BindView(R.id.tv_devinfo_question_type)
        TextView tvDevinfoQuestionType;
        @BindView(R.id.lay_check_card)
        RelativeLayout layCheckCard;

        public ViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, itemView);
        }
    }
}
