package com.app.spotcheck.moudle.device.deviceinfo;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.DeviceInfoBean;
import com.app.spotcheck.moudle.bean.DeviceListBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

public class DeviceInfoPresenter extends BasePresenter<DeviceInfoView> {

    public void fetch(String mainid){
        mView.showLoading();
        Map map = new HashMap<String,String>();
        map.put("mainid",mainid);
        NetManager.getInstance().api().getDeviceInfo(convertMapToBody(map))
                .enqueue(new BaseCallback<DeviceInfoBean>() {
                    @Override
                    protected void onSuccess(DeviceInfoBean bean) {
                        mView.showSuccess(bean);
                        mView.dissLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(code,msg);
                        mView.dissLoading();
                    }
                });
    }
}
