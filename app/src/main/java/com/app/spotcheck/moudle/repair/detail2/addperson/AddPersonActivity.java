package com.app.spotcheck.moudle.repair.detail2.addperson;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.AddPersonBean;
import com.app.spotcheck.moudle.bean.DepartmentBean;
import com.app.spotcheck.utils.GlobalKey;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加人员
 */
public class AddPersonActivity extends BaseActivity<AddPersonPresenter> implements AddPersonView {

    @BindView(R.id.personSelLay)
    ConstraintLayout personSelLay;

    @BindView(R.id.mFixedTimeEt)
    EditText mFixedTimeEt;

    @BindView(R.id.mTrendsTimeEt)
    EditText mTrendsTimeEt;

    @BindView(R.id.mSubmitBtn)
    Button mSubmitBtn;

    @BindView(R.id.mPersonSelTv)
    TextView mPersonSelTv;
    private int personPostion = 0;
    private String workNo;
    private String repid;
    @Override
    public void showLoading() {
        showLoding();
    }

    @Override
    public void hideLoading() {
        disLoding();
    }

    @Override
    public void showError(String msg) {
        ToastWrapper.show(msg);
    }

    @Override
    public void showPersonList(AddPersonBean bean) {
        if(bean == null){
            return;
        }
        List<AddPersonBean.SearchListBean> list = bean.getSearchList();
        String[] sList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sList[i] =  list.get(i).getWORKER_NAME();
        }

        new XPopup.Builder(this)
                .asBottomList("人员", sList,
                        null, personPostion,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                personPostion = position;
                                workNo = list.get(position).getWORKER_NO();
                                mPersonSelTv.setText(text);
                            }
                        })
                .show();
    }

    @Override
    public void showFinish() {
        ToastWrapper.show("提交成功");
        finish();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            repid = intent.getStringExtra(GlobalKey.KEY_REPID);
        } else {
            ToastWrapper.show("记录ID不能为空");
            finish();
        }
    }

    @Override
    protected AddPersonPresenter initPresenter() {
        return new AddPersonPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_add_person;
    }

    @Override
    protected void initView() {
        setTopTitle("维修人员");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.personSelLay, R.id.mSubmitBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mSubmitBtn://提交审核
                if(!checkNull()){
                    return;
                }
                String fixd = mFixedTimeEt.getText().toString();
                String trend = mTrendsTimeEt.getText().toString();
                mPresenter.saveRepairMan(repid,workNo,fixd,trend);
                break;
            case R.id.personSelLay:  //选择人员
                mPresenter.getWorkerList();
                break;
        }
    }

    private boolean checkNull() {
        boolean pass = false;
        String fixd = mFixedTimeEt.getText().toString();
        String trend = mTrendsTimeEt.getText().toString();
        if (TextUtils.isEmpty(workNo)){
            ToastWrapper.show("请选择人员");
        }else if (TextUtils.isEmpty(fixd)){
            ToastWrapper.show("请输入定额工时");
        }else if (TextUtils.isEmpty(trend)){
            ToastWrapper.show("请输入实动工时");
        }else{
            pass = true;
        }
        return pass;
    }
}
