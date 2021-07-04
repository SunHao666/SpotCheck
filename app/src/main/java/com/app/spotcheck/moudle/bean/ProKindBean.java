package com.app.spotcheck.moudle.bean;

import java.util.List;

public class ProKindBean{

    private List<PROBLEMKIND_LIST> PROBLEMKIND_LIST ;

    public void setPROBLEMKIND_LIST(List<PROBLEMKIND_LIST> PROBLEMKIND_LIST){
        this.PROBLEMKIND_LIST = PROBLEMKIND_LIST;
    }
    public List<PROBLEMKIND_LIST> getPROBLEMKIND_LIST(){
        return this.PROBLEMKIND_LIST;
    }
    public class PROBLEMKIND_LIST {
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
