package com.app.spotcheck.moudle.scancheck.scanresult;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanCheckResultActivity extends BaseActivity<ScanCheckResultPresenter> implements ScanCheckReultView {
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.set_place)
    TextView setPlace;
    @BindView(R.id.tv_set_place)
    TextView tvSetPlace;
    @BindView(R.id.lay_bg)
    LinearLayout layBg;
    @BindView(R.id.tv_over)
    TextView tvOver;

    @Override
    protected void initData() {

    }

    @Override
    protected ScanCheckResultPresenter initPresenter() {
        return new ScanCheckResultPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_scancheck_result;
    }

    @Override
    protected void initView() {
        String from = getIntent().getStringExtra("from");
        String msetName = getIntent().getStringExtra("setName");
        String msetPlace = getIntent().getStringExtra("setPlace");

        tvSetName.setText(msetName);
        tvSetPlace.setText(msetPlace);
        if (from.equals("00")) {
            setTopTitle("点检完成");
            setPlace.setText("点检部位:");
            tvOver.setText("点检已经完成");
            layBg.setBackgroundResource(R.drawable.home_top_shape_blue);
        } else {
            setTopTitle("润滑完成");
            setPlace.setText("润滑部位:");
            tvOver.setText("润滑已经完成");
            layBg.setBackgroundResource(R.drawable.home_top_shape_orange1);
        }
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
