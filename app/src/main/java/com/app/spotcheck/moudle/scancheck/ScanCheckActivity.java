package com.app.spotcheck.moudle.scancheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.CommonAdapter;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.HomeScanBean;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.moudle.scancheck.checkexception.CheckExceptionActivity;
import com.app.spotcheck.moudle.scancheck.scanresult.ScanCheckResultActivity;
import com.app.spotcheck.network.Contant;

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
    private String execid;
    private List<Integer> execids = new ArrayList<>();
    ScanCheckBean bean;
    @Override
    protected void initData() {
        execid = getIntent().getStringExtra("EXECID");
//        String execid ="cb611e9d3fa64ca197d0b76ee78bb6f1";
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        String loginname = SPUtils.getInstance(this).getString("Loginname");
        mPresenter.saveCheckReult(execid,loginname,execids);
    }


    @Override
    public void showSuccess(ScanCheckBean bean) {
        this.bean = bean;
        beans.clear();
        adapter.notifyDataSetChanged();
        tvSetName.setText(bean.getMAINNAME());
        tvSetPlace.setText(bean.getPARTNAME());
        List<ScanCheckBean.SearchListBean> searchList = bean.getSearchList();
        beans.addAll(searchList);
        adapter.notifyDataSetChanged();
        btnCheckOk.setEnabled(true);
        for (int i = 0; i < beans.size(); i++) {
            execids.add(beans.get(i).getID());
        }
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
        btnCheckOk.setEnabled(false);
    }

    @Override
    public void save(String msg) {
        Intent intent = new Intent(this, ScanCheckResultActivity.class);
        intent.putExtra("from","00");
        intent.putExtra("setName",bean.getMAINNAME());
        intent.putExtra("setPlace",bean.getPARTNAME());
        startActivity(intent);
        finish();
    }
}
