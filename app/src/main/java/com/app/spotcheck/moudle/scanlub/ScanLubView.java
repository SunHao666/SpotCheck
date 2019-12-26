package com.app.spotcheck.moudle.scanlub;

import com.app.spotcheck.moudle.bean.ScanLubBean;

/**
 * @ClassName: ScanLubView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/21 17:43
 */
public interface ScanLubView {

    void showSuccess(ScanLubBean bean);

    void showError(String msg);

    void showSaveSuccess(String msg);

}
