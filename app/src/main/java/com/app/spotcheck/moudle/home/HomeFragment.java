package com.app.spotcheck.moudle.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.HomeScanBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.patralcheck.PatralCheckActivity;
import com.app.spotcheck.moudle.scancheck.ScanCheckActivity;
import com.app.spotcheck.moudle.scanlub.ScanLubActivity;
import com.app.spotcheck.network.Contant;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {


    @BindView(R.id.home_check_noNum)
    TextView homeCheckNoNum;
    @BindView(R.id.home_plan_time)
    TextView homePlanTime;
    @BindView(R.id.tv_daji)
    TextView tvDaji;
    @BindView(R.id.tv_checnk_place)
    TextView tvChecnkPlace;
    @BindView(R.id.home_lub_noNum)
    TextView homeLubNoNum;
    @BindView(R.id.home_plan_lub_time)
    TextView homePlanLubTime;
    @BindView(R.id.tv_lub_daji)
    TextView tvLubDaji;
    @BindView(R.id.tv_lub_place)
    TextView tvLubPlace;
    @BindView(R.id.iv_home_check)
    TextView ivHomeCheck;
    @BindView(R.id.iv_home_setpro)
    TextView ivHomeSetpro;
    @BindView(R.id.iv_home_lub)
    TextView ivHomeLub;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            mPresenter.fetch();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.fetch();
    }

    @Override
    protected void initData() {
        long logtime = SPUtils.getInstance(getActivity()).getLong("logtime");
        LogUtils.error("logtime"+logtime);
        mPresenter.fetch();
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void showSuccess(HomeBean bean) {
        HomeBean.CHKINFOBean chkinfo = bean.getCHKINFO();
        HomeBean.LUBINFOBean lubinfo = bean.getLUBINFO();
        homeCheckNoNum.setText(chkinfo.getCHK_UNNUM()+"");
        if(chkinfo.getCHK_EXECSTARTTIME() == null || chkinfo.getCHK_EXECENDTIME() == null){
            homePlanLubTime.setText("");
        }else{
            homePlanTime.setText(chkinfo.getCHK_EXECSTARTTIME() + "~" + chkinfo.getCHK_EXECENDTIME());
        }

        tvDaji.setText(chkinfo.getCHK_MAINNAME());
        tvChecnkPlace.setText(chkinfo.getCHK_PARTNAME());

        homeLubNoNum.setText(lubinfo.getLUB_UNNUM()+"");
        if(lubinfo.getLUB_EXECSTARTTIME() == null || lubinfo.getLUB_EXECENDTIME() == null){
            homePlanLubTime.setText("");
        }else{
            homePlanLubTime.setText(lubinfo.getLUB_EXECSTARTTIME() + "~" + lubinfo.getLUB_EXECENDTIME());
        }
        tvLubDaji.setText(lubinfo.getLUB_MAINNAME());
        tvLubPlace.setText(lubinfo.getLUB_PARTNAME());
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
    }

    @Override
    public void showScanSuccess(SpotCheckAllBean bean, String qrcode) {
        List<SpotCheckAllBean.SearchListBean> searchList = bean.getSearchList();
        if(searchList == null || searchList.size() == 0){
            ToastWrapper.show("暂无待检项目");
        }else{
            //跳转至点检待检
            onCheckScanClick.onClick(1);
            Contant.CHECKQRCODE  = qrcode;
        }
    }

    @OnClick({R.id.iv_home_check, R.id.iv_home_setpro, R.id.iv_home_lub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_home_check:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class),1001);
//                mPresenter.scanCheck(Contant.CHECKQRCODE);
//                Contant.CHECKQRCODE  = "E002M05P0002";
//                Contant.CHECKSEARCH  = "";
//                onCheckScanClick.onClick(1);
                break;
            case R.id.iv_home_setpro:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class),1002);
//                Intent intent = new Intent(getActivity(), PatralCheckActivity.class);
//                intent.putExtra("execid","E002M05P0002");
//                startActivity(intent);
                break;
            case R.id.iv_home_lub:
//                Contant.LUBQRCODE = "E011M03L0001";
//                Contant.LUBSEARCH = "";
//                onLubScanClick.onClick(1);
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class),1003);
//                startActivity(new Intent(getActivity(), ScanLubActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }
        String result = data.getStringExtra(Intents.Scan.RESULT);
        if(TextUtils.isEmpty(result)|| !result.contains("qrcode=")){
            ToastWrapper.show("二维码格式不正确");
            return;
        }
        LogUtils.error("scan before="+result);
        result = result.substring(7,result.length());
        LogUtils.error(result);
        if(requestCode == 1001){
            Contant.CHECKQRCODE  = result;
            Contant.CHECKSEARCH  = "";
            onCheckScanClick.onClick(1);
        }else if(requestCode == 1002){
            Intent intent = new Intent(getActivity(), PatralCheckActivity.class);
            intent.putExtra("execid",result);
            startActivity(intent);
        }else if(requestCode == 1003){
            Contant.LUBQRCODE = result;
            Contant.LUBSEARCH = "";
            onLubScanClick.onClick(1);
        }
    }

    private OnCheckScanClick onCheckScanClick;

    public void setOnCheckScanClick(OnCheckScanClick onCheckScanClick) {
        this.onCheckScanClick = onCheckScanClick;
    }
    public interface OnCheckScanClick{
        public void onClick(int position);
    }

    private OnLubScanClick onLubScanClick;

    public void setOnLubScanClick(OnLubScanClick onLubScanClick) {
        this.onLubScanClick = onLubScanClick;
    }
    public interface OnLubScanClick{
        public void onClick(int position);
    }
}
