package com.secusoft.ssw.model.monitor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 基坑信息表
 * </p>
 *
 * @author wdz
 * @since 2019-04-15
 */
public class Foundation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 基坑名称
     */
	private Integer id;
	private String name;
    /**
     * 基坑经度
     */
	private String longitude;
    /**
     * 基坑纬度
     */
	private String latitude;
    /**
     * 创建时间
     */
	private Date inserttime;
    /**
     * 最近修改时间
     */
	private Date updatetime;
    /**
     * 工地id
     */
	private Integer outletid;


	/**
	 * 基坑深度
	 */
	private Float depth;


	public Float getDepth() {
		return depth;
	}

	public void setDepth(Float depth) {
		this.depth = depth;
	}

	public Integer getId() {
		return id;
	}

	public Foundation setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Foundation setName(String name) {
		this.name = name;
		return this;
	}

	public String getLongitude() {
		return longitude;
	}

	public Foundation setLongitude(String longitude) {
		this.longitude = longitude;
		return this;
	}

	public String getLatitude() {
		return latitude;
	}

	public Foundation setLatitude(String latitude) {
		this.latitude = latitude;
		return this;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public Foundation setInserttime(Date inserttime) {
		this.inserttime = inserttime;
		return this;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public Foundation setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
		return this;
	}

	public Integer getOutletid() {
		return outletid;
	}

	public Foundation setOutletid(Integer outletid) {
		this.outletid = outletid;
		return this;
	}


}
