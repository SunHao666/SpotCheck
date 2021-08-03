package com.app.spotcheck.moudle.repair.detail1;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.moudle.event.RepairList1Event;
import com.app.spotcheck.utils.GlobalKey;
import com.king.zxing.CaptureActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 维修记录详情
 */
public class RepairDetail1Activity extends BaseActivity<RepairDetail1Presenter> implements RepairDetail1View {


    @BindView(R.id.mDeviceNumTv)
    TextView mDeviceNumTv;
    @BindView(R.id.mDeviceNameTv)
    TextView mDeviceNameTv;

    @BindView(R.id.mPartNameTv)
    TextView mPartNameTv;
    @BindView(R.id.mCompanyTv)
    TextView mCompanyTv;

    @BindView(R.id.mPersonTv)
    TextView mPersonTv;
    @BindView(R.id.mPhoneTv)
    TextView mPhoneTv;

    @BindView(R.id.mDateTv)
    TextView mDateTv;
    @BindView(R.id.mProType)
    TextView mProType;
    @BindView(R.id.mRepariHourD)
    TextView mRepariHourD;
    @BindView(R.id.mRepariHourF)
    TextView mRepariHourF;

    @BindView(R.id.mProContentTv)
    TextView mProContentTv;

    @BindView(R.id.mCancleBtn)
    Button mCancleBtn;

    @BindView(R.id.mPassBtn)
    Button mPassBtn;

    private String repid;
    private String loginId;
    private String repairApplyCheck;
    @Override
    protected void initData() {
        loginId = SPUtils.getInstance(this).getString(GlobalKey.KEY_UUADMINUSER);

        Intent intent = getIntent();
        if (intent != null) {
            repid = intent.getStringExtra(GlobalKey.KEY_REPID);
            mPresenter.getDetailInfo(repid);
        } else {
            ToastWrapper.show("记录ID不能为空");
            finish();
        }

    }

    @Override
    protected RepairDetail1Presenter initPresenter() {
        return new RepairDetail1Presenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.acy_repair_detail1;
    }

    @Override
    protected void initView() {
        setTopTitle("审核维修申请");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        repairApplyCheck = SPUtils.getInstance(this).getString(GlobalKey.KEY_REPAIRAPPLYCHECK);
        if(!repairApplyCheck.isEmpty()&&repairApplyCheck.equals("1")){
            mPassBtn.setVisibility(View.VISIBLE);
            mCancleBtn.setVisibility(View.VISIBLE);
        }else{
            mPassBtn.setVisibility(View.GONE);
            mCancleBtn.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.mCancleBtn, R.id.mPassBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mCancleBtn://取消
                mPresenter.del(repid);
                break;
            case R.id.mPassBtn:  //通过

                mPresenter.pass(repid,loginId,getYMD());
                break;

        }
    }

    private String getYMD(){
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DATE);
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(month+1);
        sb.append("-");
        sb.append(day);
        return  sb.toString();
    }
    @Override
    public void showError(String msg) {
        ToastWrapper.show(msg);
    }

    @Override
    public void showSuccess(RepairDetailBean bean) {
        if (bean == null) {
            return;
        }

        mDeviceNumTv.setText(bean.getMAINID());
        mDeviceNameTv.setText(bean.getMAINNAME());
        mPartNameTv.setText(bean.getPARTNAME());
        mCompanyTv.setText(bean.getAPPLY_DEPARTMENT_NAME());
        mPersonTv.setText(bean.getAPPLY_MAN_NAME());
        mPhoneTv.setText(bean.getAPPLY_TEL());
        mDateTv.setText(bean.getAPPLY_TIME());
        mProType.setText(bean.getPROBLEM_KIND_VALUE());
        mProContentTv.setText(bean.getPROBLEM());
        mRepariHourD.setText(bean.getREPAIR_HOUR_D());
        mRepariHourF.setText(bean.getREPAIR_HOUR_F());
    }

    @Override
    public void showFinish(String msg) {
        ToastWrapper.show(msg);
        EventBus.getDefault().post(new RepairList1Event(1));
        finish();
    }

    @Override
    public void showLoading() {
        showLoding();
    }

    @Override
    public void disLoading() {
        disLoding();
    }

}
