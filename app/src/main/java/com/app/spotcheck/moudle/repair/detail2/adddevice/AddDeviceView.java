package com.app.spotcheck.moudle.repair.detail2.adddevice;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.AddDeviceBean;

import java.util.List;

interface AddDeviceView extends BaseView {
    void showLoading();
    void hideLoading();

    void showError(String msg);

    void showSuccess(List<AddDeviceBean.SearchListBean> searchList);
}
