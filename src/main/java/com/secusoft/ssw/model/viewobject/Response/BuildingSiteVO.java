package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName BuildingSiteVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/4 16:44
 * @Version 1.0
 */
@ApiModel(value="工地概况信息集")
public class BuildingSiteVO {
    @ApiModelProperty("项目名称")
    private String brandName;
    @ApiModelProperty("工地名称")
    private String siteName;
    @ApiModelProperty("职位名称")
    private String jobName;
//    @ApiModelProperty("员工在岗信息集")
//    private List<JobUserVO> jobUsers;


    @ApiModelProperty("员工编号")
    private String employeeId;
    @ApiModelProperty("员工名称")
    private String userName;
    @ApiModelProperty("员工头像")
    private String headUrl;
    @ApiModelProperty("是否在岗：1.在岗、2.不在岗")
    private int onLine;


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getOnLine() {
        return onLine;
    }

    public void setOnLine(int onLine) {
        this.onLine = onLine;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

//    public List<JobUserVO> getJobUsers() {
//        return jobUsers;
//    }
//
//    public void setJobUsers(List<JobUserVO> jobUsers) {
//        this.jobUsers = jobUsers;
//    }
}
