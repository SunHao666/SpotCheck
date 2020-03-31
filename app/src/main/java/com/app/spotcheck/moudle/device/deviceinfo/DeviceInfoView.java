package com.app.spotcheck.moudle.device.deviceinfo;

import com.app.spotcheck.moudle.bean.DeviceInfoBean;

public interface DeviceInfoView {

    void showLoading();

    void dissLoading();
    void showSuccess(DeviceInfoBean bean);

    void showError(int code, String msg);
}
