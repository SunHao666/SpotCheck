package com.app.spotcheck.moudle.login;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.LoginBean;

/**
 * @ClassName: LoginView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 14:43
 */
public interface LoginView{

    void showResult(String msg, LoginBean bean);

    void showError(String error);
}
