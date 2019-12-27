package com.app.spotcheck.network;

import com.app.spotcheck.moudle.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseCallback<T> implements Callback<BaseCallModel<T>> {

    public BaseCallback() {
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFailed(int code, String msg);

    @Override
    public void onResponse(Call<BaseCallModel<T>> call, Response<BaseCallModel<T>> response) {
        BaseCallModel<T> baseCallModel = response.body();
        if (response.isSuccessful() && baseCallModel != null) {
            if (baseCallModel.result_code == 200) {
                onSuccess(baseCallModel.result);
            } else if (baseCallModel.result_code == 303) {
                //比如 做token无效统一处理
                onFailed(baseCallModel.result_code, baseCallModel.result_message);
                EventBus.getDefault().post(new BaseEvent());
            } else {
                onFailed(baseCallModel.result_code, baseCallModel.result_message);
            }
        } else {
            onFailed(response.code(), response.message());
        }
    }

    @Override
    public void onFailure(Call<BaseCallModel<T>> call, Throwable t) {
        if (t instanceof ConnectException) {
            onFailed(403, "连接服务器失败");
        }  if(t instanceof SocketTimeoutException){
            onFailed(404, "连接超时");
        }else {
            onFailed(405, "连接服务器失败");
        }
    }
}