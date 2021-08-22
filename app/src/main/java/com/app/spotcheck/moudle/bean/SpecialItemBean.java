package com.app.spotcheck.moudle.bean;

import java.util.List;

/**
 * @ClassName: SpotCheckAllBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:12
 */
public class SpecialItemBean {


    private List<SearchListBean> searchList;

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean {
        private String CHECKRESULT;
        private String MAINNAME;
        private String CHECKRECORDS;
        private String CHECKITEM;
        private String CKID;
        private String CHECKTIME;
        private String MAINID;

        public String getCHECKRESULT() {
            return CHECKRESULT;
        }

        public void setCHECKRESULT(String CHECKRESULT) {
            this.CHECKRESULT = CHECKRESULT;
        }

        public String getMAINNAME() {
            return MAINNAME;
        }

        public void setMAINNAME(String MAINNAME) {
            this.MAINNAME = MAINNAME;
        }

        public String getCHECKRECORDS() {
            return CHECKRECORDS;
        }

        public void setCHECKRECORDS(String CHECKRECORDS) {
            this.CHECKRECORDS = CHECKRECORDS;
        }

        public String getCHECKITEM() {
            return CHECKITEM;
        }

        public void setCHECKITEM(String CHECKITEM) {
            this.CHECKITEM = CHECKITEM;
        }

        public String getCKID() {
            return CKID;
        }

        public void setCKID(String CKID) {
            this.CKID = CKID;
        }

        public String getCHECKTIME() {
            return CHECKTIME;
        }

        public void setCHECKTIME(String CHECKTIME) {
            this.CHECKTIME = CHECKTIME;
        }

        public String getMAINID() {
            return MAINID;
        }

        public void setMAINID(String MAINID) {
            this.MAINID = MAINID;
        }
    }
}
