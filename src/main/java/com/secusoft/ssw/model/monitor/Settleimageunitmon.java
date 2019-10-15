package com.secusoft.ssw.model.monitor;

import java.io.Serializable;

/**
 * <p>
 * 测绘图纸与监测点关联表
 * </p>
 *
 * @author wdz
 * @since 2019-04-25
 */
public class Settleimageunitmon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * 点位x轴
     */
	private Float x;
    /**
     * 点位y轴
     */
	private Float y;
    /**
     * 测量图片id
     */
	private Integer settleimageid;
    /**
     * 监测点类型id
     */
	private Integer settleunitmonid;
    /**
     * 测点编号
     */
	private String locationno;
    /**
     * 工地id
     */
	private Integer outletid;

	/**
	 * 测点父编号
	 */
	private String parentlocationno;

	public String getParentlocationno() {
		return parentlocationno;
	}

	public void setParentlocationno(String parentlocationno) {
		this.parentlocationno = parentlocationno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getX() {
		return x;
	}

	public void setX(Float x) {
		this.x = x;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float y) {
		this.y = y;
	}

	public Integer getSettleimageid() {
		return settleimageid;
	}

	public void setSettleimageid(Integer settleimageid) {
		this.settleimageid = settleimageid;
	}

	public Integer getSettleunitmonid() {
		return settleunitmonid;
	}

	public void setSettleunitmonid(Integer settleunitmonid) {
		this.settleunitmonid = settleunitmonid;
	}

	public String getLocationno() {
		return locationno;
	}

	public void setLocationno(String locationno) {
		this.locationno = locationno;
	}

	public Integer getOutletid() {
		return outletid;
	}

	public void setOutletid(Integer outletid) {
		this.outletid = outletid;
	}


}
