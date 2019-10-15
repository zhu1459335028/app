package com.secusoft.ssw.model.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName NewEMap
 * @Author jcyao
 * @Description 工地地图
 * @Date 2018/9/3 16:13
 * @Version 1.0
 */
@ApiModel(value="工地地图信息集")
public class NewEMap {
    @ApiModelProperty("地图编号")
    private int mapid;
    @ApiModelProperty("所属工地编号")
    private int outletid;
    @ApiModelProperty("地图URL")
    private String mapurl;
    @ApiModelProperty("地图高度")
    private int mapheight;
    @ApiModelProperty("地图宽带")
    private int mapwidth;
    @ApiModelProperty("上级地图编号")
    private int parentmapid;
    @ApiModelProperty("在上级地图中的坐标点:'h,w'")
    private String coordinate;

    public int getMapid() {
        return mapid;
    }

    public void setMapid(int mapid) {
        this.mapid = mapid;
    }

    public int getOutletid() {
        return outletid;
    }

    public void setOutletid(int outletid) {
        this.outletid = outletid;
    }

    public String getMapurl() {
        return mapurl;
    }

    public void setMapurl(String mapurl) {
        this.mapurl = mapurl;
    }

    public int getMapheight() {
        return mapheight;
    }

    public void setMapheight(int mapheight) {
        this.mapheight = mapheight;
    }

    public int getMapwidth() {
        return mapwidth;
    }

    public void setMapwidth(int mapwidth) {
        this.mapwidth = mapwidth;
    }

    public int getParentmapid() {
        return parentmapid;
    }

    public void setParentmapid(int parentmapid) {
        this.parentmapid = parentmapid;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}
