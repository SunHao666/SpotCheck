package com.app.spotcheck.moudle.repair.detail2.adddevice.time;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.AddDeviceBean;
import com.app.spotcheck.moudle.event.EventFinsh;
import com.app.spotcheck.utils.GlobalKey;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class AddDeviceTimeActivity extends BaseActivity<AddDeviceTimePresenter> implements AddDeviceTimeView {
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.typeNumTv)
    TextView typeNumTv;
    @BindView(R.id.companyTv)
    TextView companyTv;
    @BindView(R.id.mDeviceNameTv)
    TextView mDeviceNameTv;
    @BindView(R.id.mNumEt)
    EditText mNumEt;
    @BindView(R.id.mSubmitBtn)
    Button mSubmitBtn;
    private String repid;
    private AddDeviceBean.SearchListBean bean;
    @Override
    protected void initData() {

    }

    @Override
    protected AddDeviceTimePresenter initPresenter() {
        return new AddDeviceTimePresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_device_time;
    }

    @Override
    protected void initView() {
        setTopTitle("设备信息");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        if(intent != null){
            bean = (AddDeviceBean.SearchListBean) intent.getSerializableExtra(GlobalKey.KEY_ADDDEVICE);
            repid = intent.getStringExtra(GlobalKey.KEY_REPID);
            if(bean != null){
                mDeviceNameTv.setText(bean.getSPARE_NAME());
                typeTv.setText(bean.getKIND_NAME());
                typeNumTv.setText(bean.getKIND_NO());
                companyTv.setText(bean.getSPARE_MANUFACTOR());
            }
        }
    }

    @OnClick({ R.id.mSubmitBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mSubmitBtn://提交审核
                if(!checkNull()){
                    return;
                }
                mPresenter.saveRepairRec(repid,bean.getSPARE_NO(),mNumEt.getText().toString());
                break;
        }
    }

    private boolean checkNull() {
        boolean pass = false;
        if(TextUtils.isEmpty(mNumEt.getText().toString())){
            ToastWrapper.show("请输入数量");
        }else{
            pass = true;
        }
        return pass;
    }

    @Override
    public void showLoading() {
        showLoding();
    }

    @Override
    public void disLoading() {
        disLoding();
    }

    @Override
    public void showError(String msg) {
        ToastWrapper.show(msg);
    }

    @Override
    public void showFinish(String msg) {
        ToastWrapper.show(msg);
        EventBus.getDefault().post(new EventFinsh());
        finish();
    }
}
