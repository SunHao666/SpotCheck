package com.app.spotcheck.moudle.repair.repairitem;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.RepairItemBean;

interface RepairItemView extends BaseView {
    void showSuccess(RepairItemBean repairItemBea);

    void showError(String msg);
}
