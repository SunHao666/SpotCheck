package com.app.spotcheck.moudle.scancheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.CommonAdapter;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.moudle.scancheck.checkexception.CheckExceptionActivity;
import com.app.spotcheck.moudle.scancheck.scanresult.ScanCheckResultActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: ScanCheckActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/20 18:57
 */
public class ScanCheckActivity extends BaseActivity<ScanCheckPresenter> implements ScanCheckView {
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.tv_set_place)
    TextView tvSetPlace;
    @BindView(R.id.btn_check_ok)
    Button btnCheckOk;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<ScanCheckBean.SearchListBean> beans = new ArrayList<>();
    private ScanCheckAdapter adapter;

    @Override
    protected void initData() {
        String execid = getIntent().getStringExtra("execid");
        mPresenter.fetch(execid);
    }

    @Override
    protected ScanCheckPresenter initPresenter() {
        return new ScanCheckPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_scan_check;
    }

    @Override
    protected void initView() {
        setTopTitle("设备点检项目");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ScanCheckAdapter(this,beans);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new ScanCheckAdapter.CheckItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(ScanCheckActivity.this, CheckExceptionActivity.class);
                intent.putExtra("id",beans.get(position).getID());
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.btn_check_ok)
    public void onViewClicked() {
        mPresenter.saveCheckReult("execid","",new ArrayList<>());
    }


    @Override
    public void showSuccess(ScanCheckBean bean) {
        tvSetName.setText(bean.getMAINNAME());
        tvSetPlace.setText(bean.getPARTNAME());
        List<ScanCheckBean.SearchListBean> searchList = bean.getSearchList();
        beans.addAll(searchList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
    }

    @Override
    public void save(String msg) {
        startActivity(new Intent(this, ScanCheckResultActivity.class));
        finish();
    }
}
