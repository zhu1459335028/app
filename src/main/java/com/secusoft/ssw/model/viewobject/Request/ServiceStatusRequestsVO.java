package com.secusoft.ssw.model.viewobject.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ServiceStatusRequestsVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/4 9:33
 * @Version 1.0
 */
@ApiModel(value="多个ICS Server服务状态参数集")
public class ServiceStatusRequestsVO implements Serializable{
	
	private static final long serialVersionUID = 2L;

	@ApiModelProperty("服务状态信息集")
	private List<ServiceStatusRequestVO> icsserver;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<ServiceStatusRequestVO> getIcsserver() {
		return icsserver;
	}

	public void setIcsserver(List<ServiceStatusRequestVO> icsserver) {
		this.icsserver = icsserver;
	}

	@Override
	public String toString() {
		return "ServiceStatusRequestsVO{" +
				"icsserver=" + icsserver +
				'}';
	}
}
