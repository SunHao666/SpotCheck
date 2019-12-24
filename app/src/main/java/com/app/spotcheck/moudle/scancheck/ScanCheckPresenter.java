package com.app.spotcheck.moudle.scancheck;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.HomeScanBean;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ClassName: ScanCheckPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/20 18:58
 */
public class ScanCheckPresenter extends BasePresenter<ScanCheckView> {

    public void fetch(String execid){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",execid);
        NetManager.getInstance().api().getUnCheckItemList(convertMapToBody(map))
                .enqueue(new BaseCallback<HomeScanBean>() {
                    @Override
                    protected void onSuccess(HomeScanBean bean) {
                        mView.showSuccess(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });
    }

    public void saveCheckReult(String execid, String execman, List<String> recidList) {

        NetManager.getInstance().api().saveAllItem(execid,execman,recidList)
                .enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        LoginBean body = response.body();
//                        if(body.result_code == 200){
//                            mView.save(body.getResult_message());
//                        }else{
//                            mView.showError(body.getResult_message());
//                        }
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {
                        mView.save("success");
//                        mView.showError(t.getMessage());
                    }
                });

    }
}
