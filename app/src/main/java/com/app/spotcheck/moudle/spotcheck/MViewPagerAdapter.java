package com.app.spotcheck.moudle.spotcheck;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @ClassName: MViewPagerAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/19 20:53
 */
public class MViewPagerAdapter extends FragmentStatePagerAdapter {
    private  List<Fragment> fragments;
    private String[] tabNames;
    private Context context;
    public MViewPagerAdapter(FragmentManager supportFragmentManager,
                             Context context,
                             List<Fragment> fragments,
                             String[] tabNames) {
        super(supportFragmentManager);
        this.context = context;
        this.fragments = fragments;
        this.tabNames = tabNames;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
}
