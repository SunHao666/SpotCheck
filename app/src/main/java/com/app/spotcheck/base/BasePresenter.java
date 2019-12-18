package com.app.spotcheck.base;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * @ClassName: BasePresenter
 * @Description: c
 * @Author: 作者名
 * @CreateDate: 2019/12/18 14:35
 */
public class BasePresenter<V extends BaseView> {

    public V mView;
    private WeakReference<V> weakReference;

    public void attachView(V view){
        weakReference = new WeakReference<V>(view);
        mView = weakReference.get();
    }

    public void dettachView(V view){
        if(weakReference != null && mView!=null){
            weakReference.clear();
            weakReference = null;
        }
    }

}
