package com.app.spotcheck.moudle.scanlub;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.ScanLubBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: ScanLubActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 17:46
 */
public class ScanLubActivity extends BaseActivity<ScanLubPresenter> implements ScanLubView{
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.tv_lub_name)
    TextView tvLubName;
    @BindView(R.id.tv_lub_plantime)
    TextView tvLubPlantime;
    @BindView(R.id.tv_lub_type)
    TextView tvLubType;
    @BindView(R.id.tv_use_zhi)
    TextView tvUseZhi;
    @BindView(R.id.tv_use_zhitype)
    TextView tvUseZhitype;
    @BindView(R.id.tv_use_many)
    TextView tvUseMany;
    @BindView(R.id.et_work_time)
    EditText etWorkTime;
    @BindView(R.id.et_stop_time)
    EditText etStopTime;
    @BindView(R.id.et_forget_info)
    EditText etForgetInfo;
    @BindView(R.id.btn_over_lub)
    Button btnOverLub;
    private String lrecno;

    @Override
    protected void initData() {
        lrecno = getIntent().getStringExtra("lrecno");
        mPresenter.fetch(lrecno);
    }

    @Override
    protected ScanLubPresenter initPresenter() {
        return new ScanLubPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_scan_lub;
    }

    @Override
    protected void initView() {
        setTopTitle("润滑记录");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @OnClick(R.id.btn_over_lub)
    public void onViewClicked() {
        save();
    }

    private void save() {
        if(TextUtils.isEmpty(etWorkTime.getText().toString())){
            ToastWrapper.show("请输入时长");
            return;
        }
        if(TextUtils.isEmpty(etStopTime.getText().toString())){
            ToastWrapper.show("请输入停机时长");
            return;
        }
        String Loginname = SPUtils.getInstance(this).getString("Loginname");
        mPresenter.save(lrecno,etWorkTime.getText().toString(),etStopTime.getText().toString(),Loginname);
    }

    @Override
    public void showSuccess(ScanLubBean bean) {
        tvSetName.setText(bean.getMAINNAME());
        tvLubName.setText(bean.getPARTNAME());
        tvLubPlantime.setText(bean.getPLANTIME());
        tvLubType.setText(bean.getLUBTYPE());
        tvUseZhi.setText(bean.getLUBRICANT());
        tvUseZhitype.setText(bean.getOILTYPE());
        tvUseMany.setText(bean.getOILVOLUME());
    }

    @Override
    public void showError(String msg) {
        ToastWrapper.show(msg);
    }

    @Override
    public void showSaveSuccess(String msg) {
        ToastWrapper.show(msg);
        finish();
    }
}
