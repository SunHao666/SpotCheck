package com.app.spotcheck.moudle.mine;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.MineBean;

import java.util.List;

import butterknife.BindView;

public class MineFragment extends BaseFragment<MinePresenter> implements MineView{
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_login_time)
    TextView tvLoginTime;
    @BindView(R.id.rv_month_check)
    RecyclerView rvMonthCheck;
    @BindView(R.id.rv_month_lub)
    RecyclerView rvMonthLub;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        String loginname = SPUtils.getInstance(getActivity()).getString("Loginname");
        mPresenter.fetch(loginname);
    }

    @Override
    protected MinePresenter initPresenter() {
        return new MinePresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void showSuccess(MineBean bean) {
        String loginname = SPUtils.getInstance(getActivity()).getString("Loginname");
        long logtime = SPUtils.getInstance(getActivity()).getLong("logtime");
        tvName.setText(loginname);
        tvLoginTime.setText(logtime+"");
        initCheckList(bean);
        initLubList(bean);
    }

    private void initLubList(MineBean bean) {
        List<MineBean.LUBLISTBean> lub_list = bean.getLUB_LIST();
        rvMonthLub.setLayoutManager(new LinearLayoutManager(getActivity()));
        MineLubAdapter adapter = new MineLubAdapter(getActivity(),lub_list);
        rvMonthLub.setAdapter(adapter);
    }

    private void initCheckList(MineBean bean) {
        List<MineBean.CHKLISTBean> chk_list = bean.getCHK_LIST();
        rvMonthCheck.setLayoutManager(new LinearLayoutManager(getActivity()));
        MineAdapter adapter = new MineAdapter(getActivity(),chk_list);
        rvMonthCheck.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            String loginname = SPUtils.getInstance(getActivity()).getString("Loginname");
            mPresenter.fetch(loginname);
        }
    }
}
