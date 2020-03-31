package com.app.spotcheck.moudle.device;

import com.app.spotcheck.moudle.bean.DeviceListBean;
import com.app.spotcheck.moudle.bean.HomeBean;

public interface DeviceView {
    void showSuccess(DeviceListBean bean);

    void showError(int code,String error);

    void showLoading();

    void dissLoading();
}
