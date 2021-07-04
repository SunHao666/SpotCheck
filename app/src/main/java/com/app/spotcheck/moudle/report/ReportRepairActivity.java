package com.app.spotcheck.moudle.report;

import android.Manifest;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.MainActivity;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.app.spotcheck.network.Contant;
import com.app.spotcheck.utils.GlobalKey;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;
import com.luck.picture.lib.entity.LocalMedia;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 报修申请
 */
public class ReportRepairActivity extends BaseActivity<ReportRepairPresenter> implements ReportRepairView,EasyPermissions.PermissionCallbacks {

    @BindView(R.id.mCompanyTv)
    TextView mCompanyTv;
    @BindView(R.id.mPersonTv)
    TextView mPersonTv;

    @BindView(R.id.mSearchIv)
    ImageView mSearchIv;
    @BindView(R.id.mScanIv)
    ImageView mScanIv;

    @BindView(R.id.mDeviceNameTv)
    TextView mDeviceNameTv;
    @BindView(R.id.mPartNameTv)
    TextView mPartNameTv;

    @BindView(R.id.mRepairDateTv)
    TextView mRepairDateTv;
    @BindView(R.id.mExceptionTypeTv)
    TextView mExceptionTypeTv;

    @BindView(R.id.mExceptionInfoEt)
    EditText mExceptionInfoEt;

    private int proKindPostion = 0;
    private String mainID;
    private String partID;
    private String applyman;
    private String applyDepartmentName;
    private String problemKindCode;

    private List<LocalMedia> selectList = new ArrayList<>();
    @Override
    protected void initData() {

    }

    @Override
    protected ReportRepairPresenter initPresenter() {
        return new ReportRepairPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_report_repair;
    }

    @Override
    protected void initView() {
        setTopTitle("报修申请");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initDate();
        initRepairInfo();
        applyman = SPUtils.getInstance(this).getString(GlobalKey.KEY_LOGINID);
        applyDepartmentName = SPUtils.getInstance(this).getString(GlobalKey.KEY_DEPARTMENTNAME);
    }

    private void initDate() {

        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(month);
        sb.append("-");
        sb.append(day);
        mRepairDateTv.setText(sb.toString());
    }

    private void initRepairInfo() {
        String mDepartmentName = SPUtils.getInstance(this).getString(GlobalKey.KEY_DEPARTMENTNAME);
        String mLoginName = SPUtils.getInstance(this).getString(GlobalKey.KEY_LOGINNAME);
        mCompanyTv.setText(mDepartmentName);
        mPersonTv.setText(mLoginName);

        setDataFromIntent();

    }

    private void setDataFromIntent() {
        Intent intent = getIntent();
        if(intent != null){
            String deviceName = intent.getStringExtra(GlobalKey.KEY_DEVICE_NAME);
            String partName = intent.getStringExtra(GlobalKey.KEY_PART_NAME);
            mainID = intent.getStringExtra(GlobalKey.KEY_MAINID);
            partID = intent.getStringExtra(GlobalKey.KEY_PARTID);

            if(deviceName != null && !deviceName.isEmpty()){
                mDeviceNameTv.setText(deviceName);
            }

            if(partName != null && !partName.isEmpty()){
                mPartNameTv.setText(partName);
            }
        }
    }

    @OnClick({R.id.mSubmitBtn, R.id.mScanIv,R.id.mSearchIv,R.id.lay3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mSubmitBtn://提交
                submit();
                break;
            case R.id.mScanIv:  //扫描
                if(!checkPression()){
                    return;
                }
                startActivityForResult(new Intent(this, CaptureActivity.class), 1002);
                break;
            case R.id.mSearchIv://搜索
                break;
            case R.id.lay2://日期

                break;
            case R.id.lay3://故障类型
                mPresenter.getExceptionList();
                break;
        }
    }

    /*提交申请*/
    private void submit() {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("mainid", toRequestBody(mainID));
        map.put("partid", toRequestBody(partID));
        map.put("applyman", toRequestBody(applyman));
        map.put("applytime", toRequestBody(mRepairDateTv.getText().toString()));
        map.put("applyDepartmentName", toRequestBody(applyDepartmentName));
        map.put("problem", toRequestBody(mExceptionInfoEt.getText().toString()));
        map.put("problemKindCode", toRequestBody(problemKindCode));

        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < selectList.size(); i++) {
            if(selectList.get(i).isCompressed()){
                File file = new File(selectList.get(i).getCompressPath());
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("photoList", file.getName(), requestFile);
                parts.add(part);
            }
        }
        mPresenter.saveRepairApply(map, parts);

    }

    @Override
    public void showProList(ProKindBean bean) {
        if(bean == null){
           return;
        }
        List<ProKindBean.PROBLEMKIND_LIST> list = bean.getPROBLEMKIND_LIST();
        String[] sList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sList[i] =  list.get(i).getName();
        }

        new XPopup.Builder(this)
                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                .asBottomList("故障类型", sList,
                        null, proKindPostion,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                proKindPostion = position;
                                problemKindCode = list.get(position).getValue();
                                mExceptionTypeTv.setText(text);
                            }
                        })
                .show();
    }

    @Override
    public void showError(int code, String msg) {
        ToastWrapper.show(msg);
    }
    /*扫描获取设备信息*/
    @Override
    public void showScanSuccess(RepairReportScanBean bean, String qrcode) {
        mDeviceNameTv.setText(bean.getMAINNAME());
        mPartNameTv.setText(bean.getPARTNAME());
        mainID = bean.getMAINID();
        partID = bean.getPARTID();
    }

    @Override
    public void showSubmitSuccess(String result_message) {
        ToastWrapper.show(result_message);
        finish();
    }

    @Override
    public void showLoad() {
        showLoding();
    }

    @Override
    public void hideLoad() {
        disLoding();
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
        LogUtils.error("scan before=" + result);
        result = result.substring(7, result.length());
        LogUtils.error(result);
        if (requestCode == 1002) {
            mPresenter.scanResult(result);
        }
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
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //权限获取成功
            return true;
        }else {
            //没有权限，调用方法申请权限
            EasyPermissions.requestPermissions(this, "程序运行需要存储权限和相机权限", RC_PERMISSIONS, permissions);
            return false;
        }
    }
}
