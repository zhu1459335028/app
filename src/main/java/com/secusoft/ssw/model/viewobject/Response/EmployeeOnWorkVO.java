package com.secusoft.ssw.model.viewobject.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName EmployeeOnWorkVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/7 14:49
 * @Version 1.0
 */
@ApiModel(value="员工在岗记录结果集")
public class EmployeeOnWorkVO {

    @ApiModelProperty("累计在岗时长")
    private String allSumTime;
    @ApiModelProperty("日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date dayTime;
    @ApiModelProperty("出入时间点")
    private String crTime;
    @ApiModelProperty("进出状态（进/出）")
    private String status;
    @ApiModelProperty("当日在岗时长")
    private String daySumTime;
    @ApiModelProperty("考勤异常信息：0.无异常，1.缺少离岗考勤，2.缺少进岗考勤")
    private int error;

    public Date getDayTime() {
        return dayTime;
    }

    public void setDayTime(Date dayTime) {
        this.dayTime = dayTime;
    }

    public String getCrTime() {
        return crTime;
    }

    public void setCrTime(String crTime) {
        this.crTime = crTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDaySumTime() {
        return daySumTime;
    }

    public void setDaySumTime(String daySumTime) {
        this.daySumTime = daySumTime;
    }

    public String getAllSumTime() {
        return allSumTime;
    }

    public void setAllSumTime(String allSumTime) {
        this.allSumTime = allSumTime;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
