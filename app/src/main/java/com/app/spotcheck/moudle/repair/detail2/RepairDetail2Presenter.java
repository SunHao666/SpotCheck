package com.app.spotcheck.moudle.repair.detail2;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.moudle.bean.DepartmentBean;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

class RepairDetail2Presenter extends BasePresenter<RepairDetail2View> {
    public void getDetailInfo(String repid) {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("repId", repid);
        NetManager.getInstance().api().gotoDispatch(convertMapToBody(map))
                .enqueue(new BaseCallback<RepairDetailBean>() {
                    @Override
                    protected void onSuccess(RepairDetailBean o) {
                        mView.disLoading();
                        mView.showSuccess(o);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.disLoading();
                        mView.showError(msg);
                    }
                });
    }

    public void saveDispatch(String repid, String dispatchman, String dispatchtime, String reptime, String repcompany) {
        mView.showLoading();
        Map<String, String> map = new HashMap<>();
        map.put("repId", repid);
        map.put("dispatchman", dispatchman);
//        map.put("dispatchtime", dispatchtime);
        map.put("reptime",reptime);
        map.put("repcompany", repcompany);
        NetManager.getInstance().api().saveDispatch(convertMapToBody(map))
                .enqueue(new BaseCallback<String>() {
                    @Override
                    protected void onSuccess(String o) {
                        mView.showFinish(o);
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if (code == 605) {
                            mView.showFinish(msg);
                        } else {
                            mView.showError(msg);
                        }
                        mView.disLoading();
                    }
                });
    }


    public void getCompanyList() {
        mView.showLoading();
        Map<String,String> map = new HashMap<>();
        NetManager.getInstance().api().getDepartmentList(map)
                .enqueue(new BaseCallback<DepartmentBean>() {
                    @Override
                    protected void onSuccess(DepartmentBean bean) {
                        mView.showCompanyList(bean);
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.disLoading();
                        mView.showError(msg);
                    }
                });
    }
}
