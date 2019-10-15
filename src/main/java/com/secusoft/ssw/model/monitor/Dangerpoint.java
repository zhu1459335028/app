package com.secusoft.ssw.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wdz
 * @since 2019-04-11
 */
public class Dangerpoint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * 盾构id
     */
	private Integer shieldid;
    /**
     * 点位名称
     */
	private String pointname;
    /**
     * 点位类型
     */
	private String pointtype;
    /**
     * 起始间距
     */
	private Float startspacing;
    /**
     * 深度
     */
	private String depth;
    /**
     * 记录插入时间
     */
	private Date inserttime;
    /**
     * 记录更新时间
     */
	private Date updatetime;

	/**
	 * 工地id
	 */
	private Integer outletid;

	public Integer getOutletid() {
		return outletid;
	}

	public void setOutletid(Integer outletid) {
		this.outletid = outletid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShieldid() {
		return shieldid;
	}

	public void setShieldid(Integer shieldid) {
		this.shieldid = shieldid;
	}

	public String getPointname() {
		return pointname;
	}

	public void setPointname(String pointname) {
		this.pointname = pointname;
	}

	public String getPointtype() {
		return pointtype;
	}

	public void setPointtype(String pointtype) {
		this.pointtype = pointtype;
	}

	public Float getStartspacing() {
		return startspacing;
	}

	public void setStartspacing(Float startspacing) {
		this.startspacing = startspacing;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
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
