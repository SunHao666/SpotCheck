package com.app.spotcheck.moudle.report;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.luck.picture.lib.tools.ValueOf;

import java.util.List;

interface ReportRepairView extends BaseView {

    public void showProList(ProKindBean bean);

    void showError(int code, String msg);

    void showScanSuccess(RepairReportScanBean bean, String qrcode);

    void showSubmitSuccess(String result_message);

    void showLoad();
    void hideLoad();
}
