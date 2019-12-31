package com.app.spotcheck.moudle.lubrication;

import android.content.Intent;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.LubAllBean;
import com.app.spotcheck.moudle.bean.LubBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.scanlub.ScanLubActivity;
import com.app.spotcheck.moudle.spotcheck.MCheckAdapter;
import com.app.spotcheck.moudle.spotcheck.SpotCheckPresenter;
import com.app.spotcheck.moudle.spotcheck.SpotCheckView;
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
public class LubFragment extends BaseFragment<LubPresenter> implements LubView {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private int tab = 0;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    List<LubAllBean.SearchListBean> datas = new ArrayList<>();
    private MLubAdapter adapter;
    boolean isVisibleToUser = false;
    public LubFragment(int tab) {
        this.tab = tab;
    }

    @Override
    protected void initData() {
        LogUtils.error("lub tab="+tab+",initData");
    }

    @Override
    protected LubPresenter initPresenter() {
        return new LubPresenter();
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
        adapter = new MLubAdapter(getActivity(),datas,tab);
        recyclerview.setAdapter(adapter);

        adapter.setOnLubClickListener(new MLubAdapter.OnLubClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ScanLubActivity.class);
                intent.putExtra("lrecno",datas.get(position).getLRECNO());
                startActivity(intent);
            }
        });
    }


    @Override
    public void showSuccess(LubAllBean bean) {
        datas.clear();
        adapter.notifyDataSetChanged();
        LogUtils.error("lub bean size = "+bean.getSearchList().size());
        datas.addAll(bean.getSearchList());
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
        datas.clear();
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Contant.TAB_SELECT ==2 && isVisibleToUser) {
            LogUtils.error("lub tab="+tab+",onResume");
            initRequest();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.error("lub tab="+tab+",onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.error("lub tab="+tab+",onStop");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = getUserVisibleHint();
        if(Contant.TAB_SELECT ==2 && isVisibleToUser){
            LogUtils.error("lub tab="+tab+",setUserVisibleHint");
            if(mPresenter == null){
                mPresenter = new LubPresenter();
            }
            mPresenter.attachView(this);
            initRequest();
        }
    }

    private void initRequest() {
        LogUtils.error("tab="+tab+",LUBQRCODE="+Contant.LUBQRCODE);
//        if(TextUtils.isEmpty(Contant.LUBQRCODE) && TextUtils.isEmpty(Contant.LUBSEARCH)){
//            return;
//        }
        if(tab == 0){
            mPresenter.getLubPlanList(Contant.LUBQRCODE,Contant.LUBSEARCH,"");
        }else if(tab == 1){
            mPresenter.getLubPlanList(Contant.LUBQRCODE,Contant.LUBSEARCH,"0");
        }else if(tab == 2){
            mPresenter.getLubPlanList(Contant.LUBQRCODE,Contant.LUBSEARCH,"1");
        }
    }

}
