package com.app.spotcheck.moudle.scancheck;

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
import com.app.spotcheck.moudle.bean.ScanCheckBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanCheckAdapter extends RecyclerView.Adapter<ScanCheckAdapter.ViewHolder> {
    Context context;
    List<ScanCheckBean.SearchListBean> data;


    public ScanCheckAdapter(Context context, List<ScanCheckBean.SearchListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_check, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScanCheckBean.SearchListBean bean = data.get(position);
        holder.tvNum.setText(String.valueOf(position + 1));
        holder.mPrejectTv.setText(bean.getITEMKIND());
        holder.mItemsTv.setText(bean.getITEMS());
        String lable = "<"+bean.getPARTS()+">"+bean.getCHECKITEM();
        int preIndex = lable.indexOf(">");
        SpannableString styledText = new SpannableString(lable);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.check_label_style1), 0, preIndex, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.check_label_style2), preIndex+1, lable.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        String s = styledText.toString();
        if(bean.getPARTS().isEmpty()){
            s = s.substring(2, s.length());
        }
        holder.mPartsTv.setText(s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.tv_content)
//        TextView tvContent;
        @BindView(R.id.mPrejectTv)
        TextView mPrejectTv;
        @BindView(R.id.mItemsTv)
        TextView mItemsTv;
        @BindView(R.id.mPartsTv)
        TextView mPartsTv;

        @BindView(R.id.tv_content2)
        TextView tvContent2;
        @BindView(R.id.tv_num)
        TextView tvNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    CheckItemClickListener listener;

    public void setOnItemClickListener(CheckItemClickListener listener) {
        this.listener = listener;
    }

    public interface CheckItemClickListener {
        void onClick(int position);
    }
}
