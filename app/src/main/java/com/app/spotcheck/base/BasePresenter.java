package com.app.spotcheck.base;

import android.view.View;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @ClassName: BasePresenter
 * @Description: c
 * @Author: 作者名
 * @CreateDate: 2019/12/18 14:35
 */
public class BasePresenter<V> {

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

    public static RequestBody convertMapToBody(Map<?,?> map) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf- 8"), new JSONObject(map).toString());
    }
}
