package com.app.spotcheck.moudle.scancheck.checkexception;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

public class CheckExceptionPresenter extends BasePresenter<CheckExceptionView> {

    public void fetch(int id) {
        NetManager.getInstance().api().getUnCheckItemInfo(id)
                .enqueue(new BaseCallback<CheckExceptionBean>() {
                    @Override
                    protected void onSuccess(CheckExceptionBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
//                        mView.showSuccess(bean);
//                        mView.showError(msg);
                    }
                });

    }
}
