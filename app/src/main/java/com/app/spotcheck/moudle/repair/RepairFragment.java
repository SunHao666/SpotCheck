package com.app.spotcheck.moudle.repair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.view.ScrollableViewPager;
import com.app.spotcheck.moudle.bean.EventRepairRefresh;
import com.app.spotcheck.moudle.repair.repairitem.RepairItemFragment;
import com.app.spotcheck.moudle.report.ReportRepairActivity;
import com.app.spotcheck.moudle.spotcheck.CheckFragment;
import com.app.spotcheck.moudle.spotcheck.MViewPagerAdapter;
import com.app.spotcheck.network.Contant;
import com.baidu.speech.utils.LogUtil;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 维修tab
 */
public class RepairFragment extends BaseFragment<RepairPresenter> implements RepairView, TabLayout.OnTabSelectedListener {
    private static RepairFragment instance;
    public String[] tabNames = {"待审核", "待派工", "维修中", "待完工", "已完工", "全部"};
    public List<Fragment> fragments = new ArrayList<>();
    private int currentTab = 0;
    private MViewPagerAdapter adapter;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.mRepairAdd)
    ImageView mRepairAdd;
    @BindView(R.id.viewPager)
    ScrollableViewPager viewPager;
    @BindView(R.id.mRepairTitleTv)
    TextView mRepairTitleTv;


    public static RepairFragment getInstance() {
        if (instance == null) {
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
        updateTitle();
        initTablayout();
        mRepairAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReportRepairActivity.class));
            }
        });
    }


    private void initTablayout() {
        for (int i = 0; i < tabNames.length; i++) {
            tablayout.addTab(tablayout.newTab());
            fragments.add(RepairItemFragment.getInstance(i + 1));
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

        LogUtils.error("currentTab=" + currentTab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void setCurrentTab(int tab) {
        viewPager.setCurrentItem(tab);
    }


    public void updateTitle() {
        if(Contant.REPAIR_REPKIND == 1){
            mRepairTitleTv.setText("检修维修记录列表");
        }else if(Contant.REPAIR_REPKIND == 2){
            mRepairTitleTv.setText("中修维修记录列表");
        }else if(Contant.REPAIR_REPKIND == 3){
            mRepairTitleTv.setText("大修维修记录列表");
        }
    }
}
