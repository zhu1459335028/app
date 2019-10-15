package com.secusoft.ssw.model.viewobject.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName JobUserVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/4 16:45
 * @Version 1.0
 */
@ApiModel(value="员工在岗信息集")
public class JobUserVO implements Comparable<JobUserVO>{

    @ApiModelProperty("员工编号")
    private String employeeId;
    @ApiModelProperty("员工名称")
    private String userName;
    @ApiModelProperty("员工头像")
    private String headUrl;
    @ApiModelProperty("是否在岗：1.在岗、2.不在岗")
    private int onLine;
    @ApiModelProperty("到岗时间")
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date onTime;

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

    public Date getOnTime() {
        return onTime;
    }

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    @Override
    public int compareTo(JobUserVO jobUserVO) {
        if(this.onTime!=null){
            if(jobUserVO.getOnLine()==1 && jobUserVO.getOnTime()!=null && jobUserVO.getOnTime().getTime()>this.onTime.getTime())
                return 1;
            return -1;
        }
        return 1;
    }
}
