package com.app.spotcheck.moudle.scancheck;

import com.app.spotcheck.moudle.bean.ScanCheckBean;

/**
 * @ClassName: ScanCheckView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/20 18:58
 */
public interface ScanCheckView {

    void showSuccess(ScanCheckBean bean);

    void showError(String error);

    void save(String msg);
}
