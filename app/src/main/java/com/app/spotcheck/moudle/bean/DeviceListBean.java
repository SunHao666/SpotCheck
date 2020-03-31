package com.app.spotcheck.moudle.bean;

import java.util.List;

public class DeviceListBean {
    public List<DataBean> searchList;

    public List<DataBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<DataBean> searchList) {
        this.searchList = searchList;
    }

    public class DataBean {
        public String MAINID;
        public String MAINNAME;
        public String STOPDATE;
        public String RESTARTDATE;
        public String REPCOUNT;

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

        public String getSTOPDATE() {
            return STOPDATE;
        }

        public void setSTOPDATE(String STOPDATE) {
            this.STOPDATE = STOPDATE;
        }

        public String getRESTARTDATE() {
            return RESTARTDATE;
        }

        public void setRESTARTDATE(String RESTARTDATE) {
            this.RESTARTDATE = RESTARTDATE;
        }

        public String getREPCOUNT() {
            return REPCOUNT;
        }

        public void setREPCOUNT(String REPCOUNT) {
            this.REPCOUNT = REPCOUNT;
        }
    }


}
