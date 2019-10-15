package com.secusoft.ssw.model.monitor;

import java.io.Serializable;

/**
 * <p>
 * 阈值条目与监测点类型关联表
 * </p>
 *
 * @author wdz
 * @since 2019-04-24
 */
public class Thresholdvalue implements Serializable {

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


	public Integer getId() {
		return id;
	}

	public Thresholdvalue setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getSettleunitmonid() {
		return settleunitmonid;
	}

	public Thresholdvalue setSettleunitmonid(Integer settleunitmonid) {
		this.settleunitmonid = settleunitmonid;
		return this;
	}

	public Integer getThrnameid() {
		return thrnameid;
	}

	public Thresholdvalue setThrnameid(Integer thrnameid) {
		this.thrnameid = thrnameid;
		return this;
	}

	public String getMax_val() {
		return max_val;
	}

	public Thresholdvalue setMax_val(String max_val) {
		this.max_val = max_val;
		return this;
	}

	public String getMin_val() {
		return min_val;
	}

	public Thresholdvalue setMin_val(String min_val) {
		this.min_val = min_val;
		return this;
	}

	public String getIdentical_val() {
		return identical_val;
	}

	public Thresholdvalue setIdentical_val(String identical_val) {
		this.identical_val = identical_val;
		return this;
	}

	public Integer getOutletid() {
		return outletid;
	}

	public Thresholdvalue setOutletid(Integer outletid) {
		this.outletid = outletid;
		return this;
	}

}
