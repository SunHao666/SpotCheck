package com.app.spotcheck.moudle.spotcheck.specialcheck;

import com.app.spotcheck.moudle.bean.RepairItemBean;
import com.app.spotcheck.moudle.bean.SpecialItemBean;

interface SpecialCheckView {
    void showSuccess(SpecialItemBean repairItemBean);

    void showError(String msg);
}
