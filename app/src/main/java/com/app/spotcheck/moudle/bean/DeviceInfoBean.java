package com.app.spotcheck.moudle.bean;

import java.util.List;

public class DeviceInfoBean {
    public List<DeviceInfoListBean> list;
    public List<DeviceInfoTextBean> info;

    public List<DeviceInfoListBean> getList() {
        return list;
    }

    public void setList(List<DeviceInfoListBean> list) {
        this.list = list;
    }

    public List<DeviceInfoTextBean> getInfo() {
        return info;
    }

    public void setInfo(List<DeviceInfoTextBean> info) {
        this.info = info;
    }

    public class DeviceInfoListBean {
        public List<DeviceInfoInnerBean> searchList;

        public List<DeviceInfoInnerBean> getSearchList() {
            return searchList;
        }

        public void setSearchList(List<DeviceInfoInnerBean> searchList) {
            this.searchList = searchList;
        }

        public class DeviceInfoInnerBean {
            public String PROBLEM;
            public String FINDTYPE;
            public String MAINID;
            public String PARTID;
            public String PROBLEMKIND;
            public String MAINNAME;
            public String PARTNAME;
            public String FINDTIME;
            public String ITEMNAME;

            public String getPROBLEM() {
                return PROBLEM;
            }

            public void setPROBLEM(String PROBLEM) {
                this.PROBLEM = PROBLEM;
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

            public String getPARTNAME() {
                return PARTNAME;
            }

            public void setPARTNAME(String PARTNAME) {
                this.PARTNAME = PARTNAME;
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

    public class DeviceInfoTextBean {
        public String MAINID;
        public String MAINNAME;
        public String STOPDATE;
        public String RESTARTDATE;

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
    }
}
