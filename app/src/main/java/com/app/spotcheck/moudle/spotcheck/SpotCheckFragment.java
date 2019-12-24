package com.app.spotcheck.moudle.spotcheck;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.event.SpotCheckEvent;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SpotCheckFragment extends BaseFragment<SpotCheckPresenter> implements SpotCheckView, TabLayout.OnTabSelectedListener {
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.scan)
    ImageView scan;
    @BindView(R.id.lay_search)
    LinearLayout laySearch;
    public String[] tabNames = {"全部点检部位", "待检部位", "已检部位"};
    public List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private MViewPagerAdapter adapter;
    private int tab = 0;
    private boolean isParer = false;
    public static SpotCheckFragment newInstance() {
        return new SpotCheckFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
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

        return R.layout.fragment_spotcheck;
    }

    @Override
    protected void initView() {

        initTablayout();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void skiptab(SpotCheckEvent event) {
        tab = event.tab;
        LogUtils.error("tab==" + event.tab);
        viewPager.setCurrentItem(1);
    }

    private void initTablayout() {
        for (int i = 0; i < tabNames.length; i++) {
            tablayout.addTab(tablayout.newTab());
            fragments.add(new CheckFragment(i));
        }
        tablayout.addOnTabSelectedListener(this);

        adapter = new MViewPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), fragments, tabNames);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(tab);
        isParer = true;
    }

    @Override
    public void showSuccess(SpotCheckAllBean bean) {

    }

    @Override
    public void showError(String error) {

    }

    @OnClick({R.id.scan, R.id.lay_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                break;
            case R.id.lay_search:
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void setTab(int tab) {
        this.tab = tab;
        if(isParer){
            viewPager.setCurrentItem(tab);
        }
    }
}
