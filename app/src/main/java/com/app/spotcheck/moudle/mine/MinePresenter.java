package com.app.spotcheck.moudle.mine;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.MineBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MinePresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 20:36
 */
public class MinePresenter extends BasePresenter<MineView> {

    public void fetch(String workman){
        Map<String,String> map = new HashMap<>();
        map.put("workman",workman);

        NetManager.getInstance().api().getInfo(convertMapToBody(map))
                .enqueue(new BaseCallback<MineBean>() {
                    @Override
                    protected void onSuccess(MineBean bean) {
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
