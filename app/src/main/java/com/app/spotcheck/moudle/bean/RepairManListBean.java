package com.app.spotcheck.moudle.bean;

import java.util.List;

/**
 * @ClassName: SpotCheckAllBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:12
 */
public class RepairManListBean {


    private List<SearchListBean> searchList;

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean {
        private String REPID;
        private String WORKER_NO;
        private String WORKER_NAME;
        private String WORKHOUR_D;
        private String WORKHOUR_F;

        public String getREPID() {
            return REPID;
        }

        public void setREPID(String REPID) {
            this.REPID = REPID;
        }

        public String getWORKER_NO() {
            return WORKER_NO;
        }

        public void setWORKER_NO(String WORKER_NO) {
            this.WORKER_NO = WORKER_NO;
        }

        public String getWORKER_NAME() {
            return WORKER_NAME;
        }

        public void setWORKER_NAME(String WORKER_NAME) {
            this.WORKER_NAME = WORKER_NAME;
        }

        public String getWORKHOUR_D() {
            return WORKHOUR_D;
        }

        public void setWORKHOUR_D(String WORKHOUR_D) {
            this.WORKHOUR_D = WORKHOUR_D;
        }

        public String getWORKHOUR_F() {
            return WORKHOUR_F;
        }

        public void setWORKHOUR_F(String WORKHOUR_F) {
            this.WORKHOUR_F = WORKHOUR_F;
        }
    }
}
