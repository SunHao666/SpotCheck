package com.app.spotcheck.moudle.scancheck.checkexception;

import com.app.spotcheck.moudle.bean.CheckExceptionBean;

public interface CheckExceptionView {
    void showSuccess(CheckExceptionBean bean);

    void showError(String error);

    void save(String msg);
}
