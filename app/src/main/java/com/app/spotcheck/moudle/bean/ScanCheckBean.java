package com.app.spotcheck.moudle.bean;

import java.util.List;

public class ScanCheckBean {


    /**
     * MAINNAME : 测试卸船机
     * searchList : [{"CHECKCONTEXT":"异响","EXECID":"17848907a2d14d348bca2ca93e22259c","ID":18,"ITEMNAME":"电动机"},{"CHECKCONTEXT":"温升","EXECID":"17848907a2d14d348bca2ca93e22259c","ID":19,"ITEMNAME":"减速机"},{"CHECKCONTEXT":"异响","EXECID":"17848907a2d14d348bca2ca93e22259c","ID":20,"ITEMNAME":"减速机"}]
     * FINISHFLG : 0
     * EXECID : 17848907a2d14d348bca2ca93e22259c
     * PARTNAME : 俯仰机构
     */

    private String MAINNAME;
    private String FINISHFLG;
    private String EXECID;
    private String PARTNAME;
    private List<SearchListBean> searchList;

    public String getMAINNAME() {
        return MAINNAME;
    }

    public void setMAINNAME(String MAINNAME) {
        this.MAINNAME = MAINNAME;
    }

    public String getFINISHFLG() {
        return FINISHFLG;
    }

    public void setFINISHFLG(String FINISHFLG) {
        this.FINISHFLG = FINISHFLG;
    }

    public String getEXECID() {
        return EXECID;
    }

    public void setEXECID(String EXECID) {
        this.EXECID = EXECID;
    }

    public String getPARTNAME() {
        return PARTNAME;
    }

    public void setPARTNAME(String PARTNAME) {
        this.PARTNAME = PARTNAME;
    }

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean {
        /**
         * CHECKCONTEXT : 异响
         * EXECID : 17848907a2d14d348bca2ca93e22259c
         * ID : 18
         * ITEMNAME : 电动机
         */

        private String CHECKCONTEXT;
        private String EXECID;
        private int ID;
        private String ITEMNAME;

        public String getCHECKCONTEXT() {
            return CHECKCONTEXT;
        }

        public void setCHECKCONTEXT(String CHECKCONTEXT) {
            this.CHECKCONTEXT = CHECKCONTEXT;
        }

        public String getEXECID() {
            return EXECID;
        }

        public void setEXECID(String EXECID) {
            this.EXECID = EXECID;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getITEMNAME() {
            return ITEMNAME;
        }

        public void setITEMNAME(String ITEMNAME) {
            this.ITEMNAME = ITEMNAME;
        }
    }
}
