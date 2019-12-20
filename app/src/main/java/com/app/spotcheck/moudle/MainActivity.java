package com.app.spotcheck.moudle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.moudle.home.HomeFragment;
import com.app.spotcheck.moudle.login.LoginActivity;
import com.app.spotcheck.moudle.lubrication.LubricationFragment;
import com.app.spotcheck.moudle.mine.MineFragment;
import com.app.spotcheck.moudle.spotcheck.SpotCheckFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

public class MainActivity extends BaseActivity<BasePresenter> implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.layout_container)
    FrameLayout layoutContainer;
    @BindView(R.id.bottomnavigationview)
    BottomNavigationView bottomnavigationview;
    private HomeFragment mHomeFragment;
    private SpotCheckFragment mSpotCheckFragment;
    private LubricationFragment mLubricationFragment;
    private MineFragment mMineFragment;
    // 当前正在显示的Fragment
    private Fragment mCurrentFragment;


    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return new BasePresenter();
    }

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        // "内存重启"时(例如修改手机字体大小), 恢复之前的Fragment.
        // 注意此方法只有在父类的onCreate(Bundle)调用之后才有效.
        retrieveFragments();
        bottomnavigationview.setOnNavigationItemSelectedListener(this);
        toolbar.setVisibility(View.GONE);
    }


    /**
     * 找回FragmentManager中存储的Fragment
     */
    private void retrieveFragments() {
        FragmentManager manager = getSupportFragmentManager();
        mHomeFragment = (HomeFragment) manager.findFragmentByTag(HomeFragment.class.getName());
        mSpotCheckFragment = (SpotCheckFragment) manager.findFragmentByTag(SpotCheckFragment.class.getName());
        mLubricationFragment = (LubricationFragment) manager.findFragmentByTag(LubricationFragment.class.getName());
        mMineFragment = (MineFragment) manager.findFragmentByTag(MineFragment.class.getName());
    }



    /**
     * 首页4个Fragment切换, 使用hide和show, 而不是replace.
     *
     * @param target 要显示的目标Fragment.
     */
    private void switchFragment(Fragment target) {
        if (mCurrentFragment == target) return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mCurrentFragment != null) {
            // 隐藏当前正在显示的Fragment
            transaction.hide(mCurrentFragment);
        }
        if (target.isAdded()) {
            // 如果目标Fragment已经添加过, 就显示它
            transaction.show(target);
        } else {
            // 否则直接添加该Fragment
            transaction.add(R.id.layout_container, target, target.getClass().getName());
        }
        transaction.commit();
        mCurrentFragment = target;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.tab_one://首页
                if (mHomeFragment == null) mHomeFragment = HomeFragment.newInstance();
                switchFragment(mHomeFragment);
                return true;
            case R.id.tab_two://分类
                if (mSpotCheckFragment == null)
                    mSpotCheckFragment = SpotCheckFragment.newInstance();
                switchFragment(mSpotCheckFragment);
                return true;
            case R.id.tab_three://购物车
                if (mLubricationFragment == null)
                    mLubricationFragment = LubricationFragment.newInstance();
                switchFragment(mLubricationFragment);
                return true;
            case R.id.tab_four://我的
                if (mMineFragment == null) mMineFragment = MineFragment.newInstance();
                switchFragment(mMineFragment);
                return true;
            default:
                return false;
        }
    }

}
