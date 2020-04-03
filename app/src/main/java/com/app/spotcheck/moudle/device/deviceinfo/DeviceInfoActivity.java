package com.app.spotcheck.moudle.device.deviceinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.DialogUtils;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.DeviceInfoBean;
import com.app.spotcheck.moudle.device.DeveiceListAdapter;
import com.app.spotcheck.moudle.device.devicesave.DeviceInfoSaveActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备信息
 */
public class DeviceInfoActivity extends BaseActivity<DeviceInfoPresenter> implements DeviceInfoView {
    @BindView(R.id.tv_devinfo_id)
    TextView tvDevinfoId;
    @BindView(R.id.tv_devinfo_name)
    TextView tvDevinfoName;
    @BindView(R.id.tv_devinfo_stop_time)
    TextView tvDevinfoStopTime;
    @BindView(R.id.tv_devinfo_respect_time)
    TextView tvDevinfoRespectTime;
    @BindView(R.id.rv_device_info)
    RecyclerView rvDeviceInfo;
    private DialogUtils loading;
    private DeveiceInfoAdapter adapter;
    private List<DeviceInfoBean.ListBean.SearchListBean> data = new ArrayList<>();
    private String mainid;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mainid = intent.getStringExtra("mainid");
        mPresenter.fetch(mainid);
    }

    @Override
    protected DeviceInfoPresenter initPresenter() {
        return new DeviceInfoPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_device_info;
    }

    @Override
    protected void initView() {
        setTopTitle("设备列表");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rvDeviceInfo.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DeveiceInfoAdapter(this,data);
        rvDeviceInfo.setAdapter(adapter);
        adapter.setOnCheckItemClickListener(new DeveiceInfoAdapter.OnCheckItemClickListener() {
            @Override
            public void onClick(int position) {
                // TODO: 2020/3/31
                DeviceInfoBean.ListBean.SearchListBean searchListBean = data.get(position);
                String mainname = searchListBean.getMAINNAME();
                String partname = searchListBean.getPARTNAME();
                String findtime = searchListBean.getFINDTIME();
                String problemkind = searchListBean.getPROBLEMKIND();
                String itemname = searchListBean.getITEMNAME();
                String problem = searchListBean.getPROBLEM();
                String recid = searchListBean.getRECID();

                Intent intent = new Intent(DeviceInfoActivity.this, DeviceInfoSaveActivity.class);
                intent.putExtra("MAINNAME",mainname);
                intent.putExtra("PARTNAME",partname);
                intent.putExtra("FINDTIME",findtime);
                intent.putExtra("PROBLEMKIND",problemkind);
                intent.putExtra("ITEMNAME",itemname);
                intent.putExtra("PROBLEM",problem);
                intent.putExtra("recid",recid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showLoading() {
        if(loading == null){
            loading = new DialogUtils(this,R.style.CustomDialog);
        }
        loading.show();
    }

    @Override
    public void dissLoading() {
        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }
    }

    @Override
    public void showSuccess(DeviceInfoBean bean) {
        data.clear();
        adapter.notifyDataSetChanged();
        LogUtils.error("bean size = " + bean.getList().getSearchList().size());
        data.addAll(bean.getList().getSearchList());
        adapter.notifyDataSetChanged();
        DeviceInfoBean.InfoBean info = bean.getInfo();
        if(info==null){
            return;
        }
        tvDevinfoId.setText(info.getMAINID());
        tvDevinfoName.setText(info.getMAINNAME());
        tvDevinfoRespectTime.setText(info.getRESTARTDATE());
        tvDevinfoStopTime.setText(info.getSTOPDATE());
    }

    @Override
    public void showError(int code, String msg) {
        ToastWrapper.show(msg);
        data.clear();
        adapter.notifyDataSetChanged();
    }
}
