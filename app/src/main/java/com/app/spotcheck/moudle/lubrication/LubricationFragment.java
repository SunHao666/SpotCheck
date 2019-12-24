package com.app.spotcheck.moudle.lubrication;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.moudle.bean.LubBean;
import com.app.spotcheck.moudle.spotcheck.CheckFragment;
import com.app.spotcheck.moudle.spotcheck.MViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LubricationFragment extends BaseFragment<LubPresenter> implements LubView , TabLayout.OnTabSelectedListener {
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
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    public String[] tabNames = {"全部润滑工作", "待润滑", "已完成"};
    public List<Fragment> fragments = new ArrayList<>();
    private MViewPagerAdapter adapter;

    public static LubricationFragment newInstance() {
        return new LubricationFragment();
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
        return R.layout.fragment_lubrication;
    }

    @Override
    protected void initView() {
        initTablayout();
    }
    private void initTablayout() {
        for (int i = 0; i < tabNames.length; i++) {
            tablayout.addTab(tablayout.newTab());
            fragments.add(new LubFragment(i));
        }
        tablayout.addOnTabSelectedListener(this);
        adapter = new MViewPagerAdapter(getActivity().getSupportFragmentManager(),getActivity(),fragments,tabNames);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }
    @Override
    public void showSuccess(LubBean bean) {

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
}
