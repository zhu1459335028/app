package com.secusoft.ssw.model.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName AccessDeviceEmapMapper
 * @Author jcyao
 * @Description 人脸设备(门禁)和地图关联表
 * @Date 2018/9/13 10:20
 * @Version 1.0
 */
@ApiModel(value="地图中的门禁信息集")
public class AccessDeviceEmap {
    @ApiModelProperty("门禁设备编号")
    private String accessdeviceid;
    @ApiModelProperty("地图编号")
    private int emapid;
    @ApiModelProperty("在地图中的坐标点:'h,w'")
    private String coordinate;
    @ApiModelProperty("门禁进出方向：1或3.进，2或4.出")
    private String direction;
    @ApiModelProperty("备注")
    private String remarks;

    public String getAccessdeviceid() {
        return accessdeviceid;
    }

    public void setAccessdeviceid(String accessdeviceid) {
        this.accessdeviceid = accessdeviceid;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
