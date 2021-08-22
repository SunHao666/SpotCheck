package com.app.spotcheck.moudle.bean;

import java.util.List;

public class SpecialSearchBean {

    public List<Device> searchList;

    public List<Device> getSearchList() {
        return searchList;
    }


    public static class Device {
        public String MAINNAME;
        public String MAINID;

        public String getMAINNAME() {
            return MAINNAME;
        }

        public void setMAINNAME(String MAINNAME) {
            this.MAINNAME = MAINNAME;
        }

        public String getMAINID() {
            return MAINID;
        }

        public void setMAINID(String MAINID) {
            this.MAINID = MAINID;
        }


    }
}
