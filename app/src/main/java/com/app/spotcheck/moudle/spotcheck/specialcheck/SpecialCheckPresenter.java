package com.app.spotcheck.moudle.spotcheck.specialcheck;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.RepairItemBean;
import com.app.spotcheck.moudle.bean.SpecialItemBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 专检
 */
class SpecialCheckPresenter extends BasePresenter<SpecialCheckView> {

    public void getSpecialList() {
        Map<String,String> map = new HashMap<>();
        map.put("mainid","");
        NetManager.getInstance().api().getSpecialCheckList(convertMapToBody(map))
                .enqueue(new BaseCallback<SpecialItemBean>() {
                    @Override
                    protected void onSuccess(SpecialItemBean repairItemBean) {
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
