package com.app.spotcheck.moudle.repair.detail2;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.BasisTimesUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.DepartmentBean;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.moudle.event.RepairList1Event;
import com.app.spotcheck.moudle.repair.detail2.adddevice.AddDeviceActivity;
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
public class RepairDetail2Activity extends BaseActivity<RepairDetail2Presenter> implements RepairDetail2View {


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

    @BindView(R.id.mCompanySelTv)
    TextView mCompanySelTv;

    @BindView(R.id.mDateSelTv)
    TextView mDateSelTv;

    @BindView(R.id.mRepairDispatchBtn)
    Button mRepairDispatchBtn;
    private int proKindPostion = 0;
    private String problemKindCode;
    private String repid;
    private String loginId;
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
    protected RepairDetail2Presenter initPresenter() {
        return new RepairDetail2Presenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.acy_repair_detail2;
    }

    @Override
    protected void initView() {
        setTopTitle("维修派工");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @OnClick({R.id.mRepairDispatchBtn, R.id.companyLay,R.id.timeLay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mRepairDispatchBtn://提交审核
                String dateSel = mDateSelTv.getText().toString();
                String startDate = mDateTv.getText().toString();
                mPresenter.saveDispatch(repid,loginId,startDate,dateSel,problemKindCode);
                break;
            case R.id.companyLay:  //维修单位
                mPresenter.getCompanyList();
                break;
            case R.id.timeLay:  //时间
                showDate();
                break;
        }
    }
    private void showDate() {
        BasisTimesUtils.showDatePickerDialog(RepairDetail2Activity.this,
                "请选择日期", new BasisTimesUtils.OnDatePickerListener() {
                    @Override
                    public void onConfirm(int year, int month, int dayOfMonth) {
                        mDateSelTv.setText(year+"-"+month+"-"+dayOfMonth);
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
        mRepariHourD.setText(bean.getREPAIR_HOUR_D());
        mRepariHourF.setText(bean.getREPAIR_HOUR_F());
    }

    @Override
    public void showFinish(String msg) {
        ToastWrapper.show(msg);
        EventBus.getDefault().post(new RepairList1Event(2));
        finish();
    }

    @Override
    public void showCompanyList(DepartmentBean bean) {
        if(bean == null){
            return;
        }
        List<DepartmentBean.DEPARTMENT_LIST> list = bean.getDEPARTMENT_LIST();
        String[] sList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sList[i] =  list.get(i).getName();
        }

        new XPopup.Builder(this)
//                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                .asBottomList("维修单位", sList,
                        null, proKindPostion,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                proKindPostion = position;
                                problemKindCode = list.get(position).getValue();
                                mCompanySelTv.setText(text);
                            }
                        })
                .show();
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
