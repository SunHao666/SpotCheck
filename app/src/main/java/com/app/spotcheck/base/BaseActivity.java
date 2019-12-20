package com.app.spotcheck.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.app.spotcheck.R;
import com.app.spotcheck.base.wrapper.ToastWrapper;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 通用Activity基类.
 */
public abstract class BaseActivity<T extends BasePresenter> extends TransitionActivity implements BaseView{

    public Toolbar toolbar;
    private FrameLayout viewContent;
    private TextView mTitle;


    private Unbinder mUnbind;
    public T mPresenter;

    private View.OnClickListener onClickListenerTopLeft;   //左边图标的点击事件
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar_base);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        viewContent = (FrameLayout)findViewById(R.id.view_content);
        mTitle = (TextView)findViewById(R.id.tv_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LayoutInflater.from(this).inflate(getContentViewLayout(),viewContent);

        mUnbind = ButterKnife.bind(this);
        ToastWrapper.init(this);
        mPresenter = initPresenter();
        if(mPresenter == null){
            throw new RuntimeException("mPresenter no init");
        }
        mPresenter.attachView(this);
        initView();
        initData();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            onClickListenerTopLeft.onClick();
            finish();
        }
        return true;    //自己处理点击事件
    }


    //添加一个方法设置图标资源id和监听器
    public void setTopLeftButton(int iconId, View.OnClickListener listener) {
        toolbar.setNavigationIcon(iconId);
        this.onClickListenerTopLeft = listener;    //接口回调
    }

    public void setTopTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        }
    }

    protected abstract void initData();

    protected abstract T initPresenter();

    @Override
    protected final void onDestroy() {
        super.onDestroy();
        mPresenter.dettachView(this);
        mUnbind.unbind();
        mUnbind = null;
    }
    @LayoutRes
    protected abstract int getContentViewLayout();

    protected abstract void initView();

    public static void show(Context context, Class<?> cls) {
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

}
