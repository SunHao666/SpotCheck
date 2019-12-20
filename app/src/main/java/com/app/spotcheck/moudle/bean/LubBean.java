package com.app.spotcheck.moudle.bean;

import java.util.List;

/**
 * @ClassName: LubBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/20 16:10
 */
public class LubBean {

    private List<LubSearchListBean> searchList;

    public List<LubSearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<LubSearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class LubSearchListBean {
        /**
         * PARTID : 94acaa661576483eaf1bec2dce1a2ec2
         * PLANTIME : 2019-10-25
         * MAINNAME : 1#取料机
         * EXECSTARTTIME : null
         * EXECENDTIME : null
         * EXECSTATUS : 0
         * RECID : 65f0e53698d045089132a047e371ebdf
         * PARTNAME : 润滑部位A
         * FINISHTIME : null
         * MAINID : E001
         */

        private String PARTID;
        private String PLANTIME;
        private String MAINNAME;
        private Object EXECSTARTTIME;
        private Object EXECENDTIME;
        private String EXECSTATUS;
        private String RECID;
        private String PARTNAME;
        private Object FINISHTIME;
        private String MAINID;

        public String getPARTID() {
            return PARTID;
        }

        public void setPARTID(String PARTID) {
            this.PARTID = PARTID;
        }

        public String getPLANTIME() {
            return PLANTIME;
        }

        public void setPLANTIME(String PLANTIME) {
            this.PLANTIME = PLANTIME;
        }

        public String getMAINNAME() {
            return MAINNAME;
        }

        public void setMAINNAME(String MAINNAME) {
            this.MAINNAME = MAINNAME;
        }

        public Object getEXECSTARTTIME() {
            return EXECSTARTTIME;
        }

        public void setEXECSTARTTIME(Object EXECSTARTTIME) {
            this.EXECSTARTTIME = EXECSTARTTIME;
        }

        public Object getEXECENDTIME() {
            return EXECENDTIME;
        }

        public void setEXECENDTIME(Object EXECENDTIME) {
            this.EXECENDTIME = EXECENDTIME;
        }

        public String getEXECSTATUS() {
            return EXECSTATUS;
        }

        public void setEXECSTATUS(String EXECSTATUS) {
            this.EXECSTATUS = EXECSTATUS;
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

        public Object getFINISHTIME() {
            return FINISHTIME;
        }

        public void setFINISHTIME(Object FINISHTIME) {
            this.FINISHTIME = FINISHTIME;
        }

        public String getMAINID() {
            return MAINID;
        }

        public void setMAINID(String MAINID) {
            this.MAINID = MAINID;
        }
    }
}
