package com.secusoft.ssw.model.viewobject.Response;

/**
 * 基坑人员信息列表
 */
public class FoundaperVO {

    private Integer id;

    /**
     * 姓名
     */
    private String empname;

    /**
     * 班组
     */
    private String deptname;

    /**
     * 手机号码
     */
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
