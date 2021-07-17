package com.app.spotcheck.moudle.repair.detail4;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.RepairDetailBean;
import com.app.spotcheck.moudle.bean.RepairDeviceListBean;
import com.app.spotcheck.moudle.bean.RepairManListBean;

import java.util.List;

interface RepairDetail4View extends BaseView {
    void showError(String msg);

    void showSuccess(RepairDetailBean bean);

    void showFinish(String msg);

    void showLoading();

    void disLoading();

    void showManList(List<RepairManListBean.SearchListBean> searchList);

    void showDeviceList(List<RepairDeviceListBean.SearchListBean> searchList);
}
