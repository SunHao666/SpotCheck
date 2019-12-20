package com.app.spotcheck.moudle.mine;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.MineBean;

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
        mPresenter.fetch("");
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
        tvName.setText("");
        tvLoginTime.setText("");
        initCheckList(bean);
        initLubList(bean);
    }

    private void initLubList(MineBean bean) {

    }

    private void initCheckList(MineBean bean) {
//        MineBean.CHKLISTBean chk_list = bean.getCHK_LIST();

    }

    @Override
    public void showError(String error) {
        ToastWrapper.show(error);
    }
}
