package com.app.spotcheck.moudle.repair.detail4;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.BasisTimesUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.moudle.bean.RepairDeviceListBean;
import com.app.spotcheck.moudle.bean.RepairManListBean;
import com.app.spotcheck.moudle.event.RepairList1Event;
import com.app.spotcheck.moudle.repair.detail3.adapter.AddDeviceListAdapter;
import com.app.spotcheck.moudle.repair.detail3.adapter.AddManListAdapter;
import com.app.spotcheck.utils.GlobalKey;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 维修记录详情
 */
public class RepairDetail4Activity extends BaseActivity<RepairDetail4Presenter> implements RepairDetail4View {
    @BindView(R.id.mRepariHourD)
    TextView mRepariHourD;
    @BindView(R.id.mRepariHourF)
    TextView mRepariHourF;

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

    @BindView(R.id.mOverTimeTv)
    TextView mOverTimeTv;
    @BindView(R.id.mDeviceRv)
    RecyclerView mDeviceRv;
    @BindView(R.id.mPersonRv)
    RecyclerView mPersonRv;
    @BindView(R.id.mSureOverBtn)
    Button mSureOverBtn;
    private String repid;
    private String loginId;
    private AddDeviceListAdapter mDeviceAdapter;
    private AddManListAdapter mManAdapter;
    private String repairFinishRefirm;
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
    protected void onResume() {
        super.onResume();
        mPresenter.getRepairApareListByRepId(repid);
        mPresenter.getRepairManListByRepId(repid);
    }

    @Override
    protected RepairDetail4Presenter initPresenter() {
        return new RepairDetail4Presenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.acy_repair_detail4;
    }

    @Override
    protected void initView() {
        setTopTitle("维修完工确认");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mDeviceAdapter = new AddDeviceListAdapter();
        mDeviceRv.setLayoutManager(new LinearLayoutManager(this));
        mDeviceRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mDeviceRv.setAdapter(mDeviceAdapter);

        mManAdapter = new AddManListAdapter();
        mPersonRv.setLayoutManager(new LinearLayoutManager(this));
        mPersonRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mPersonRv.setAdapter(mManAdapter);

        repairFinishRefirm = SPUtils.getInstance(this).getString(GlobalKey.KEY_REPAIRFINISHREFIRM);
        if(!repairFinishRefirm.isEmpty()&& repairFinishRefirm.equals("1")){
            mSureOverBtn.setVisibility(View.VISIBLE);
        }else{
            mSureOverBtn.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.mSureOverBtn, R.id.overTimeLay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mSureOverBtn://提交审核
                String overTime = mOverTimeTv.getText().toString();
                mPresenter.saveRepairRec(repid,loginId,overTime);
                break;
            case R.id.overTimeLay:  //完成时间
                showDate();
                break;

        }
    }

    private void showDate() {
        BasisTimesUtils.showDatePickerDialog(RepairDetail4Activity.this,
                "请选择日期", new BasisTimesUtils.OnDatePickerListener() {
                    @Override
                    public void onConfirm(int year, int month, int dayOfMonth) {
                        mOverTimeTv.setText(year+"-"+month+"-"+dayOfMonth);
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
        EventBus.getDefault().post(new RepairList1Event(4));
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

    @Override
    public void showDeviceList(List<RepairDeviceListBean.SearchListBean> searchList) {
        if(searchList == null || searchList.isEmpty()){
            return;
        }
        mDeviceAdapter.setData(searchList);
    }

    @Override
    public void showManList(List<RepairManListBean.SearchListBean> searchList) {
        if(searchList == null || searchList.isEmpty()){
            return;
        }
        mManAdapter.setData(searchList);
    }
}
