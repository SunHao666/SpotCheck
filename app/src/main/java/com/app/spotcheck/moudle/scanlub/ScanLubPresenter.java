package com.app.spotcheck.moudle.scanlub;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.ScanLubBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ClassName: ScanLubPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 17:46
 */
public class ScanLubPresenter extends BasePresenter<ScanLubView> {
    public void fetch(String lrecno) {
        Map<String,String> map = new HashMap<>();
        map.put("lrecno",lrecno);
        NetManager.getInstance().api().getLubricationInfo(convertMapToBody(map))
                .enqueue(new BaseCallback<ScanLubBean>() {
                    @Override
                    protected void onSuccess(ScanLubBean scanLubBean) {
                        mView.showSuccess(scanLubBean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(msg);
                    }
                });


    }

    public void save(String lrecno, String workhours, String stophours, String remark,String loginname) {

        Map<String,String> map = new HashMap<>();
        map.put("lrecno",lrecno);
        map.put("workhours",workhours);
        map.put("stophours",stophours);
        map.put("execman",loginname);
        map.put("remark",remark);
        NetManager.getInstance().api().saveLubrication(convertMapToBody(map))
                .enqueue(new Callback<BaseCallModel>() {
                    @Override
                    public void onResponse(Call<BaseCallModel> call, Response<BaseCallModel> response) {
                        BaseCallModel body = response.body();
                        if(body == null)
                            return;
                        if(body.result_code == 500){
                            mView.showSaveSuccess(body.result_message);
                        }else{
                            mView.showError(body.result_message);
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseCallModel> call, Throwable t) {
                        mView.showError(t.getMessage());
                    }
                });

    }
}
