package com.app.spotcheck.moudle.bean;

import java.util.List;

/**
 * @ClassName: PatralCheckBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/25 10:46
 */
public class PatralCheckBean {


    /**
     * PARTID : M05P0001
     * PROBLEMKIND : [{"value":"1","name":"A类"},{"value":"2","name":"B类"}]
     * MAINNAME : 皮带机BJ1
     * PARTNAME : 高、低压开关
     * MAINID : E002
     */

    private String PARTID;
    private String MAINNAME;
    private String PARTNAME;
    private String MAINID;
    private List<PROBLEMKINDBean> PROBLEMKIND;

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

    public List<PROBLEMKINDBean> getPROBLEMKIND() {
        return PROBLEMKIND;
    }

    public void setPROBLEMKIND(List<PROBLEMKINDBean> PROBLEMKIND) {
        this.PROBLEMKIND = PROBLEMKIND;
    }

}
