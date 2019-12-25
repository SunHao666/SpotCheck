package com.app.spotcheck.moudle.home;

import android.content.Intent;
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
        homePlanTime.setText(chkinfo.getCHK_EXECSTARTTIME() + "~" + chkinfo.getCHK_EXECENDTIME());
        tvDaji.setText(chkinfo.getCHK_MAINNAME());
        tvChecnkPlace.setText(chkinfo.getCHK_PARTNAME());

        homeLubNoNum.setText(lubinfo.getLUB_UNNUM()+"");
        homePlanLubTime.setText(lubinfo.getLUB_EXECSTARTTIME() + "~" + lubinfo.getLUB_EXECENDTIME());
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
//                startActivityForResult(new Intent(getActivity(), CaptureActivity.class),1001);
                mPresenter.scanCheck(Contant.CHECKQRCODE);
                break;
            case R.id.iv_home_setpro:
//                startActivityForResult(new Intent(getActivity(), CaptureActivity.class),1002);
                Intent intent = new Intent(getActivity(), PatralCheckActivity.class);
                intent.putExtra("execid","E002M05P0002");
                startActivity(intent);
                break;
            case R.id.iv_home_lub:
                startActivity(new Intent(getActivity(), ScanLubActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001){
            String qrcode = "E002M05P0001";
            mPresenter.scanCheck(qrcode);
        }else if(requestCode == 1002){
            Intent intent = new Intent(getActivity(), PatralCheckActivity.class);
            intent.putExtra("execid","E002M05P0001");
            startActivity(intent);
        }
    }

    private OnCheckScanClick onCheckScanClick;

    public void setOnCheckScanClick(OnCheckScanClick onCheckScanClick) {
        this.onCheckScanClick = onCheckScanClick;
    }
    public interface OnCheckScanClick{
        public void onClick(int position);
    }
}
