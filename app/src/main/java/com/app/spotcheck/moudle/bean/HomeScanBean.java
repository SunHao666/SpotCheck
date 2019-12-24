package com.app.spotcheck.moudle.bean;

import java.util.List;

/**
 * @ClassName: HomeScanBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/24 17:48
 */
public class HomeScanBean {

    private List<SearchListBean> searchList;

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean {
        /**
         * PARTID : M05P0001
         * MAINNAME : 皮带机BJ1
         * EXECSTARTTIME : 2019-12-20
         * EXECENDTIME : 2019-12-23
         * RECID : 313d06d6fa2a41f3af3bddfc61f918b1
         * PARTNAME : 高、低压开关
         * UNCHECKNUM : 2
         * MAINID : E002
         * CHECKNUM : 0
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
