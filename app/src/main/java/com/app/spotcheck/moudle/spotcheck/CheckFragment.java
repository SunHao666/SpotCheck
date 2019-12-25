package com.app.spotcheck.moudle.spotcheck;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.app.spotcheck.moudle.scancheck.ScanCheckActivity;
import com.app.spotcheck.moudle.scancheck.checkexception.CheckExceptionActivity;
import com.app.spotcheck.network.Contant;

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
        adapter.setOnCheckItemClickListener(new MCheckAdapter.OnCheckItemClickListener() {
            @Override
            public void onClick(int position, int tab) {
                if(tab == 1){
                    Intent intent = new Intent(getActivity(), ScanCheckActivity.class);
                    intent.putExtra("EXECID",datas.get(position).getEXECID());
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

    @Override
    public void onResume() {
        super.onResume();
        initRequest();
    }

    private void initRequest() {
        if(TextUtils.isEmpty(Contant.CHECKQRCODE) && TextUtils.isEmpty(Contant.CHECKSEARCH)){
            return;
        }
        if(tab == 0){
            mPresenter.getCheckPlanList(Contant.CHECKQRCODE,Contant.CHECKSEARCH);
        }else if(tab == 1){
            mPresenter.getUnCheckPlanList(Contant.CHECKQRCODE,Contant.CHECKSEARCH);
        }else if(tab == 2){
            mPresenter.getCheckedPlanList(Contant.CHECKQRCODE,Contant.CHECKSEARCH);
        }
    }

}
