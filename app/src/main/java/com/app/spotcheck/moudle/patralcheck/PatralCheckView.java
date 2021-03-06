package com.app.spotcheck.moudle.patralcheck;

import com.app.spotcheck.moudle.bean.PatralCheckBean;
import com.app.spotcheck.moudle.bean.ScanCheckBean;

/**
 * @ClassName: PatralCheckView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 17:16
 */
public interface PatralCheckView {

    void showSuccess(PatralCheckBean bean);

    void showError(String error);

    void save(String result_message);
}
