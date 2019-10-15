package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName ComeOnAndOutVO
 * @Author jcyao
 * @Description
 * @Date 2018/12/17 15:05
 * @Version 1.0
 */
@ApiModel(value="出入场总人数")
public class ComeOnAndOutVO {

    @ApiModelProperty("入场总人数")
    private int onCount;
    @ApiModelProperty("出场总人数")
    private int outCount;
    @ApiModelProperty("出场总人数")
    private String imageUrl;

    public int getOnCount() {
        return onCount;
    }

    public void setOnCount(int onCount) {
        this.onCount = onCount;
    }

    public int getOutCount() {
        return outCount;
    }

    public void setOutCount(int outCount) {
        this.outCount = outCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
