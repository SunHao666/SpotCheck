package com.app.spotcheck.moudle.repair.detail3;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.DepartmentBean;
import com.app.spotcheck.moudle.bean.RepairDetailBean;

interface RepairDetail3View extends BaseView {
    void showError(String msg);

    void showSuccess(RepairDetailBean bean);

    void showFinish(String msg);

    void showLoading();

    void disLoading();

}
