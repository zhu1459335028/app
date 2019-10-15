package com.secusoft.ssw.model.monitor;

import java.io.Serializable;

/**
 * <p>
 * 基坑与监测点关联表
 * </p>
 *
 * @author wdz
 * @since 2019-04-15
 */
public class Foundwatermonitor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * 基坑信息表id
     */
	private Integer foundid;
    /**
     * 监测点表id
     */
	private Integer wmid;


	public Integer getId() {
		return id;
	}

	public Foundwatermonitor setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getFoundid() {
		return foundid;
	}

	public Foundwatermonitor setFoundid(Integer foundid) {
		this.foundid = foundid;
		return this;
	}

	public Integer getWmid() {
		return wmid;
	}

	public Foundwatermonitor setWmid(Integer wmid) {
		this.wmid = wmid;
		return this;
	}

}
