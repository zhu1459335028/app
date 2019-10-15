package com.secusoft.ssw.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 进度统计(推进统计)
 * </p>
 *
 * @author wdz
 * @since 2019-04-09
 */
public class Progressstatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;

	/**
     * 当前环数
     */
	private float currentnumber;

//	/**
//     * 当前里程
//     */
//	private Integer currentmileage;

	/**
	 * 当前里程数据
	 */
	private float currentmileage;
    /**
     * 每天环数
     */
	private float everynumber;

	/**
	 * outletid id
	 */
	private Integer outletid;
	/**
	 * 盾构id
	 */
	private Integer shieldid;

	/**
	 *
	 */
	private float historynumber;

    /**
     * 每天
     */
	private Date date;
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

	public float getCurrentnumber() {
		return currentnumber;
	}

	public void setCurrentnumber(float currentnumber) {
		this.currentnumber = currentnumber;
	}

	public float getCurrentmileage() {
		return currentmileage;
	}

	public void setCurrentmileage(float currentmileage) {
		this.currentmileage = currentmileage;
	}

	public float getEverynumber() {
		return everynumber;
	}

	public void setEverynumber(float everynumber) {
		this.everynumber = everynumber;
	}

	public Integer getOutletid() {
		return outletid;
	}

	public void setOutletid(Integer outletid) {
		this.outletid = outletid;
	}

	public Integer getShieldid() {
		return shieldid;
	}

	public void setShieldid(Integer shieldid) {
		this.shieldid = shieldid;
	}

	public float getHistorynumber() {
		return historynumber;
	}

	public void setHistorynumber(float historynumber) {
		this.historynumber = historynumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
