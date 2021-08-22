package com.app.spotcheck.moudle.spotcheck.specialcheck.report;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairReportScanBean;
import com.app.spotcheck.moudle.bean.ReportSearchBean;
import com.app.spotcheck.moudle.bean.SpecialItemBean;
import com.app.spotcheck.moudle.bean.SpecialSearchBean;

interface ReportSpecialView extends BaseView {


    void showError(int code, String msg);


    void showSubmitSuccess(String result_message);

    void showLoad();
    void hideLoad();

    void showSearchList(SpecialSearchBean bean);
}
