package com.app.spotcheck.network;

import java.net.ConnectException;

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
            onFailed(403, t.getMessage());
        }  else {
            onFailed(405, t.getMessage());
        }
    }
}