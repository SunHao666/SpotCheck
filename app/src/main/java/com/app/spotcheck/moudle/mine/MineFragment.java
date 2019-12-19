package com.app.spotcheck.moudle.mine;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;

public class MineFragment extends BaseFragment<MinePresenter> {
    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }
}
