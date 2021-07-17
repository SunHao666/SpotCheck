package com.app.spotcheck.moudle.repair.detail5;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
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
public class RepairDetail5Activity extends BaseActivity<RepairDetail5Presenter> implements RepairDetail5View {

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

    @BindView(R.id.mRepairContentTopLine)
    View mRepairContentTopLine;

    @BindView(R.id.mRepairTv)
    TextView mRepairTv;
    @BindView(R.id.mStopTimeTv)
    TextView mStopTimeTv;
    @BindView(R.id.mDeviceRv)
    RecyclerView mDeviceRv;
    @BindView(R.id.mPersonRv)
    RecyclerView mPersonRv;
    @BindView(R.id.mRepairContentLay)
    RelativeLayout mRepairContentLay;
    private AddDeviceListAdapter mDeviceAdapter;
    private AddManListAdapter mManAdapter;
    private String repid;
    private String loginId;
    @Override
    protected void initData() {
        loginId = SPUtils.getInstance(this).getString(GlobalKey.KEY_LOGINID);
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
    protected RepairDetail5Presenter initPresenter() {
        return new RepairDetail5Presenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.acy_repair_detail5;
    }

    @Override
    protected void initView() {
        setTopTitle("维修信息详情");
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
        mRepairTv.setText(bean.getREPAIR_CONTEXT());
        mRepairContentLay.setVisibility(View.VISIBLE);
        mRepairContentTopLine.setVisibility(View.VISIBLE);
        mStopTimeTv.setText(bean.getSTOPTIME()+"");
        mRepariHourD.setText(bean.getREPAIR_HOUR_D());
        mRepariHourF.setText(bean.getREPAIR_HOUR_F());
    }

    @Override
    public void showFinish(String msg) {
        ToastWrapper.show(msg);
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
