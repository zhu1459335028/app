package com.secusoft.ssw.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 盾构表
 * </p>
 *
 * @author wdz
 * @since 2019-04-11
 */
public class Shield implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 盾构id
     */
	private Integer id;
    /**
     * 机器名称
     */
	private String shieldname;
    /**
     * 盾构编号惟一标识
     */
	private String shieldno;
    /**
     * 生产厂商
     */
	private String manufacturers;
    /**
     * 机器负责人
     */
	private String headperson;
    /**
     * 联系方式
     */
	private String contact;
    /**
     * 机器类型
     */
	private String machinetype;
    /**
     * plc型号
     */
	private String plcmodel;
    /**
     * plc地址
     */
	private String plcaddr;
    /**
     * plc端口
     */
	private String plcport;
    /**
     * 总的环数
     */
	private Float totalring;
    /**
     * 总的里程
     */
	private Float totalmileage;
    /**
     * 环宽(每环里程数)
     */
	private Float eachringmileage;

	/**
	 * 坑深标准
	 */
	private Float depth;
    /**
     * 视频设备编号
     */
	private String camerano;
    /**
     * 推进方向 0从左向右，1从右向左
     */
	private Integer advancefx;


	/**
	 * 起始点位
	 */
	private Float startpoint;

	/**
	 * 结束点位
	 */
	private Float endpoint;

	/**
	 * 图纸总长
	 */
	private Float drawlength;
    /**
     * 工地id
     */
	private Integer outletid;
    /**
     * 插入时间
     */
	private Date inserttime;
    /**
     * 更新时间
     */
	private Date updatetime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShieldname() {
		return shieldname;
	}

	public void setShieldname(String shieldname) {
		this.shieldname = shieldname;
	}

	public String getShieldno() {
		return shieldno;
	}

	public void setShieldno(String shieldno) {
		this.shieldno = shieldno;
	}

	public String getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(String manufacturers) {
		this.manufacturers = manufacturers;
	}

	public String getHeadperson() {
		return headperson;
	}

	public void setHeadperson(String headperson) {
		this.headperson = headperson;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMachinetype() {
		return machinetype;
	}

	public void setMachinetype(String machinetype) {
		this.machinetype = machinetype;
	}

	public String getPlcmodel() {
		return plcmodel;
	}

	public void setPlcmodel(String plcmodel) {
		this.plcmodel = plcmodel;
	}

	public String getPlcaddr() {
		return plcaddr;
	}

	public void setPlcaddr(String plcaddr) {
		this.plcaddr = plcaddr;
	}

	public String getPlcport() {
		return plcport;
	}

	public void setPlcport(String plcport) {
		this.plcport = plcport;
	}

	public Float getTotalring() {
		return totalring;
	}

	public void setTotalring(Float totalring) {
		this.totalring = totalring;
	}

	public Float getTotalmileage() {
		return totalmileage;
	}

	public void setTotalmileage(Float totalmileage) {
		this.totalmileage = totalmileage;
	}

	public Float getEachringmileage() {
		return eachringmileage;
	}

	public void setEachringmileage(Float eachringmileage) {
		this.eachringmileage = eachringmileage;
	}

	public Float getDepth() {
		return depth;
	}

	public void setDepth(Float depth) {
		this.depth = depth;
	}

	public String getCamerano() {
		return camerano;
	}

	public void setCamerano(String camerano) {
		this.camerano = camerano;
	}

	public Integer getAdvancefx() {
		return advancefx;
	}

	public void setAdvancefx(Integer advancefx) {
		this.advancefx = advancefx;
	}

	public Float getStartpoint() {
		return startpoint;
	}

	public void setStartpoint(Float startpoint) {
		this.startpoint = startpoint;
	}

	public Float getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(Float endpoint) {
		this.endpoint = endpoint;
	}

	public Float getDrawlength() {
		return drawlength;
	}

	public void setDrawlength(Float drawlength) {
		this.drawlength = drawlength;
	}

	public Integer getOutletid() {
		return outletid;
	}

	public void setOutletid(Integer outletid) {
		this.outletid = outletid;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
