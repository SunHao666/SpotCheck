package com.app.spotcheck.moudle.repair.detail2.adddevice.time;

import com.app.spotcheck.base.BaseView;

interface AddDeviceTimeView extends BaseView {
    void showLoading();
    void disLoading();

    void showError(String msg);

    void showFinish(String msg);
}
