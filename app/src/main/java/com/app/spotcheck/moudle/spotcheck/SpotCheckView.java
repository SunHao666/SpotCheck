package com.app.spotcheck.moudle.spotcheck;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.KeyWordsBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;

/**
 * @ClassName: SpotCheckView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:11
 */
public interface SpotCheckView extends BaseView {

    void showSuccess(SpotCheckAllBean bean);

    void showError(String error);

    void showSearchSuccess(KeyWordsBean bean);

    void showFinish();

    void showLoading();

    void dissLoading();
}
