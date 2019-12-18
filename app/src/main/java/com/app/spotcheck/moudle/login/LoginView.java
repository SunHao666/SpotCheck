package com.app.spotcheck.moudle.login;

import com.app.spotcheck.base.BaseView;

/**
 * @ClassName: LoginView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 14:43
 */
public interface LoginView<T> extends BaseView {

    void showResult(T t);

    void showError(String error);
}
