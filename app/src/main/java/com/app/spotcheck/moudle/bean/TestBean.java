package com.app.spotcheck.moudle.bean;

import java.util.List;

public class TestBean {


    /**
     * List : {"searchList":[{"PROBLEM":"悬梁磨损","RECID":"ef2f74db7e134c69b1d2616fb12ebd28","FINDTYPE":"1","MAINID":"E004","PARTID":"M02P0002","PROBLEMKIND":"A","MAINNAME":"1#堆料机","REPAIRWORK":null,"FINISHFLG":"0","PARTNAME":"俯仰机构","REPAIRTIME":null,"FINDTIME":"2020-03-29","ITEMNAME":"液压系统"}]}
     * Info : {"STOPDATE":"2020-03-27 08:00:00","MAINNAME":"1#堆料机","RESTARTDATE":"2020-03-27 10:00:00","MAINID":"E004"}
     */

    private ListBean List;
    private InfoBean Info;

    public ListBean getList() {
        return List;
    }

    public void setList(ListBean List) {
        this.List = List;
    }

    public InfoBean getInfo() {
        return Info;
    }

    public void setInfo(InfoBean Info) {
        this.Info = Info;
    }

    public static class ListBean {
        private java.util.List<SearchListBean> searchList;

        public List<SearchListBean> getSearchList() {
            return searchList;
        }

        public void setSearchList(List<SearchListBean> searchList) {
            this.searchList = searchList;
        }

        public static class SearchListBean {
            /**
             * PROBLEM : 悬梁磨损
             * RECID : ef2f74db7e134c69b1d2616fb12ebd28
             * FINDTYPE : 1
             * MAINID : E004
             * PARTID : M02P0002
             * PROBLEMKIND : A
             * MAINNAME : 1#堆料机
             * REPAIRWORK : null
             * FINISHFLG : 0
             * PARTNAME : 俯仰机构
             * REPAIRTIME : null
             * FINDTIME : 2020-03-29
             * ITEMNAME : 液压系统
             */

            private String PROBLEM;
            private String RECID;
            private String FINDTYPE;
            private String MAINID;
            private String PARTID;
            private String PROBLEMKIND;
            private String MAINNAME;
            private Object REPAIRWORK;
            private String FINISHFLG;
            private String PARTNAME;
            private Object REPAIRTIME;
            private String FINDTIME;
            private String ITEMNAME;

            public String getPROBLEM() {
                return PROBLEM;
            }

            public void setPROBLEM(String PROBLEM) {
                this.PROBLEM = PROBLEM;
            }

            public String getRECID() {
                return RECID;
            }

            public void setRECID(String RECID) {
                this.RECID = RECID;
            }

            public String getFINDTYPE() {
                return FINDTYPE;
            }

            public void setFINDTYPE(String FINDTYPE) {
                this.FINDTYPE = FINDTYPE;
            }

            public String getMAINID() {
                return MAINID;
            }

            public void setMAINID(String MAINID) {
                this.MAINID = MAINID;
            }

            public String getPARTID() {
                return PARTID;
            }

            public void setPARTID(String PARTID) {
                this.PARTID = PARTID;
            }

            public String getPROBLEMKIND() {
                return PROBLEMKIND;
            }

            public void setPROBLEMKIND(String PROBLEMKIND) {
                this.PROBLEMKIND = PROBLEMKIND;
            }

            public String getMAINNAME() {
                return MAINNAME;
            }

            public void setMAINNAME(String MAINNAME) {
                this.MAINNAME = MAINNAME;
            }

            public Object getREPAIRWORK() {
                return REPAIRWORK;
            }

            public void setREPAIRWORK(Object REPAIRWORK) {
                this.REPAIRWORK = REPAIRWORK;
            }

            public String getFINISHFLG() {
                return FINISHFLG;
            }

            public void setFINISHFLG(String FINISHFLG) {
                this.FINISHFLG = FINISHFLG;
            }

            public String getPARTNAME() {
                return PARTNAME;
            }

            public void setPARTNAME(String PARTNAME) {
                this.PARTNAME = PARTNAME;
            }

            public Object getREPAIRTIME() {
                return REPAIRTIME;
            }

            public void setREPAIRTIME(Object REPAIRTIME) {
                this.REPAIRTIME = REPAIRTIME;
            }

            public String getFINDTIME() {
                return FINDTIME;
            }

            public void setFINDTIME(String FINDTIME) {
                this.FINDTIME = FINDTIME;
            }

            public String getITEMNAME() {
                return ITEMNAME;
            }

            public void setITEMNAME(String ITEMNAME) {
                this.ITEMNAME = ITEMNAME;
            }
        }
    }

    public static class InfoBean {
        /**
         * STOPDATE : 2020-03-27 08:00:00
         * MAINNAME : 1#堆料机
         * RESTARTDATE : 2020-03-27 10:00:00
         * MAINID : E004
         */

        private String STOPDATE;
        private String MAINNAME;
        private String RESTARTDATE;
        private String MAINID;

        public String getSTOPDATE() {
            return STOPDATE;
        }

        public void setSTOPDATE(String STOPDATE) {
            this.STOPDATE = STOPDATE;
        }

        public String getMAINNAME() {
            return MAINNAME;
        }

        public void setMAINNAME(String MAINNAME) {
            this.MAINNAME = MAINNAME;
        }

        public String getRESTARTDATE() {
            return RESTARTDATE;
        }

        public void setRESTARTDATE(String RESTARTDATE) {
            this.RESTARTDATE = RESTARTDATE;
        }

        public String getMAINID() {
            return MAINID;
        }

        public void setMAINID(String MAINID) {
            this.MAINID = MAINID;
        }
    }
}
