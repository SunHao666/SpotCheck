package com.app.spotcheck.moudle.scancheck.scanresult;

import android.view.View;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;

public class ScanCheckResultActivity extends BaseActivity<ScanCheckResultPresenter> implements ScanCheckReultView {
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
        setTopTitle("点检完成");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
