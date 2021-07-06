package com.app.spotcheck.moudle.bean;

import java.util.List;

/**
 * @ClassName: SpotCheckAllBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:12
 */
public class RepairItemBean {


    private List<SearchListBean> searchList;

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean {
        private String REPID;
        private String MAINID;
        private String MAINNAME;
        private String PARTID;
        private String PARTNAME;
        private String REPSTATE;
        private String PROBLEM;
        private String APPLYTIME;
        private String FINISHCHECKTIME;
        private String REPSTATE_VALUE;
        private String REPSTATE_CODE;

        public String getREPSTATE_VALUE() {
            return REPSTATE_VALUE;
        }

        public void setREPSTATE_VALUE(String REPSTATE_VALUE) {
            this.REPSTATE_VALUE = REPSTATE_VALUE;
        }

        public String getREPSTATE_CODE() {
            return REPSTATE_CODE;
        }

        public void setREPSTATE_CODE(String REPSTATE_CODE) {
            this.REPSTATE_CODE = REPSTATE_CODE;
        }

        public String getREPID() {
            return REPID;
        }

        public void setREPID(String REPID) {
            this.REPID = REPID;
        }

        public String getMAINID() {
            return MAINID;
        }

        public void setMAINID(String MAINID) {
            this.MAINID = MAINID;
        }

        public String getMAINNAME() {
            return MAINNAME;
        }

        public void setMAINNAME(String MAINNAME) {
            this.MAINNAME = MAINNAME;
        }

        public String getPARTID() {
            return PARTID;
        }

        public void setPARTID(String PARTID) {
            this.PARTID = PARTID;
        }

        public String getPARTNAME() {
            return PARTNAME;
        }

        public void setPARTNAME(String PARTNAME) {
            this.PARTNAME = PARTNAME;
        }

        public String getREPSTATE() {
            return REPSTATE;
        }

        public void setREPSTATE(String REPSTATE) {
            this.REPSTATE = REPSTATE;
        }

        public String getPROBLEM() {
            return PROBLEM;
        }

        public void setPROBLEM(String PROBLEM) {
            this.PROBLEM = PROBLEM;
        }

        public String getAPPLYTIME() {
            return APPLYTIME;
        }

        public void setAPPLYTIME(String APPLYTIME) {
            this.APPLYTIME = APPLYTIME;
        }

        public String getFINISHCHECKTIME() {
            if(null == FINISHCHECKTIME){
                return "";
            }
            return FINISHCHECKTIME;
        }

        public void setFINISHCHECKTIME(String FINISHCHECKTIME) {
            this.FINISHCHECKTIME = FINISHCHECKTIME;
        }
    }
}
