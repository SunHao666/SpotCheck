package com.app.spotcheck.moudle.repair.detail2.adddevice;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.AddDeviceBean;
import com.app.spotcheck.moudle.event.EventFinsh;
import com.app.spotcheck.moudle.repair.detail2.adddevice.time.AddDeviceTimeActivity;
import com.app.spotcheck.utils.GlobalKey;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *维修记录-添加设备和人员
 */
public class AddDeviceActivity extends BaseActivity<AddDevicePresenter> implements AddDeviceView {
    @BindView(R.id.serarchEt)
    EditText serarchEt;
    @BindView(R.id.ivSearch)
    ImageView ivSearch;
    @BindView(R.id.mAddDeviceRv)
    RecyclerView mAddDeviceRv;

    private List<AddDeviceBean.SearchListBean> searchList;
    private AddDeviceAdapter mAdapter;
    private String repid;
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
    protected AddDevicePresenter initPresenter() {
        return new AddDevicePresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_adddevice;
    }

    @Override
    protected void initView() {
        setTopTitle("备件信息");
        setTopLeftButton(R.drawable.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAdapter = new AddDeviceAdapter();
        mAddDeviceRv.setLayoutManager(new LinearLayoutManager(this));
        mAddDeviceRv.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new AddDeviceAdapter.OnDeviceCLickListener() {
            @Override
            public void onClick(int position) {
                if(searchList == null)
                    return;
                AddDeviceBean.SearchListBean bean = searchList.get(position);
                Intent intent = new Intent(AddDeviceActivity.this, AddDeviceTimeActivity.class);
                intent.putExtra(GlobalKey.KEY_ADDDEVICE,bean);
                intent.putExtra(GlobalKey.KEY_REPID,repid);
                startActivity(intent);
            }
        });
    }

    @OnClick({ R.id.searchTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchTv://搜索
                String sparename = serarchEt.getText().toString();
                mPresenter.getSpareStoreList(sparename);
                break;
        }
    }
    @Override
    public void showLoading() {
        showLoding();
    }

    @Override
    public void hideLoading() {
        hideLoading();
    }

    @Override
    public void showError(String msg) {
        ToastWrapper.show(msg);
    }

    @Override
    public void showSuccess(List<AddDeviceBean.SearchListBean> searchList) {
        this.searchList = searchList;
        mAdapter.setData(searchList);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishPage(EventFinsh eventFinsh){
        Log.e("finishPage","finishPage----->");
        finish();
    }
}
