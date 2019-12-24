package com.app.spotcheck.moudle.home;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.HomeScanBean;

/**
 * @ClassName: HomeView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 15:57
 */
public interface HomeView {

    void showSuccess(HomeBean bean);

    void showError(String error);

    void showScanSuccess(HomeScanBean bean);
}
