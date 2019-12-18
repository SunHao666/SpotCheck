package com.app.spotcheck.moudle.bean;

import java.util.List;

/**
 * @ClassName: SpotCheckAllBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:12
 */
public class SpotCheckAllBean {


    private List<SearchListBean> searchList;

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean {
        /**
         * PARTID : M04P003
         * MAINNAME : 测试卸船机
         * EXECSTARTTIME : 2019-12-09
         * EXECENDTIME : 2019-12-12
         * RECID : 3973eb5acdc64ed682777091c00db1f1
         * PARTNAME : 俯仰机构
         * UNCHECKNUM : 0
         * MAINID : E006
         * CHECKNUM : 5
         */

        private String PARTID;
        private String MAINNAME;
        private String EXECSTARTTIME;
        private String EXECENDTIME;
        private String RECID;
        private String PARTNAME;
        private int UNCHECKNUM;
        private String MAINID;
        private int CHECKNUM;

        public String getPARTID() {
            return PARTID;
        }

        public void setPARTID(String PARTID) {
            this.PARTID = PARTID;
        }

        public String getMAINNAME() {
            return MAINNAME;
        }

        public void setMAINNAME(String MAINNAME) {
            this.MAINNAME = MAINNAME;
        }

        public String getEXECSTARTTIME() {
            return EXECSTARTTIME;
        }

        public void setEXECSTARTTIME(String EXECSTARTTIME) {
            this.EXECSTARTTIME = EXECSTARTTIME;
        }

        public String getEXECENDTIME() {
            return EXECENDTIME;
        }

        public void setEXECENDTIME(String EXECENDTIME) {
            this.EXECENDTIME = EXECENDTIME;
        }

        public String getRECID() {
            return RECID;
        }

        public void setRECID(String RECID) {
            this.RECID = RECID;
        }

        public String getPARTNAME() {
            return PARTNAME;
        }

        public void setPARTNAME(String PARTNAME) {
            this.PARTNAME = PARTNAME;
        }

        public int getUNCHECKNUM() {
            return UNCHECKNUM;
        }

        public void setUNCHECKNUM(int UNCHECKNUM) {
            this.UNCHECKNUM = UNCHECKNUM;
        }

        public String getMAINID() {
            return MAINID;
        }

        public void setMAINID(String MAINID) {
            this.MAINID = MAINID;
        }

        public int getCHECKNUM() {
            return CHECKNUM;
        }

        public void setCHECKNUM(int CHECKNUM) {
            this.CHECKNUM = CHECKNUM;
        }
    }
}
