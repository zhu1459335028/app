package com.secusoft.ssw.model.monitor;

import io.swagger.models.auth.In;

/**
 * 门店信息表
 * @author yaojiacheng
 * 2018年6月6日
 */
public class Outlet {
	private int outletid;
	private String outletname;
	private String description;
	private String outletlogo;
	private String outletaddress;
	private String outletcode;
	private String areamanager;
	private String storemanager;
	private String tradingarea;
	private int topbrandid;
	private Integer userid;
	private Integer lastoutletid;

	public Integer getLastoutletid() {
		return lastoutletid;
	}

	public void setLastoutletid(Integer lastoutletid) {
		this.lastoutletid = lastoutletid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public int getTopbrandid() {
		return topbrandid;
	}
	public void setTopbrandid(int topbrandid) {
		this.topbrandid = topbrandid;
	}
	public int getOutletid() {
		return outletid;
	}
	public void setOutletid(int outletid) {
		this.outletid = outletid;
	}
	public String getOutletname() {
		return outletname;
	}
	public void setOutletname(String outletname) {
		this.outletname = outletname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOutletlogo() {
		return outletlogo;
	}
	public void setOutletlogo(String outletlogo) {
		this.outletlogo = outletlogo;
	}
	public String getOutletaddress() {
		return outletaddress;
	}
	public void setOutletaddress(String outletaddress) {
		this.outletaddress = outletaddress;
	}
	public String getOutletcode() {
		return outletcode;
	}
	public void setOutletcode(String outletcode) {
		this.outletcode = outletcode;
	}
	public String getAreamanager() {
		return areamanager;
	}
	public void setAreamanager(String areamanager) {
		this.areamanager = areamanager;
	}
	public String getStoremanager() {
		return storemanager;
	}
	public void setStoremanager(String storemanager) {
		this.storemanager = storemanager;
	}
	public String getTradingarea() {
		return tradingarea;
	}
	public void setTradingarea(String tradingarea) {
		this.tradingarea = tradingarea;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Outlet)) {
			return false;
		}
		Outlet outlet = (Outlet) o;
		return outlet.outletname.equals(outletname) &&
				outlet.outletid == outletid ;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + outletname.hashCode();
		result = 31 * result + outletid;
		return result;
	}

}

