package com.app.spotcheck.moudle.lubrication;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.LubAllBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: HomePresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 15:57
 */
public class LubPresenter extends BasePresenter<LubView> {

    public void getCheckPlanList(String mainid,String mainname){
//        NetManager.getInstance().api().getCheckPlanList(mainid,mainname)
//                .enqueue(new BaseCallback<SpotCheckAllBean>() {
//                    @Override
//                    protected void onSuccess(SpotCheckAllBean bean) {
//
//                    }
//
//                    @Override
//                    protected void onFailed(int code, String msg) {
//                    }
//                });
    }

    public void getLubPlanList(String qrcode,String mainname,String execstatus){

        Map<String,Object> map = new HashMap<>();
        map.put("qrcode",qrcode);
        map.put("mainname",mainname);
        map.put("execstatus",execstatus);//0：未完成；1：完成
        NetManager.getInstance().api().getLubPlanList(convertMapToBody(map))
                .enqueue(new BaseCallback<LubAllBean>() {
                    @Override
                    protected void onSuccess(LubAllBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if(code != -1&& code != -400){
                            mView.showError(msg);
                        }else{
                            mView.showFinsh();
                        }
                    }
                });
    }


    public void getLubedPlanList(String mainid,String mainname){
//        NetManager.getInstance().api().getCheckedPlanList(mainid,mainname)
//                .enqueue(new BaseCallback<SpotCheckAllBean>() {
//                    @Override
//                    protected void onSuccess(SpotCheckAllBean bean) {
//                    }
//
//                    @Override
//                    protected void onFailed(int code, String msg) {
//                    }
//                });
    }
}
