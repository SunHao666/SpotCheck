package com.app.spotcheck.moudle.scancheck.checkexception;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckExceptionPresenter extends BasePresenter<CheckExceptionView> {

    public void fetch(int id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        NetManager.getInstance().api().getUnCheckItemInfo(convertMapToBody(map))
                .enqueue(new BaseCallback<CheckExceptionBean>() {
                    @Override
                    protected void onSuccess(CheckExceptionBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });

    }

    public void save(Map<String, RequestBody> map, List<MultipartBody.Part> parts) {
        NetManager.getInstance().api().saveCheckItem(map,parts)
                .enqueue(new Callback<BaseCallModel>() {
                    @Override
                    public void onResponse(Call<BaseCallModel> call, Response<BaseCallModel> response) {
                        BaseCallModel body = response.body();
                        if (body.result_code == 400){
                            mView.save(body.getResult_message());
                        }else{
                            mView.showError(body.getResult_message());
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseCallModel> call, Throwable t) {

                    }
                });
    }
}
