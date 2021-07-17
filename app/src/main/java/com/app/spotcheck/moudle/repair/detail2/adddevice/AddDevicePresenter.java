package com.app.spotcheck.moudle.repair.detail2.adddevice;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.AddDeviceBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

class AddDevicePresenter extends BasePresenter<AddDeviceView> {
    public void getSpareStoreList(String sparename) {
        Map<String, String> map = new HashMap<>();
        map.put("sparename", sparename);
        NetManager.getInstance().api().getSpareStoreList(convertMapToBody(map))
                .enqueue(new BaseCallback<AddDeviceBean>() {
                    @Override
                    protected void onSuccess(AddDeviceBean repairItemBean) {
                        mView.showSuccess(repairItemBean.getSearchList());
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });
    }
}
