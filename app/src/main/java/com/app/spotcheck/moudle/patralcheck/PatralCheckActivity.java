package com.app.spotcheck.moudle.patralcheck;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.moudle.bean.ScanCheckBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: PatralCheckActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 17:18
 */
public class PatralCheckActivity extends BaseActivity<PatralCheckPresenter> implements PatralCheckView {
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.tv_set_place)
    TextView tvSetPlace;
    @BindView(R.id.et_proinfo)
    EditText etProinfo;
    @BindView(R.id.tv_pro_type)
    TextView tvProType;
    @BindView(R.id.lay_pro_type)
    LinearLayout layProType;
    @BindView(R.id.et_info)
    EditText etInfo;
    @BindView(R.id.btn_ex_save)
    Button btnExSave;

    @Override
    protected void initData() {
        String execid = getIntent().getStringExtra("execid");
        mPresenter.fetch(execid);
    }

    @Override
    protected PatralCheckPresenter initPresenter() {
        return new PatralCheckPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_patral;
    }

    @Override
    protected void initView() {
        setTopTitle("巡检问题登记");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.lay_pro_type, R.id.btn_ex_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_pro_type:
                break;
            case R.id.btn_ex_save:
                break;
        }
    }

    @Override
    public void showSuccess(ScanCheckBean bean) {

    }

    @Override
    public void showError(String error) {

    }
}
