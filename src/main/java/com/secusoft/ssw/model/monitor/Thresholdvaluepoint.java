package com.secusoft.ssw.model.monitor;

import java.io.Serializable;

/**
 * <p>
 * 单个点位----阈值条关联表通过监测点id与阈值条目id确定一条阈值
 * </p>
 *
 * @author wdz
 * @since 2019-05-09
 */
public class Thresholdvaluepoint implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 监测点类型id
     */
	private Integer settleunitmonid;
    /**
     * 阈值条目id
     */
	private Integer thrnameid;
    /**
     * 阈值上限值
     */
	private String max_val;
    /**
     * 下限值
     */
	private String min_val;
    /**
     * 恒等值
     */
	private String identical_val;
    /**
     * 工地id
     */
	private Integer outletid;
    /**
     * 点位编号
     */
	private String location_no;


	public Integer getId() {
		return id;
	}

	public Thresholdvaluepoint setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getSettleunitmonid() {
		return settleunitmonid;
	}

	public Thresholdvaluepoint setSettleunitmonid(Integer settleunitmonid) {
		this.settleunitmonid = settleunitmonid;
		return this;
	}

	public Integer getThrnameid() {
		return thrnameid;
	}

	public Thresholdvaluepoint setThrnameid(Integer thrnameid) {
		this.thrnameid = thrnameid;
		return this;
	}

	public String getMax_val() {
		return max_val;
	}

	public Thresholdvaluepoint setMax_val(String max_val) {
		this.max_val = max_val;
		return this;
	}

	public String getMin_val() {
		return min_val;
	}

	public Thresholdvaluepoint setMin_val(String min_val) {
		this.min_val = min_val;
		return this;
	}

	public String getIdentical_val() {
		return identical_val;
	}

	public Thresholdvaluepoint setIdentical_val(String identical_val) {
		this.identical_val = identical_val;
		return this;
	}

	public Integer getOutletid() {
		return outletid;
	}

	public Thresholdvaluepoint setOutletid(Integer outletid) {
		this.outletid = outletid;
		return this;
	}

	public String getLocation_no() {
		return location_no;
	}

	public Thresholdvaluepoint setLocation_no(String location_no) {
		this.location_no = location_no;
		return this;
	}

}
