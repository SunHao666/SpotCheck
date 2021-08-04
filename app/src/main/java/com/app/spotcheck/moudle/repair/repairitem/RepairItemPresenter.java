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
    public void getRepairItemList(String mainid, String mainname, String state,String repkind) {
        Map<String,String> map = new HashMap<>();
        map.put("mainid",mainid);
        map.put("mainname",mainname);
        map.put("state",state);
        map.put("repkind",repkind);
        if (state.equals("1")){
            getApplyCheckList(map);
        }else if(state.equals("2")){
            getDispatchList(map);
        }else if(state.equals("3")){
            geWorkRefirmList(map);
        }else if(state.equals("4")){
            geFinishRefirmList(map);
        }else if(state.equals("5")){
            getFinishList(map);
        }

    }

    private void getApplyCheckList(Map<String, String> map) {
        NetManager.getInstance().api().getApplyCheckList(convertMapToBody(map))
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

    private void getDispatchList(Map<String, String> map) {
        NetManager.getInstance().api().getDispatchList(convertMapToBody(map))
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

    private void geWorkRefirmList(Map<String, String> map) {
        NetManager.getInstance().api().geWorkRefirmList(convertMapToBody(map))
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

    private void geFinishRefirmList(Map<String, String> map) {
        NetManager.getInstance().api().geFinishRefirmList(convertMapToBody(map))
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


    private void getFinishList(Map<String, String> map) {
        NetManager.getInstance().api().getFinishList(convertMapToBody(map))
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
