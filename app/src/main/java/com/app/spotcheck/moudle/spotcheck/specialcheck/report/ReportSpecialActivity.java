package com.app.spotcheck.moudle.spotcheck.specialcheck.report;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.app.spotcheck.moudle.bean.SpecialItemBean;
import com.app.spotcheck.moudle.bean.SpecialSearchBean;
import com.app.spotcheck.utils.GlobalKey;
import com.app.spotcheck.widgets.BottomSearchView;
import com.app.spotcheck.widgets.SpecialSearchView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

/**
 * 报修申请
 */
public class ReportSpecialActivity extends BaseActivity<ReportSpecialPresenter> implements ReportSpecialView{

    @BindView(R.id.mSearchIv)
    ImageView mSearchIv;
    @BindView(R.id.mDeviceNameTv)
    TextView mDeviceNameTv;
    @BindView(R.id.mCheckItemET)
    EditText mCheckItemET;
    @BindView(R.id.mCheckContentEt)
    EditText mCheckContentEt;
    @BindView(R.id.mResultEt)
    TextView mResultEt;
    @BindView(R.id.mSaveBtn)
    Button mSaveBtn;
    private String applyman;
    private int proKindPostion = 0;
    private String mainID;
    private String deviceCode;
    SpecialSearchView bottomFullDialog;
    private String[] exceptions = {"异常","无异常"};
    @Override
    protected void initData() {

    }

    @Override
    protected ReportSpecialPresenter initPresenter() {
        return new ReportSpecialPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_special_repair;
    }

    @Override
    protected void initView() {
        setTopTitle("专检记录");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        applyman = SPUtils.getInstance(this).getString(GlobalKey.KEY_UUADMINUSER);
    }


    private boolean checkNull() {
        boolean pass = false;
        if(TextUtils.isEmpty(mDeviceNameTv.getText().toString())){
            ToastWrapper.show("请选择设备");
        }else{
            pass = true;
        }
        return pass;
    }

    @OnClick({R.id.mSaveBtn,R.id.mSearchIv,R.id.mResultEt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mSaveBtn://提交
                if(!checkNull()){
                    return;
                }
                submit();
                break;
            case R.id.mResultEt:
                showException();
                break;
            case R.id.mSearchIv://搜索
                showDevPart();
                break;

        }
    }

    public void showException(){
        new XPopup.Builder(this)
                .asBottomList("结论", exceptions,
                        null, 0,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                mResultEt.setText(text);
                            }
                        })
                .show();
    }
    /**
     * 显示搜索
     */
    private void showDevPart() {
        SpecialSearchView bottomTextView = new SpecialSearchView(this, 1);
        if(bottomFullDialog == null){
             bottomFullDialog = (SpecialSearchView) new XPopup.Builder(this)
                    .asCustom(bottomTextView);
        }
        if(!bottomFullDialog.isShow()){
            bottomFullDialog.show();
        }
        bottomFullDialog.findViewById(R.id.closeIV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomFullDialog.dismiss();
            }
        });
        bottomFullDialog.setDeviceOnClickListener(new SpecialSearchView.DeviceOnClickListener() {
            @Override
            public void onClick(String mainName,  String mainId) {
                setDeviceWithPart(mainName,mainId);
                bottomFullDialog.dismiss();
            }
        });
        EditText serarchEt = bottomFullDialog.findViewById(R.id.serarchEt);
        bottomFullDialog.findViewById(R.id.searchTv).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mPresenter.search(serarchEt.getText().toString());
            }
        });
    }


    /*提交申请*/
    private void submit() {

        Map<String, String> map = new HashMap<>();
        map.put("mainid", mainID);
        map.put("checkman", applyman);
        map.put("checkresult", mResultEt.getText().toString());
        map.put("checkitem", mCheckItemET.getText().toString());
        map.put("checkrecords", mCheckContentEt.getText().toString());


        mPresenter.saveSpecialApply(map);

    }


    @Override
    public void showError(int code, String msg) {
        ToastWrapper.show(msg);
    }

    @Override
    public void showSubmitSuccess(String result_message) {
        ToastWrapper.show(result_message);
        finish();
    }

    @Override
    public void showLoad() {
        showLoding();
    }

    @Override
    public void hideLoad() {
        disLoding();
    }

    @Override
    public void showSearchList(SpecialSearchBean bean) {
        bottomFullDialog.setData(bean.getSearchList());
    }

    public void setDeviceWithPart(String mainName, String mainId){
        mDeviceNameTv.setText(mainName);
        this.mainID = mainId;
    }


}
