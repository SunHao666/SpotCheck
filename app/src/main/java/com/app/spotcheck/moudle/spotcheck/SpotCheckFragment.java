package com.app.spotcheck.moudle.spotcheck;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;

public class SpotCheckFragment extends BaseFragment {
    public static SpotCheckFragment newInstance() {
        return new SpotCheckFragment();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_spotcheck;
    }

    @Override
    protected void initView() {

    }
}
