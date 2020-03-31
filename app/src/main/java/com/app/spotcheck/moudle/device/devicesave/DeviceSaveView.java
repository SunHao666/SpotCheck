package com.app.spotcheck.moudle.device.devicesave;

import com.app.spotcheck.moudle.bean.DeviceListBean;

public interface DeviceSaveView {
    void showSuccess(String msg);

    void showError(int code,String error);

    void showLoading();

    void dissLoading();
}
