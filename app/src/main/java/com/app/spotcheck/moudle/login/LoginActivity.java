package com.app.spotcheck.moudle.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.app.spotcheck.moudle.MApplication;
import com.app.spotcheck.moudle.MainActivity;
import com.app.spotcheck.moudle.bean.LoginBean;
import com.app.spotcheck.network.Contant;
import com.app.spotcheck.utils.GlobalKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

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
    public int press = 0;
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
        findViewById(R.id.iv_logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press++;
                if(press == 30){
                    showAddressDialog();
                }
            }
        });
        String registrationID = JPushInterface.getRegistrationID(this);
        Log.e("id","------------------------------>"+registrationID);
    }

    private void showAddressDialog() {

        String path = SPUtils.getInstance(MApplication.getIntance()).getString("path");
        if(TextUtils.isEmpty(path)){
            path = "hqmjml7980.xicp.net";
        }
        EditText editText = new EditText(this);
        editText.setText(path);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请输入访问地址")
                .setView(editText)
                .setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        press =0;
                    }
                })
                .setPositiveButton("确认修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!TextUtils.isEmpty(editText.getText().toString())){
                            SPUtils.getInstance(LoginActivity.this).put("path",editText.getText().toString());
                        }
                        dialog.dismiss();
                        press =0;
                    }
                });
        builder.show();
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
        SPUtils.getInstance(this).put(GlobalKey.KEY_LOGINNAME,bean.getLoginname());
        SPUtils.getInstance(this).put(GlobalKey.KEY_LOGINID,bean.getLoginid());
        SPUtils.getInstance(this).put(GlobalKey.KEY_DEPARTMENTNAME,bean.getDepartmentName());
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
