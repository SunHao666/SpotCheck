package com.app.spotcheck.moudle.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SpotCheckAllBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 16:12
 */
public class AddDeviceBean {


    private List<SearchListBean> searchList;

    public List<SearchListBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListBean> searchList) {
        this.searchList = searchList;
    }

    public static class SearchListBean implements Serializable {
        private String SPARE_UNIT;
        private int UNITPRICE;
        private String KIND_NAME;
        private String SPARE_NAME;
        private String SPARE_NO;
        private String KIND_NO;
        private String SPARE_TYPE;
        private String STORE_AREA;
        private String SPARE_MANUFACTOR;
        private int STORE_NUM;

        public String getSPARE_UNIT() {
            return SPARE_UNIT;
        }

        public void setSPARE_UNIT(String SPARE_UNIT) {
            this.SPARE_UNIT = SPARE_UNIT;
        }

        public int getUNITPRICE() {
            return UNITPRICE;
        }

        public void setUNITPRICE(int UNITPRICE) {
            this.UNITPRICE = UNITPRICE;
        }

        public String getKIND_NAME() {
            return KIND_NAME;
        }

        public void setKIND_NAME(String KIND_NAME) {
            this.KIND_NAME = KIND_NAME;
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

        public String getKIND_NO() {
            return KIND_NO;
        }

        public void setKIND_NO(String KIND_NO) {
            this.KIND_NO = KIND_NO;
        }

        public String getSPARE_TYPE() {
            return SPARE_TYPE;
        }

        public void setSPARE_TYPE(String SPARE_TYPE) {
            this.SPARE_TYPE = SPARE_TYPE;
        }

        public String getSTORE_AREA() {
            return STORE_AREA;
        }

        public void setSTORE_AREA(String STORE_AREA) {
            this.STORE_AREA = STORE_AREA;
        }

        public String getSPARE_MANUFACTOR() {
            return SPARE_MANUFACTOR;
        }

        public void setSPARE_MANUFACTOR(String SPARE_MANUFACTOR) {
            this.SPARE_MANUFACTOR = SPARE_MANUFACTOR;
        }

        public int getSTORE_NUM() {
            return STORE_NUM;
        }

        public void setSTORE_NUM(int STORE_NUM) {
            this.STORE_NUM = STORE_NUM;
        }
    }
}
