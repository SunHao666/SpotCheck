package com.app.spotcheck.moudle.bean;

import java.io.Serializable;

/**
 * @ClassName: LoginBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 15:12
 */
public class LoginBean implements Serializable {

    /**
     * WorkClassName : 机电1班
     * Loginid : test1
     * logtime : 1577156159786
     */

    private String WorkClassName;
    private String Loginid;
    private long logtime;
    private String Loginname;
    private String DepartmentId;    //部门ID
    private String DepartmentName;    //部门名称
    private String uuAdminUser;
    private String RepairDispatch;
    private String RepairFinishRefirm;
    private String RepairRecordRefirm;
    private String Tel;
    private String RepairApplyCheck;
    private String WorkClassNo;

    private String RepairApplyCheckNum;
    private String RepairDispatchNum;
    private String RepairRecordRefirmNum;
    private String RepairFinishRefirmNum;

    public String getRepairApplyCheckNum() {
        return RepairApplyCheckNum;
    }

    public void setRepairApplyCheckNum(String repairApplyCheckNum) {
        RepairApplyCheckNum = repairApplyCheckNum;
    }

    public String getRepairDispatchNum() {
        return RepairDispatchNum;
    }

    public void setRepairDispatchNum(String repairDispatchNum) {
        RepairDispatchNum = repairDispatchNum;
    }

    public String getRepairRecordRefirmNum() {
        return RepairRecordRefirmNum;
    }

    public void setRepairRecordRefirmNum(String repairRecordRefirmNum) {
        RepairRecordRefirmNum = repairRecordRefirmNum;
    }

    public String getRepairFinishRefirmNum() {
        return RepairFinishRefirmNum;
    }

    public void setRepairFinishRefirmNum(String repairFinishRefirmNum) {
        RepairFinishRefirmNum = repairFinishRefirmNum;
    }

    public String getUuAdminUser() {
        return uuAdminUser;
    }

    public void setUuAdminUser(String uuAdminUser) {
        this.uuAdminUser = uuAdminUser;
    }

    public String getRepairDispatch() {
        return RepairDispatch;
    }

    public void setRepairDispatch(String repairDispatch) {
        RepairDispatch = repairDispatch;
    }

    public String getRepairFinishRefirm() {
        return RepairFinishRefirm;
    }

    public void setRepairFinishRefirm(String repairFinishRefirm) {
        RepairFinishRefirm = repairFinishRefirm;
    }

    public String getRepairRecordRefirm() {
        return RepairRecordRefirm;
    }

    public void setRepairRecordRefirm(String repairRecordRefirm) {
        RepairRecordRefirm = repairRecordRefirm;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getRepairApplyCheck() {
        return RepairApplyCheck;
    }

    public void setRepairApplyCheck(String repairApplyCheck) {
        RepairApplyCheck = repairApplyCheck;
    }

    public String getWorkClassNo() {
        return WorkClassNo;
    }

    public void setWorkClassNo(String workClassNo) {
        WorkClassNo = workClassNo;
    }

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getLoginname() {
        return Loginname;
    }

    public void setLoginname(String loginname) {
        Loginname = loginname;
    }

    public String getWorkClassName() {
        return WorkClassName;
    }

    public void setWorkClassName(String WorkClassName) {
        this.WorkClassName = WorkClassName;
    }

    public String getLoginid() {
        return Loginid;
    }

    public void setLoginid(String Loginid) {
        this.Loginid = Loginid;
    }

    public long getLogtime() {
        return logtime;
    }

    public void setLogtime(long logtime) {
        this.logtime = logtime;
    }
}
