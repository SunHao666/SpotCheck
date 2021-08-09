package com.app.spotcheck.moudle.scancheck;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.moudle.bean.HomeScanBean;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;
import com.app.spotcheck.utils.GlobalKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * @ClassName: ScanCheckPresenter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/20 18:58
 */
public class ScanCheckPresenter extends BasePresenter<ScanCheckView> {

    public void fetch(String execid){
        Map<String,String> map = new HashMap<>();
        map.put("taskId",execid);
//        map.put("taskId",
//                "9f231f6db2c24b129e343df067aec3e4");
        NetManager.getInstance().api().getUnCheckItemList(convertMapToBody(map))
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

    public void saveCheckReult(String execid, String execman, List<Integer> recidList) {
//        @Field("execid")String execid,@Field("execman")String execman,@Field("recidList") List<Integer> recidList

        Map<String,Object> map = new HashMap<>();
        map.put("execid",execid);
        map.put("execman",execman);
        map.put("recidList",recidList);
        NetManager.getInstance().api().saveAllItem(convertMapToBody(map))
                .enqueue(new Callback<BaseCallModel>() {
                    @Override
                    public void onResponse(Call<BaseCallModel> call, Response<BaseCallModel> response) {
                        BaseCallModel body = response.body();
                        if(body.result_code == 401){
                            mView.save(body.getResult_message());
                        }else{
                            mView.showError(body.getResult_message());
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseCallModel> call, Throwable t) {
                        mView.save("success");
//                        mView.showError(t.getMessage());
                    }
                });

    }
}
