package com.app.spotcheck.moudle.spotcheck;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.CommonAdapter;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.utils.MultiItemCommonAdapter;
import com.app.spotcheck.base.utils.MultiItemTypeSupport;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName: CheckFragment
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/19 20:52
 */
public class CheckFragment extends BaseFragment<SpotCheckPresenter> implements SpotCheckView {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private int tab = 0;
    List<SpotCheckAllBean.SearchListBean> datas = new ArrayList<>();
    private MCheckAdapter adapter;

    public CheckFragment(int tab) {
        this.tab = tab;
    }

    @Override
    protected void initData() {

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
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MCheckAdapter(getActivity(),datas,tab);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void showSuccess(SpotCheckAllBean bean) {
        datas.clear();
        adapter.notifyDataSetChanged();
        LogUtils.error("bean size = "+bean.getSearchList().size());
        datas.addAll(bean.getSearchList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(mPresenter == null){
                mPresenter = new SpotCheckPresenter();
            }
            mPresenter.attachView(this);
            initRequest();
        }
    }

    private void initRequest() {
        if(tab == 0){
            mPresenter.getCheckPlanList("E002M05P0001","");
        }else if(tab == 1){
            mPresenter.getUnCheckPlanList("","");
        }else if(tab == 2){
            mPresenter.getCheckedPlanList("","");
        }
    }

}
