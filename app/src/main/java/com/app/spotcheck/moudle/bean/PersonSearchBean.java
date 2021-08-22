package com.app.spotcheck.moudle.bean;

import android.os.WorkSource;

import java.util.List;

public class PersonSearchBean {

    public List<PersonItemBean> searchList;

    public List<PersonItemBean> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<PersonItemBean> searchList) {
        this.searchList = searchList;
    }

    public static class PersonItemBean {

        public List<WorkListDTO> WORKERLIST;
        public String DEPARTMENT_NAME;//部门名称；

        public List<WorkListDTO> getWORKERLIST() {
            return WORKERLIST;
        }

        public void setWORKERLIST(List<WorkListDTO> WORKERLIST) {
            this.WORKERLIST = WORKERLIST;
        }

        public String getDEPARTMENT_NAME() {
            return DEPARTMENT_NAME;
        }

        public void setDEPARTMENT_NAME(String DEPARTMENT_NAME) {
            this.DEPARTMENT_NAME = DEPARTMENT_NAME;
        }
        public static class WorkListDTO {
            public String DEPARTMENT_NAME;//部门名称；
            public String WORKER_NO;//工人编号；
            public String WORKER_NAME;//工人名称
            public String DEPARTMENT_ID; //部门编号；

            public String getDEPARTMENT_NAME() {
                return DEPARTMENT_NAME;
            }

            public void setDEPARTMENT_NAME(String DEPARTMENT_NAME) {
                this.DEPARTMENT_NAME = DEPARTMENT_NAME;
            }

            public String getWORKER_NO() {
                return WORKER_NO;
            }

            public void setWORKER_NO(String WORKER_NO) {
                this.WORKER_NO = WORKER_NO;
            }

            public String getWORKER_NAME() {
                return WORKER_NAME;
            }

            public void setWORKER_NAME(String WORKER_NAME) {
                this.WORKER_NAME = WORKER_NAME;
            }

            public String getDEPARTMENT_ID() {
                return DEPARTMENT_ID;
            }

            public void setDEPARTMENT_ID(String DEPARTMENT_ID) {
                this.DEPARTMENT_ID = DEPARTMENT_ID;
            }
        }
    }


}
