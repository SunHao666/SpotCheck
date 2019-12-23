package com.app.spotcheck.moudle.search;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;

/**
 * @ClassName: SearchActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/23 18:42
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchView {
    @Override
    protected void initData() {

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

    }
}
