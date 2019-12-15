package com.app.spotcheck.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 通用Fragment基类.
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbind;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewLayout(), container, false);
        mUnbind = ButterKnife.bind(this, view);
        return view;
    }

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
    }

    @LayoutRes
    protected abstract int getContentViewLayout();

    protected abstract void initView();

}
