package com.app.spotcheck.moudle.spotcheck;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.CommonAdapter;
import com.app.spotcheck.base.utils.MultiItemCommonAdapter;
import com.app.spotcheck.base.utils.MultiItemTypeSupport;
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
        if(tab == 0){
            mPresenter.getCheckPlanList("","");
        }else if(tab == 1){
            mPresenter.getUnCheckPlanList("","");
        }else if(tab == 2){
            mPresenter.getCheckedPlanList("","");
        }
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
        adapter = new MCheckAdapter(getActivity(),R.layout.item_check,datas);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void showSuccess(SpotCheckAllBean bean) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            initData();
        }
    }

    private class MCheckAdapter extends CommonAdapter<SpotCheckAllBean.SearchListBean>{


        public MCheckAdapter(Context context, int layoutId, List<SpotCheckAllBean.SearchListBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, SpotCheckAllBean.SearchListBean bean) {
            if(tab == 0){
                holder.setText(R.id.tv_title,bean.getMAINNAME());

                holder.getView(R.id.tv_checked_num).setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_checked_num,"已点检项目 "+bean.getCHECKNUM()+" 项");

                holder.getView(R.id.tv_uncheck_num).setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_uncheck_num,bean.getUNCHECKNUM()+"");

                holder.setText(R.id.tv_check_place,bean.getPARTNAME());
                holder.setText(R.id.tv_check_time,bean.getEXECSTARTTIME()+"~"+bean.getEXECENDTIME());
            }else if(tab == 1){
                holder.setText(R.id.tv_title,bean.getMAINNAME());
                holder.getView(R.id.tv_uncheck_num).setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_uncheck_num,bean.getUNCHECKNUM()+"");

                holder.setText(R.id.tv_check_place,bean.getPARTNAME());
                holder.setText(R.id.tv_check_time,bean.getEXECSTARTTIME()+"~"+bean.getEXECENDTIME());
            }else if(tab == 2){
                holder.setText(R.id.tv_title,bean.getMAINNAME());

                holder.getView(R.id.tv_checked_num).setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_checked_num,"已点检项目 "+bean.getCHECKNUM()+" 项");

                holder.setText(R.id.tv_check_place,bean.getPARTNAME());
                holder.setText(R.id.tv_check_time,bean.getEXECSTARTTIME()+"~"+bean.getEXECENDTIME());
            }
        }
    }
}
