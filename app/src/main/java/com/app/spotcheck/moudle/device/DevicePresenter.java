package com.app.spotcheck.moudle.device;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.DeviceListBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

public class DevicePresenter extends BasePresenter<DeviceView> {

    public void fetch(){
        Map map = new HashMap<Object,Object>();
        NetManager.getInstance().api().getDeviceList(convertMapToBody(map))
                .enqueue(new BaseCallback<DeviceListBean>() {
                    @Override
                    protected void onSuccess(DeviceListBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(code,msg);
                    }
                });

    }

}
