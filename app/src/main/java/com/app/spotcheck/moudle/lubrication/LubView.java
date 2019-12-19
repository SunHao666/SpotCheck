package com.app.spotcheck.moudle.lubrication;

import com.app.spotcheck.moudle.bean.HomeBean;

/**
 * @ClassName: HomeView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 15:57
 */
public interface LubView {

    void showSuccess(HomeBean bean);

    void showError(String error);
}
