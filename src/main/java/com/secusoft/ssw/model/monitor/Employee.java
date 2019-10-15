package com.secusoft.ssw.model.monitor;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName Employee
 * @Author jcyao
 * @Description 员工信息表
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
public class Employee {

    private String id;

    private int outletid;

    private String departmentid;

    private String cardid;

    private String name;

    private String jobid;

    private String phone;

    //紧急联系人/备选人员
    private String alternative;

    //1.黑名单，2.白名单
    private int type;

    private String remarks;

    private String headurl;

    //性别：1.男，2.女
    private int sex;


    private String cardfronturl;

    private String cardreverseurl;

    /**
     * 工号
     */
    private String worknumber;

    public String getWorknumber() {
        return worknumber;
    }

    public void setWorknumber(String worknumber) {
        this.worknumber = worknumber;
    }

    public String getCardfronturl() {
        return cardfronturl;
    }

    public void setCardfronturl(String cardfronturl) {
        this.cardfronturl = cardfronturl;
    }

    public String getCardreverseurl() {
        return cardreverseurl;
    }

    public void setCardreverseurl(String cardreverseurl) {
        this.cardreverseurl = cardreverseurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOutletid() {
        return outletid;
    }

    public void setOutletid(int outletid) {
        this.outletid = outletid;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
