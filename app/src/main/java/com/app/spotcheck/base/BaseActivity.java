package com.app.spotcheck.base;


import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 通用Activity基类.
 */
public abstract class BaseActivity extends TransitionActivity {

    private Unbinder mUnbind;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        mUnbind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected final void onDestroy() {
        super.onDestroy();
        mUnbind.unbind();
        mUnbind = null;
    }
    @LayoutRes
    protected abstract int getContentViewLayout();

    protected abstract void initView();

}
