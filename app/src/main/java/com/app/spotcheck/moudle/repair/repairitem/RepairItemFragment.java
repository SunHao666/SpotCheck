package com.app.spotcheck.moudle.repair.repairitem;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.RepairItemBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.event.RepairList1Event;
import com.app.spotcheck.moudle.repair.detail1.RepairDetail1Activity;
import com.app.spotcheck.moudle.repair.detail2.RepairDetail2Activity;
import com.app.spotcheck.moudle.repair.repairitem.adapter.RepairItemAdapter;
import com.app.spotcheck.moudle.scancheck.ScanCheckActivity;
import com.app.spotcheck.moudle.spotcheck.MCheckAdapter;
import com.app.spotcheck.network.Contant;
import com.app.spotcheck.utils.GlobalKey;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RepairItemFragment extends BaseFragment<RepairItemPresenter> implements RepairItemView {
    private int tab = 0;
    private static RepairItemFragment instance;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private RepairItemAdapter adapter;

    private String mainid;
    private String mainname;
    private String state;

    List<RepairItemBean.SearchListBean> datas = new ArrayList<>();

    public static RepairItemFragment getInstance(int tab) {
        instance = new RepairItemFragment(tab);
        return instance;
    }

    private RepairItemFragment(int tab) {
        this.tab = tab;
    }

    @Override
    protected void initData() {
        initRequest();
    }

    private void initRequest() {
        state = String.valueOf(tab);
        mPresenter.getRepairItemList(mainid, mainname, state);
    }

    public void setTab(int tab){
        this.tab = tab;
    }

    @Override
    protected RepairItemPresenter initPresenter() {
        return new RepairItemPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_repair_item;
    }

    @Override
    protected void initView() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initRequest();
            }
        });

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RepairItemAdapter(getActivity(), datas, tab);
        adapter.setOnCheckItemClickListener(new RepairItemAdapter.OnCheckItemClickListener() {
            @Override
            public void onClick(int position, int tab) {
                if (tab == 1) {
                    Intent intent = new Intent(getActivity(), RepairDetail1Activity.class);
                    intent.putExtra(GlobalKey.KEY_REPID,datas.get(position).getREPID());
                    startActivity(intent);
                }else if (tab == 2) {
                    Intent intent = new Intent(getActivity(), RepairDetail2Activity.class);
                    intent.putExtra(GlobalKey.KEY_REPID,datas.get(position).getREPID());
                    startActivity(intent);
                }


            }
        });
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void showSuccess(RepairItemBean bean) {
        datas.clear();
        adapter.notifyDataSetChanged();
        datas.addAll(bean.getSearchList());
        adapter.notifyDataSetChanged();
        if(refreshLayout != null){
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void showError(String msg) {
        ToastWrapper.show(msg);
        datas.clear();
        adapter.notifyDataSetChanged();
        if(refreshLayout != null){
            refreshLayout.finishRefresh();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshList(RepairList1Event event){
        tab = event.tab;
        initRequest();
    }

    @Override
    protected void initEventBus() {
        super.initEventBus();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void destoryEventBus() {
        super.destoryEventBus();
        EventBus.getDefault().unregister(this);
    }
}
