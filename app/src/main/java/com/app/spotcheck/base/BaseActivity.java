package com.app.spotcheck.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.app.spotcheck.base.wrapper.ToastWrapper;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 通用Activity基类.
 */
public abstract class BaseActivity<T extends BasePresenter> extends TransitionActivity implements BaseView{

    private Unbinder mUnbind;
    public T mPresenter;
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        mUnbind = ButterKnife.bind(this);
        ToastWrapper.init(this);
        mPresenter = initPresenter();
        if(mPresenter == null){
            throw new RuntimeException("mPresenter no init");
        }
        mPresenter.attachView(this);
        initView();
    }

    protected abstract T initPresenter();

    @Override
    protected final void onDestroy() {
        super.onDestroy();
        mPresenter.dettachView(this);
        mUnbind.unbind();
        mUnbind = null;
    }
    @LayoutRes
    protected abstract int getContentViewLayout();

    protected abstract void initView();

    public static void show(Context context, Class<?> cls) {
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

}
