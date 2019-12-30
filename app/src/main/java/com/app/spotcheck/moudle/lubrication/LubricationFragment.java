package com.app.spotcheck.moudle.lubrication;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.view.ScrollableViewPager;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.LubAllBean;
import com.app.spotcheck.moudle.bean.LubBean;
import com.app.spotcheck.moudle.patralcheck.PatralCheckActivity;
import com.app.spotcheck.moudle.search.SearchActivity;
import com.app.spotcheck.moudle.spotcheck.CheckFragment;
import com.app.spotcheck.moudle.spotcheck.MViewPagerAdapter;
import com.app.spotcheck.network.Contant;
import com.google.android.material.tabs.TabLayout;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;

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
    ScrollableViewPager viewPager;
    public String[] tabNames = {"全部润滑工作", "待润滑", "已完成"};
    public List<Fragment> fragments = new ArrayList<>();
    private ViewPagerLubAdapter adapter;
    private int tab = 0;
    private boolean isParer = false;
    private boolean isVisible = true;
    public static LubricationFragment newInstance() {
        return new LubricationFragment();
    }

    @Override
    protected void initData() {
        Contant.TAB_SELECT = 2;
        LogUtils.error("LubFragment initData");
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

    @Override
    public void onResume() {
        super.onResume();
        if(!isVisible){
            Contant.TAB_SELECT = 2;
            LogUtils.error("LubFragment onResume");
        }
    }

    private void initTablayout() {
        for (int i = 0; i < tabNames.length; i++) {
            tablayout.addTab(tablayout.newTab());
            fragments.add(new LubFragment(i));
        }
        tablayout.addOnTabSelectedListener(this);
        adapter = new ViewPagerLubAdapter(getChildFragmentManager(),getActivity(),fragments,tabNames);
        viewPager.setAdapter(adapter);
        viewPager.setScanScroll(false);
        tablayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(tab);
        isParer = true;
    }
    @Override
    public void showSuccess(LubAllBean bean) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isVisible = hidden;
        if(!hidden){
            LogUtils.error("LubFragment onHiddenChanged");
            Contant.TAB_SELECT = 2;
        }
    }

    @OnClick({R.id.scan, R.id.lay_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                scanLub();
                break;
            case R.id.lay_search:
                Intent intent =new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("usekind","2");
                startActivityForResult(intent,1005);
                break;
        }
    }

    private void scanLub() {
        tvSearch.setText("");
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class),1003);
//        Contant.LUBQRCODE  = "E011M03L0001";
//        Contant.LUBSEARCH = "";
//        viewPager.setCurrentItem(1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){ return;}

        if(requestCode == 1003){
            String result = data.getStringExtra(Intents.Scan.RESULT);
            if(TextUtils.isEmpty(result)|| !result.contains("qrcode=")){
                ToastWrapper.show("二维码格式不正确");
                return;
            }
            LogUtils.error("scan before="+result);
            result = result.substring(7,result.length());
            LogUtils.error(result);
            Contant.LUBQRCODE = result;
            Contant.LUBSEARCH = "";
            viewPager.setCurrentItem(1);
        }else if(requestCode == 1005 && resultCode == 2005){
            String key = data.getStringExtra("key");
            if(!TextUtils.isEmpty(key)){
                tvSearch.setText(key);
                Contant.LUBQRCODE  = "";
                Contant.LUBSEARCH = key;
                viewPager.setCurrentItem(1);
            }
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
        //第n次进点检列表页
        if(isParer){
            viewPager.setCurrentItem(tab);
        }
    }
}
