package com.secusoft.ssw.model.viewobject.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class FoundationVo {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("检测日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dt;
    @ApiModelProperty("检测人")
    private String rummager;
    @ApiModelProperty("盾构区间")
    private String tbm_range;
    private String O2 = "";
    private String CO = "";
    private String H2S = "";
    @ApiModelProperty("可燃气 type==1")
    private String FireO2 = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public String getRummager() {
        return rummager;
    }

    public void setRummager(String rummager) {
        this.rummager = rummager;
    }

    public String getTbm_range() {
        return tbm_range;
    }

    public void setTbm_range(String tbm_range) {
        this.tbm_range = tbm_range;
    }

    public String getO2() {
        return O2;
    }

    public void setO2(String o2) {
        O2 = o2;
    }

    public String getCO() {
        return CO;
    }

    public void setCO(String CO) {
        this.CO = CO;
    }

    public String getH2S() {
        return H2S;
    }

    public void setH2S(String h2S) {
        H2S = h2S;
    }

    public String getFireO2() {
        return FireO2;
    }

    public void setFireO2(String fireO2) {
        FireO2 = fireO2;
    }
}
