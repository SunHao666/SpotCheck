package com.app.spotcheck.moudle.home;

import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.HomeScanBean;
import com.app.spotcheck.moudle.bean.LubAllBean;
import com.app.spotcheck.moudle.bean.PatralCheckBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
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
public class HomePresenter extends BasePresenter<HomeView> {

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
                        mView.showError(code,msg);
                    }
        });
    }


    public void scanCheck(String qrcode){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",qrcode);
        NetManager.getInstance().api().getUnCheckPlanList(convertMapToBody(map))
                .enqueue(new BaseCallback<SpotCheckAllBean>() {
                    @Override
                    protected void onSuccess(SpotCheckAllBean bean) {
                        mView.showScanSuccess(bean,qrcode);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(code,msg);
                    }
                });
    }

    public void scanPatralCheck(String qrcode){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",qrcode);
        NetManager.getInstance().api().gotoAddRepair(convertMapToBody(map))
                .enqueue(new BaseCallback<PatralCheckBean>() {
                    @Override
                    protected void onSuccess(PatralCheckBean bean) {
                        mView.showPatralSuccess(bean,qrcode);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(code,msg);
                    }
                });
    }
    /*维修*/
    public void scanResult(String qrcode){
        Map<String,String> map = new HashMap<>();
        map.put("qrcode",qrcode);
        NetManager.getInstance().api().getDevByQcode(convertMapToBody(map))
                .enqueue(new BaseCallback<RepairReportScanBean>() {
                    @Override
                    protected void onSuccess(RepairReportScanBean bean) {
                        mView.showRepairSuccess(bean,qrcode);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(code,msg);
                    }
                });
    }

    public void scanLub(String qrcode){
        Map<String,Object> map = new HashMap<>();
        map.put("qrcode",qrcode);
        map.put("mainname","");
        map.put("execstatus","0");//0：未完成；1：完成
        NetManager.getInstance().api().getLubPlanList(convertMapToBody(map))
                .enqueue(new BaseCallback<LubAllBean>() {
                    @Override
                    protected void onSuccess(LubAllBean bean) {
                        mView.showLubSuccess(bean,qrcode);
                    }

                    @Override
                    protected void onFailed(int code, String msg) {
                        mView.showError(code,msg);
                    }
                });
    }
}
