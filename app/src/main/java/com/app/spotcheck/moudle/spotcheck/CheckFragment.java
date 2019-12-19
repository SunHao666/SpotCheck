package com.app.spotcheck.moudle.spotcheck;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.CommonAdapter;
import com.app.spotcheck.base.utils.MultiItemCommonAdapter;
import com.app.spotcheck.base.utils.MultiItemTypeSupport;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;

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
        recyclerview.setAdapter(new MCheckAdapter(getActivity(),null,multiItemSupport));
    }

    @Override
    public void showSuccess(SpotCheckAllBean bean) {

    }

    @Override
    public void showError(String error) {

    }

    MultiItemTypeSupport  multiItemSupport = new MultiItemTypeSupport<SpotCheckAllBean>() {
        @Override
        public int getLayoutId(int itemType) {
            return 0;
        }

        @Override
        public int getItemViewType(int position, SpotCheckAllBean bean) {
            return 0;
        }
    };
    private class MCheckAdapter extends MultiItemCommonAdapter<SpotCheckAllBean>{

        public MCheckAdapter(Context context, List<SpotCheckAllBean> datas, MultiItemTypeSupport<SpotCheckAllBean> multiItemTypeSupport) {
            super(context, datas, multiItemTypeSupport);
        }

        @Override
        public void convert(ViewHolder holder, SpotCheckAllBean bean) {

        }
    }
}
