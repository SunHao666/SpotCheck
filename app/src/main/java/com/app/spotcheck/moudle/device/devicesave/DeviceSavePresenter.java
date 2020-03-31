package com.app.spotcheck.moudle.device.devicesave;

import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceSavePresenter extends BasePresenter<DeviceSaveView> {

    public void fetch(){

    }

    public void save(String recid, String repairtime, String repairhour, String repairwork, String repairman) {
        Map<String,String> map = new HashMap<>();
        map.put("recid",recid);
        map.put("repairtime",repairtime);
        map.put("repairhour",repairhour);
        map.put("repairman",repairman);
        map.put("repairwork",repairwork);
        NetManager.getInstance().api().saveDeviceInfo(map)
                .enqueue(new Callback<BaseCallModel<Object>>() {
                    @Override
                    public void onResponse(Call<BaseCallModel<Object>> call, Response<BaseCallModel<Object>> response) {
                        BaseCallModel<Object> body = response.body();
                        if (response.isSuccessful() && body != null) {
                            if (body.result_code == 700) {
                                mView.showSuccess(body.result_message);
                            }else{
                                mView.showError(1,body.result_message);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseCallModel<Object>> call, Throwable t) {
                        if (t instanceof ConnectException) {
                            mView.showError(403, "连接服务器失败");
                        }  if(t instanceof SocketTimeoutException){
                            mView.showError(404, "连接超时");
                        }else {
                            mView.showError(405, "连接服务器失败");
                        }
                    }
                });


    }
}
