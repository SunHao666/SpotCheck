package com.app.spotcheck.moudle.scanlub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: ScanLubActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 17:46
 */
public class ScanLubActivity extends BaseActivity<ScanLubPresenter> {
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

    @Override
    protected void initData() {


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

    }
}
