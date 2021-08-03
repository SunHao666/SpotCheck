package com.app.spotcheck.moudle.repair.detail4;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.moudle.bean.RepairDeviceListBean;
import com.app.spotcheck.moudle.bean.RepairManListBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

class RepairDetail4Presenter extends BasePresenter<RepairDetail4View> {
    public void getDetailInfo(String repid) {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("repId", repid);
        NetManager.getInstance().api().gotoRepairRecRefirm(convertMapToBody(map))
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
//        map.put("finishchecktime", finishchecktime);
        NetManager.getInstance().api().saveRepairRecRefirm(convertMapToBody(map))
                .enqueue(new BaseCallback<String>() {
                    @Override
                    protected void onSuccess(String o) {
                        mView.showFinish(o);
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if (code == 609 || code == 610) {
                            mView.showFinish(msg);
                        } else {
                            mView.showError(msg);
                        }
                        mView.disLoading();
                    }
                });
    }

    public void getRepairApareListByRepId(String repid) {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("repId", repid);
        NetManager.getInstance().api().getRepairApareListByRepId(convertMapToBody(map))
                .enqueue(new BaseCallback<RepairDeviceListBean>() {
                    @Override
                    protected void onSuccess(RepairDeviceListBean bean) {
//                        mView.showSuccess(bean);
                        mView.showDeviceList(bean.getSearchList());
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                        mView.disLoading();
                    }
                });
    }

    public void getRepairManListByRepId(String repid) {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("repId", repid);
        NetManager.getInstance().api().getRepairManListByRepId(convertMapToBody(map))
                .enqueue(new BaseCallback<RepairManListBean>() {
                    @Override
                    protected void onSuccess(RepairManListBean bean) {
                        mView.showManList(bean.getSearchList());
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                        mView.disLoading();
                    }
                });
    }

}
