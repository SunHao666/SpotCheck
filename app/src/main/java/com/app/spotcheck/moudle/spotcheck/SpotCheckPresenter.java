package com.app.spotcheck.moudle.spotcheck;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.KeyWordsBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SpotCheckPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:15
 */
public class SpotCheckPresenter extends BasePresenter<SpotCheckView> {

    public void getCheckPlanList(String qrcode,String mainname,String checkkind){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",qrcode);
        map.put("mainname",mainname);
        map.put("checkkind",checkkind);
        NetManager.getInstance().api().getCheckPlanList(convertMapToBody(map))
                .enqueue(new BaseCallback<SpotCheckAllBean>() {
                    @Override
                    protected void onSuccess(SpotCheckAllBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if(code != -1 && code != -400){
                            mView.showError(msg);
                        }else{
                            mView.showFinish();
                        }
                    }
                });
    }

    public void getUnCheckPlanList(String qrcode,String mainname,String checkkind){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",qrcode);
        map.put("mainname",mainname);
        map.put("checkkind",checkkind);
        NetManager.getInstance().api().getUnCheckPlanList(convertMapToBody(map))
            .enqueue(new BaseCallback<SpotCheckAllBean>() {
        @Override
        protected void onSuccess(SpotCheckAllBean bean) {
            mView.showSuccess(bean);
        }

        @Override
        protected void onFailed(int code, String msg) {
            if(code != -1&& code != -400){
                mView.showError(msg);
            }else{
                mView.showFinish();
            }
        }
    });
}


    public void getCheckedPlanList(String qrcode,String mainname,String checkkind){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",qrcode);
        map.put("mainname",mainname);
        map.put("checkkind",checkkind);
        NetManager.getInstance().api().getCheckedPlanList(convertMapToBody(map))
                .enqueue(new BaseCallback<SpotCheckAllBean>() {
                    @Override
                    protected void onSuccess(SpotCheckAllBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if(code != -1&& code != -400){
                            mView.showError(msg);
                        }else{
                            mView.showFinish();
                        }
                    }
                });
    }

    public void getCheckSearch(String usekind, String userid) {
        Map<String,String> map = new HashMap<>();
        map.put("usekind",usekind);
        map.put("userid",userid);
        NetManager.getInstance().api().getKeyWordInfo(convertMapToBody(map))
                .enqueue(new BaseCallback<KeyWordsBean>() {
                    @Override
                    protected void onSuccess(KeyWordsBean keyWordsBean) {
                        mView.showSearchSuccess(keyWordsBean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        if(code != -1){
                            mView.showError(msg);
                        }else{
                            mView.showFinish();
                        }
                    }
                });


    }
}
