package com.app.spotcheck.moudle.scancheck;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.wrapper.ToolbarWrapper;

/**
 * @ClassName: ScanCheckActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/20 18:57
 */
public class ScanCheckActivity extends BaseActivity<ScanCheckPresenter> implements ScanCheckView {
    @Override
    protected void initData() {

    }

    @Override
    protected ScanCheckPresenter initPresenter() {
        return new ScanCheckPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_scan_check;
    }

    @Override
    protected void initView() {
        new ToolbarWrapper(this).setCustomTitle("设备点检项目").setShowBack(true);
    }
}
