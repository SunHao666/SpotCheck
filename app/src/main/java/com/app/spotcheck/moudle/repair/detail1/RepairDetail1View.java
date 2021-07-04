package com.app.spotcheck.moudle.repair.detail1;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.RepairDetailBean;

interface RepairDetail1View extends BaseView {
    void showError(String msg);

    void showSuccess(RepairDetailBean bean);

    void showFinish(String msg);
}
