package com.app.spotcheck.moudle.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.patralcheck.PatralCheckActivity;
import com.app.spotcheck.moudle.scancheck.ScanCheckActivity;
import com.app.spotcheck.moudle.scanlub.ScanLubActivity;

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
        homeCheckNoNum.setText(chkinfo.getCHK_UNNUM());
        homePlanTime.setText(chkinfo.getCHK_EXECSTARTTIME() + "~" + chkinfo.getCHK_EXECENDTIME());
        tvDaji.setText(chkinfo.getCHK_MAINNAME());
        tvChecnkPlace.setText(chkinfo.getCHK_PARTNAME());

        homeLubNoNum.setText(lubinfo.getLUB_UNNUM());
        homePlanLubTime.setText(lubinfo.getLUB_EXECSTARTTIME() + "~" + lubinfo.getLUB_EXECENDTIME());
        tvLubDaji.setText(lubinfo.getLUB_MAINNAME());
        tvLubPlace.setText(lubinfo.getLUB_PARTNAME());
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
    }

    @OnClick({R.id.iv_home_check, R.id.iv_home_setpro, R.id.iv_home_lub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_home_check:
                startActivity(new Intent(getActivity(), ScanCheckActivity.class));
                break;
            case R.id.iv_home_setpro:
                startActivity(new Intent(getActivity(), PatralCheckActivity.class));
                break;
            case R.id.iv_home_lub:
                startActivity(new Intent(getActivity(), ScanLubActivity.class));
                break;
        }
    }
}
