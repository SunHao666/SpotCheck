package com.app.spotcheck.moudle.patralcheck;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PatralCheckPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 17:17
 */
public class PatralCheckPresenter extends BasePresenter<PatralCheckView> {

    public void fetch(String qrcode){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",qrcode);
        NetManager.getInstance().api().gotoAddRepair(convertMapToBody(map))
                .enqueue(new BaseCallback<ScanCheckBean>() {
                    @Override
                    protected void onSuccess(ScanCheckBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });
    }
}
