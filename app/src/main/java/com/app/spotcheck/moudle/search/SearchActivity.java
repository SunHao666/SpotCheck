package com.app.spotcheck.moudle.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.bean.KeyWordsBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: SearchActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/23 18:42
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchView {
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @BindView(R.id.lay_tag)
    LinearLayout layTag;
    @BindView(R.id.id_flowlayout_all)
    TagFlowLayout idFlowlayoutAll;
    @BindView(R.id.tv_search_skip)
    TextView tvSearchSkip;
    @BindView(R.id.search_toolbar)
    Toolbar searchToolbar;
    @BindView(R.id.et_search)
    EditText etSearch;

    private List<String> tags = new ArrayList<>();
    private List<String> tagsAll = new ArrayList<>();
    private TagAdapter searchAdapter;
    private TagAdapter searchAllAdapter;
    private String usekind;

    @Override
    protected void initData() {
        usekind = getIntent().getStringExtra("usekind");
        requestSearch();
    }

    @Override
    protected SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        toolbar.setVisibility(View.GONE);
        setSupportActionBar(searchToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        searchToolbar.setNavigationIcon(R.drawable.back);

        initSearch();
        initSearchAll();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;    //自己处理点击事件
    }

    private void initSearchAll() {
        searchAllAdapter = new TagAdapter<String>(tagsAll) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tv,
                        idFlowlayoutAll, false);
                tv.setText(s);
                return tv;
            }
        };
        idFlowlayoutAll.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                saveKey(tagsAll.get(position));
                return true;
            }
        });
        idFlowlayoutAll.setAdapter(searchAllAdapter);
    }

    private void saveKey(String key) {
//        usekind	类型
//        userid	登录人
//        keywords	关键字
        String userid = SPUtils.getInstance(this).getString("Loginid");
        mPresenter.saveKey(usekind, userid, key);

    }

    private void initSearch() {
        searchAdapter = new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tv,
                        idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        };
        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                saveKey(tagsAll.get(position));
                return true;
            }
        });
        idFlowlayout.setAdapter(searchAdapter);
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showSearchSuccess(KeyWordsBean keyWordsBean) {
        if (keyWordsBean.getSearchList() == null || keyWordsBean.getSearchList().size() == 0) {
            return;
        }
        tags.clear();
        searchAdapter.notifyDataChanged();
        for (int i = 0; i < keyWordsBean.getSearchList().size(); i++) {
            tags.add(keyWordsBean.getSearchList().get(i).getKEYWORDS());
        }

        searchAdapter.notifyDataChanged();
    }

    @Override
    public void showSearchAllSuccess(KeyWordsBean keyWordsBean) {
        if (keyWordsBean.getSearchList() == null || keyWordsBean.getSearchList().size() == 0) {
            return;
        }
        tagsAll.clear();
        searchAllAdapter.notifyDataChanged();
        for (int i = 0; i < keyWordsBean.getSearchList().size(); i++) {
            tagsAll.add(keyWordsBean.getSearchList().get(i).getKEYWORDS());
        }
        searchAllAdapter.notifyDataChanged();
    }

    @Override
    public void showSaveSuccess(String key) {
        Intent intent = new Intent();
        intent.putExtra("key", key);
        if(usekind.equals("1")){
            setResult(2004, intent);
        }else{
            setResult(2005, intent);
        }
        finish();
    }

    @Override
    public void showSaveError(String message, String keywords) {
        Intent intent = new Intent();
        intent.putExtra("key", keywords);
        if(usekind.equals("1")){
            setResult(2004, intent);
        }else{
            setResult(2005, intent);
        }
        finish();
    }

    private void requestSearch() {
//        usekind	类型	1：点检；2：润滑
//        userid	登录人
        String userid = SPUtils.getInstance(this).getString("Loginid");
        mPresenter.getCheckSearch(usekind, userid);

        userid = "";
        mPresenter.getCheckSearch(usekind, userid);
    }


    @OnClick(R.id.tv_search_skip)
    public void onViewClicked() {
        if(TextUtils.isEmpty(etSearch.getText().toString())){
        }else{
            saveKey(etSearch.getText().toString());
        }

    }
}
