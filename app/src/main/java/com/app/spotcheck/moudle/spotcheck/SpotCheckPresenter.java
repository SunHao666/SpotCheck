package com.app.spotcheck.moudle.spotcheck;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

/**
 * @ClassName: SpotCheckPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:15
 */
public class SpotCheckPresenter extends BasePresenter<SpotCheckView> {

    public void getCheckPlanList(String mainid,String mainname){
        NetManager.getInstance().api().getCheckPlanList(mainid,mainname)
                .enqueue(new BaseCallback<SpotCheckAllBean>() {
                    @Override
                    protected void onSuccess(SpotCheckAllBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });
    }

    public void getUnCheckPlanList(String mainid,String mainname){
        NetManager.getInstance().api().getUnCheckPlanList(mainid,mainname)
                .enqueue(new BaseCallback<SpotCheckAllBean>() {
                    @Override
                    protected void onSuccess(SpotCheckAllBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });
    }


    public void getCheckedPlanList(String mainid,String mainname){
        NetManager.getInstance().api().getCheckedPlanList(mainid,mainname)
                .enqueue(new BaseCallback<SpotCheckAllBean>() {
                    @Override
                    protected void onSuccess(SpotCheckAllBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });
    }
}
