package com.app.spotcheck.moudle.bean;

import java.util.List;

public class ReportSearchBean {

    public List<SearchListDTO> searchList;

    public List<SearchListDTO> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListDTO> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListDTO {
        public String MAINNAME;
        public String MAINID;
        public List<PartslistDTO> PARTSLIST;

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

        public List<PartslistDTO> getPARTSLIST() {
            return PARTSLIST;
        }

        public void setPARTSLIST(List<PartslistDTO> PARTSLIST) {
            this.PARTSLIST = PARTSLIST;
        }

        public static class PartslistDTO {
            public String PARTID;
            public String MAINNAME;
            public String PARTNAME;
            public String MAINID;

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

            public String getPARTNAME() {
                return PARTNAME;
            }

            public void setPARTNAME(String PARTNAME) {
                this.PARTNAME = PARTNAME;
            }

            public String getMAINID() {
                return MAINID;
            }

            public void setMAINID(String MAINID) {
                this.MAINID = MAINID;
            }
        }
    }
}
