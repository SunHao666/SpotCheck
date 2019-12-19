package com.app.spotcheck.moudle.lubrication;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.home.HomeView;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

/**
 * @ClassName: HomePresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 15:57
 */
public class LubPresenter extends BasePresenter<LubView> {

    public void fetch(){
        NetManager.getInstance().api().index()
                .enqueue(new BaseCallback<HomeBean>() {
                    @Override
                    protected void onSuccess(HomeBean bean) {
                        if(mView == null){
                            return;
                        }
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if(mView == null){
                            return;
                        }
                        mView.showError(msg);
                    }
        });
    }
}
