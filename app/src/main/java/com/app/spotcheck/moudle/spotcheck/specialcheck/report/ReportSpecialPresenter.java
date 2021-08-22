package com.app.spotcheck.moudle.spotcheck.specialcheck.report;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.app.spotcheck.moudle.bean.ReportSearchBean;
import com.app.spotcheck.moudle.bean.SpecialItemBean;
import com.app.spotcheck.moudle.bean.SpecialSearchBean;
import com.app.spotcheck.network.BaseCallModel;
import com.app.spotcheck.network.BaseCallback;
import com.app.spotcheck.network.NetManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ReportSpecialPresenter extends BasePresenter<ReportSpecialView> {

    public void search(String mainname) {
        mView.showLoad();
        Map<String,String> map = new HashMap<>();
        map.put("mainname",mainname);
        NetManager.getInstance().api().getDevMainList(convertMapToBody(map))
                .enqueue(new BaseCallback<SpecialSearchBean>() {
                    @Override
                    protected void onSuccess(SpecialSearchBean bean) {
                        mView.showSearchList(bean);
                        mView.hideLoad();
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(code,msg);
                        mView.hideLoad();
                    }
                });
    }

    public void saveSpecialApply(Map<String, String> map) {
        mView.showLoad();
        NetManager.getInstance().api().saveSpecialcheck(convertMapToBody(map))
                .enqueue(new Callback<BaseCallModel>() {
                    @Override
                    public void onResponse(Call<BaseCallModel> call, Response<BaseCallModel> response) {
                        BaseCallModel body = response.body();
                        if ( body.result_code == 200){
                            mView.showSubmitSuccess(body.getResult_message());
                        }else{
                            mView.showError(100,body.getResult_message());
                        }
                        mView.hideLoad();
                    }

                    @Override
                    public void onFailure(Call<BaseCallModel> call, Throwable t) {
                        mView.showError(100,t.getMessage());
                        mView.hideLoad();
                    }
                });
    }
}
