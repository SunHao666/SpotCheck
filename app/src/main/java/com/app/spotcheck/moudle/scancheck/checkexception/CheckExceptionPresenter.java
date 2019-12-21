package com.app.spotcheck.moudle.scancheck.checkexception;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckExceptionPresenter extends BasePresenter<CheckExceptionView> {

    public void fetch(int id) {
        NetManager.getInstance().api().getUnCheckItemInfo(id)
                .enqueue(new BaseCallback<CheckExceptionBean>() {
                    @Override
                    protected void onSuccess(CheckExceptionBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
//                        mView.showSuccess(bean);
//                        mView.showError(msg);
                    }
                });

    }

    public void save(Map<String, RequestBody> map, List<MultipartBody.Part> parts) {
        NetManager.getInstance().api().saveCheckItem(map,parts)
                .enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        LoginBean body = response.body();
                        if (body.result_code == 200){
                            mView.save(body.getResult_message());
                        }else{
                            mView.showError(body.getResult_message());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {

                    }
                });
    }
}
