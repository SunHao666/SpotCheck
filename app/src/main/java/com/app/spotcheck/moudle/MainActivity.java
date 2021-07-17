package com.app.spotcheck.moudle;

import android.Manifest;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.home.HomeNewFragment;
import com.app.spotcheck.moudle.lubrication.LubricationFragment;
import com.app.spotcheck.moudle.mine.MineFragment;
import com.app.spotcheck.moudle.repair.RepairFragment;
import com.app.spotcheck.moudle.spotcheck.SpotCheckFragment;
import com.app.spotcheck.network.Contant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity<BasePresenter> implements BottomNavigationView.OnNavigationItemSelectedListener ,EasyPermissions.PermissionCallbacks{

    private static final int RC_PERMISSIONS = 119;
    public int checkFrom = -1;
    public int lubFrom = -1;

    @BindView(R.id.layout_container)
    FrameLayout layoutContainer;
    @BindView(R.id.bottomnavigationview)
    BottomNavigationView bottomnavigationview;

    private HomeNewFragment mHomeFragment;
    private SpotCheckFragment mSpotCheckFragment;
    private LubricationFragment mLubricationFragment;
    private RepairFragment mRepairFrament;
    private MineFragment mMineFragment;
    // 当前正在显示的Fragment
    private Fragment mCurrentFragment;

    @Override
    protected void initData() {
        checkPression();
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
        initFragment();
        bottomnavigationview.setOnNavigationItemSelectedListener(this);
        toolbar.setVisibility(View.GONE);
        initListener();
    }

    private void initListener() {
        mHomeFragment.setOnCheckScanClick(new HomeNewFragment.OnCheckScanClick() {
            @Override
            public void onClick(int position) {
                mSpotCheckFragment.setTab(0);
                if (mSpotCheckFragment == null)
                    mSpotCheckFragment = SpotCheckFragment.newInstance();
                mSpotCheckFragment.setTab(1);
                bottomnavigationview.setSelectedItemId(R.id.tab_two);
            }
        });

        mHomeFragment.setOnLubScanClick(new HomeNewFragment.OnLubScanClick() {
            @Override
            public void onClick(int position) {
                if (mLubricationFragment == null)
                    mLubricationFragment = LubricationFragment.newInstance();
                mLubricationFragment.setTab(1);
                bottomnavigationview.setSelectedItemId(R.id.tab_three);
            }
        });
    }


    /**
     * 找回FragmentManager中存储的Fragment
     */
    private void initFragment() {
        mHomeFragment = new HomeNewFragment();
        mSpotCheckFragment = new SpotCheckFragment();
        mLubricationFragment = new LubricationFragment();
        mRepairFrament = RepairFragment.getInstance();
        mMineFragment = new MineFragment();
        switchFragment(mHomeFragment);
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
                if (mHomeFragment == null) mHomeFragment = HomeNewFragment.newInstance();
                switchFragment(mHomeFragment);
                return true;
            case R.id.tab_two://点检
                if(checkFrom != 1){
                    Contant.CHECKQRCODE= "";
                    Contant.CHECKSEARCH= "";
                }else{
                    checkFrom = -1;
                    Contant.CHECKSEARCH= "";
                }
                LogUtils.error("onNavigationItemSelected"+Contant.CHECKQRCODE);
                if (mSpotCheckFragment == null)
                    mSpotCheckFragment = SpotCheckFragment.newInstance();
                switchFragment(mSpotCheckFragment);
                return true;
            case R.id.tab_three://润滑
                if(lubFrom != 1){
                    Contant.LUBQRCODE= "";
                    Contant.LUBSEARCH="";
                }else{
                    lubFrom = -1;
                    Contant.LUBSEARCH="";
                }

                if (mLubricationFragment == null)
                    mLubricationFragment = LubricationFragment.newInstance();
                switchFragment(mLubricationFragment);
                return true;
            case R.id.tab_four://维修
                if (mRepairFrament == null)  mRepairFrament= RepairFragment.getInstance();
                switchFragment(mRepairFrament);
                return true;
            case R.id.tab_five://我的
                if (mMineFragment == null) mMineFragment = MineFragment.newInstance();
                switchFragment(mMineFragment);
                return true;

            default:
                return false;
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //退出时的时间
    private long mExitTime;
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastWrapper.show("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //用户勾选了“不再询问”，引导用户去设置页面打开权限
            new AppSettingsDialog.Builder(this)
                    .setTitle("权限申请")
                    .setRationale("应用程序运行缺少必要的权限，请前往设置页面打开")
                    .setPositiveButton("去设置")
                    .setNegativeButton("取消")
                    .setRequestCode(110)
                    .build()
                    .show();
        }
    }


    @AfterPermissionGranted(RC_PERMISSIONS)
    private void checkPression() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //权限获取成功
        }else {
            //没有权限，调用方法申请权限
            EasyPermissions.requestPermissions(this, "程序运行需要存储权限和相机权限", RC_PERMISSIONS, permissions);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            //用户从设置页面返回，
        }
    }

    /**
     * 目前只用于repair界面
     * @param tab 选择的tab
     */
    public void selectedTab(int tab,int childTab) {
        if(tab == 4){
            bottomnavigationview.setSelectedItemId(R.id.tab_four);
        }else if(tab == 3){
            bottomnavigationview.setSelectedItemId(R.id.tab_three);
        }
    }
}
