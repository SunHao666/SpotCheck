package com.app.spotcheck.moudle.home;

import android.Manifest;
import android.content.Intent;
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
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.LubAllBean;
import com.app.spotcheck.moudle.bean.PatralCheckBean;
import com.app.spotcheck.moudle.bean.RefreshWarnBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.event.HomeEvent;
import com.app.spotcheck.moudle.event.ScanCheckEvent;
import com.app.spotcheck.moudle.patralcheck.PatralCheckActivity;
import com.app.spotcheck.moudle.scancheck.ScanCheckActivity;
import com.app.spotcheck.moudle.scanlub.ScanLubActivity;
import com.app.spotcheck.network.Contant;
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

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView,EasyPermissions.PermissionCallbacks {


    @BindView(R.id.home_check_noNum)
    TextView homeCheckNoNum;
    @BindView(R.id.home_plan_time)
    TextView homePlanTime;
    @BindView(R.id.tv_daji)
    TextView tvDaji;
    @BindView(R.id.tv_checnk_place)
    TextView tvChecnkPlace;
    @BindView(R.id.home_lub_noNum)
    TextView homeLubNoNum;
    @BindView(R.id.home_plan_lub_time)
    TextView homePlanLubTime;
    @BindView(R.id.tv_lub_daji)
    TextView tvLubDaji;
    @BindView(R.id.tv_lub_place)
    TextView tvLubPlace;
    @BindView(R.id.iv_home_check)
    TextView ivHomeCheck;
    @BindView(R.id.iv_home_setpro)
    TextView ivHomeSetpro;
    @BindView(R.id.iv_home_lub)
    TextView ivHomeLub;
    @BindView(R.id.no_num)
    RelativeLayout noNum;
    @BindView(R.id.lay_has_num)
    RelativeLayout layHasNum;
    @BindView(R.id.no_runhua_num)
    RelativeLayout noRunhuaNum;
    @BindView(R.id.lay_has_runhua_num)
    LinearLayout layHasRunhuaNum;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    HomeBean bean;
    private boolean isVisible = true;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Contant.TAB_SELECT = 0;
        isVisible = hidden;
        if (!hidden) {
            LogUtils.error("HomeFragment onHiddenChanged");
            mPresenter.fetch();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.fetch();
//        if(!isVisible){
            Contant.TAB_SELECT = 0;
            LogUtils.error("HomeFragment onResume");
            mPresenter.fetch();
//        }
    }

    @Override
    protected void initData() {
        LogUtils.error("HomeFragment initData");
        long logtime = SPUtils.getInstance(getActivity()).getLong("logtime");
        LogUtils.error("logtime" + logtime);
        Contant.TAB_SELECT = 0;
        mPresenter.fetch();
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
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Contant.TAB_SELECT = 0;
                mPresenter.fetch();
            }
        });
    }

    @Override
    public void showSuccess(HomeBean bean) {
        this.bean = bean;
        HomeBean.CHKINFOBean chkinfo = bean.getCHKINFO();
        HomeBean.LUBINFOBean lubinfo = bean.getLUBINFO();
        if (chkinfo == null || chkinfo.getCHK_UNNUM() == 0) {
            homeCheckNoNum.setText("0");
            noNum.setVisibility(View.VISIBLE);
            layHasNum.setVisibility(View.GONE);
        } else {
            noNum.setVisibility(View.GONE);
            layHasNum.setVisibility(View.VISIBLE);
            homeCheckNoNum.setText(chkinfo.getCHK_UNNUM() + "");
            if (chkinfo.getCHK_EXECSTARTTIME() == null || chkinfo.getCHK_EXECENDTIME() == null) {
                homePlanTime.setText("");
            } else {
                homePlanTime.setText(chkinfo.getCHK_EXECSTARTTIME() + "~" + chkinfo.getCHK_EXECENDTIME());
            }

            tvDaji.setText(chkinfo.getCHK_MAINNAME());
            tvChecnkPlace.setText(chkinfo.getCHK_PARTNAME());
        }

        if (lubinfo == null || lubinfo.getLUB_UNNUM() == 0) {
            noRunhuaNum.setVisibility(View.VISIBLE);
            layHasRunhuaNum.setVisibility(View.GONE);
            homeLubNoNum.setText("0");
        } else {
            noRunhuaNum.setVisibility(View.GONE);
            layHasRunhuaNum.setVisibility(View.VISIBLE);
            homeLubNoNum.setText(lubinfo.getLUB_UNNUM() + "");
            if (lubinfo.getLUB_EXECSTARTTIME() == null || lubinfo.getLUB_EXECENDTIME() == null) {
                homePlanLubTime.setText("");
            } else {
                homePlanLubTime.setText(lubinfo.getLUB_EXECSTARTTIME() + "~" + lubinfo.getLUB_EXECENDTIME());
            }
            tvLubDaji.setText(lubinfo.getLUB_MAINNAME());
            tvLubPlace.setText(lubinfo.getLUB_PARTNAME());
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void showError(int code,String error) {
        if(code == -3000){
            showQrcodeDialog();
        }else{
            ToastWrapper.show(error);
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void showScanSuccess(SpotCheckAllBean bean, String qrcode) {
//        List<SpotCheckAllBean.SearchListBean> searchList = bean.getSearchList();
//        if (searchList == null || searchList.size() == 0) {
//            ToastWrapper.show("暂无待检项目");
//        } else {
            //跳转至点检待检
            onCheckScanClick.onClick(1);
//            Contant.CHECKQRCODE = qrcode;
//        }
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

    }

    @Override
    public void showWarn(RefreshWarnBean bean) {

    }

    @OnClick({R.id.iv_home_check, R.id.iv_home_setpro, R.id.iv_home_lub, R.id.lay_has_num, R.id.lay_has_runhua_num,
            R.id.home_check_noNum, R.id.home_lub_noNum})
    public void onViewClicked(View view) {
        if(!checkPression()){
            return;
        }
        switch (view.getId()) {
            case R.id.iv_home_check:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 1001);
//                mPresenter.scanCheck(Contant.CHECKQRCODE);
//                Contant.CHECKQRCODE  = "E002M05P0002";
//                Contant.CHECKSEARCH  = "";
//                onCheckScanClick.onClick(1);
                break;
            case R.id.iv_home_setpro:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 1002);
//                Intent intent = new Intent(getActivity(), PatralCheckActivity.class);
//                intent.putExtra("execid","E002M05P0002");
//                startActivity(intent);
                break;
            case R.id.iv_home_lub:
//                Contant.LUBQRCODE = "E011M03L0001";
//                Contant.LUBSEARCH = "";
//                onLubScanClick.onClick(1);
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 1003);
//                startActivity(new Intent(getActivity(), ScanLubActivity.class));
                break;
            case R.id.lay_has_num:
                if(bean == null){
                    return;
                }
                Intent intent = new Intent(getActivity(), ScanCheckActivity.class);
                intent.putExtra("TaskId",bean.getCHKINFO().getCHK_RECID());
                startActivity(intent);
                break;
            case R.id.lay_has_runhua_num:
                if(bean == null){
                    return;
                }
                Intent intent1 = new Intent(getActivity(), ScanLubActivity.class);
                intent1.putExtra("lrecno",bean.getLUBINFO().getLUB_RECID());
                startActivity(intent1);
                break;

            case R.id.home_check_noNum:
                Contant.TAB_SELECT = 1;
                Contant.CHECKQRCODE = "";
                Contant.CHECKSEARCH = "";
                onCheckScanClick.onClick(1);
                break;
            case R.id.home_lub_noNum:
                Contant.TAB_SELECT = 2;
                Contant.LUBQRCODE = "";
                Contant.LUBSEARCH = "";
                onLubScanClick.onClick(1);
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
//            ToastWrapper.show("二维码格式不正确");
            showQrcodeDialog();
            return;
        }
        LogUtils.error("scan before=" + result);
        result = result.substring(7, result.length());
        LogUtils.error(result);
        if (requestCode == 1001) {
            Contant.CHECKQRCODE = result;
            Contant.CHECKSEARCH = "";
            if(getActivity() instanceof MainActivity){
                ((MainActivity)getActivity()).checkFrom = 1;
            }
            mPresenter.scanCheck(result);
        } else if (requestCode == 1002) {
            mPresenter.scanPatralCheck(result);
        } else if (requestCode == 1003) {
            Contant.LUBQRCODE = result;
            Contant.LUBSEARCH = "";
            if(getActivity() instanceof MainActivity){
                ((MainActivity)getActivity()).checkFrom = 1;
            }
            mPresenter.scanLub(result);
        } else  if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
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
        }else {
            //没有权限，调用方法申请权限
            EasyPermissions.requestPermissions(this, "程序运行需要存储权限和相机权限", RC_PERMISSIONS, permissions);
            return false;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    private void update(HomeEvent event){
        Contant.TAB_SELECT = 0;
        mPresenter.fetch();
    }
}
