package com.app.spotcheck.moudle.repair.detail2.addperson;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.AddDeviceBean;
import com.app.spotcheck.moudle.bean.AddPersonBean;
import com.app.spotcheck.moudle.bean.DepartmentBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

public class AddPersonPresenter extends BasePresenter<AddPersonView> {
    public void saveRepairMan(String repId, String workerNo, String hourD, String hourF) {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("repId", repId);
        map.put("workerNo", workerNo);
        map.put("hourD", hourD);
        map.put("hourF", hourF);
        NetManager.getInstance().api().saveRepairMan(convertMapToBody(map))
                .enqueue(new BaseCallback<String>() {
                    @Override
                    protected void onSuccess(String repairItemBean) {
                        mView.showFinish();
                        mView.hideLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                        mView.hideLoading();
                    }
                });
    }

    public void getWorkerList() {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        NetManager.getInstance().api().getWorkerList(convertMapToBody(map))
                .enqueue(new BaseCallback<AddPersonBean>() {
                    @Override
                    protected void onSuccess(AddPersonBean bean) {
                        mView.showPersonList(bean);
                        mView.hideLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.hideLoading();
                        mView.showError(msg);
                    }
                });
    }
}
