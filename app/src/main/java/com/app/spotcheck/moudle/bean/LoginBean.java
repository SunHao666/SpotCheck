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
    private String DepartmentId;	//部门ID
    private String DepartmentName;	//部门名称

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
