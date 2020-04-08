package com.app.spotcheck.base;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.spotcheck.R;
import com.app.spotcheck.base.utils.DialogUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.event.BaseEvent;
import com.app.spotcheck.moudle.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private DialogUtils loading;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar_base);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        viewContent = (FrameLayout)findViewById(R.id.view_content);
        mTitle = (TextView)findViewById(R.id.tv_title);

        EventBus.getDefault().register(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LayoutInflater.from(this).inflate(getContentViewLayout(),viewContent);

        ToastWrapper.init(this);
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
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dettachView(this);
        mUnbind.unbind();
        mUnbind = null;
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void toLogin(BaseEvent event){
        startActivity(new Intent(this, LoginActivity.class));
    }

    @LayoutRes
    protected abstract int getContentViewLayout();

    protected abstract void initView();

    public static void show(Context context, Class<?> cls) {
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

    public void showLoding(){//点击加载并按钮模仿网络请求
        if(loading == null){
            loading = new DialogUtils(this,R.style.CustomDialog);
        }
        loading.show();
    }

    public void disLoding(){//点击加载并按钮模仿网络请求
        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }
    }

}
