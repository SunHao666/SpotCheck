package com.app.spotcheck.moudle.repair;

import androidx.fragment.app.Fragment;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.view.ScrollableViewPager;
import com.app.spotcheck.moudle.repair.repairitem.RepairItemFragment;
import com.app.spotcheck.moudle.spotcheck.CheckFragment;
import com.app.spotcheck.moudle.spotcheck.MViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 维修tab
 */
public class RepairFragment extends BaseFragment<RepairPresenter> implements RepairView,TabLayout.OnTabSelectedListener  {
    private static RepairFragment instance;
    public String[] tabNames = {"待审核申请", "待派工", "维修中","待完工审核","已完工"};
    public List<Fragment> fragments = new ArrayList<>();
    private int currentTab = 0;
    private MViewPagerAdapter adapter;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ScrollableViewPager viewPager;


    public static RepairFragment getInstance(){
        if(instance == null){
            instance = new RepairFragment();
        }
        return instance;
    }
    @Override
    protected void initData() {

    }

    @Override
    protected RepairPresenter initPresenter() {
        return new RepairPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_repair;
    }

    @Override
    protected void initView() {
        initTablayout();
    }


    private void initTablayout() {
        for (int i = 0; i < tabNames.length; i++) {
            tablayout.addTab(tablayout.newTab());
            fragments.add(RepairItemFragment.getInstance(i+1));
        }
        tablayout.addOnTabSelectedListener(this);
        viewPager.setScanScroll(false);
        adapter = new MViewPagerAdapter(getChildFragmentManager(), getActivity(), fragments, tabNames);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        currentTab = tab.getPosition();

        LogUtils.error("currentTab="+currentTab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
