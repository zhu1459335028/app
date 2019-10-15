package com.secusoft.ssw.model.monitor;

import java.io.Serializable;

/**
 * <p>
 * 基坑信息表id与accessdevice表id关联表
 * </p>
 *
 * @author wdz
 * @since 2019-04-15
 */
public class Foundaccessdevice implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 基坑信息表id
     */
	private Integer foundid;
    /**
     * accessdevice表id
     */
	private Integer accessdeviceid;

	/**
	 *
	 */
	private Integer type;


	public Integer getId() {
		return id;
	}

	public Foundaccessdevice setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getFoundid() {
		return foundid;
	}

	public Foundaccessdevice setFoundid(Integer foundid) {
		this.foundid = foundid;
		return this;
	}

	public Integer getAccessdeviceid() {
		return accessdeviceid;
	}

	public Foundaccessdevice setAccessdeviceid(Integer accessdeviceid) {
		this.accessdeviceid = accessdeviceid;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
