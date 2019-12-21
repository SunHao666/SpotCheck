package com.app.spotcheck.moudle.patralcheck;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PatralCheckPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 17:17
 */
public class PatralCheckPresenter extends BasePresenter<PatralCheckView> {

    public void fetch(String execid){
        NetManager.getInstance().api().getUnCheckItemList(execid)
                .enqueue(new BaseCallback<ScanCheckBean>() {
                    @Override
                    protected void onSuccess(ScanCheckBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        ScanCheckBean bean = new ScanCheckBean();
                        bean.setMAINNAME("1111");
                        bean.setPARTNAME("2222");
                        List<ScanCheckBean.SearchListBean> bean1 = new ArrayList<>();
                        for (int i = 0; i < 4; i++) {
                            ScanCheckBean.SearchListBean bean2 = new ScanCheckBean.SearchListBean();
                            bean2.setITEMNAME("212121"+"place"+i);
                            bean1.add(bean2);
                        }
                        bean.setSearchList(bean1);
                        mView.showSuccess(bean);
//                        mView.showError(msg);
                    }
                });
    }
}
