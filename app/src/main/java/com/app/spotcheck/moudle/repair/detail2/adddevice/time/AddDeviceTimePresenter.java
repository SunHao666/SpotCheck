package com.app.spotcheck.moudle.repair.detail2.adddevice.time;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

public class AddDeviceTimePresenter extends BasePresenter<AddDeviceTimeView> {

    public void saveRepairRec(String repid, String spareNo, String usequantity) {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("repId", repid);
        map.put("spareNo", spareNo);
        map.put("usequantity", usequantity);
        NetManager.getInstance().api().saveRepairSpareUse(convertMapToBody(map))
                .enqueue(new BaseCallback<String>() {
                    @Override
                    protected void onSuccess(String o) {
                        mView.showFinish(o);
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if (code == 701) {
                            mView.showFinish(msg);
                        } else {
                            mView.showError(msg);
                        }
                        mView.disLoading();
                    }
                });
    }
}
