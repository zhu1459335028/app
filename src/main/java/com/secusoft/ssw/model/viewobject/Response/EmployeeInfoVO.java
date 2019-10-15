package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName EmployeeInfoVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/7 11:29
 * @Version 1.0
 */
@ApiModel(value="员工信息结果集")
public class EmployeeInfoVO {

    @ApiModelProperty("员工编号")
    private String id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("所属部门名称")
    private String departmentName;
    @ApiModelProperty("卡号")
    private String cardid;
    @ApiModelProperty("岗位名称")
    private String jobName;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("家庭住址")
    private String address;
    @ApiModelProperty("头像地址")
    private String headUrl;
    @ApiModelProperty("性别：1.男，2.女")
    private int sex;

    @ApiModelProperty("身份证正面url")
    private String cardfronturl;

    @ApiModelProperty("身份证反面url")
    private String cardreverseurl;

    @ApiModelProperty("工号")
    private String worknumber;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
