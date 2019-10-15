package com.secusoft.ssw.model.viewobject.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("环境安全和基坑安全数据集")
public class EnvironmentalVo {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("检测日期")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dt;
    private String PM25 = "";
    private String PM10 = "";
    private String Noise = "";
    private String Temperature = "";
    private String Humidity = "";
    private String WindSpeed = "";
    private String WindDirection = "";
    private String WindPower = "";
    @ApiModelProperty("检测人")
    private String rummager;
    @ApiModelProperty("盾构区间")
    private String tbm_range;

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public String getPM25() {
        return PM25;
    }

    public void setPM25(String PM25) {
        this.PM25 = PM25;
    }

    public String getPM10() {
        return PM10;
    }

    public void setPM10(String PM10) {
        this.PM10 = PM10;
    }

    public String getNoise() {
        return Noise;
    }

    public void setNoise(String noise) {
        Noise = noise;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        WindSpeed = windSpeed;
    }

    public String getWindDirection() {
        return WindDirection;
    }

    public void setWindDirection(String windDirection) {
        WindDirection = windDirection;
    }

    public String getWindPower() {
        return WindPower;
    }

    public void setWindPower(String windPower) {
        WindPower = windPower;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
