package com.app.spotcheck.moudle.repair.detail2.addperson;

import com.app.spotcheck.base.BaseView;
import com.app.spotcheck.moudle.bean.AddDeviceBean;
import com.app.spotcheck.moudle.bean.AddPersonBean;

import java.util.List;

interface AddPersonView extends BaseView {
    void showLoading();
    void hideLoading();

    void showError(String msg);

    void showPersonList(AddPersonBean bean);

    void showFinish();
}
