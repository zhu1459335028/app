package com.secusoft.ssw.model.monitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName Algorithum
 * @Description
 * @Author jmGui
 * @Date 2018/10/19 10:27
 **/
@ApiModel(value = "算法信息")
public class Algorithum {
    @ApiModelProperty(value = "算法Id")
    private int serviceId;
    @ApiModelProperty(value = "算法名称")
    private String cnName;
    @ApiModelProperty(value = "英文名称")
    private String enName;
    @ApiModelProperty(value = "启用或禁用")
    private int enable=1;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
