package com.app.spotcheck.moudle.bean;

/**
 * @ClassName: ScanLubBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/26 10:16
 */
public class ScanLubBean {


    /**
     * LRECNO : 65f0e53698d045089132a047e371ebdf
     * PLANTIME : 2019-10-25
     * MAINNAME : 1#取料机
     * EXECSTARTTIME : null
     * LUBRICANT : 2#锂基脂
     * EXECENDTIME : null
     * EXECSTATUS : 0
     * OILVOLUME : 10
     * OILTYPE : 加油
     * PARTNAME : 润滑部位A
     * FINISHTIME : null
     * LUBTYPE : 灌注
     */

    private String LRECNO;
    private String PLANTIME;
    private String MAINNAME;
    private Object EXECSTARTTIME;
    private String LUBRICANT;
    private Object EXECENDTIME;
    private String EXECSTATUS;
    private int OILVOLUME;
    private String OILTYPE;
    private String PARTNAME;
    private Object FINISHTIME;
    private String LUBTYPE;
    private String POINTNAME;

    public String getPOINTNAME() {
        return POINTNAME;
    }

    public void setPOINTNAME(String POINTNAME) {
        this.POINTNAME = POINTNAME;
    }

    public String getLRECNO() {
        return LRECNO;
    }

    public void setLRECNO(String LRECNO) {
        this.LRECNO = LRECNO;
    }

    public String getPLANTIME() {
        return PLANTIME;
    }

    public void setPLANTIME(String PLANTIME) {
        this.PLANTIME = PLANTIME;
    }

    public String getMAINNAME() {
        return MAINNAME;
    }

    public void setMAINNAME(String MAINNAME) {
        this.MAINNAME = MAINNAME;
    }

    public Object getEXECSTARTTIME() {
        return EXECSTARTTIME;
    }

    public void setEXECSTARTTIME(Object EXECSTARTTIME) {
        this.EXECSTARTTIME = EXECSTARTTIME;
    }

    public String getLUBRICANT() {
        return LUBRICANT;
    }

    public void setLUBRICANT(String LUBRICANT) {
        this.LUBRICANT = LUBRICANT;
    }

    public Object getEXECENDTIME() {
        return EXECENDTIME;
    }

    public void setEXECENDTIME(Object EXECENDTIME) {
        this.EXECENDTIME = EXECENDTIME;
    }

    public String getEXECSTATUS() {
        return EXECSTATUS;
    }

    public void setEXECSTATUS(String EXECSTATUS) {
        this.EXECSTATUS = EXECSTATUS;
    }

    public int getOILVOLUME() {
        return OILVOLUME;
    }

    public void setOILVOLUME(int OILVOLUME) {
        this.OILVOLUME = OILVOLUME;
    }

    public String getOILTYPE() {
        return OILTYPE;
    }

    public void setOILTYPE(String OILTYPE) {
        this.OILTYPE = OILTYPE;
    }

    public String getPARTNAME() {
        return PARTNAME;
    }

    public void setPARTNAME(String PARTNAME) {
        this.PARTNAME = PARTNAME;
    }

    public Object getFINISHTIME() {
        return FINISHTIME;
    }

    public void setFINISHTIME(Object FINISHTIME) {
        this.FINISHTIME = FINISHTIME;
    }

    public String getLUBTYPE() {
        return LUBTYPE;
    }

    public void setLUBTYPE(String LUBTYPE) {
        this.LUBTYPE = LUBTYPE;
    }
}
