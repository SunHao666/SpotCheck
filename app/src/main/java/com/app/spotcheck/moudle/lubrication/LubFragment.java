package com.app.spotcheck.moudle.lubrication;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.moudle.bean.LubBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.spotcheck.MCheckAdapter;
import com.app.spotcheck.moudle.spotcheck.SpotCheckPresenter;
import com.app.spotcheck.moudle.spotcheck.SpotCheckView;

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
    List<LubBean.LubSearchListBean> datas = new ArrayList<>();
    private MLubAdapter adapter;

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
    }


    @Override
    public void showSuccess(LubBean bean) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(mPresenter == null){
                mPresenter = new LubPresenter();
            }
            mPresenter.attachView(this);
            initRequest();
        }
    }

    private void initRequest() {
        if(tab == 0){
            mPresenter.getCheckPlanList("","");
        }else if(tab == 1){
            mPresenter.getUnCheckPlanList("","");
        }else if(tab == 2){
            mPresenter.getCheckedPlanList("","");
        }
    }

}
