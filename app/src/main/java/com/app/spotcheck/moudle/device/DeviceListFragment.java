package com.app.spotcheck.moudle.device;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.DialogUtils;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.DeviceListBean;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.device.deviceinfo.DeviceInfoActivity;
import com.app.spotcheck.moudle.event.BaseEvent;
import com.app.spotcheck.moudle.event.DeviceEvent;
import com.app.spotcheck.moudle.login.LoginActivity;
import com.app.spotcheck.moudle.scancheck.ScanCheckActivity;
import com.app.spotcheck.moudle.spotcheck.MCheckAdapter;
import com.app.spotcheck.network.Contant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DeviceListFragment extends BaseFragment<DevicePresenter> implements DeviceView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_device_list)
    RecyclerView rvDeviceList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private boolean isVisible = true;
    List<DeviceListBean.DataBean> data = new ArrayList<>();
    private DeveiceListAdapter adapter;

    private DialogUtils loading;

    public static DeviceListFragment newInstance() {
        return new DeviceListFragment();
    }

    @Override
    protected void initData() {
        mPresenter.fetch();
    }

    @Override
    protected DevicePresenter initPresenter() {
        return new DevicePresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_device;
    }

    @Override
    protected void initView() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initRequest();
            }
        });

        rvDeviceList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DeveiceListAdapter(getActivity(),data);
        rvDeviceList.setAdapter(adapter);

        adapter.setOnCheckItemClickListener(new DeveiceListAdapter.OnCheckItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), DeviceInfoActivity.class);
                intent.putExtra("mainid",data.get(position).getMAINID());
                startActivity(intent);
            }
        });
    }

    private void initRequest() {
        mPresenter.fetch();
    }

    @Override
    public void showSuccess(DeviceListBean bean) {
        data.clear();
        adapter.notifyDataSetChanged();
        LogUtils.error("bean size = " + bean.getSearchList().size());
        data.addAll(bean.getSearchList());
        adapter.notifyDataSetChanged();
        if(refreshLayout != null){
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void showError(int code, String error) {
        ToastWrapper.show(error);
        data.clear();
        adapter.notifyDataSetChanged();
        if(refreshLayout != null){
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void showLoading() {
        if(loading == null){
            loading = new DialogUtils(getActivity(),R.style.CustomDialog);
        }
        loading.show();
    }

    @Override
    public void dissLoading() {
        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshView(DeviceEvent event){
        initRequest();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Contant.TAB_SELECT = 0;
        isVisible = hidden;
        if (!hidden) {
            LogUtils.error("HomeFragment onHiddenChanged");
            mPresenter.fetch();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.fetch();
        if(!isVisible){
            Contant.TAB_SELECT = 0;
            LogUtils.error("HomeFragment onResume");
            mPresenter.fetch();
        }
    }
}
