package com.app.spotcheck.moudle.home;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.HomeBean;
import com.app.spotcheck.moudle.bean.HomeScanBean;
import com.app.spotcheck.moudle.bean.LubAllBean;
import com.app.spotcheck.moudle.bean.PatralCheckBean;
import com.app.spotcheck.moudle.bean.RefreshWarnBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.app.spotcheck.moudle.bean.SpotCheckAllBean;
import com.luck.picture.lib.tools.ValueOf;

/**
 * @ClassName: HomeView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 15:57
 */
public interface HomeView {

    void showSuccess(HomeBean bean);

    void showError(int code,String error);

    void showScanSuccess(SpotCheckAllBean bean, String qrcode);

    void showLubSuccess(LubAllBean bean, String qrcode);

    void showPatralSuccess(PatralCheckBean bean, String qrcode);
    void showRepairSuccess(RepairReportScanBean bean, String qrcode);


    void showWarn(RefreshWarnBean bean);
}
