package com.secusoft.ssw.model.viewobject.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("相机轮播设置数据集")
public class DeviceVO {
    @ApiModelProperty("是否轮播：1.是、0.否")
    private int playback;
    @ApiModelProperty("轮播停留时间(秒)")
    private int second;
    @ApiModelProperty("周界轮播：1.是、0.否")
    private int enableSlicing;
    @ApiModelProperty("切图时长（分）")
    private int slicingTime;

    public int getPlayback() {
        return playback;
    }

    public void setPlayback(int playback) {
        this.playback = playback;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getEnableSlicing() {
        return enableSlicing;
    }

    public void setEnableSlicing(int enableSlicing) {
        this.enableSlicing = enableSlicing;
    }

    public int getSlicingTime() {
        return slicingTime;
    }

    public void setSlicingTime(int slicingTime) {
        this.slicingTime = slicingTime;
    }
}
