package com.app.spotcheck.moudle.spotcheck;

import android.view.LayoutInflater;
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
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.view.ScrollableViewPager;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.KeyWordsBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.google.android.material.tabs.TabLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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
    ScrollableViewPager viewPager;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @BindView(R.id.lay_tag)
    LinearLayout layTag;
    private MViewPagerAdapter adapter;
    private int tab = 0;
    private boolean isParer = false;
    KeyWordsBean bean = new KeyWordsBean();
    private TagAdapter searchAdapter;
    private List<String> tags = new ArrayList<>();

    public static SpotCheckFragment newInstance() {
        return new SpotCheckFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestSearch();
    }

    @Override
    public void onPause() {
        super.onPause();
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

        initSearch();
    }

    private void initSearch() {
        searchAdapter = new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tv,
                        idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        };
        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent)
            {
                ToastWrapper.show(tags.get(position));
                layTag.setVisibility(View.GONE);
                return true;
            }
        });
        idFlowlayout.setAdapter(searchAdapter);
    }

    private void initTablayout() {
        for (int i = 0; i < tabNames.length; i++) {
            tablayout.addTab(tablayout.newTab());
            fragments.add(new CheckFragment(i));
        }
        tablayout.addOnTabSelectedListener(this);
        viewPager.setScanScroll(false);
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

    @Override
    public void showSearchSuccess(KeyWordsBean bean) {
        this.bean = bean;
        tags.clear();
        searchAdapter.notifyDataChanged();
        for (int i = 0; i < bean.getSearchList().size(); i++) {
            tags.add(bean.getSearchList().get(i).getKEYWORDS());
        }
    }

    @OnClick({R.id.scan, R.id.lay_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scan:
                break;
            case R.id.lay_search:
                layTag.setVisibility(View.VISIBLE);

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
        //第n次进点检列表页
        if (isParer) {
            viewPager.setCurrentItem(tab);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.error("SpotCheck onHidder"+hidden);
        if(!hidden){//显示中
            requestSearch();
        }
    }

    private void requestSearch() {
//        usekind	类型	1：点检；2：润滑
//        userid	登录人
        String usekind = "1";
        String userid = SPUtils.getInstance(getActivity()).getString("Loginid");
        mPresenter.getCheckSearch(usekind,userid);
    }
}
