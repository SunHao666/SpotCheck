package com.app.spotcheck.moudle.bean;

import java.util.List;

public class CheckExceptionBean {


    /**
     * PROBLEMKIND : [{"value":"1","name":"A类"},{"value":"2","name":"B类"}]
     * MAINNAME : 测试卸船机
     * CHECKCONTEXT : 异响
     * FINISHFLG : 0
     * EXECID : 17848907a2d14d348bca2ca93e22259c
     * PARTNAME : 俯仰机构
     * ID : 18
     * ITEMNAME : 电动机
     */

    private String MAINNAME;
    private String CHECKCONTEXT;
    private String FINISHFLG;
    private String EXECID;
    private String PARTNAME;
    private int ID;
    private String ITEMNAME;
    private String PARTID;
    private String MAINID;

    public String getPARTID() {
        return PARTID;
    }

    public void setPARTID(String PARTID) {
        this.PARTID = PARTID;
    }

    public String getMAINID() {
        return MAINID;
    }

    public void setMAINID(String MAINID) {
        this.MAINID = MAINID;
    }

    private List<PROBLEMKINDBean> PROBLEMKIND;

    public String getMAINNAME() {
        return MAINNAME;
    }

    public void setMAINNAME(String MAINNAME) {
        this.MAINNAME = MAINNAME;
    }

    public String getCHECKCONTEXT() {
        return CHECKCONTEXT;
    }

    public void setCHECKCONTEXT(String CHECKCONTEXT) {
        this.CHECKCONTEXT = CHECKCONTEXT;
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

    public List<PROBLEMKINDBean> getPROBLEMKIND() {
        return PROBLEMKIND;
    }

    public void setPROBLEMKIND(List<PROBLEMKINDBean> PROBLEMKIND) {
        this.PROBLEMKIND = PROBLEMKIND;
    }

}
