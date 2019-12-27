package com.app.spotcheck.moudle.search;

import com.app.spotcheck.moudle.bean.KeyWordsBean;

/**
 * @ClassName: SearchView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/23 18:41
 */
public interface SearchView {

    void showSuccess();

    void showError(String error);

    void showSearchSuccess(KeyWordsBean keyWordsBean);

    void showSearchAllSuccess(KeyWordsBean keyWordsBean);

    void showSaveSuccess(String key);

    void showSaveError(String message, String keywords);
}
