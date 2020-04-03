package com.app.spotcheck.moudle.device.devicesave;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.BasisTimesUtils;
import com.app.spotcheck.base.utils.DialogUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.moudle.device.deviceinfo.DeviceInfoActivity;
import com.luck.picture.lib.tools.ToastUtils;

import butterknife.BindView;

public class DeviceInfoSaveActivity extends BaseActivity<DeviceSavePresenter> implements DeviceSaveView {

    @BindView(R.id.tv_devs_name)
    TextView tvDevsName;
    @BindView(R.id.tv_devs_place)
    TextView tvDevsPlace;
    @BindView(R.id.tv_devs_find_time)
    TextView tvDevsFindTime;
    @BindView(R.id.tv_devs_find_type)
    TextView tvDevsFindType;
    @BindView(R.id.tv_devs_project)
    TextView tvDevsProject;
    @BindView(R.id.tv_devs_desc)
    TextView tvDevsDesc;
    @BindView(R.id.name1)
    TextView name1;
    @BindView(R.id.et_work_time)
    TextView etWorkTime;
    @BindView(R.id.name2)
    TextView name2;
    @BindView(R.id.et_dev_work_hour)
    EditText etDevWorkHour;
    @BindView(R.id.name3)
    TextView name3;
    @BindView(R.id.et_devs_repair_content)
    EditText etDevsRepairContent;
    @BindView(R.id.btn_devs_save)
    Button btnDevsSave;
    private DialogUtils loading;
    private String recid;

    @Override
    protected void initData() {
    }

    @Override
    protected DeviceSavePresenter initPresenter() {
        return new DeviceSavePresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_save;
    }

    @Override
    protected void initView() {
        setTopTitle("设备列表");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String mainname = intent.getStringExtra("MAINNAME");
        String partname = intent.getStringExtra("PARTNAME");
        String findtime = intent.getStringExtra("FINDTIME");
        String problemkind = intent.getStringExtra("PROBLEMKIND");
        String itemname = intent.getStringExtra("ITEMNAME");
        String problem = intent.getStringExtra("PROBLEM");
        recid = intent.getStringExtra("recid");

        tvDevsName.setText(mainname);
        tvDevsPlace.setText(partname);
        tvDevsFindTime.setText(findtime);
        tvDevsFindType.setText(problemkind);
        tvDevsProject.setText(itemname);
        tvDevsDesc.setText(problem);
        //日期选择
        etWorkTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });


        btnDevsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etWorkTime.getText().toString())){
                    ToastUtils.s(DeviceInfoSaveActivity.this,"请输入维修时间");
                    return;
                }

                if(TextUtils.isEmpty(etDevWorkHour.getText().toString())){
                    ToastUtils.s(DeviceInfoSaveActivity.this,"请输入工时");
                    return;
                }

                if(TextUtils.isEmpty(etDevsRepairContent.getText().toString())){
                    ToastUtils.s(DeviceInfoSaveActivity.this,"请输入维修内容");
                    return;
                }
                String loginname = SPUtils.getInstance(DeviceInfoSaveActivity.this).getString("Loginname");
                // TODO: 2020/3/31 保存维修记录 recid loginname
                mPresenter.save(recid,etWorkTime.getText().toString(),etDevWorkHour.getText().toString(),
                        etDevsRepairContent.getText().toString(),loginname);
            }
        });
    }

    private void showDate() {
        BasisTimesUtils.showDatePickerDialog(DeviceInfoSaveActivity.this, BasisTimesUtils.THEME_HOLO_LIGHT, "请选择年月日", 2020, 1, 1, new BasisTimesUtils.OnDatePickerListener() {

            @Override
            public void onConfirm(int year, int month, int dayOfMonth) {
//                ToastUtils.s(DeviceInfoSaveActivity.this,year + "-" + month + "-" + dayOfMonth);
                etWorkTime.setText(year + "-" + month + "-" + dayOfMonth);
            }

            @Override
            public void onCancel() {
            }
        });

    }

    @Override
    public void showSuccess(String bean) {
        ToastUtils.s(DeviceInfoSaveActivity.this,bean);
        finish();
    }

    @Override
    public void showError(int code, String error) {
        ToastUtils.s(DeviceInfoSaveActivity.this,error);
    }

    @Override
    public void showLoading() {
        if (loading == null) {
            loading = new DialogUtils(this, R.style.CustomDialog);
        }
        loading.show();
    }

    @Override
    public void dissLoading() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }

}
