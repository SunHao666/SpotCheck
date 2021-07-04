package com.app.spotcheck.moudle.repair.repairitem;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.RepairItemBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RepairItemPresenter extends BasePresenter<RepairItemView> {
    public void getRepairItemList(String mainid, String mainname, String state) {
        Map<String,String> map = new HashMap<>();
        map.put("mainid",mainid);
        map.put("mainname",mainname);
        map.put("state",state);
        NetManager.getInstance().api().getRecordList(convertMapToBody(map))
                .enqueue(new BaseCallback<RepairItemBean>() {
                    @Override
                    protected void onSuccess(RepairItemBean repairItemBean) {
                        mView.showSuccess(repairItemBean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if(code != -1){
                            mView.showError(msg);
                        }
                    }
                });
    }
}
