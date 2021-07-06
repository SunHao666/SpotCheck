package com.app.spotcheck.moudle.repair.detail3;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.BasisTimesUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.DepartmentBean;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.moudle.event.RepairList1Event;
import com.app.spotcheck.moudle.repair.detail2.RepairDetail2Activity;
import com.app.spotcheck.utils.GlobalKey;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 维修记录详情
 */
public class RepairDetail3Activity extends BaseActivity<RepairDetail3Presenter> implements RepairDetail3View {


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

    @BindView(R.id.mProContentTv)
    TextView mProContentTv;

    @BindView(R.id.mStopTimeTv)
    TextView mStopTimeTv;
    @BindView(R.id.mRepairContentEt)
    TextView mRepairContentEt;

    private String repid;
    @Override
    protected void initData() {
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
    protected RepairDetail3Presenter initPresenter() {
        return new RepairDetail3Presenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.acy_repair_detail3;
    }

    @Override
    protected void initView() {
        setTopTitle("维修记录");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.stopTimeLay, R.id.mRepairOverBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mRepairOverBtn://提交审核
                String stopTime = mStopTimeTv.getText().toString();
                String repairContent = mRepairContentEt.getText().toString();
                mPresenter.saveRepairRec(repid,stopTime,repairContent);
                break;
            case R.id.stopTimeLay:  //停机时间
                showDate();
                break;

        }
    }
    private void showDate() {
        BasisTimesUtils.showDatePickerDialog(RepairDetail3Activity.this,
                "请选择日期", new BasisTimesUtils.OnDatePickerListener() {
                    @Override
                    public void onConfirm(int year, int month, int dayOfMonth) {
                        mStopTimeTv.setText(year+"-"+month+"-"+dayOfMonth);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
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
    }

    @Override
    public void showFinish(String msg) {
        ToastWrapper.show(msg);
        EventBus.getDefault().post(new RepairList1Event(3));
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
