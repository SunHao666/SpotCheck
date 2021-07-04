package com.app.spotcheck.moudle.repair.detail2;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.DepartmentBean;
import com.app.spotcheck.moudle.bean.ProKindBean;
import com.app.spotcheck.moudle.bean.RepairDetailBean;

interface RepairDetail2View extends BaseView {
    void showError(String msg);

    void showSuccess(RepairDetailBean bean);

    void showFinish(String msg);

    void showCompanyList(DepartmentBean bean);
}
