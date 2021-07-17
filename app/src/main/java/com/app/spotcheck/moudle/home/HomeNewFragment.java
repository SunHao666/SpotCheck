package com.app.spotcheck.moudle.home;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.MainActivity;
import com.app.spotcheck.moudle.bean.EventRepairRefresh;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.LubAllBean;
import com.app.spotcheck.moudle.bean.PatralCheckBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.event.CheckTitleEvent;
import com.app.spotcheck.moudle.event.HomeEvent;
import com.app.spotcheck.moudle.patralcheck.PatralCheckActivity;
import com.app.spotcheck.moudle.report.ReportRepairActivity;
import com.app.spotcheck.moudle.scancheck.ScanCheckActivity;
import com.app.spotcheck.moudle.scanlub.ScanLubActivity;
import com.app.spotcheck.network.Contant;
import com.app.spotcheck.utils.GlobalKey;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeNewFragment extends BaseFragment<HomePresenter> implements HomeView, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.iv_home_check)
    TextView ivHomeCheck;
    @BindView(R.id.iv_home_setpro)
    TextView ivHomeSetpro;
    @BindView(R.id.iv_home_lub)
    TextView ivHomeLub;
    @BindView(R.id.mDayCheck)
    TextView mDayCheck;
    @BindView(R.id.mWeekCheck)
    TextView mWeekCheck;
    @BindView(R.id.mMonthCheck)
    TextView mMonthCheck;
    @BindView(R.id.mSpecialCheck)
    TextView mSpecialCheck;

    @BindView(R.id.mCheckRepair)
    TextView mCheckRepair;
    @BindView(R.id.mBigRepair)
    TextView mBigRepair;
    @BindView(R.id.mMiddleRepair)
    TextView mMiddleRepair;

    @BindView(R.id.mLub)
    TextView mLub;

    HomeBean bean;
    private boolean isVisible = true;

    private static HomeNewFragment instance;

    public static HomeNewFragment newInstance() {
        if (instance == null) {
            instance = new HomeNewFragment();
        }
        return instance;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Contant.TAB_SELECT = 0;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {
        LogUtils.error("HomeFragment initData");
        Contant.TAB_SELECT = 0;
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_new_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void showSuccess(HomeBean bean) {

    }

    @Override
    public void showError(int code, String error) {
        if (code == -3000) {
            showQrcodeDialog();
        } else {
            ToastWrapper.show(error);
        }
    }

    @Override
    public void showScanSuccess(SpotCheckAllBean bean, String qrcode) {
        Contant.CHECK_TYPE = 1;
        onCheckScanClick.onClick(1);
    }

    @Override
    public void showLubSuccess(LubAllBean bean, String qrcode) {
        onLubScanClick.onClick(1);
    }

    @Override
    public void showPatralSuccess(PatralCheckBean bean, String qrcode) {
        Intent intent = new Intent(getActivity(), PatralCheckActivity.class);
        intent.putExtra("execid", qrcode);
        startActivity(intent);
    }

    @Override
    public void showRepairSuccess(RepairReportScanBean bean, String qrcode) {
        Intent intent = new Intent(getActivity(), ReportRepairActivity.class);
        intent.putExtra(GlobalKey.KEY_DEVICE_NAME, bean.getMAINNAME());
        intent.putExtra(GlobalKey.KEY_PART_NAME, bean.getPARTNAME());
        intent.putExtra(GlobalKey.KEY_MAINID, bean.getMAINID());
        intent.putExtra(GlobalKey.KEY_PARTID, bean.getPARTID());
        startActivity(intent);
    }

    @OnClick({R.id.iv_home_check, R.id.iv_home_setpro, R.id.iv_home_lub, R.id.mDayCheck, R.id.mWeekCheck,
            R.id.mMonthCheck, R.id.mSpecialCheck, R.id.mCheckRepair, R.id.mMiddleRepair, R.id.mBigRepair
            , R.id.mLub})
    public void onViewClicked(View view) {
        if (!checkPression()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_home_check:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 1001);
                break;
            case R.id.iv_home_setpro:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 1002);
                break;
            case R.id.iv_home_lub:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 1003);
                break;
            case R.id.mDayCheck:
                Contant.CHECK_TYPE = 1;
                EventBus.getDefault().post(new CheckTitleEvent());
                onCheckScanClick.onClick(1);
                break;
            case R.id.mWeekCheck:
                Contant.CHECK_TYPE = 2;
                EventBus.getDefault().post(new CheckTitleEvent());
                onCheckScanClick.onClick(1);
                break;
            case R.id.mMonthCheck:
                Contant.CHECK_TYPE = 3;
                EventBus.getDefault().post(new CheckTitleEvent());
                onCheckScanClick.onClick(1);
                break;
            case R.id.mCheckRepair:
                Contant.REPAIR_REPKIND = 1;
                //跳转repair tab
                if(getActivity() instanceof MainActivity){
                    ((MainActivity)getActivity()).selectedTab(4,0);
                }
                //刷新界面
                EventBus.getDefault().post(new EventRepairRefresh(1));
                break;
            case R.id.mMiddleRepair:
                Contant.REPAIR_REPKIND = 2;
                //跳转repair tab
                if(getActivity() instanceof MainActivity){
                    ((MainActivity)getActivity()).selectedTab(4,0);
                }
                //刷新界面
                EventBus.getDefault().post(new EventRepairRefresh(2));
                break;
            case R.id.mBigRepair:
                Contant.REPAIR_REPKIND = 3;
                if(getActivity() instanceof MainActivity){
                    ((MainActivity)getActivity()).selectedTab(4,0);
                }
                EventBus.getDefault().post(new EventRepairRefresh(3));
                break;
            case R.id.mLub:
                if(getActivity() instanceof MainActivity){
                    ((MainActivity)getActivity()).selectedTab(3,0);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String result = data.getStringExtra(Intents.Scan.RESULT);
        if (TextUtils.isEmpty(result) || !result.contains("qrcode=")) {
            showQrcodeDialog();
            return;
        }
        result = result.substring(7, result.length());
        if (requestCode == 1001) {
            Contant.CHECKQRCODE = result;
            Contant.CHECKSEARCH = "";
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).checkFrom = 1;
            }
            mPresenter.scanCheck(result);
        } else if (requestCode == 1002) {
            mPresenter.scanResult(result);
        } else if (requestCode == 1003) {
            Contant.LUBQRCODE = result;
            Contant.LUBSEARCH = "";
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).lubFrom = 1;
            }
            mPresenter.scanLub(result);
        } else if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            //用户从设置页面返回，
        }
    }

    private OnCheckScanClick onCheckScanClick;

    public void setOnCheckScanClick(OnCheckScanClick onCheckScanClick) {
        this.onCheckScanClick = onCheckScanClick;
    }

    public interface OnCheckScanClick {
        public void onClick(int position);
    }

    private OnLubScanClick onLubScanClick;

    public void setOnLubScanClick(OnLubScanClick onLubScanClick) {
        this.onLubScanClick = onLubScanClick;
    }

    public interface OnLubScanClick {
        public void onClick(int position);
    }

    private static final int RC_PERMISSIONS = 119;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //用户勾选了“不再询问”，引导用户去设置页面打开权限
            new AppSettingsDialog.Builder(this)
                    .setTitle("权限申请")
                    .setRationale("应用程序运行缺少必要的权限，请前往设置页面打开")
                    .setPositiveButton("去设置")
                    .setNegativeButton("取消")
                    .setRequestCode(110)
                    .build()
                    .show();
        }
    }


    @AfterPermissionGranted(RC_PERMISSIONS)
    private boolean checkPression() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(getActivity(), permissions)) {
            //权限获取成功
            return true;
        } else {
            //没有权限，调用方法申请权限
            EasyPermissions.requestPermissions(this, "程序运行需要存储权限和相机权限", RC_PERMISSIONS, permissions);
            return false;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    private void update(HomeEvent event) {
        Contant.TAB_SELECT = 0;
    }

}
