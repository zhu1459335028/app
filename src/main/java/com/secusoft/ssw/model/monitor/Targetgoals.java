package com.secusoft.ssw.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 阶段目标表
 * </p>
 *
 * @author wdz
 * @since 2019-04-11
 */
public class Targetgoals implements Serializable {

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
     * 目标名称
     */
	private String targetname;
    /**
     * 目标环数
     */
	private Float targetring;
    /**
     * 目标里程
     */
	private Float targetmileage;
    /**
     * 起始日期
     */
	private Date startdate;
    /**
     * 结束日期
     */
	private Date enddate;
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

	public Targetgoals setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getShieldid() {
		return shieldid;
	}

	public Targetgoals setShieldid(Integer shieldid) {
		this.shieldid = shieldid;
		return this;
	}

	public String getTargetname() {
		return targetname;
	}

	public Targetgoals setTargetname(String targetname) {
		this.targetname = targetname;
		return this;
	}

	public Float getTargetring() {
		return targetring;
	}

	public Targetgoals setTargetring(Float targetring) {
		this.targetring = targetring;
		return this;
	}

	public Float getTargetmileage() {
		return targetmileage;
	}

	public Targetgoals setTargetmileage(Float targetmileage) {
		this.targetmileage = targetmileage;
		return this;
	}

	public Date getStartdate() {
		return startdate;
	}

	public Targetgoals setStartdate(Date startdate) {
		this.startdate = startdate;
		return this;
	}

	public Date getEnddate() {
		return enddate;
	}

	public Targetgoals setEnddate(Date enddate) {
		this.enddate = enddate;
		return this;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public Targetgoals setInserttime(Date inserttime) {
		this.inserttime = inserttime;
		return this;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public Targetgoals setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
		return this;
	}

}
