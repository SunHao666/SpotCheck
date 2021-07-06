package com.app.spotcheck.moudle.repair.detail5;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

class RepairDetail5Presenter extends BasePresenter<RepairDetail5View> {
    public void getDetailInfo(String repid) {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("repId", repid);
        NetManager.getInstance().api().gotoDetail(convertMapToBody(map))
                .enqueue(new BaseCallback<RepairDetailBean>() {
                    @Override
                    protected void onSuccess(RepairDetailBean bean) {
                        mView.showSuccess(bean);
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                        mView.disLoading();
                    }
                });
    }

    public void saveRepairRec(String repid, String finishcheckman, String finishchecktime) {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("repId", repid);
        map.put("finishcheckman", finishcheckman);
        map.put("finishchecktime", finishchecktime);
        NetManager.getInstance().api().saveRepairRecRefirm(convertMapToBody(map))
                .enqueue(new BaseCallback<String>() {
                    @Override
                    protected void onSuccess(String o) {
                        mView.showFinish(o);
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if (code == 609) {
                            mView.showFinish(msg);
                        } else {
                            mView.showError(msg);
                        }
                        mView.disLoading();
                    }
                });
    }



}
