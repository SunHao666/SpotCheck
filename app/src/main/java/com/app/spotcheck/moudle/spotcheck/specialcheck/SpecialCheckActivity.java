package com.app.spotcheck.moudle.spotcheck.specialcheck;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.RepairItemBean;
import com.app.spotcheck.moudle.bean.SpecialItemBean;
import com.app.spotcheck.moudle.patralcheck.PatralCheckActivity;
import com.app.spotcheck.moudle.patralcheck.PatralCheckPresenter;
import com.app.spotcheck.moudle.patralcheck.PatralCheckView;
import com.app.spotcheck.moudle.repair.repairitem.adapter.RepairItemAdapter;
import com.app.spotcheck.moudle.spotcheck.specialcheck.adapter.SpecialAdapter;
import com.app.spotcheck.moudle.spotcheck.specialcheck.report.ReportSpecialActivity;
import com.app.spotcheck.moudle.voice.VoiceActivity;
import com.luck.picture.lib.tools.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 专检
 */
public class SpecialCheckActivity  extends BaseActivity<SpecialCheckPresenter> implements SpecialCheckView {
    @BindView(R.id.addIV)
    ImageView addIV;
    @BindView(R.id.backIV)
    ImageView backIV;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.specialRv)
    RecyclerView specialRv;

    private SpecialAdapter adapter;
    private List<SpecialItemBean.SearchListBean> data = new ArrayList<SpecialItemBean.SearchListBean>();
    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getSpecialList();
    }

    @Override
    protected SpecialCheckPresenter initPresenter() {
        return new SpecialCheckPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_special_check;
    }

    @Override
    protected void initView() {
        toolbar.setVisibility(View.GONE);
        specialRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SpecialAdapter(this,data);
        specialRv.setAdapter(adapter);
    }

    @OnClick({R.id.backIV, R.id.addIV})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backIV:
                finish();
                break;
            case R.id.addIV:
                startActivity(new Intent(this, ReportSpecialActivity.class));
                break;
        }
    }

    @Override
    public void showSuccess(SpecialItemBean repairItemBean) {
        data.clear();
        data.addAll(repairItemBean.getSearchList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        ToastWrapper.show(msg);
    }
}
