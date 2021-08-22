package com.app.spotcheck.widgets;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.ReportSearchBean;
import com.app.spotcheck.moudle.bean.SpecialSearchBean;
import com.app.spotcheck.moudle.spotcheck.specialcheck.adapter.DeviceSeachAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 专检搜索
 */
public class SpecialSearchView extends BottomView {
    private List<SpecialSearchBean.Device> data = new ArrayList<>();
    private EditText serarchEt;
    private TextView titleTv;
    private TextView searchTv;
    private Context context;
    private RecyclerView mSearchRv;
    private DeviceSeachAdapter adapter;
    public SpecialSearchView(@NonNull Context context, int tag) {
        super(context, tag);
        this.context = context;

    }

    @Override
    protected int getLayout(int tag) {
        return R.layout.report_special_search;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mSearchRv = findViewById(R.id.mSearchRv);
        serarchEt = findViewById(R.id.serarchEt);
        titleTv = findViewById(R.id.titleTv);
        searchTv = findViewById(R.id.searchTv);
        setListener();
        adapter = new DeviceSeachAdapter();
        adapter.setOnDeviceOnClickListener(new DeviceSeachAdapter.OnDeviceOnClickListener() {
            @Override
            public void onClick(int position) {
                SpecialSearchBean.Device device = data.get(position);
                mDeviceListener.onClick(device.MAINNAME,device.MAINID);
            }
        });
        mSearchRv.setLayoutManager(new LinearLayoutManager(context));
        mSearchRv.setAdapter(adapter);
    }

    private void setListener() {

    }

    public void setData(List<SpecialSearchBean.Device> bean) {
        data = bean;
        adapter.setData(data);
    }

    private DeviceOnClickListener mDeviceListener;

    public interface DeviceOnClickListener {
        public void onClick(String mainName, String mainId);
    }

    public void setDeviceOnClickListener(DeviceOnClickListener mDeviceListener) {
        this.mDeviceListener = mDeviceListener;
    }

}
