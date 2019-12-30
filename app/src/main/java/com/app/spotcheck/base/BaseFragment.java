package com.app.spotcheck.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 通用Fragment基类.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    private Unbinder mUnbind;
    public T mPresenter;
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewLayout(), container, false);
        mUnbind = ButterKnife.bind(this, view);
        ToastWrapper.init(getActivity());
        mPresenter = initPresenter();

        if(mPresenter == null){
            throw new RuntimeException("mPresenter no init");
        }
        mPresenter.attachView(this);
        initData();
        return view;
    }

    protected abstract void initData();

    protected abstract T initPresenter();

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }


    @Override
    public final void onDestroyView() {
        super.onDestroyView();
        mUnbind.unbind();
        mUnbind = null;
        mPresenter.dettachView(this);
    }

    @LayoutRes
    protected abstract int getContentViewLayout();

    protected abstract void initView();

}
