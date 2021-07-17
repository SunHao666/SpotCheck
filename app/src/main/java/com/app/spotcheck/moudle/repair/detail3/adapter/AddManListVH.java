package com.app.spotcheck.moudle.repair.detail3.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddManListVH extends RecyclerView.ViewHolder {
    @BindView(R.id.mManName)
    TextView mManName;
    @BindView(R.id.mFixedTv)
    TextView mFixedTv;
    @BindView(R.id.mTendsTv)
    TextView mTendsTv;

    public AddManListVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}