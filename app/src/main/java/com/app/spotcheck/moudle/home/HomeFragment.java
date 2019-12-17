package com.app.spotcheck.moudle.home;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;

public class HomeFragment extends BaseFragment {


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }
}
