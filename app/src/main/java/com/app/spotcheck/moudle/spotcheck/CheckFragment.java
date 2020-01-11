package com.app.spotcheck.moudle.spotcheck;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.DialogUtils;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.KeyWordsBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.scancheck.ScanCheckActivity;
import com.app.spotcheck.network.Contant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: CheckFragment
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/19 20:52
 */
public class CheckFragment extends BaseFragment<SpotCheckPresenter> implements SpotCheckView, SpotCheckFragment.CheckClearListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int tab = 0;
    List<SpotCheckAllBean.SearchListBean> datas = new ArrayList<>();
    boolean isVisibleToUser = false;
    private MCheckAdapter adapter;
    private DialogUtils loading;

    public CheckFragment(int tab) {
        this.tab = tab;
    }


    @Override
    protected void initData() {
        LogUtils.error("check tab=" + tab + ",initData");
//        initRequest();
        SpotCheckFragment fragment = (SpotCheckFragment) getParentFragment();
        fragment.setCheckClearListener(this);
    }

    @Override
    protected SpotCheckPresenter initPresenter() {
        return new SpotCheckPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_check;
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
        adapter = new MCheckAdapter(getActivity(), datas, tab);
        adapter.setOnCheckItemClickListener(new MCheckAdapter.OnCheckItemClickListener() {
            @Override
            public void onClick(int position, int tab) {
                if (tab == 1) {
                    Intent intent = new Intent(getActivity(), ScanCheckActivity.class);
                    intent.putExtra("EXECID", datas.get(position).getEXECID());
                    startActivity(intent);
                }
            }
        });
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void showSuccess(SpotCheckAllBean bean) {
        datas.clear();
        adapter.notifyDataSetChanged();
        LogUtils.error("bean size = " + bean.getSearchList().size());
        datas.addAll(bean.getSearchList());
        adapter.notifyDataSetChanged();
        if(refreshLayout != null){
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
        datas.clear();
        adapter.notifyDataSetChanged();
        if(refreshLayout != null){
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void showSearchSuccess(KeyWordsBean bean) {

    }

    @Override
    public void showFinish() {
        datas.clear();
        adapter.notifyDataSetChanged();
        if(refreshLayout!= null){
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.error("check  tab=" + tab + ",setUserVisibleHint：" + isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (Contant.TAB_SELECT == 1 && isVisibleToUser) {
            LogUtils.error("check  tab=" + tab + ",setUserVisibleHint：" + isVisibleToUser);
            if (mPresenter == null) {
                mPresenter = new SpotCheckPresenter();
            }
            mPresenter.attachView(this);
            initRequest();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Contant.TAB_SELECT == 1 && isVisibleToUser) {
            LogUtils.error("check  tab=" + tab + ",onResume：");
            initRequest();
        }
    }

    private void initRequest() {
        LogUtils.error("tab=" + tab + ",CHECKQRCODE=" + Contant.CHECKQRCODE);
//        if(TextUtils.isEmpty(Contant.CHECKQRCODE) && TextUtils.isEmpty(Contant.CHECKSEARCH)){
//            return;
//        }
        if (tab == 0) {
            mPresenter.getCheckPlanList(Contant.CHECKQRCODE, Contant.CHECKSEARCH);
        } else if (tab == 1) {
            mPresenter.getUnCheckPlanList(Contant.CHECKQRCODE, Contant.CHECKSEARCH);
        } else if (tab == 2) {
            mPresenter.getCheckedPlanList(Contant.CHECKQRCODE, Contant.CHECKSEARCH);
        }
    }

    @Override
    public void clear(int ptab) {
        Contant.CHECKQRCODE = "";
        Contant.CHECKSEARCH = "";
        LogUtils.error("ptab"+ptab);
        if(isVisibleToUser)
            initRequest();
    }
}
