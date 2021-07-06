package com.app.spotcheck.moudle.repair.detail1;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

class RepairDetail1Presenter extends BasePresenter<RepairDetail1View> {
    public void getDetailInfo(String repid) {
        mView.showLoading();
        Map<String,String> map = new HashMap<>();
        map.put("repId",repid);
        NetManager.getInstance().api().gotoApplyCheck(convertMapToBody(map))
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

    public void del(String repid) {
        mView.showLoading();
        Map<String,String> map = new HashMap<>();
        map.put("repId",repid);
        NetManager.getInstance().api().saveApplyCommitDel(convertMapToBody(map))
                .enqueue(new BaseCallback<String>() {
                    @Override
                    protected void onSuccess(String o) {
                        mView.showFinish(o);
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if(code == 603){
                            mView.showFinish(msg);
                        }else{
                            mView.showError(msg);
                        }
                        mView.disLoading();
                    }
                });
    }

    public void pass(String repid,String applyCheckman,String applyChecktime) {
        mView.showLoading();
        Map<String,String> map = new HashMap<>();
        map.put("repId",repid);
        map.put("applyCheckman",applyCheckman);
        map.put("applyChecktime",applyChecktime);

        NetManager.getInstance().api().saveApplyCommit(convertMapToBody(map))
                .enqueue(new BaseCallback<String>() {
                    @Override
                    protected void onSuccess(String o) {
                        mView.showFinish(o);
                        mView.disLoading();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if(code == 601){
                            mView.showFinish(msg);
                        }else{
                            mView.showError(msg);
                        }
                        mView.disLoading();
                    }
                });
    }
}
