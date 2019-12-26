package com.app.spotcheck.moudle.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.spotcheck.R;
import com.app.spotcheck.base.BaseActivity;
import com.app.spotcheck.base.BasePresenter;
import com.app.spotcheck.base.utils.SPUtils;
import com.app.spotcheck.base.wrapper.ToastWrapper;
import com.app.spotcheck.moudle.MainActivity;
import com.app.spotcheck.moudle.bean.LoginBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: LoginActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 20:28
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView{


    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_b1)
    TextView tvB1;
    @BindView(R.id.tv_b2)
    TextView tvB2;

    @Override
    protected void initData() {

    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        toolbar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if(TextUtils.isEmpty(etUsername.getText().toString())){
           ToastWrapper.show("请输入登录名");
           return;
        }else if(TextUtils.isEmpty(etPwd.getText().toString())){
            ToastWrapper.show("请输入密码");
            return;
        }
        showLoding();
        mPresenter.login(etUsername.getText().toString(),etPwd.getText().toString());

    }

    @Override
    public void showResult(String success,LoginBean bean) {
        SPUtils.getInstance(this).put("logtime",bean.getLogtime());
        SPUtils.getInstance(this).put("Loginname",bean.getLoginname());
        SPUtils.getInstance(this).put("Loginid",bean.getLoginid());
        disLoding();
        ToastWrapper.show(success);
        MainActivity.show(LoginActivity.this,MainActivity.class);
        finish();
    }

    @Override
    public void showError(String error) {
        disLoding();
        ToastWrapper.show(error);
    }
}
