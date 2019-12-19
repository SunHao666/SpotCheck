package com.app.spotcheck.moudle.lubrication;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.moudle.bean.HomeBean;

public class LubricationFragment extends BaseFragment<LubPresenter> implements LubView{
    public static LubricationFragment newInstance() {
        return new LubricationFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected LubPresenter initPresenter() {
        return new LubPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_lubrication;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void showSuccess(HomeBean bean) {

    }

    @Override
    public void showError(String error) {

    }
}
