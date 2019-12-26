package com.app.spotcheck.moudle.bean;

import java.util.List;

/**
 * @ClassName: KeyWordsBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/26 13:56
 */
public class KeyWordsBean {


    private List<SearchListBean> searchList;

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean {
        /**
         * KEYWORDS : 1#取料机
         */

        private String KEYWORDS;

        public String getKEYWORDS() {
            return KEYWORDS;
        }

        public void setKEYWORDS(String KEYWORDS) {
            this.KEYWORDS = KEYWORDS;
        }
    }
}
