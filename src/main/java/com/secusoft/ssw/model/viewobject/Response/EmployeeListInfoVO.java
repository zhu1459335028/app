package com.secusoft.ssw.model.viewobject.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName EmployeeListInfoVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/7 13:13
 * @Version 1.0
 */
@ApiModel(value="员工列表信息结果集")
public class EmployeeListInfoVO {

    @ApiModelProperty("员工编号")
    private String id;
    @ApiModelProperty("卡号")
    private String cardid;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("职位名称")
    private String jobName;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("性别（男/女）")
    private String sex;
    @ApiModelProperty("是否在岗（是/否）")
    private String onLine;
    @ApiModelProperty("今日到岗时间")
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date todayTime;
    @ApiModelProperty("今日在岗时长")
    private String todaySumTime;
    @ApiModelProperty("累计在岗时长")
    private String allSumTime;

    @ApiModelProperty("身份证正面url")
    private String cardfronturl;

    @ApiModelProperty("身份证反面url")
    private String cardreverseurl;

    @ApiModelProperty("工号")
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOnLine() {
        return onLine;
    }

    public void setOnLine(String onLine) {
        this.onLine = onLine;
    }

    public Date getTodayTime() {
        return todayTime;
    }

    public void setTodayTime(Date todayTime) {
        this.todayTime = todayTime;
    }

    public String getTodaySumTime() {
        return todaySumTime;
    }

    public void setTodaySumTime(String todaySumTime) {
        this.todaySumTime = todaySumTime;
    }

    public String getAllSumTime() {
        return allSumTime;
    }

    public void setAllSumTime(String allSumTime) {
        this.allSumTime = allSumTime;
    }
}
