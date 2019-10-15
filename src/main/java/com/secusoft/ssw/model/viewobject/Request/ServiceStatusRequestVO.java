package com.secusoft.ssw.model.viewobject.Request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName ServiceStatusRequestVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/4 9:33
 * @Version 1.0
 */
@ApiModel(value="ICS Server服务状态参数集")
public class ServiceStatusRequestVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("数据源Id")
	private String monitorid;

	@ApiModelProperty("门店Id")
	private String outletid;

	@ApiModelProperty("服务状态：on.在线、off.离线")
	private String state;

	public String getMonitorid() {
		return monitorid;
	}

	public void setMonitorid(String monitorid) {
		this.monitorid = monitorid;
	}

	public String getOutletid() {
		return outletid;
	}

	public void setOutletid(String outletid) {
		this.outletid = outletid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ServiceStatusRequestVO{" +
				"monitorid='" + monitorid + '\'' +
				", outletid='" + outletid + '\'' +
				", state='" + state + '\'' +
				'}';
	}
}
