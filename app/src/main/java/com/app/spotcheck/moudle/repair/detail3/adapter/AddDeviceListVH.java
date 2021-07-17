package com.app.spotcheck.moudle.repair.detail3.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDeviceListVH extends RecyclerView.ViewHolder {
    @BindView(R.id.mDeviceNameTv)
    TextView mDeviceNameTv;
    @BindView(R.id.mTypeNumTv)
    TextView mTypeNumTv;
    @BindView(R.id.mNumDeviceTv)
    TextView mNumDeviceTv;

    public AddDeviceListVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}