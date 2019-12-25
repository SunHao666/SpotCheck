package com.app.spotcheck.moudle.patralcheck;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.moudle.bean.PatralCheckBean;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ClassName: PatralCheckPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 17:17
 */
public class PatralCheckPresenter extends BasePresenter<PatralCheckView> {

    public void fetch(String qrcode){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",qrcode);
        NetManager.getInstance().api().gotoAddRepair(convertMapToBody(map))
                .enqueue(new BaseCallback<PatralCheckBean>() {
                    @Override
                    protected void onSuccess(PatralCheckBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });
    }

    public void save(Map<String, RequestBody> map, List<MultipartBody.Part> parts) {
        NetManager.getInstance().api().addRepairSave(map,parts)
                .enqueue(new Callback<BaseCallModel>() {
                    @Override
                    public void onResponse(Call<BaseCallModel> call, Response<BaseCallModel> response) {
                        BaseCallModel body = response.body();
                        if ( body.result_code == 600){
                            mView.save(body.getResult_message());
                        }else{
                            mView.showError(body.getResult_message());
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseCallModel> call, Throwable t) {
                        mView.showError(t.getMessage());
                    }
                });
    }
}
