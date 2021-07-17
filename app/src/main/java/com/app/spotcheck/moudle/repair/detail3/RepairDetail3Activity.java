package com.app.spotcheck.moudle.repair.detail3;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.BasisTimesUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.moudle.bean.RepairDeviceListBean;
import com.app.spotcheck.moudle.bean.RepairManListBean;
import com.app.spotcheck.moudle.event.RepairList1Event;
import com.app.spotcheck.moudle.repair.detail2.adddevice.AddDeviceActivity;
import com.app.spotcheck.moudle.repair.detail2.addperson.AddPersonActivity;
import com.app.spotcheck.moudle.repair.detail3.adapter.AddDeviceListAdapter;
import com.app.spotcheck.moudle.repair.detail3.adapter.AddManListAdapter;
import com.app.spotcheck.utils.GlobalKey;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.ConfirmPopupView;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 维修记录详情
 */
public class RepairDetail3Activity extends BaseActivity<RepairDetail3Presenter> implements RepairDetail3View {

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

    @BindView(R.id.mStopTimeTv)
    EditText mStopTimeTv;
    @BindView(R.id.mRepairContentEt)
    TextView mRepairContentEt;
    @BindView(R.id.mDeviceFold)
    ImageView mDeviceFold;
    @BindView(R.id.mPersonFold)
    ImageView mPersonFold;
    @BindView(R.id.mDeviceRv)
    RecyclerView mDeviceRv;
    @BindView(R.id.mPersonRv)
    RecyclerView mPersonRv;

    private String repid;
    private boolean isDeviceFold = false;
    private boolean isPersonFold = false;
    private AddDeviceListAdapter mDeviceAdapter;
    private AddManListAdapter mManAdapter;
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
    protected void onResume() {
        super.onResume();
        getRepairApareList();
        getRepairManList();
    }

    private void getRepairApareList(){
        mPresenter.getRepairApareListByRepId(repid);
    }
    private void getRepairManList(){
        mPresenter.getRepairManListByRepId(repid);
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

        mDeviceAdapter = new AddDeviceListAdapter();
        mDeviceRv.setLayoutManager(new LinearLayoutManager(this));
        mDeviceRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mDeviceRv.setAdapter(mDeviceAdapter);
        mDeviceAdapter.setOnDeviceLongClickListener(new AddDeviceListAdapter.OnDeviceLongClickListener() {
            @Override
            public void onLongClick(RepairDeviceListBean.SearchListBean bean) {
                deviceDialog(bean);
            }
        });

        mManAdapter = new AddManListAdapter();
        mPersonRv.setLayoutManager(new LinearLayoutManager(this));
        mPersonRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mPersonRv.setAdapter(mManAdapter);
        mManAdapter.setOnManLongClickListener(new AddManListAdapter.OnManLongClickListener() {
            @Override
            public void onLongClick(RepairManListBean.SearchListBean bean) {
                manDialog(bean);
            }
        });
    }

    private void manDialog(RepairManListBean.SearchListBean bean) {
        new XPopup.Builder(this).asConfirm(
                "提示",
                "确定删除人员",
                "取消",
                "确定删除",
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        mPresenter.deleteRepairMan(repid,bean.getWORKER_NO());
                    }
                },
                new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                },
                true
        ).show();
    }

    private void deviceDialog(RepairDeviceListBean.SearchListBean bean) {
        new XPopup.Builder(this).asConfirm(
                "提示",
                "确定删除备件",
                "取消",
                "确定删除",
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        mPresenter.saveRepairSpareReturn(repid,bean.getSPARE_NO());
                    }
                },
                new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                },
                true
        ).show();
    }

    @OnClick({R.id.mRepairOverBtn, R.id.mDeviceAdd, R.id.mDeviceFold,R.id.mPersonAdd,R.id.mPersonFold})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mRepairOverBtn://提交审核
                String stopTime = mStopTimeTv.getText().toString();
                String repairContent = mRepairContentEt.getText().toString();
                mPresenter.saveRepairRec(repid, repairContent,stopTime);
                break;
            case R.id.mDeviceAdd://设备添加
                Intent intent = new Intent(RepairDetail3Activity.this, AddDeviceActivity.class);
                intent.putExtra(GlobalKey.KEY_REPID, repid);
                startActivity(intent);
                break;
            case R.id.mDeviceFold://设备折叠
                if (!isDeviceFold) {//折叠
                    isDeviceFold = true;
                    mDeviceRv.setVisibility(View.VISIBLE);
                    mDeviceFold.setBackgroundResource(R.drawable.zhankai);
                } else {
                    isDeviceFold = false;
                    mDeviceRv.setVisibility(View.GONE);
                    mDeviceFold.setBackgroundResource(R.drawable.zhedie);
                }
                break;
            case R.id.mPersonAdd://添加人员
                Intent intent1 = new Intent(RepairDetail3Activity.this, AddPersonActivity.class);
                intent1.putExtra(GlobalKey.KEY_REPID, repid);
                startActivity(intent1);
                break;
            case R.id.mPersonFold://人员折叠
                if (!isPersonFold) {//折叠
                    isPersonFold = true;
                    mPersonRv.setVisibility(View.VISIBLE);
                    mPersonFold.setBackgroundResource(R.drawable.zhankai);
                } else {
                    isPersonFold = false;
                    mPersonRv.setVisibility(View.GONE);
                    mPersonFold.setBackgroundResource(R.drawable.zhedie);
                }
                break;
        }
    }

    private void showDate() {
        BasisTimesUtils.showDatePickerDialog(RepairDetail3Activity.this,
                "请选择日期", new BasisTimesUtils.OnDatePickerListener() {
                    @Override
                    public void onConfirm(int year, int month, int dayOfMonth) {
                        mStopTimeTv.setText(year + "-" + month + "-" + dayOfMonth);
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

    @Override
    public void deviceDelSuccess(String msg) {
        getRepairApareList();
    }

    @Override
    public void manDelSuccess(String o) {
        getRepairManList();
    }

}
