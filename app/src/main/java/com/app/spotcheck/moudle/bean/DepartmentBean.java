package com.app.spotcheck.moudle.bean;

import java.util.List;

public class DepartmentBean {

    private List<DEPARTMENT_LIST> DEPARTMENT_LIST ;

    public List<DepartmentBean.DEPARTMENT_LIST> getDEPARTMENT_LIST() {
        return DEPARTMENT_LIST;
    }

    public void setDEPARTMENT_LIST(List<DepartmentBean.DEPARTMENT_LIST> DEPARTMENT_LIST) {
        this.DEPARTMENT_LIST = DEPARTMENT_LIST;
    }

    public class DEPARTMENT_LIST {
        private String value;

        private String name;

        public void setValue(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }

    }
}
