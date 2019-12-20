package com.app.spotcheck.moudle.scancheck.checkexception;

import android.view.View;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;

public class CheckExceptionActivity extends BaseActivity<CheckExceptionPresenter> implements CheckExceptionView {
    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        mPresenter.fetch(id);
    }

    @Override
    protected CheckExceptionPresenter initPresenter() {
        return new CheckExceptionPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_check_exception;
    }

    @Override
    protected void initView() {
        setTopTitle("点检异常问题登记");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showSuccess(CheckExceptionBean bean) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void save(String msg) {

    }
}
