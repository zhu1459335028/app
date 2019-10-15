package com.secusoft.ssw.model.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName CameraEmap
 * @Author jcyao
 * @Description 相机和地图关联表
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
@ApiModel(value="地图中的相机信息集")
public class CameraEmap {
    @ApiModelProperty("相机编号")
    private int cameraid;
    @ApiModelProperty("地图编号")
    private int emapid;
    @ApiModelProperty("在地图中的坐标点:'h,w'")
    private String coordinate;
    @ApiModelProperty("相机在地图上的方向")
    private String direction;
    @ApiModelProperty("相机在地图上的视野角度")
    private String scopeangle;
    @ApiModelProperty("相机在地图上的视野距离")
    private String scopedistance;

    public int getCameraid() {
        return cameraid;
    }

    public void setCameraid(int cameraid) {
        this.cameraid = cameraid;
    }

    public int getEmapid() {
        return emapid;
    }

    public void setEmapid(int emapid) {
        this.emapid = emapid;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getScopeangle() {
        return scopeangle;
    }

    public void setScopeangle(String scopeangle) {
        this.scopeangle = scopeangle;
    }

    public String getScopedistance() {
        return scopedistance;
    }

    public void setScopedistance(String scopedistance) {
        this.scopedistance = scopedistance;
    }
}
