package com.app.spotcheck.moudle.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SpotCheckAllBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:12
 */
public class AddPersonBean {


    private List<SearchListBean> searchList;

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean {
        private String WORKER_NAME;

        private String WORKER_NO;

        public String getWORKER_NAME() {
            return WORKER_NAME;
        }

        public void setWORKER_NAME(String WORKER_NAME) {
            this.WORKER_NAME = WORKER_NAME;
        }

        public String getWORKER_NO() {
            return WORKER_NO;
        }

        public void setWORKER_NO(String WORKER_NO) {
            this.WORKER_NO = WORKER_NO;
        }
    }
}
