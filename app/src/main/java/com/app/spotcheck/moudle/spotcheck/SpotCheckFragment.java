package com.app.spotcheck.moudle.spotcheck;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseFragment;
import com.app.spotcheck.base.utils.LogUtils;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.view.ScrollableViewPager;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.KeyWordsBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.app.spotcheck.moudle.search.SearchActivity;
import com.app.spotcheck.network.Contant;
import com.google.android.material.tabs.TabLayout;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SpotCheckFragment extends BaseFragment<SpotCheckPresenter> implements SpotCheckView, TabLayout.OnTabSelectedListener {
    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.scan)
    ImageView scan;
    @BindView(R.id.lay_search)
    LinearLayout laySearch;
    @BindView(R.id.iv_delete)
    ImageView iv_delete;
    public String[] tabNames = {"全部点检部位", "待检部位", "已检部位"};
    public List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.viewPager)
    ScrollableViewPager viewPager;
    private MViewPagerAdapter adapter;
    private int tab = 0;
    private boolean isParer = false;
    KeyWordsBean bean = new KeyWordsBean();
    private int currentTab = 0;
    private boolean isVisible =true;
    public static SpotCheckFragment newInstance() {
        return new SpotCheckFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        Contant.TAB_SELECT = 1;
        if(!isVisible){
            LogUtils.error("CheckFragment onResume");
            if(TextUtils.isEmpty(Contant.CHECKQRCODE) && TextUtils.isEmpty(Contant.CHECKSEARCH)){
                tvSearch.setText("");
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void initData() {
        Contant.TAB_SELECT = 1;
        LogUtils.error("CheckFragment initData");
        if(TextUtils.isEmpty(Contant.CHECKQRCODE) && TextUtils.isEmpty(Contant.CHECKSEARCH)){
            tvSearch.setText("");
        }
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
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSearch.setText("");
                Contant.CHECKSEARCH = "";
//                viewPager.setCurrentItem(1);
//                if(listener != null){
//                    listener.clear(tablayout.getSelectedTabPosition());
//                }
                if(getTab() == 0){
                    viewPager.setCurrentItem(1);
                    viewPager.setCurrentItem(0);
                }else if(getTab() == 1){
                    viewPager.setCurrentItem(2);
                    viewPager.setCurrentItem(1);
                }else if(getTab() == 2){
                    viewPager.setCurrentItem(1);
                    viewPager.setCurrentItem(2);
                }
            }
        });
        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s)){
                    iv_delete.setVisibility(View.GONE);
                }else{
                    iv_delete.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    private void initTablayout() {
        for (int i = 0; i < tabNames.length; i++) {
            tablayout.addTab(tablayout.newTab());
            fragments.add(new CheckFragment(i));
        }
        tablayout.addOnTabSelectedListener(this);
        viewPager.setScanScroll(false);
        adapter = new MViewPagerAdapter(getChildFragmentManager(), getActivity(), fragments, tabNames);
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

    @Override
    public void showSearchSuccess(KeyWordsBean bean) {
        this.bean = bean;
//        tags.clear();
//        searchAdapter.notifyDataChanged();
//        for (int i = 0; i < bean.getSearchList().size(); i++) {
//            tags.add(bean.getSearchList().get(i).getKEYWORDS());
//        }
    }

    @Override
    public void showFinish() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dissLoading() {

    }

    @OnClick({R.id.scan, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                scanCheck();
                break;
            case R.id.tv_search:
                Intent intent =new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("usekind","1");
                startActivityForResult(intent,1004);
                break;
        }
    }

    private void scanCheck() {
        tvSearch.setText("");
        startActivityForResult(new Intent(getActivity(), CaptureActivity.class),1001);
//        Contant.CHECKQRCODE  = "E002M05P0002";
//        Contant.CHECKSEARCH = "";
//        viewPager.setCurrentItem(1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){ return;}

        if(requestCode == 1001){
            String result = data.getStringExtra(Intents.Scan.RESULT);
            if(TextUtils.isEmpty(result)|| !result.contains("qrcode=")){
                showQrcodeDialog();
                return;
            }
            LogUtils.error("scan before="+result);
            result = result.substring(7,result.length());
            LogUtils.error(result);
            Contant.CHECKQRCODE  = result;
            Contant.CHECKSEARCH = "";
            viewPager.setCurrentItem(1);
        }else if(requestCode == 1004 && resultCode == 2004){
            String key = data.getStringExtra("key");
            if(!TextUtils.isEmpty(key)){
                tvSearch.setText(key);
                Contant.CHECKQRCODE  = "";
                Contant.CHECKSEARCH = key;
                viewPager.setCurrentItem(1);
            }
        }
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

    public void setTab(int tab) {
        this.tab = tab;
        //第n次进点检列表页
        if (isParer) {
            viewPager.setCurrentItem(tab);
        }
    }

    public int getTab(){
        return tab;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.error("SpotCheck onHidder"+hidden);
        isVisible = hidden;
        if(!hidden){//显示中
            Contant.TAB_SELECT = 1;
            if(TextUtils.isEmpty(Contant.CHECKQRCODE) && TextUtils.isEmpty(Contant.CHECKSEARCH)){
                tvSearch.setText("");
            }
            if(getTab() == 0){
                viewPager.setCurrentItem(1);
                viewPager.setCurrentItem(0);
            }else if(getTab() == 1){
                viewPager.setCurrentItem(2);
                viewPager.setCurrentItem(1);
            }else if(getTab() == 2){
                viewPager.setCurrentItem(1);
                viewPager.setCurrentItem(2);
            }
            LogUtils.error("CheckFragment onHiddenChanged");
//            requestSearch();
        }
    }

    private void requestSearch() {
//        usekind	类型	1：点检；2：润滑
//        userid	登录人
        String usekind = "1";
        String userid = SPUtils.getInstance(getActivity()).getString("Loginid");
        mPresenter.getCheckSearch(usekind,userid);
    }

    public interface CheckClearListener{
        void clear(int tab);
    }
    CheckClearListener listener;

    public void setCheckClearListener(CheckClearListener listener){
        this.listener = listener;
    };
}
