package com.app.spotcheck.moudle.lubrication;

import android.content.Intent;
import android.text.TextUtils;

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
    List<LubAllBean.SearchListBean> datas = new ArrayList<>();
    private MLubAdapter adapter;
    boolean isVisibleToUser = false;
    public LubFragment(int tab) {
        this.tab = tab;
    }

    @Override
    protected void initData() {

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
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
        datas.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.error("tab="+tab+",onResume：");
        if(isVisibleToUser)
            initRequest();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if(isVisibleToUser){
            if(mPresenter == null){
                mPresenter = new LubPresenter();
            }
            mPresenter.attachView(this);
            initRequest();
        }
    }

    private void initRequest() {
        LogUtils.error("tab="+tab+",LUBQRCODE="+Contant.LUBQRCODE);
        if(TextUtils.isEmpty(Contant.LUBQRCODE) && TextUtils.isEmpty(Contant.LUBSEARCH)){
            return;
        }
        if(tab == 0){
            mPresenter.getLubPlanList(Contant.LUBQRCODE,Contant.LUBSEARCH,"");
        }else if(tab == 1){
            mPresenter.getLubPlanList(Contant.LUBQRCODE,Contant.LUBSEARCH,"0");
        }else if(tab == 2){
            mPresenter.getLubPlanList(Contant.LUBQRCODE,Contant.LUBSEARCH,"1");
        }
    }

}
