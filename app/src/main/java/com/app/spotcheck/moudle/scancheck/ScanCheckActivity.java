package com.app.spotcheck.moudle.scancheck;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.IntentKeys;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.ScanCheckBean;
import com.app.spotcheck.moudle.event.HomeEvent;
import com.app.spotcheck.moudle.scancheck.checkexception.CheckExceptionActivity;
import com.app.spotcheck.moudle.scancheck.scanresult.ScanCheckResultActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
//    @BindView(R.id.tv_electric)
//    TextView tvElectric;
    @BindView(R.id.btn_check_ok)
    Button btnCheckOk;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<ScanCheckBean.SearchListBean> beans = new ArrayList<>();
    private ScanCheckAdapter adapter;
    private String taskId;
    private List<Integer> execids = new ArrayList<>();
    ScanCheckBean bean;
    @Override
    protected void initData() {
        taskId = getIntent().getStringExtra("TaskId");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.fetch(taskId);
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
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter = new ScanCheckAdapter(this,beans);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new ScanCheckAdapter.CheckItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(ScanCheckActivity.this, CheckExceptionActivity.class);
                intent.putExtra(IntentKeys.CK_ID,beans.get(position).getCKID());
                intent.putExtra(IntentKeys.TASK_ID,taskId);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.btn_check_ok)
    public void onViewClicked() {
        String loginname = SPUtils.getInstance(this).getString("Loginname");
        mPresenter.saveCheckReult(taskId,loginname,execids);
    }


    @Override
    public void showSuccess(ScanCheckBean bean) {
        this.bean = bean;
        beans.clear();
        adapter.notifyDataSetChanged();
        setTopTitle("["+bean.getMAINNAME()+"]["+bean.getPARTNAME()+"]");
//        tvSetName.setText(bean.getMAINNAME());
//        tvSetPlace.setText(bean.getPARTNAME());
//        tvElectric.setText(bean.getITEMKIND());
        List<ScanCheckBean.SearchListBean> searchList = bean.getSearchList();
        beans.addAll(searchList);
        adapter.notifyDataSetChanged();
        btnCheckOk.setEnabled(true);
        for (int i = 0; i < beans.size(); i++) {
            execids.add(beans.get(i).getCKID());
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
