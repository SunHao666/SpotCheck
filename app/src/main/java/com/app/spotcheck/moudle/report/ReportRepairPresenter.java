package com.app.spotcheck.moudle.report;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.bean.PatralCheckBean;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
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

class ReportRepairPresenter extends BasePresenter<ReportRepairView> {
    /**
     * 故障类型
     */
    public void getExceptionList() {
        Map<String,String> map = new HashMap<>();
        NetManager.getInstance().api().problemKindInfo(map)
                .enqueue(new BaseCallback<ProKindBean>() {
                    @Override
                    protected void onSuccess(ProKindBean bean) {
                        mView.showProList(bean);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                    }
                });
    }

    public void scanResult(String qrcode){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",qrcode);
        NetManager.getInstance().api().getDevByQcode(convertMapToBody(map))
                .enqueue(new BaseCallback<RepairReportScanBean>() {
                    @Override
                    protected void onSuccess(RepairReportScanBean bean) {
                        mView.showScanSuccess(bean,qrcode);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(code,msg);
                    }
                });
    }

    public void saveRepairApply(Map<String, RequestBody> map, List<MultipartBody.Part> parts) {
        mView.showLoad();
        NetManager.getInstance().api().saveRepairApply(map,parts)
                .enqueue(new Callback<BaseCallModel>() {
                    @Override
                    public void onResponse(Call<BaseCallModel> call, Response<BaseCallModel> response) {
                        BaseCallModel body = response.body();
                        if ( body.result_code == 600){
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
