package com.app.spotcheck.moudle.bean;

import java.util.List;

/**
 * @ClassName: SpotCheckAllBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:12
 */
public class RepairDeviceListBean {


    private List<SearchListBean> searchList;

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean {
        private String KINDNO;
        private String MANUFACTOR;
        private String SPARE_UNIT;
        private String KINDNAME;
        private String SPARE_NAME;
        private String SPARE_NO;
        private String REPID;
        private String SPARE_TYPE;
        private int USEQUANTITY;

        public String getKINDNO() {
            return KINDNO;
        }

        public void setKINDNO(String KINDNO) {
            this.KINDNO = KINDNO;
        }

        public String getMANUFACTOR() {
            return MANUFACTOR;
        }

        public void setMANUFACTOR(String MANUFACTOR) {
            this.MANUFACTOR = MANUFACTOR;
        }

        public String getSPARE_UNIT() {
            return SPARE_UNIT;
        }

        public void setSPARE_UNIT(String SPARE_UNIT) {
            this.SPARE_UNIT = SPARE_UNIT;
        }

        public String getKINDNAME() {
            return KINDNAME;
        }

        public void setKINDNAME(String KINDNAME) {
            this.KINDNAME = KINDNAME;
        }

        public String getSPARE_NAME() {

            return SPARE_NAME;
        }

        public void setSPARE_NAME(String SPARE_NAME) {
            this.SPARE_NAME = SPARE_NAME;
        }

        public String getSPARE_NO() {
            return SPARE_NO;
        }

        public void setSPARE_NO(String SPARE_NO) {
            this.SPARE_NO = SPARE_NO;
        }

        public String getREPID() {
            return REPID;
        }

        public void setREPID(String REPID) {
            this.REPID = REPID;
        }

        public String getSPARE_TYPE() {
            return SPARE_TYPE;
        }

        public void setSPARE_TYPE(String SPARE_TYPE) {
            this.SPARE_TYPE = SPARE_TYPE;
        }

        public int getUSEQUANTITY() {
            return USEQUANTITY;
        }

        public void setUSEQUANTITY(int USEQUANTITY) {
            this.USEQUANTITY = USEQUANTITY;
        }
    }
}
