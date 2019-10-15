package com.secusoft.ssw.model.monitor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 监测点类型及对应类型编号
 * </p>
 *
 * @author wdz
 * @since 2019-04-23
 */
public class Settleunitmon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * 中文名称
     */
	private String name;
    /**
     * 编号
     */
	private String code;
    /**
     * 单位
     */
	private String unit;
    /**
     * 添加时间
     */
	private Date inserttime;
    /**
     * 更新时间
     */
	private Date updatetime;
    /**
     * 工地id
     */
	private Integer outletid;

	/**
	 * 1沉降、2斜侧、3位移,4轴力,5水位
	 */
	private Integer type;


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public Settleunitmon setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Settleunitmon setName(String name) {
		this.name = name;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Settleunitmon setCode(String code) {
		this.code = code;
		return this;
	}

	public String getUnit() {
		return unit;
	}

	public Settleunitmon setUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public Settleunitmon setInserttime(Date inserttime) {
		this.inserttime = inserttime;
		return this;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public Settleunitmon setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
		return this;
	}

	public Integer getOutletid() {
		return outletid;
	}

	public Settleunitmon setOutletid(Integer outletid) {
		this.outletid = outletid;
		return this;
	}

}
