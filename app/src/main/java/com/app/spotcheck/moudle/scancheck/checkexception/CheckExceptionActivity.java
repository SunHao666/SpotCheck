package com.app.spotcheck.moudle.scancheck.checkexception;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.wrapper.PopuwindowListView;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.CheckExceptionBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CheckExceptionActivity extends BaseActivity<CheckExceptionPresenter> implements CheckExceptionView {
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.tv_set_place)
    TextView tvSetPlace;
    @BindView(R.id.tv_check_project)
    TextView tvCheckProject;
    @BindView(R.id.tv_check_content)
    TextView tvCheckContent;
    @BindView(R.id.tv_pro_type)
    TextView tvProType;
    @BindView(R.id.et_info)
    EditText etInfo;
    @BindView(R.id.btn_ex_save)
    Button btnExSave;
    @BindView(R.id.lay_pro_type)
    LinearLayout layProType;
    CheckExceptionBean bean;
    public List<CheckExceptionBean.PROBLEMKINDBean> data = new ArrayList<>();
    private int id;

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        mPresenter.fetch(id);
    }

    @Override
    protected CheckExceptionPresenter initPresenter() {
        return new CheckExceptionPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_check_exception;
    }

    @Override
    protected void initView() {
        setTopTitle("点检异常问题登记");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showSuccess(CheckExceptionBean bean) {
        this.bean = bean;
        tvSetName.setText(bean.getMAINNAME());
        tvSetPlace.setText(bean.getPARTNAME());
        tvCheckProject.setText(bean.getITEMNAME());
        tvCheckContent.setText(bean.getCHECKCONTEXT());
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
    }

    @Override
    public void save(String msg) {
        ToastWrapper.show(msg);
        finish();
    }



    @OnClick({R.id.lay_pro_type, R.id.btn_ex_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_pro_type:
                showPopu();
                break;
            case R.id.btn_ex_save:
                save();
                break;
        }
    }

    private void save() {
        if(bean == null){
            ToastWrapper.show("获取设备信息失败，请返回重新扫描");
            return;
        }
//        itemname	点检的项目
//        execman	点检人
//        problem	点检出的问题描述
//        problemkind	问题的类型

        Map<String, RequestBody> map = new HashMap<>();
        map.put("id",toRequestBody(bean.getID()+""));
        map.put("mainid",toRequestBody(""));
        map.put("partid",toRequestBody(""));
        map.put("itemname",toRequestBody(""));
        map.put("execman",toRequestBody(""));
        map.put("problem",toRequestBody(etInfo.getText().toString()));
        map.put("problemkind",toRequestBody(""));

        List<MultipartBody.Part> parts = new ArrayList<>();
        mPresenter.save(map,parts);
    }

    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }
    private void showPopu() {
        PopuwindowListView popu = new PopuwindowListView(this,data);
        popu.setonPOPClickListener(new PopuwindowListView.onPOPClickListener() {
            @Override
            public void onClick(int position) {
                tvProType.setText(data.get(position).getName());
            }
        });
        popu.showAsDropDown(layProType);
    }
}
